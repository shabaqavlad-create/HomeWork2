package org.example.pages;

import org.assertj.core.api.AbstractAssert;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import io.qameta.allure.Step;


public class AdminPageAssert
        extends AbstractAssert<AdminPageAssert, AdminPage> {

    public AdminPageAssert(AdminPage actual) {
        super(actual, AdminPageAssert.class);
    }

    @Step("Проверить уведомление об успешном создании товара")
    public AdminPageAssert productCreatedToastIsVisible() {
        actual.toasts
                .last()
                .shouldBe(visible)
                .shouldHave(text("Товар успешно добавлен!"));

        return this;
    }

    @Step("Проверить уведомление об успешном обновлении товара")
    public AdminPageAssert productUpdatedToastIsVisible() {
        actual.toasts
                .last()
                .shouldBe(visible)
                .shouldHave(text("обновлен"));

        return this;
    }

    public AdminPage page() {
        return actual;
    }
}