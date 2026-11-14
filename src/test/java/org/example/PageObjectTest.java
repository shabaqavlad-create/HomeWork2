package org.example;

import org.junit.jupiter.api.Test;

public class PageObjectTest extends BaseTest {

    @Test
    void pageObjectWorksTest() {

        mainPage
                .openPage()
                .check()
                .cartButtonIsVisible();
    }
}