package org.example;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;

import io.restassured.specification.RequestSpecification;

public class GoodsApiTest {

    @Test
    void getGoodsListGivenWhenThenTest() {

        RequestSpecification requestSpec = given()
                .baseUri("http://localhost:8080")
                .queryParam("page", 0)
                .queryParam("size", 10);

        requestSpec
                .when()
                .get("/goods/list")
                .then()
                .statusCode(200)
                .body("goods", empty());
    }
}
