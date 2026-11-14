package org.example.pages;

import org.assertj.core.api.AbstractAssert;

import static com.codeborne.selenide.Condition.visible;

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

    public MainPageAssert makeOrderButtonIsVisible() {
        actual.makeOrderButton.shouldBe(visible);
        return this;
    }
}