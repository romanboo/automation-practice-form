package tests.demowebshop;

import io.restassured.response.Response;
import models.demowebshop.CartResponse;
import org.junit.jupiter.api.Test;
import steps.demowebshop.CartSteps;

import static io.restassured.RestAssured.given;

import static specs.demowebshop.AuthSpec.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class CartTestsDemoWebshop extends TestBase {

    CartSteps cartSteps = new CartSteps();

    @Test
    void addToCartTest() {
        String authCookieValue = given()
                .spec(loginRequestSpec)
                .formParam("Email", login)
                .formParam("Password", password)
                .when()
                .post("/login")
                .then()
                .spec(loginResponseSpec)
                .extract()
                .cookie("NOPCOMMERCE.AUTH");

        CartResponse response = cartSteps.addProductToCart(authCookieValue, 13)
                .as(CartResponse.class);

        assertThat(response.isSuccess(), is(true));
        assertThat(response.getUpdateTopCartSectionHtml(), is("(1)"));
    }

    @Test
    void deleteFromCartTest() {
        String authCookieValue = given()
                .spec(loginRequestSpec)
                .formParam("Email", login)
                .formParam("Password", password)
                .when()
                .post("/login")
                .then()
                .spec(loginResponseSpec)
                .extract()
                .cookie("NOPCOMMERCE.AUTH");

        Response cartResponse = cartSteps.getCart(authCookieValue);
        String productId = extractInputValue(cartResponse.getBody().asString(), "removefromcart");

        Response deleteResponse = cartSteps.removeProductFromCart(authCookieValue, productId);
        assertThat(deleteResponse.getBody().asString(), containsString("Your Shopping Cart is empty!"));
    }

    private String extractInputValue(String html, String inputName) {
        String regex = "<input[^>]*name=\"" + inputName + "\"[^>]*value=\"([^\"]*)\"[^>]*>";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(html);
        return matcher.find() ? matcher.group(1) : null;
    }
}
