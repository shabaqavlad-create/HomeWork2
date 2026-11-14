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

    final SelenideElement makeOrderButton =
            $("#makeOrder");


    public MainPage addProductToCart(String productName) {

        SelenideElement productCard =
                productCards.findBy(text(productName));

        productCard
                .$("[data-action='add-to-cart']")
                .click();

        return this;
    }

    public MainPage openCart() {
        cartButton.click();
        return this;
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

    public MainPage makeOrder() {
        makeOrderButton.click();
        return this;
    }

}