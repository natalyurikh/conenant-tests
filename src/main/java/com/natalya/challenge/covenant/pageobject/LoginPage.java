package com.natalya.challenge.covenant.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "CovenantUserRegister_UserName")
    private WebElement usernameInput;

    @FindBy(id = "CovenantUserRegister_Password")
    private WebElement passwordInput;

    @FindBy(id = "CovenantUserRegister_Password")
    private WebElement confirmPasswordInput;

    @FindBy(xpath = "//button[text()='Login']")
    private WebElement loginButton;

    public LoginPage openLoginPage(String url) {
        actionBot.navigateUrl(url);
        return new LoginPage();
    }

    public LoginPage typeUserName(String username) {
        actionBot.sendKeys(usernameInput, username);
        return this;
    }

    public LoginPage typePassword(String password) {
        actionBot.sendKeys(passwordInput, password);
        return this;
    }
    public LoginPage typeConfirmPassword(String password) {
        actionBot.sendKeys(confirmPasswordInput, password);
        return this;
    }

    public DashBoardPage clickLoginButton() {
        actionBot.click(loginButton);
        return new DashBoardPage();
    }

    @Override
    public void waitForReadiness() {
        waitBot.waitElementDisplayed(usernameInput);
    }
}
