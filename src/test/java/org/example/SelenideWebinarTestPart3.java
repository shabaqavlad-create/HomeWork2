package org.example;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.ElementsCollection;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import java.util.UUID;
import static com.codeborne.selenide.Condition.value;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;




public class SelenideWebinarTestPart3 {

    private String createdProductName;

    @BeforeEach
    void setUp() {
        Configuration.baseUrl = "http://localhost:8080";
    }

    @AfterEach
    void tearDown() {

        if (createdProductName != null) {

            open("/admin");

            $("#username")
                    .setValue("admin");

            $("#password")
                    .setValue("secret123");

            $("button[type='submit']")
                    .click();

            SelenideElement productRow =
                    $x("//input[@value='" + createdProductName + "']/ancestor::tr")
                            .shouldBe(visible);

            productRow.$x(".//button[normalize-space()='Удалить']")
                    .click();

            createdProductName = null;
        }

        closeWebDriver();
    }


    @Test
    void orderThreeProductsTest() {

        open("/");

        SelenideElement glassCard =
                $(".product-card[data-name='Стакан']")
                        .shouldBe(visible);

        SelenideElement addButton =
                glassCard.$("button[data-action='add-to-cart']");

        for (int i = 0; i < 3; i++) {
            addButton.click();
        }

        $("#open-cart-btn")
                .shouldHave(text("(3)"))
                .click();

        $("#cartModal")
                .shouldBe(visible)
                .shouldHave(text("75 ₽"));

        $("#makeOrder")
                .shouldBe(visible)
                .click();

        SelenideElement lastToast =
                $$(".toast").last()
                        .shouldBe(visible)
                        .shouldHave(text("Заказ принят в обработку!"));
    }

    @Test
    void severalDifferentProductsTotalTest() {

        open("/");

        ElementsCollection products =
                $$(".product-card")
                        .shouldHave(sizeGreaterThan(2));

        int expectedTotal = 0;

        for (int i = 0; i < 3; i++) {

            SelenideElement productCard = products.get(i);

            String priceText =
                    productCard.getAttribute("data-price");

            int price = Integer.parseInt(priceText);

            expectedTotal = expectedTotal + price;

            productCard.$("button[data-action='add-to-cart']")
                    .click();
        }

        $("#open-cart-btn")
                .shouldHave(text("(3)"))
                .click();

        $("#cartModal")
                .shouldBe(visible)
                .shouldHave(text(expectedTotal + " ₽"));
    }

    @Test
    void addProductInAdminAndCheckNotificationTest() {

        String productName =
                "Webinar Product-" + UUID.randomUUID();

        open("/admin");

        $("#username")
                .setValue("admin");

        $("#password")
                .setValue("secret123");

        $("button[type='submit']")
                .click();

        $("#n-name")
                .shouldBe(visible)
                .setValue(productName);

        $("#n-price")
                .setValue("150");

        $("#add-btn")
                .click();

        $$(".toast").last()
                .shouldBe(visible)
                .shouldHave(text("Товар успешно добавлен!"));
    }

    @Test
    void editProductInAdminTest() {

        String productName =
                "Edit Product-" + UUID.randomUUID();

        String editedProductName =
                "Edited Product-" + UUID.randomUUID();

        open("/admin");

        $("#username")
                .setValue("admin");

        $("#password")
                .setValue("secret123");

        $("button[type='submit']")
                .click();

        $("#n-name")
                .shouldBe(visible)
                .setValue(productName);

        $("#n-price")
                .setValue("100");

        $("#add-btn")
                .click();

        $$(".toast").last()
                .shouldBe(visible)
                .shouldHave(text("Товар успешно добавлен!"));

        SelenideElement productRow =
                $x("//input[@value='" + productName + "']/ancestor::tr")
                        .shouldBe(visible);

        productRow.$("input[type='text']")
                .setValue(editedProductName);

        productRow.$("input[type='number']")
                .setValue("175");

        int toastCountBeforeSave = $$(".toast").size();

        productRow.$x(".//button[normalize-space()='Сохранить']")
                .click();

        $$(".toast")
                .shouldHave(sizeGreaterThan(toastCountBeforeSave));


        SelenideElement saveToast =
                $$(".toast").last()
                        .shouldBe(visible)
                        .shouldHave(text("Товар #"))
                        .shouldHave(text("обновлен"));

        refresh();

        SelenideElement editedProductRow =
                $x("//input[@value='" + editedProductName + "']/ancestor::tr")
                        .shouldBe(visible);

        editedProductRow.$("input[type='text']")
                .shouldHave(value(editedProductName));

        editedProductRow.$("input[type='number']")
                .shouldHave(value("175"));
    }

    private void createProductViaApi(String name, int price) {

        String requestBody = """
            {
              "name": "%s",
              "price": %d
            }
            """.formatted(name, price);

        given()
                .auth()
                .preemptive()
                .basic("admin", "secret123")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("http://localhost:8080/goods/add");


    }

    @Test
    void productCreatedViaApiIsVisibleInShopTest() {

        String productName =
                "API Product-" + UUID.randomUUID();

        createdProductName = productName;

        createProductViaApi(productName, 99);

        open("/");

        $(".product-card[data-name='" + productName + "']")
                .shouldBe(visible)
                .shouldHave(text(productName));
    }


}