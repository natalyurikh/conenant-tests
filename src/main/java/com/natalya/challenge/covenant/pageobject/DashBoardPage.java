package com.natalya.challenge.covenant.pageobject;

import com.natalya.challenge.covenant.pageobject.blocks.Table;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashBoardPage extends BasePage {
    @FindBy(xpath = "//div[@class='table-responsive'][1]/table")
    private WebElement gruntsTable;
    @FindBy(xpath = "//div[@class='table-responsive'][2]/table")
    private WebElement listenersTable;
    @FindBy(xpath = "//div[@class='table-responsive'][3]/table")
    private WebElement taskingsTable;

    private Table getGruntsTable() {
        return new Table(gruntsTable);
    }

    private Table getListenersTable() {
        return new Table(listenersTable);
    }

    private Table getTaskingsTable() {
        return new Table(taskingsTable);
    }

    @Override
    public void waitForReadiness() {
        waitBot.waitElementDisplayed(gruntsTable);
    }
}
