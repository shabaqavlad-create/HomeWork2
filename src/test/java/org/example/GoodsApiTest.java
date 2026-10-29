package org.example;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;
import java.util.UUID;
import static org.hamcrest.Matchers.hasItem;
import io.restassured.response.Response;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.Tag;


@Tag("api_test")
public class GoodsApiTest {

    private Long createdProductId;

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

        Response createResponse = requestSpecPost
                .when()
                .post("/goods/add");

        createResponse
                .then()
                .statusCode(200);

        createdProductId = createResponse
                .jsonPath()
                .getLong("data.id");

        RequestSpecification requestSpecGet = given()
                .baseUri("http://localhost:8080")
                .queryParam("page", 0)
                .queryParam("size", 100);

        requestSpecGet
                .when()
                .get("/goods/list")
                .then()
                .statusCode(200)
                .body("goods.name", hasItem(productName));
    }

    @Test
    void addGoodAndCheckInListAssertJTest() {
        String productName = "Test Product-" + UUID.randomUUID();
        double productPrice = 200.5;

        String requestBody = "{"
                + "\"name\":\"" + productName + "\","
                + "\"price\":" + productPrice
                + "}";

        RequestSpecification requestSpecPost = given()
                .baseUri("http://localhost:8080")
                .auth().preemptive().basic("admin", "secret123")
                .contentType(ContentType.JSON)
                .body(requestBody);

        Response createResponse = requestSpecPost
                .when()
                .post("/goods/add");

        createResponse
                .then()
                .statusCode(200);

        createdProductId = createResponse
                .jsonPath()
                .getLong("data.id");

        Response response = given()
                .baseUri("http://localhost:8080")
                .queryParam("page", 0)
                .queryParam("size", 100)
                .when()
                .get("/goods/list");

        assertThat(response.getStatusCode())
                .isEqualTo(200);

        List<String> productNames = response.jsonPath().getList("goods.name", String.class);

        assertThat(productNames)
                .contains(productName);
    }

    @AfterEach
    void cleanUp() {
        if (createdProductId != null) {
            given()
                    .baseUri("http://localhost:8080")
                    .auth().preemptive().basic("admin", "secret123")
                    .when()
                    .delete("/goods/" + createdProductId)
                    .then()
                    .statusCode(200);
        }
    }

    @Test
    void getGoodByIdSuccessTest() {

        String productName = "Test Product-" + UUID.randomUUID();
        double productPrice = 123.4;

        String requestBody = "{"
                + "\"name\":\"" + productName + "\","
                + "\"price\":" + productPrice
                + "}";

        Response createResponse = given()
                .baseUri("http://localhost:8080")
                .auth().preemptive().basic("admin", "secret123")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/goods/add");

        createResponse
                .then()
                .statusCode(200);

        createdProductId = createResponse
                .jsonPath()
                .getLong("data.id");

        given()
                .baseUri("http://localhost:8080")
                .auth().preemptive().basic("admin", "secret123")
                .when()
                .get("/goods/" + createdProductId)
                .then()
                .statusCode(200)
                .body("id", equalTo(createdProductId.intValue()))
                .body("name", equalTo(productName));

    }

    @Test
    void getGoodByIdNotFoundTest() {

        long nonExistingId = 0;

        given()
                .baseUri("http://localhost:8080")
                .auth().preemptive().basic("admin", "secret123")
                .when()
                .get("/goods/" + nonExistingId)
                .then()
                .statusCode(404);
    }

    @Test
    void addGoodInvalidPriceTest() {

        String requestBody = """
            {
              "name": "Test Product",
              "price": "Sting_NOT_duble"
            }
            """;

        given()
                .baseUri("http://localhost:8080")
                .auth().preemptive().basic("admin", "secret123")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/goods/add")
                .then()
                .statusCode(400);
    }

    @Test
    void deleteGoodSuccessTest() {

        String productName = "Delete Product-" + UUID.randomUUID();

        String requestBody = """
            {
              "name": "%s",
              "price": 150.0
            }
            """.formatted(productName);

        Response createResponse = given()
                .baseUri("http://localhost:8080")
                .auth().preemptive().basic("admin", "secret123")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/goods/add");

        createResponse
                .then()
                .statusCode(200);

        createdProductId = createResponse
                .jsonPath()
                .getLong("data.id");

        long deletedProductId = createdProductId;

        given()
                .baseUri("http://localhost:8080")
                .auth().preemptive().basic("admin", "secret123")
                .when()
                .delete("/goods/" + deletedProductId)
                .then()
                .statusCode(200);

        createdProductId = null;

        given()
                .baseUri("http://localhost:8080")
                .auth().preemptive().basic("admin", "secret123")
                .when()
                .get("/goods/" + deletedProductId)
                .then()
                .statusCode(404);
    }

    @Test
    void deleteGoodNotFoundTest() {

        long nonExistingId = 0;

        given()
                .baseUri("http://localhost:8080")
                .auth().preemptive().basic("admin", "secret123")
                .when()
                .delete("/goods/" + nonExistingId)
                .then()
                .statusCode(404);
    }

    @Test
    void patchGoodSuccessTest() {

        String productName = "Patch Product-" + UUID.randomUUID();

        String createBody = """
            {
              "name": "%s",
              "price": 100.0
            }
            """.formatted(productName);

        Response createResponse = given()
                .baseUri("http://localhost:8080")
                .auth().preemptive().basic("admin", "secret123")
                .contentType(ContentType.JSON)
                .body(createBody)
                .when()
                .post("/goods/add");

        createResponse
                .then()
                .statusCode(200);

        createdProductId = createResponse
                .jsonPath()
                .getLong("data.id");

        String updatedName = "Updated Product-" + UUID.randomUUID();

        String patchBody = """
            {
              "name": "%s",
              "price": 250.5
            }
            """.formatted(updatedName);

        given()
                .baseUri("http://localhost:8080")
                .auth().preemptive().basic("admin", "secret123")
                .contentType(ContentType.JSON)
                .body(patchBody)
                .when()
                .patch("/goods/" + createdProductId)
                .then()
                .statusCode(200);

        given()
                .baseUri("http://localhost:8080")
                .auth().preemptive().basic("admin", "secret123")
                .when()
                .get("/goods/" + createdProductId)
                .then()
                .statusCode(200)
                .body("name", equalTo(updatedName))
                .body("price", equalTo(250.5F));
    }
    @Test
    void patchGoodNotFoundTest() {

        long nonExistingId = 0;

        String patchBody = """
            {
              "name": "Updated Product",
              "price": 250.5
            }
            """;

        given()
                .baseUri("http://localhost:8080")
                .auth().preemptive().basic("admin", "secret123")
                .contentType(ContentType.JSON)
                .body(patchBody)
                .when()
                .patch("/goods/" + nonExistingId)
                .then()
                .statusCode(404);
    }

}
