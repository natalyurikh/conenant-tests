package com.natalya.challenge.covenant.pageobject.user;

import com.natalya.challenge.covenant.pageobject.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserCreatePage extends BasePage {

    @FindBy(id = "UserName")
    private WebElement userNameInput;

    @FindBy(id = "Password")
    private WebElement passwordInput;

    @FindBy(id = "ConfirmPassword")
    private WebElement confirmPasswordInput;

    @FindBy(xpath = "//button[@type = 'submit']")
    private WebElement createButton;

    public UserCreatePage enterUserName(String userName) {
        actionBot.sendKeys(userNameInput, userName);
        return this;
    }

    public UserCreatePage enterPassword(String password) {
        actionBot.sendKeys(passwordInput, password);
        return this;
    }

    public UserCreatePage enterConfirmPassword(String password) {
        actionBot.sendKeys(confirmPasswordInput, password);
        return this;
    }

    public UsersViewPage clickCreateButton() {
        actionBot.click(createButton);
        return new UsersViewPage();
    }

    @Override
    public void waitForReadiness() {
        waitBot.waitElementDisplayed(userNameInput);
    }
}
