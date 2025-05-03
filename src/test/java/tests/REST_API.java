package tests;



import io.qameta.allure.restassured.AllureRestAssured;
import models.lombok.RegisterBodyLombokModel;

import models.lombok.RegisterResponseLombokModel;
import models.pojo.LoginBodyModel;
import models.pojo.LoginResponseModel;
import org.junit.jupiter.api.Test;
import utils.FileUtils;


import static helpers.CustomAllureListener.withCustomTemplates;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.LoginSpec.RegisterRequestSpec;
import static specs.LoginSpec.RegisterResponceSpec;

public class REST_API {

    @Test
    void successfulCreateBadPracticeTest() {
        String createData = "{\"name\": \"morpheus\", \"job\": \"leader\"}";

        given()
                .body(createData)
                .contentType(JSON)
                .log().uri()
                .log().body()
                .log().headers()

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
    void successfulRegisterPojoTest() {
        //String registerData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\"}";

        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("pistol");

        LoginResponseModel response = given()
                .body(authData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("https://reqres.in/api/register")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseModel.class);

        assertEquals("QpwL5tke4Pnpja7X4", response.getToken());

    }



    @Test
    void unsuccessfulRegisterLombokTest() {
        //String registerData = "{\"email\": \"sydney@fife\"}";

        RegisterBodyLombokModel registerData = new RegisterBodyLombokModel();
        registerData.setEmail("sydney@fife");

        RegisterResponseLombokModel response = given()
                .body(registerData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("https://reqres.in/api/register")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                //.body("error", is("Missing password"));
                .extract().as(RegisterResponseLombokModel.class);
        assertEquals("Missing password", response.getError());
    }

    @Test
    void unsuccessfulRegisterWithStepsTest() {

        RegisterResponseLombokModel response = step("make reguest", () -> {
            RegisterBodyLombokModel registerData = new RegisterBodyLombokModel();
            registerData.setEmail("sydney@fife");


           return given()
                    .body(registerData)
                    .contentType(JSON)
                    .log().uri()

                    .when()
                    .post("https://reqres.in/api/register")

                    .then()
                    .log().status()
                    .log().body()
                    .statusCode(400)
                    //.body("error", is("Missing password"));
                    .extract().as(RegisterResponseLombokModel.class);
        });

        step("check response", () -> {
            assertEquals("Missing password", response.getError());
        });
    }


    @Test
    void delayedResponseAllureTest() {

        given()
                .filter(new AllureRestAssured())
                .when()
                .get("https://reqres.in/api/users?delay=3")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total_pages", is(2));
    }

    @Test
    void listUserCustomAllureTest() {

        given()

                .filter(withCustomTemplates())
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

    @Test
    void successfulRegisterSpecsTest() {
        //String registerData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\"}";

        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("pistol");

        LoginResponseModel response = given()
                .spec(RegisterRequestSpec)
                .body(authData)

                .when()
                .post("https://reqres.in/api/register")

                .then()
                .spec(RegisterResponceSpec)
                .statusCode(200)
                .extract().as(LoginResponseModel.class);

        assertEquals("QpwL5tke4Pnpja7X4", response.getToken());

    }

}