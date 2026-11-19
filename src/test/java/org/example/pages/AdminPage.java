package org.example.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;
import io.qameta.allure.Step;


public class AdminPage {

    final SelenideElement productNameInput =
            $("#n-name");

    final SelenideElement productPriceInput =
            $("#n-price");

    final SelenideElement addProductButton =
            $("#add-btn");

    final ElementsCollection toasts =
            $$(".toast");

    @Step("Создать товар {name} стоимостью {price}")
    public AdminPage createProduct(String name, int price) {

        productNameInput.setValue(name);
        productPriceInput.setValue(String.valueOf(price));
        addProductButton.click();

        return this;
    }

    public AdminPageAssert check() {
        return new AdminPageAssert(this);
    }

    @Step("Отредактировать товар {currentName}")
    public AdminPage editProduct(
            String currentName,
            String newName,
            int newPrice
    ) {

        SelenideElement productRow =
                $x("//input[@value='" + currentName + "']/ancestor::tr");

        productRow
                .$("input[type='text']")
                .setValue(newName);

        productRow
                .$("input[type='number']")
                .setValue(String.valueOf(newPrice));

        productRow
                .$x(".//button[normalize-space()='Сохранить']")
                .click();

        return this;
    }

}