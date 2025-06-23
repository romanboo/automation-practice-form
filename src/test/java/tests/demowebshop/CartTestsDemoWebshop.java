package tests.demowebshop;

import io.restassured.response.Response;
import models.demowebshop.CartResponse;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.MethodSource;
import steps.demowebshop.CartSteps;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static specs.demowebshop.AuthSpec.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class CartTestsDemoWebshop extends TestBase {

    CartSteps cartSteps = new CartSteps();

    // Метод для предоставления данных для параметризованного теста
    private static Stream<Integer> productIdsProvider() {
        return Stream.of(13, 22); // Пример различных ID товаров
    }

    @ParameterizedTest(name = "Добавление товара с ID {0} в корзину")
    @MethodSource("productIdsProvider")
    void addToCartTest(int productId) {
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

        CartResponse response = cartSteps.addProductToCart(authCookieValue, productId)
                .as(CartResponse.class);

        assertThat(response.isSuccess(), is(true));
        assertThat(response.getUpdateTopCartSectionHtml(), is("(1)"));
    }

    @ParameterizedTest(name = "Добавление и удаление товара с ID {0}")
    @ValueSource(ints = {13, 22}) // Альтернативный способ параметризации
    void addAndDeleteFromCartTest(int productId) {
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

        // Добавляем товар
        CartResponse addResponse = cartSteps.addProductToCart(authCookieValue, productId)
                .as(CartResponse.class);
        assertThat(addResponse.isSuccess(), is(true));

        // Получаем корзину и извлекаем ID товара для удаления
        Response cartResponse = cartSteps.getCart(authCookieValue);
        String productIdToRemove = extractInputValue(cartResponse.getBody().asString(), "removefromcart");

        // Удаляем товар
        Response deleteResponse = cartSteps.removeProductFromCart(authCookieValue, productIdToRemove);
        assertThat(deleteResponse.getBody().asString(), containsString("Your Shopping Cart is empty!"));
    }

    private String extractInputValue(String html, String inputName) {
        String regex = "<input[^>]*name=\"" + inputName + "\"[^>]*value=\"([^\"]*)\"[^>]*>";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(html);
        return matcher.find() ? matcher.group(1) : null;
    }
}
