package org.example;

import org.junit.jupiter.api.Test;

import static org.example.config.ConfigProvider.CONFIG;



public class PageObjectTest extends BaseTest {


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

    @Test
    void buyThreeProductsTest() {

        int expectedTotal =
                CONFIG.productPrice() * 3;

        mainPage
                .openPage()
                .addProductToCart(CONFIG.productName())
                .addProductToCart(CONFIG.productName())
                .addProductToCart(CONFIG.productName())
                .openCart()
                .check()
                .cartIsVisible()
                .productIsInCart(CONFIG.productName())
                .totalIs(expectedTotal)
                .page()
                .makeOrder()
                .check()
                .orderAcceptedToastIsVisible();
    }

    @Test
    void differentProductsTotalTest() {

        mainPage.openPage();

        int expectedTotal =
                mainPage.addFirstDifferentProductsToCart(3);

        mainPage
                .openCart()
                .check()
                .cartIsVisible()
                .totalIs(expectedTotal);
    }

}