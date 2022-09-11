package com.natalya.challenge.covenant.core.bot;

import com.natalya.challenge.covenant.core.driver.DriverManager;
import java.time.Duration;
import java.util.Objects;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * A class provides different type of wait options
 */
@Log4j2
public class WaitBot {

    private final WebDriver driver;
    private WebDriverWait defaultWait;

    public WaitBot(@NonNull WebDriver driver) {
        this.driver = driver;
        defaultWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        defaultWait.ignoring(JavascriptException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(WebDriverException.class);
    }

    public void waitElementClickable(@NonNull WebElement element) {
        defaultWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitElementDisplayed(@NonNull WebElement element) {
        defaultWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitElementPresent(@NonNull By by) {
        defaultWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void waitForCondition(@NonNull ExpectedCondition condition) {
        defaultWait.until(condition);
    }

    public void waitForPageLoadIsComplete() {
        try {
            ExpectedCondition<Boolean> pageLoadFinishedCondition = driver -> ((JavascriptExecutor) Objects.requireNonNull(
                    driver))
                    .executeScript("return document.readyState")
                    .equals("complete");
            defaultWait.until(pageLoadFinishedCondition);
            log.debug("Page load is complete");
        } catch (TimeoutException e) {
            log.info("Waiting for Page loading failed", e);
        }
    }

    public static WaitBot getBot() {
        return new WaitBot(DriverManager.getDriver());
    }
}
