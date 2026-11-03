package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class SeleniumGoodsTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void openShopTest() {

        driver.get("http://localhost:8080");

        WebElement cartButton =
                driver.findElement(By.id("open-cart-btn"));

        System.out.println(cartButton.getText());
    }

    @Test
    void addProductThroughAdminTest() {

        String productName = "Selenium Product-" + UUID.randomUUID();

        driver.get("http://localhost:8080");

        driver.findElement(By.cssSelector("a[href='/admin']"))
                .click();

        driver.findElement(By.id("username"))
                .sendKeys("admin");

        driver.findElement(By.id("password"))
                .sendKeys("secret123");

        driver.findElement(By.cssSelector("button[type='submit']"))
                .click();

        System.out.println("После логина URL: " + driver.getCurrentUrl());

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement nameInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("n-name"))
        );

        nameInput.sendKeys(productName);

        driver.findElement(By.id("n-price"))
                .sendKeys("123");

        driver.findElement(By.id("add-btn"))
                .click();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class,'toast') and contains(text(),'Товар успешно добавлен!')]")
                )
        );

       driver.findElement(By.linkText("Вернуться на сайт"))
                .click();


        WebElement createdProduct = driver.findElement(
                By.cssSelector(".product-card[data-name='" + productName + "']")
        );

        assertTrue(
                createdProduct.isDisplayed(),
                "Созданный товар не отображается на витрине"
        );
    }
}