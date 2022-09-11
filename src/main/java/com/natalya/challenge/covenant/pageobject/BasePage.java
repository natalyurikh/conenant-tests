package com.natalya.challenge.covenant.pageobject;

import com.natalya.challenge.covenant.core.bot.ActionBot;
import com.natalya.challenge.covenant.core.bot.WaitBot;
import com.natalya.challenge.covenant.core.driver.DriverManager;
import com.natalya.challenge.covenant.pageobject.blocks.GlobalNavBlock;
import com.natalya.challenge.covenant.pageobject.blocks.Header;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
@Log4j2
public abstract class BasePage {

    protected final ActionBot actionBot;
    protected final WaitBot waitBot;
    protected final WebDriver driver;

    @FindBy(xpath = "//nav[@class='navbar navbar-expand navbar-dark sticky-top border-bottom border-dark']")
    private WebElement headerElement;

    @FindBy(xpath = "//ul[@class='nav flex-column']")
    private WebElement navigationElement;

    protected BasePage() {
        this.driver = DriverManager.getDriver();
        actionBot = new ActionBot(driver);
        waitBot = new WaitBot(driver);
        PageFactory.initElements(driver, this);
        waitBot.waitForPageLoadIsComplete();
    }

    /**
     * wait for specific page finishes loading
     */
    public abstract void waitForReadiness();

    public GlobalNavBlock navigation() {
        waitBot.waitElementDisplayed(navigationElement);
        return new GlobalNavBlock(navigationElement);
    }

    public Header header() {
        waitBot.waitElementDisplayed(headerElement);
        return new Header(headerElement);
    }
}
