package org.example.pages;

import org.assertj.core.api.AbstractAssert;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import io.qameta.allure.Step;

public class CartPopupAssert
        extends AbstractAssert<CartPopupAssert, MainPage.CartPopup> {

    public CartPopupAssert(MainPage.CartPopup actual) {
        super(actual, CartPopupAssert.class);
    }

    @Step("Проверить, что корзина отображается")
    public CartPopupAssert cartIsVisible() {
        actual.cartModal.shouldBe(visible);
        return this;
    }

    @Step("Проверить наличие товара {productName} в корзине")
    public CartPopupAssert productIsInCart(String productName) {
        actual.cartItems.shouldHave(text(productName));
        return this;
    }

    @Step("Проверить, что кнопка оформления заказа отображается")
    public CartPopupAssert makeOrderButtonIsVisible() {
        actual.makeOrderButton.shouldBe(visible);
        return this;
    }

    @Step("Проверить итоговую сумму корзины {expectedTotal} рублей")
    public CartPopupAssert totalIs(int expectedTotal) {
        actual.cartModal
                .shouldHave(text(expectedTotal + " ₽"));

        return this;
    }

    public MainPage.CartPopup page() {
        return actual;
    }
}