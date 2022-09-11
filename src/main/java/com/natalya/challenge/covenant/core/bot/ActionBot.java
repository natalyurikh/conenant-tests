package com.natalya.challenge.covenant.core.bot;

import com.natalya.challenge.covenant.core.driver.DriverManager;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * A class helps to interact with WebElements, like click, sendKeys, select
 * each method firstly wait element is ready for action
 */
@Log4j2
public class ActionBot {

    private final WebDriver driver;
    private final WaitBot waitBot;

    public ActionBot(@NonNull WebDriver driver) {
        this.driver = driver;
        waitBot = new WaitBot(driver);
    }

    public void sendKeys(WebElement element, String text) {
        waitBot.waitElementClickable(element);
        element.clear();
        element.sendKeys(text);
    }

    public void selectByText(Select element, String text) {
        waitBot.waitElementClickable(element.getWrappedElement());
        element.selectByVisibleText(text);
    }

    public void click(WebElement element) {
        waitBot.waitElementClickable(element);
        try {
            element.click();
        } catch (StaleElementReferenceException e) {
            click(element);
        }
    }

    public void navigateUrl(String url) {
        driver.navigate().to(url);
        waitBot.waitForPageLoadIsComplete();
    }

    public boolean isElementDisplayed(WebElement element) {
        waitBot.waitForPageLoadIsComplete();
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static ActionBot getBot() {
        return new ActionBot(DriverManager.getDriver());
    }
}
