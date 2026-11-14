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
}