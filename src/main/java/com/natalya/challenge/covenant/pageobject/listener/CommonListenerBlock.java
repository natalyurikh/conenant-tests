package com.natalya.challenge.covenant.pageobject.listener;

import com.natalya.challenge.covenant.pageobject.blocks.PageBlock;
import lombok.NonNull;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CommonListenerBlock extends PageBlock {

    public CommonListenerBlock(@NonNull WebElement element) {
        super(element);
    }

    @FindBy(id = "http-tab")
    private WebElement httpTabSwitcher;

    @FindBy(id = "bridge-tab")
    private WebElement bridgeTabSwitcher;

    @FindBy(id = "Description")
    private WebElement descriptionLabel;

    @FindBy(id = "Name")
    private WebElement nameInput;

    @FindBy(id = "BindAddress")
    private WebElement bindAddressInput;

    @FindBy(id = "BindPort")
    private WebElement bindPortInput;

    @FindBy(id = "ConnectPort")
    private WebElement connectionPortInput;

    @FindBy(id = "ProfileId")
    private WebElement profileSelector;
    private Select profileDropdown = new Select(profileSelector);

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement createButton;

    private Select getProfileDropdown() {
        return new Select(profileSelector);
    }

    public CommonListenerBlock switchBridgeTab() {
        actionBot.click(bridgeTabSwitcher);
        return this;
    }

    public CommonListenerBlock switchHttpTab() {
        actionBot.click(httpTabSwitcher);
        return this;
    }

    public CommonListenerBlock enterName(String name) {
        actionBot.sendKeys(nameInput, name);
        return this;
    }

    public CommonListenerBlock enterBindAddress(String address) {
        actionBot.sendKeys(bindAddressInput, address);
        return this;
    }

    public CommonListenerBlock enterBindPort(String port) {
        actionBot.sendKeys(bindPortInput, port);
        return this;
    }

    public CommonListenerBlock enterConnectPort(String port) {
        actionBot.sendKeys(connectionPortInput, port);
        return this;
    }

    public CommonListenerBlock selectProfile(String profile) {
        actionBot.selectByText(profileDropdown, profile);
        return this;
    }

    public ListenersViewPage clickCreateButton() {
        actionBot.click(createButton);
        return new ListenersViewPage();
    }

    public String getName() {
        waitBot.waitElementDisplayed(nameInput);
        return nameInput.getText();
    }
}
