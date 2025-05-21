package specs.demowebshop;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.ALL;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class CartSpec {
    public static RequestSpecification cartRequestSpec = new RequestSpecBuilder()
            .setContentType("application/x-www-form-urlencoded; charset=UTF-8")
            .log(ALL)
            .build();

    public static ResponseSpecification addToCartResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectBody("success", is(true))
            .expectBody("message", containsString("shopping cart"))
            .expectBody("updatetopcartsectionhtml", is("(1)"))
            .log(ALL)
            .build();

    public static RequestSpecification cartMultipartRequestSpec = new RequestSpecBuilder()
            .setContentType("multipart/form-data")
            .log(ALL)
            .build();
}