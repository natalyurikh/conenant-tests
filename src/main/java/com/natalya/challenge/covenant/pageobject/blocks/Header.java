package com.natalya.challenge.covenant.pageobject.blocks;

import com.natalya.challenge.covenant.pageobject.LoginPage;
import lombok.NonNull;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Header extends PageBlock {

    public Header(@NonNull WebElement element) {
        super(element);
    }

    @FindBy(partialLinkText = "Welcome, ")
    private WebElement editUserLink;

    @FindBy(linkText = "Logout")
    private WebElement logoutLink;

    public LoginPage clickLogout() {
        actionBot.click(logoutLink);
        return new LoginPage();
    }

    public String getUserName() {
        waitBot.waitElementDisplayed(editUserLink);
        String text = editUserLink.getText();
        //Welcome, username!
        return text.substring(text.indexOf(",") + 2, text.indexOf("!"));
    }
}
