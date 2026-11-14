package org.example.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;




public class MainPage {


     final ElementsCollection productCards =
            $$(".product-card");

     final SelenideElement cartButton =
            $("#open-cart-btn");

    final SelenideElement productsList =
            $("#products-list");

    final ElementsCollection addToCartButtons =
            $$("button[data-action='add-to-cart']");



    public MainPage addProductToCart(String productName) {

        SelenideElement productCard =
                productCards.findBy(text(productName));

        productCard
                .$("[data-action='add-to-cart']")
                .click();

        return this;
    }

    public CartPopup openCart() {
        cartButton.click();
        return cartPopup;
    }

    public MainPage openPage() {
        open("/");
        return this;
    }
    public MainPageAssert check() {
        return new MainPageAssert(this);
    }

    public MainPage scrollToProducts() {
        productsList.scrollIntoCenter();
        return this;
    }

    public MainPage addFirstProductToCart() {
        addToCartButtons.first().click();
        return this;
    }

    final ElementsCollection toasts =
            $$(".toast");

    private final CartPopup cartPopup =
            new CartPopup();

    public class CartPopup {

        final SelenideElement cartModal =
                $("#cartModal");

        final SelenideElement cartItems =
                $("#cart-items");

        final ElementsCollection cartItemRows =
                $$(".cart-item");

        final SelenideElement makeOrderButton =
                $("#makeOrder");

        public CartPopup removeProduct(String productName) {

            SelenideElement cartItem =
                    cartItemRows.findBy(text(productName));

            cartItem
                    .$("[data-action='remove']")
                    .click();

            return this;
        }

        public MainPage makeOrder() {
            makeOrderButton.click();
            return MainPage.this;
        }

        public CartPopupAssert check() {
            return new CartPopupAssert(this);
        }
    }

    public int addFirstDifferentProductsToCart(int count) {

        int total = 0;

        for (int i = 0; i < count; i++) {

            SelenideElement productCard =
                    productCards.get(i);

            int price = Integer.parseInt(
                    productCard.getAttribute("data-price")
            );

            productCard
                    .$("[data-action='add-to-cart']")
                    .click();

            total += price;
        }

        return total;
    }

}