package org.example.pages;

import org.assertj.core.api.AbstractAssert;

import static com.codeborne.selenide.Condition.*;

public class MainPageAssert
        extends AbstractAssert<MainPageAssert, MainPage> {

    public MainPageAssert(MainPage actual) {
        super(actual, MainPageAssert.class);
    }

    public MainPageAssert cartButtonIsVisible() {
        actual.cartButton.shouldBe(visible);
        return this;
    }

    public MainPageAssert productsListIsVisible() {
        actual.productsList.shouldBe(visible);
        return this;
    }

    public MainPageAssert productCardsAreVisible() {
        actual.productCards.forEach(
                productCard -> productCard.shouldBe(visible)
        );
        return this;
    }

    public MainPageAssert addToCartButtonsAreVisible() {
        actual.addToCartButtons.forEach(
                button -> button.shouldBe(visible)
        );
        return this;
    }


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

    public MainPageAssert orderAcceptedToastIsVisible() {
        actual.toasts
                .last()
                .shouldBe(visible)
                .shouldHave(text("Заказ принят в обработку!"));

        return this;
    }

}