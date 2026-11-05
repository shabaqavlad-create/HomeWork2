package org.example;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.DragAndDropOptions.to;
import static com.codeborne.selenide.Selenide.*;




public class BestPracticesTest {

    @BeforeEach
    void setUp() {
        Configuration.baseUrl = "http://localhost:8080";
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }

    @Test
    void dragAndDropProductToCartTest() {

        open("/");

        SelenideElement product =
                $(".product-card")
                        .shouldBe(visible);

        String productName =
                product.getAttribute("data-name");

        product.dragAndDrop(
                to("#open-cart-btn").usingSeleniumActions()
        );

        $("#open-cart-btn")
                .shouldHave(text("(1)"))
                .click();

        $("#cart-items")
                .shouldBe(visible)
                .shouldHave(text(productName));
    }

    @Test
    void removeProductFromCartTest() {

        open("/");

        SelenideElement product =
                $(".product-card")
                        .shouldBe(visible);

        String productName =
                product.getAttribute("data-name");

        product.$("button[data-action='add-to-cart']")
                .click();

        $("#open-cart-btn")
                .shouldHave(text("(1)"))
                .click();

        SelenideElement cartItem =
                $$(".cart-item")
                        .findBy(text(productName))
                        .shouldBe(visible);

        cartItem.$("button[data-action='remove']")
                .click();

        cartItem.shouldNot(exist);

        $("#cart-items")
                .shouldNotHave(text(productName));
    }

}