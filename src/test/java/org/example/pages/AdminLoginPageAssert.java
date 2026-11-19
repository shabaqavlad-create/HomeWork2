package org.example.pages;

import org.assertj.core.api.AbstractAssert;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.value;
import io.qameta.allure.Step;



public class AdminLoginPageAssert
        extends AbstractAssert<AdminLoginPageAssert, AdminLoginPage> {

    public AdminLoginPageAssert(AdminLoginPage actual) {
        super(actual, AdminLoginPageAssert.class);
    }

    @Step("Проверить отображение поля логина")

    public AdminLoginPageAssert usernameInputIsVisible() {
        actual.usernameInput.shouldBe(visible);
        return this;
    }

    @Step("Проверить отображение поля пароля")
    public AdminLoginPageAssert passwordInputIsVisible() {
        actual.passwordInput.shouldBe(visible);
        return this;
    }

    @Step("Проверить отображение кнопки входа")
    public AdminLoginPageAssert loginButtonIsVisible() {
        actual.loginButton.shouldBe(visible);
        return this;
    }

    @Step("Проверить значение поля логина")
    public AdminLoginPageAssert usernameInputHasValue(String username) {
        actual.usernameInput.shouldHave(value(username));
        return this;
    }

    @Step("Проверить значение поля пароля")
    public AdminLoginPageAssert passwordInputHasValue(String password) {
        actual.passwordInput.shouldHave(value(password));
        return this;
    }

}