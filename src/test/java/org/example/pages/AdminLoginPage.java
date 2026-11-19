package org.example.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import io.qameta.allure.Step;


public class AdminLoginPage {

    final SelenideElement usernameInput =
            $("#username");

    final SelenideElement passwordInput =
            $("#password");

    final SelenideElement loginButton =
            $("button[type='submit']");

    @Step("Открыть страницу входа в админку")
    public AdminLoginPage openPage() {
        open("/admin");
        return this;
    }

    @Step("Ввести логин")
    public AdminLoginPage setUsername(String username) {
        usernameInput.setValue(username);
        return this;
    }

    @Step("Ввести пароль")
    public AdminLoginPage setPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    @Step("Нажать кнопку входа")
    public AdminLoginPage clickLoginButton() {
        loginButton.click();
        return this;
    }

    @Step("Авторизоваться в админке")
    public AdminPage login(String username, String password) {
        usernameInput.setValue(username);
        passwordInput.setValue(password);
        loginButton.click();

        return new AdminPage();
    }

    public AdminLoginPageAssert check() {
        return new AdminLoginPageAssert(this);
    }

}