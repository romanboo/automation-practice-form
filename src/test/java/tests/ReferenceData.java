package tests;


import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;



public class ReferenceData {


    @Test
    void benefitCategoryAllTest() {

        given()
                .when()
                .post("http://10.15.203.59:9840/kpd-registry-service/benefit_category/all")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id",  notNullValue())
                .body("code",  notNullValue())
                .body("fullName",  notNullValue())
                .body("shortName",  notNullValue());
    }

    @Test
    void addressTypeGet5Test() {

        given()
                .when()
                .post("http://10.15.203.59:9840/kpd-registry-service/addr_type/get/5")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is("5"))
                .body("code", is("4"))
                .body("name", is("Почтовый"));


    }

    @Test
    void benefitCategoryGet5Test() {

        given()
                .when()
                .post("http://10.15.203.59:9840/kpd-registry-service/benefit_category/get/5")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is("5"))
                .body("code", is("1013"))
                .body("fullName", is("Инвалид 2 группы"))
                .body("shortName", is("Инвалид 2 группы"));


    }

    @Test
    void cardSpeciesAllTest() {

        given()
                .when()
                .post("http://10.15.203.59:9840/kpd-registry-service/card_species/all")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)

                .body("id", hasItems("1", "2", "3"))
                .body("code", hasItems("01", "02", "03"))
                .body("name", hasItems("Другая", "Транспортная", "Социальная"));


    }

    @Test
    void cardSpeciesGet2Test() {

        given()
                .when()
                .post("http://10.15.203.59:9840/kpd-registry-service/card_species/get/2")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)

                .body("id", is("2"))
                .body("code", is("02"))
                .body("name", is("Социальная"));


    }

    @Test
    void cardTypeAllTest() {

        given()
                .when()
                .post("http://10.15.203.59:9840/kpd-registry-service/card_type/all")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)

                .body("id", hasItems("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"))
                .body("code", hasItems( "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"))
                .body("cardSpecies", hasItems( "01", "02", "03"));



    }

    @Test
    void cardTypeGet5Test() {

        given()
                .when()
                .post("http://10.15.203.59:9840/kpd-registry-service/card_type/get/5")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)

                .body("id", is("5"))
                .body("code", is("5"))
                .body("name", is("Карта москвича для нельготных категорий (ЕРСЛ)"))
                .body("cardSpecies", is("02"));



    }

    @Test
    void contactTypeAllTest() {

        given()
                .when()
                .post("http://10.15.203.59:9840/kpd-registry-service/contact_type/all")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)

                .body("id", hasItems("1", "2", "3", "4", "5", "6", "7", "8"))
                .body("code", hasItems( "7", "13", "1", "12", "11", "10", "9",  "8"))
                .body("name", hasItems( "E-mail", "E-mail организации", "Домашний телефон",
                        "Телефон организации", "Рабочий e-mail", "Дополнительный e-mail",
                        "Дополнительный мобильный телефон", "Мобильный телефон"));


    }

    @Test
    void contactTypeGet5Test() {

        given()
                .when()
                .post("http://10.15.203.59:9840/kpd-registry-service/contact_type/all")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)

                .body("id", hasItems("5"))
                .body("code", hasItems( "13"))
                .body("name", hasItems( "E-mail организации"));


    }

    @Test
    void countryTypeGet5Test() {

        given()
                .when()
                .post("http://10.15.203.59:9840/kpd-registry-service/country/get/5")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)

                .body("id", is("5"))
                .body("code", is( "090"))
                .body("code2", is("SB"))
                .body("code3", is( "SLB"))
                .body("name", is( "Соломоновы Острова"))
                .body("shortName", is( "СОЛОМОНОВЫ ОСТРОВА"));


    }

}
