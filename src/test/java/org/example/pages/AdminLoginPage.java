package org.example.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class AdminLoginPage {

    final SelenideElement usernameInput =
            $("#username");

    final SelenideElement passwordInput =
            $("#password");

    final SelenideElement loginButton =
            $("button[type='submit']");

    public AdminLoginPage openPage() {
        open("/admin");
        return this;
    }

    public AdminLoginPage setUsername(String username) {
        usernameInput.setValue(username);
        return this;
    }

    public AdminLoginPage setPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    public AdminLoginPage clickLoginButton() {
        loginButton.click();
        return this;
    }

    public AdminLoginPage login(String username, String password) {
        usernameInput.setValue(username);
        passwordInput.setValue(password);
        loginButton.click();

        return this;
    }

    public AdminLoginPageAssert check() {
        return new AdminLoginPageAssert(this);
    }

}