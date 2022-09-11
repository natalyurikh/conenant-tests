package com.natalya.challenge.covenant.pageobject.user;

import com.natalya.challenge.covenant.pageobject.BasePage;
import com.natalya.challenge.covenant.pageobject.blocks.Table;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UsersViewPage extends BasePage {
    @FindBy(xpath = "//div[@id='users']//table")
    private WebElement usersTable;

    @FindBy(xpath = "//div[@id='users']//a[@class = 'btn btn-primary']")
    private WebElement createButton;

    private Table usersViewTable = new Table(usersTable);

    public UserCreatePage clickCreateButton() {
        actionBot.click(createButton);
        return new UserCreatePage();
    }
    @Override
    public void waitForReadiness() {
        waitBot.waitElementDisplayed(usersTable);
    }
}
