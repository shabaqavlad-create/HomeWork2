package org.example;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.example.config.ConfigProvider.CONFIG;

import org.junit.jupiter.api.AfterEach;



public class ConfigurationTest {

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }

    @BeforeAll
    static void setUpConfig() {

        Configuration.baseUrl = CONFIG.baseUrl();
        Configuration.timeout = CONFIG.timeout();

        RestAssured.baseURI = CONFIG.apiUrl();

        System.out.println("Base URL: " + CONFIG.baseUrl());
        System.out.println("API URL: " + CONFIG.apiUrl());
        System.out.println("Timeout: " + CONFIG.timeout());
        System.out.println("Logging mode: " + CONFIG.loggingMode());
        System.out.println("Product name: " + CONFIG.productName());
        System.out.println("Product price: " + CONFIG.productPrice());
    }

    @Test
    void configIsUsedInTest() {

        open("/");

        $(".product-card[data-name='" + CONFIG.productName() + "']")
                .shouldBe(visible)
                .shouldHave(text(String.valueOf(CONFIG.productPrice())));
    }

    @Test
    void adminCredentialsAreUsedFromConfigTest() {

        open("/admin");

        $("#username")
                .setValue(CONFIG.adminUsername());

        $("#password")
                .setValue(CONFIG.adminPassword());

        $("button[type='submit']")
                .click();

        $("#n-name")
                .shouldBe(visible);
    }

}