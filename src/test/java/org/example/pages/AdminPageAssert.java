package org.example.pages;

import org.assertj.core.api.AbstractAssert;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class AdminPageAssert
        extends AbstractAssert<AdminPageAssert, AdminPage> {

    public AdminPageAssert(AdminPage actual) {
        super(actual, AdminPageAssert.class);
    }

    public AdminPageAssert productCreatedToastIsVisible() {
        actual.toasts
                .last()
                .shouldBe(visible)
                .shouldHave(text("Товар успешно добавлен!"));

        return this;
    }

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