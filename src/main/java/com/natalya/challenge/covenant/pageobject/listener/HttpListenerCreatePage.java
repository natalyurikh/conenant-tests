package com.natalya.challenge.covenant.pageobject.listener;

import com.natalya.challenge.covenant.pageobject.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class HttpListenerCreatePage extends BasePage {

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

    @FindBy(id = "ConnectAddresses_0_")
    private WebElement connectionAddressInput;

    @FindBy(id = "Urls_i_")
    private WebElement urlsInput;

    @FindBy(id = "UseSSL")
    private Select useSslSelector;

    @FindBy(id = "ProfileId")
    private WebElement profileSelector;

    @FindBy(xpath = "//button/span(text='Create')")
    private WebElement createButton;

    private Select getProfileDropdown() {
        return new Select(profileSelector);
    }

    public BridgeListenerCreatePage switchBridgeTab() {
        actionBot.click(bridgeTabSwitcher);
        return new BridgeListenerCreatePage();
    }

    public HttpListenerCreatePage switchHttpTab() {
        actionBot.click(httpTabSwitcher);
        return this;
    }

    public HttpListenerCreatePage enterName(String name) {
        actionBot.sendKeys(nameInput, name);
        return this;
    }

    public HttpListenerCreatePage enterBindAddress(String address) {
        actionBot.sendKeys(bindAddressInput, address);
        return this;
    }

    public HttpListenerCreatePage enterBindPort(String port) {
        actionBot.sendKeys(bindAddressInput, port);
        return this;
    }

    public HttpListenerCreatePage enterConnectPort(String port) {
        actionBot.sendKeys(connectionPortInput, port);
        return this;
    }

    public HttpListenerCreatePage enterConnectAddress(String address) {
        actionBot.sendKeys(connectionAddressInput, address);
        return this;
    }

    public HttpListenerCreatePage enterUrl(String url) {
        actionBot.sendKeys(urlsInput, url);
        return this;
    }

    public HttpListenerCreatePage selectBridgeProfile(String profile) {
        actionBot.selectByText(getProfileDropdown(), profile);
        return this;
    }

    public HttpListenerCreatePage selectHttpProfile(String profile) {
        actionBot.sendKeys(connectionAddressInput, profile);
        return this;
    }

    public HttpListenerCreatePage selectUseSsl(String useSsl) {
        actionBot.selectByText(useSslSelector, useSsl);
        return this;
    }

    public ListenersViewPage clickCreateButton() {
        actionBot.click(createButton);
        return new ListenersViewPage();
    }

    @Override
    public void waitForReadiness() {
        waitBot.waitElementDisplayed(nameInput);
    }
}
