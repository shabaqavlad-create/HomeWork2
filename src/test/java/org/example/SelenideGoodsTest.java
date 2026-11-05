package org.example;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;


public class SelenideGoodsTest {


    @BeforeEach
    void setUp() {
        Configuration.baseUrl = "http://localhost:8080";
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }



    @Test
    void addProductThroughAdminTest() {

        String productName =
                "Selenide Product-" + UUID.randomUUID();

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
                .setValue("123");

        $("#add-btn")
                .click();

        $(".toast")
                .shouldBe(visible)
                .shouldHave(text("Товар успешно добавлен!"));

        $x("//a[normalize-space()='Вернуться на сайт']")
                .click();

        $(".product-card[data-name='" + productName + "']")
                .shouldBe(visible);
    }

    @Test
    void addProductToCartTest() {

        open("/");

        SelenideElement productCard =
                $(".product-card")
                        .shouldBe(visible);

        String productName =
                productCard.getAttribute("data-name");

        productCard.$("button[data-action='add-to-cart']")
                .click();

        $("#open-cart-btn")
                .click();

        $("#cart-items")
                .shouldBe(visible)
                .shouldHave(text(productName));
    }

    @Test
    void wrongAdminLoginTest() {

        open("/admin");

        $("#username")
                .setValue("admin12");

        $("#password")
                .setValue("secret12345");

        $("button[type='submit']")
                .click();

        webdriver()
                .shouldHave(urlContaining("error"));
    }


    @Test
    void cartPersistsAfterRefreshTest() {

        open("/");

        SelenideElement productCard =
                $(".product-card")
                        .shouldBe(visible);

        String productName =
                productCard.getAttribute("data-name");

        productCard.$("button[data-action='add-to-cart']")
                .click();

        $("#open-cart-btn")
                .shouldHave(text("(1)"))
                .click();

        $("#cart-items")
                .shouldHave(text(productName));

        refresh();

        $("#open-cart-btn")
                .shouldHave(text("(1)"))
                .click();

        $("#cart-items")
                .shouldHave(text(productName));
    }

    @Test
    void checkoutWithTotalMoreThan300Test() {

        open("/");

        SelenideElement glassCard =
                $(".product-card[data-name='Стакан']")
                        .shouldBe(visible);

        SelenideElement addButton =
                glassCard.$("button[data-action='add-to-cart']");

        for (int i = 0; i < 13; i++) {
            addButton.click();
        }

        $("#open-cart-btn")
                .shouldHave(text("(13)"))
                .click();

        $("#cartModal")
                .shouldHave(text("Сумма:"))
                .shouldHave(text("325 ₽"));

        $("#makeOrder")
                .shouldBe(visible)
                .click();

        confirm("[SmartShop]: Денег не хватает! Сумма 325 ₽ превышает лимит 300 ₽.");
    }

}