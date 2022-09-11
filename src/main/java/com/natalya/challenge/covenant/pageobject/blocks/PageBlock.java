package com.natalya.challenge.covenant.pageobject.blocks;

import com.natalya.challenge.covenant.core.bot.ActionBot;
import com.natalya.challenge.covenant.core.bot.WaitBot;
import com.natalya.challenge.covenant.core.driver.DriverManager;
import lombok.NonNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.support.PageFactory;

/**
 * PageBlock is a part of web page, consists of WebElements
 *
 */
public abstract class PageBlock implements WrapsElement {
    protected final ActionBot actionBot;
    protected final WaitBot waitBot;
    protected final WebDriver driver;
    protected final WebElement element;

    /**
     *
     * @param element is a root element of the block
     * inner elements will be populated relatively to element
     */
    protected PageBlock(@NonNull WebElement element) {
        this.driver = DriverManager.getDriver();
        this.actionBot = new ActionBot(driver);
        this.waitBot = new WaitBot(driver);
        this.element = element;
        PageFactory.initElements(element, this);
    }

    public WebElement getWrappedElement() {
        return element;
    }
}
