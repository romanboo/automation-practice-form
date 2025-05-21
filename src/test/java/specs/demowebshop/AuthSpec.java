package specs.demowebshop;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.ALL;

public class AuthSpec {
    public static RequestSpecification loginRequestSpec = new RequestSpecBuilder()
            .setContentType("application/x-www-form-urlencoded")
            .log(ALL)
            .build();

    public static ResponseSpecification loginResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(302)
            .log(ALL)
            .build();
}