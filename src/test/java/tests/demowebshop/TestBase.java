package tests.demowebshop;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import steps.demowebshop.CartSteps;

public class TestBase {

    protected String login = "cucumber@mail.ru",
            password = "cucumber";

    protected String authCookieValue;
    protected CartSteps cartSteps = new CartSteps();

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demowebshop.tricentis.com";
        RestAssured.baseURI = "https://demowebshop.tricentis.com";
    }

    @BeforeEach
    void auth() {
        // Получаем auth cookie один раз перед каждым тестом
        authCookieValue = RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("Email", login)
                .formParam("Password", password)
                .when()
                .post("/login")
                .then()
                .statusCode(302)
                .extract()
                .cookie("NOPCOMMERCE.AUTH");
    }

    @AfterEach
    void clearCart() {
        // Очищаем корзину после каждого теста
        cartSteps.clearCart(authCookieValue);
    }
}