package org.example;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;

import io.restassured.specification.RequestSpecification;

import io.restassured.http.ContentType;

import java.util.UUID;

import static org.hamcrest.Matchers.hasItem;


public class GoodsApiTest {

    @Test
    void getGoodsListGivenWhenThenTest() {

        given()
                .baseUri("http://localhost:8080")
                .queryParam("page", 0)
                .queryParam("size", 10)
                .when()
                .get("/goods/list")
                .then()
                .statusCode(200)
                .body("goods", empty());
    }

    @Test
    void getGoodsListRequestSpecificationTest() {

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

    @Test
    void addGoodAndCheckInListTest() {

        String productName = "Test Product-" + UUID.randomUUID();
        double productPrice = 100.9;

        String requestBody = "{"
        + "\"name\":\"" + productName + "\","
        + "\"price\":" + productPrice
                + "}";

        RequestSpecification requestSpecPost = given()
                .baseUri("http://localhost:8080")
                .auth().preemptive().basic("admin", "secret123")
                .contentType(ContentType.JSON)
                .body(requestBody);

        requestSpecPost
                .when()
                .post("/goods/add")
                .then()
                .statusCode(200);


        RequestSpecification requestSpecGet = given()
                .baseUri("http://localhost:8080")
                .queryParam("page", 0)
                .queryParam("size", 10);

        requestSpecGet
                .when()
                .get("/goods/list")
                .then()
                .statusCode(200)
                .body("goods.name", hasItem(productName));
    }
}
