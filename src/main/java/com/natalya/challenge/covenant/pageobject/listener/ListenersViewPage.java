package com.natalya.challenge.covenant.pageobject.listener;

import com.natalya.challenge.covenant.pageobject.BasePage;
import com.natalya.challenge.covenant.pageobject.blocks.Table;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ListenersViewPage extends BasePage {

    @FindBy(xpath = "//div[@class='table-responsive']")
    private WebElement listenersTable;

    @FindBy(partialLinkText = "Create")
    private WebElement createButton;

    private Table getListenersTable() {
        return new Table(listenersTable);
    }

    public HttpListenerCreatePage clickCreate() {
        actionBot.click(createButton);
        return new HttpListenerCreatePage();
    }

    @Override
    public void waitForReadiness() {
        waitBot.waitElementDisplayed(listenersTable);
    }
}
