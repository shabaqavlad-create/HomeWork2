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

    @Test
    void createProductInAdminTest() {

        String productName =
                "PageObject товар " + System.currentTimeMillis();

        int productPrice = 100;

        adminLoginPage
                .openPage()
                .login(
                        CONFIG.adminUsername(),
                        CONFIG.adminPassword()
                )
                .createProduct(productName, productPrice)
                .check()
                .productCreatedToastIsVisible();
    }

    @Test
    void editProductInAdminTest() {

        String originalName =
                "PageObject edit " + System.currentTimeMillis();

        String editedName =
                originalName + " updated";

        int originalPrice = 90;
        int editedPrice = 120;

        adminLoginPage
                .openPage()
                .login(
                        CONFIG.adminUsername(),
                        CONFIG.adminPassword()
                )
                .createProduct(originalName, originalPrice)
                .check()
                .productCreatedToastIsVisible()
                .page()
                .editProduct(
                        originalName,
                        editedName,
                        editedPrice
                )
                .check()
                .productUpdatedToastIsVisible();

        mainPage
                .openPage()
                .check()
                .productHasNameAndPrice(
                        editedName,
                        editedPrice
                );
    }

}