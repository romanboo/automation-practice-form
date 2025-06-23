package steps.demowebshop;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static specs.demowebshop.CartSpec.*;

public class CartSteps {

    public Response addProductToCart(String authCookieValue, int productId) {
        return given()
                .spec(cartRequestSpec)
                .cookie("NOPCOMMERCE.AUTH", authCookieValue)
                .when()
                .post("/addproducttocart/catalog/" + productId + "/1/1")
                .then()
                .spec(addToCartResponseSpec)
                .extract()
                .response();
    }

    public Response getCart(String authCookieValue) {
        return given()
                .cookie("NOPCOMMERCE.AUTH", authCookieValue)
                .when()
                .get("/cart")
                .then()
                .extract()
                .response();
    }

    public Response removeProductFromCart(String authCookieValue, String productId) {
        return given()
                .spec(cartMultipartRequestSpec)
                .multiPart("removefromcart", productId)
                .multiPart("itemquantity" + productId, "1")
                .multiPart("updatecart", "Update shopping cart")
                .cookie("NOPCOMMERCE.AUTH", authCookieValue)
                .when()
                .post("/cart")
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public Response clearCart(String authCookieValue) {
        Response cartResponse = getCart(authCookieValue);
        String cartHtml = cartResponse.getBody().asString();

        Pattern pattern = Pattern.compile("name=\"removefromcart\" value=\"(\\d+)\"");
        Matcher matcher = pattern.matcher(cartHtml);

        RequestSpecification spec = given()
                .spec(cartMultipartRequestSpec)
                .multiPart("updatecart", "Update shopping cart")
                .cookie("NOPCOMMERCE.AUTH", authCookieValue);

        while (matcher.find()) {
            final String id = matcher.group(1);
            spec.multiPart("removefromcart", id);
            spec.multiPart("itemquantity" + id, "1");
        }

        return spec
                .when()
                .post("/cart")
                .then()
                .statusCode(200)
                .extract()
                .response();
    }
}
