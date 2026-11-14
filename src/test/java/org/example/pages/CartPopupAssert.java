package org.example.pages;

import org.assertj.core.api.AbstractAssert;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class CartPopupAssert
        extends AbstractAssert<CartPopupAssert, MainPage.CartPopup> {

    public CartPopupAssert(MainPage.CartPopup actual) {
        super(actual, CartPopupAssert.class);
    }

    public CartPopupAssert cartIsVisible() {
        actual.cartModal.shouldBe(visible);
        return this;
    }

    public CartPopupAssert productIsInCart(String productName) {
        actual.cartItems.shouldHave(text(productName));
        return this;
    }

    public CartPopupAssert makeOrderButtonIsVisible() {
        actual.makeOrderButton.shouldBe(visible);
        return this;
    }

    public CartPopupAssert totalIs(int expectedTotal) {
        actual.cartModal
                .shouldHave(text(expectedTotal + " ₽"));

        return this;
    }

    public MainPage.CartPopup page() {
        return actual;
    }
}