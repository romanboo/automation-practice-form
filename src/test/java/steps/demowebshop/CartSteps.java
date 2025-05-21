package steps.demowebshop;

import io.restassured.response.Response;


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
}