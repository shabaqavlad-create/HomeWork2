package org.example.pages;

import org.assertj.core.api.AbstractAssert;

import static com.codeborne.selenide.Condition.*;
import io.qameta.allure.Step;


public class MainPageAssert
        extends AbstractAssert<MainPageAssert, MainPage> {

    public MainPageAssert(MainPage actual) {
        super(actual, MainPageAssert.class);
    }

    @Step("Проверить, что кнопка корзины отображается")
    public MainPageAssert cartButtonIsVisible() {
        actual.cartButton.shouldBe(visible);
        return this;
    }

    @Step("Проверить, что список товаров отображается")
    public MainPageAssert productsListIsVisible() {
        actual.productsList.shouldBe(visible);
        return this;
    }

    @Step("Проверить, что карточки товаров отображаются")
    public MainPageAssert productCardsAreVisible() {
        actual.productCards.forEach(
                productCard -> productCard.shouldBe(visible)
        );
        return this;
    }

    @Step("Проверить, что кнопки добавления товара отображаются")
    public MainPageAssert addToCartButtonsAreVisible() {
        actual.addToCartButtons.forEach(
                button -> button.shouldBe(visible)
        );
        return this;
    }


    @Step("Проверить товар {productName} с ценой {productPrice}")
    public MainPageAssert productHasNameAndPrice(
            String productName,
            int productPrice
    ) {

        actual.productCards
                .findBy(attribute("data-name", productName))
                .shouldBe(visible)
                .shouldHave(
                        attribute(
                                "data-price",
                                String.valueOf(productPrice)
                        )
                );

        return this;
    }

    @Step("Проверить уведомление об успешном оформлении заказа")
    public MainPageAssert orderAcceptedToastIsVisible() {
        actual.toasts
                .last()
                .shouldBe(visible)
                .shouldHave(text("Заказ принят в обработку!"));

        return this;
    }

}