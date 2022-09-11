package com.natalya.challenge.covenant.pageobject.listener;

import com.natalya.challenge.covenant.pageobject.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class BridgeListenerCreatePage extends BasePage {

    @FindBy(id = "bridge")
    private WebElement commonDataBlock;

    private CommonListenerBlock commonBlock = new CommonListenerBlock(commonDataBlock);
    private CommonListenerBlock getCommonDataBlock() {
        return new CommonListenerBlock(commonDataBlock);
    }
    @FindBy(xpath = "//div[@id='bridge']//*[@id='ConnectAddresses_0_']")
    private WebElement connectionAddressInput;


    @Override
    public void waitForReadiness() {
        waitBot.waitElementDisplayed(commonDataBlock);
    }

    @FindBy(id = "UseSSL")
    private WebElement useSslSelector;

    @FindBy(xpath = "//div[@id='bridge']//button[@type='submit']")
    private WebElement createButton;
    private Select getUseSslDropdown() {
        waitBot.waitElementDisplayed(useSslSelector);
        return new Select(useSslSelector);
    }

    public BridgeListenerCreatePage enterConnectAddress(String address) {
        actionBot.sendKeys(connectionAddressInput, address);
        return this;
    }

    public BridgeListenerCreatePage selectUseSsl(String useSsl) {
        actionBot.selectByText(getUseSslDropdown(), useSsl);
        return this;
    }

    public BridgeListenerCreatePage enterName(String name) {
        commonBlock.enterName(name);
        return this;
    }

    public BridgeListenerCreatePage enterBindAddress(String bindAddress) {
        commonBlock.enterBindAddress(bindAddress);
        return this;
    }

    public BridgeListenerCreatePage enterBindPort(String bindPort) {
        commonBlock.enterBindPort(bindPort);
        return this;
    }

    public BridgeListenerCreatePage enterConnectPort(String connectPort) {
        commonBlock.enterConnectPort(connectPort);
        return this;
    }

    public BridgeListenerCreatePage selectBridgeProfile(String description) {
        commonBlock.selectProfile(description);
        return this;
    }

    public ListenersViewPage clickCreateButton() {
        actionBot.click(createButton);
        return new ListenersViewPage();
    }

    public String getName() {
        return commonBlock.getName();
    }
}
