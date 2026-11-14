package org.example.pages;

import org.assertj.core.api.AbstractAssert;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.value;



public class AdminLoginPageAssert
        extends AbstractAssert<AdminLoginPageAssert, AdminLoginPage> {

    public AdminLoginPageAssert(AdminLoginPage actual) {
        super(actual, AdminLoginPageAssert.class);
    }

    public AdminLoginPageAssert usernameInputIsVisible() {
        actual.usernameInput.shouldBe(visible);
        return this;
    }

    public AdminLoginPageAssert passwordInputIsVisible() {
        actual.passwordInput.shouldBe(visible);
        return this;
    }

    public AdminLoginPageAssert loginButtonIsVisible() {
        actual.loginButton.shouldBe(visible);
        return this;
    }

    public AdminLoginPageAssert usernameInputHasValue(String username) {
        actual.usernameInput.shouldHave(value(username));
        return this;
    }

    public AdminLoginPageAssert passwordInputHasValue(String password) {
        actual.passwordInput.shouldHave(value(password));
        return this;
    }

}