package org.example;

import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.example.config.ConfigProvider.CONFIG;

@Tag("api_test")
public class AllureApiTest {

    @Test
    void getGoodsListTest() {

        given()
                .filter(new AllureRestAssured())
                .baseUri(CONFIG.apiUrl())
                .queryParam("page", 0)
                .queryParam("size", 10)
                .when()
                .get("/goods/list")
                .then()
                .statusCode(200);
    }
}