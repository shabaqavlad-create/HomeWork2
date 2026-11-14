package org.example;

import com.codeborne.selenide.Configuration;
import org.example.pages.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static org.example.config.ConfigProvider.CONFIG;

public class BaseTest {

    protected MainPage mainPage = new MainPage();

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = CONFIG.baseUrl();
        Configuration.timeout = CONFIG.timeout();
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }

}