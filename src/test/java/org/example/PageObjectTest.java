package org.example;

import org.junit.jupiter.api.Test;

import static org.example.config.ConfigProvider.CONFIG;



public class PageObjectTest extends BaseTest {

    @Test
    void pageObjectWorksTest() {

        mainPage
                .openPage()
                .check()
                .cartButtonIsVisible();
    }

    @Test
    void adminLoginPageWorksTest() {

        adminLoginPage
                .openPage()
                .check()
                .usernameInputIsVisible()
                .passwordInputIsVisible()
                .loginButtonIsVisible();

        adminLoginPage
                .setUsername(CONFIG.adminUsername())
                .setPassword(CONFIG.adminPassword())
                .check()
                .usernameInputHasValue(CONFIG.adminUsername())
                .passwordInputHasValue(CONFIG.adminPassword());
    }

}