package com.natalya.challenge.covenant.pageobject.launcher;

import com.natalya.challenge.covenant.pageobject.blocks.CustomSelect;
import com.natalya.challenge.covenant.pageobject.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class InstallUtilLauncherCreatePage extends BasePage {

    @FindBy(id = "generate-tab")
    private WebElement generateTab;

    @FindBy(id = "host-tab")
    private WebElement hostTab;

    @FindBy(id = "code-tab")
    private WebElement codeTab;

    @FindBy(id = "ListenerId")
    private WebElement listenerSelector;

    private Select getListenerDropdown() {
        return new Select(listenerSelector);
    }

    @FindBy(id = "ImplantTemplateId")
    private WebElement implantTemplateSelector;

    private Select getImplantTemplateDropdown() {
        return new Select(implantTemplateSelector);
    }

    @FindBy(xpath = "//div[contains(@class, 'dropdown bootstrap-select show-menu-arrow')]")
    private WebElement dotNetVersionSelector;

    private CustomSelect getDotNetVersionDropdown() {
        waitBot.waitElementDisplayed(dotNetVersionSelector);
        return new CustomSelect(dotNetVersionSelector);
    }

    @FindBy(id = "ValidateCert")
    private WebElement validateCertSelector;

    private Select getValidateCertDropdown() {
        return new Select(validateCertSelector);
    }

    @FindBy(id = "UseCertPinning")
    private WebElement useCertPinning;

    private Select getUseCertPinningDropdown() {
        return new Select(useCertPinning);
    }

    @FindBy(id = "Delay")
    private WebElement delayInput;

    @FindBy(id = "JitterPercent")
    private WebElement jitterPercentInput;

    @FindBy(id = "ConnectAttempts")
    private WebElement connectAttemptsInput;

    @FindBy(id = "KillDate")
    private WebElement killDateInput;

    @FindBy(id = "generate")
    private WebElement generateButton;

    @FindBy(id = "download")
    private WebElement downloadButton;

    @FindBy(id = "Launcher")
    private WebElement launcherInput;

    @Override
    public void waitForReadiness() {
        waitBot.waitElementDisplayed(generateTab);
    }

    public InstallUtilLauncherCreatePage selectListener(String value) {
        actionBot.selectByText(getListenerDropdown(), value);
        return this;
    }

    public InstallUtilLauncherCreatePage selectTemplate(String value) {
        actionBot.selectByText(getImplantTemplateDropdown(), value);
        return this;
    }

    public InstallUtilLauncherCreatePage selectDotNetVersion(String value) {
        getDotNetVersionDropdown().selectByText(value);
        return this;
    }

    public InstallUtilLauncherCreatePage selectValidateCert(String value) {
        actionBot.selectByText(getValidateCertDropdown(), value);
        return this;
    }

    public InstallUtilLauncherCreatePage selectUseCertPinning(String value) {
        actionBot.selectByText(getUseCertPinningDropdown(), value);
        return this;
    }

    public InstallUtilLauncherCreatePage enterDelay(String value) {
        actionBot.sendKeys(delayInput, value);
        return this;
    }

    public InstallUtilLauncherCreatePage enterJitterPercent(String value) {
        actionBot.sendKeys(jitterPercentInput, value);
        return this;
    }

    public InstallUtilLauncherCreatePage enterConnectAttempts(String value) {
        actionBot.sendKeys(connectAttemptsInput, value);
        return this;
    }

    public InstallUtilLauncherCreatePage enterKillDate(String value) {
        waitBot.waitElementClickable(killDateInput);
        killDateInput.sendKeys(Keys.CONTROL + "A");
        killDateInput.sendKeys(value);
        return this;
    }

    public InstallUtilLauncherCreatePage clickGenerate() {
        actionBot.click(generateButton);
        return this;
    }

    public InstallUtilLauncherCreatePage clickDownload() {
        actionBot.click(downloadButton);
        return this;
    }
}

