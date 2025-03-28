package tests;
import org.junit.jupiter.api.Test;
import utils.FileUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.notNullValue;

public class REST_API {

    @Test
    void successfulCreateTest() {
        String createData = "{\"name\": \"morpheus\", \"job\": \"leader\"}";

        given()
                .body(createData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("https://reqres.in/api/users")

                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"))
                .body("id",  notNullValue())
                .body("createdAt",  notNullValue());
    }

    @Test
    void successfulRegisterTest() {
        String registerData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\"}";

        given()
                .body(registerData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("https://reqres.in/api/register")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is(4))
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void unsuccessfulRegisterTest() {
        String registerData = "{\"email\": \"sydney@fife\"}";

        given()
                .body(registerData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("https://reqres.in/api/register")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void delayedResponseTest() {


        given()

                .when()
                .get("https://reqres.in/api/users?delay=3")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total_pages", is(2));
    }

    @Test
    void listUserTest() {


        given()

                .when()
                .get("https://reqres.in/api/users?page=2")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total_pages", is(2))
                .body("per_page", is(6))
                .body("total", is(12));
    }



}