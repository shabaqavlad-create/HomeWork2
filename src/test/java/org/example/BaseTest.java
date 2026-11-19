package org.example;

import com.codeborne.selenide.Configuration;
import org.example.pages.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.example.pages.AdminLoginPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static org.example.config.ConfigProvider.CONFIG;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;



public class BaseTest {

    protected MainPage mainPage = new MainPage();

    protected AdminLoginPage adminLoginPage = new AdminLoginPage();

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = CONFIG.baseUrl();
        Configuration.timeout = CONFIG.timeout();

        SelenideLogger.addListener(
                "AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
        );
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }

    @AfterAll
    static void removeAllureListener() {
        SelenideLogger.removeListener("AllureSelenide");
    }

}