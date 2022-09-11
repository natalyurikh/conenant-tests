package com.natalya.challenge.covenant.core.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class FirefoxBrowser implements Browser {

    @Override
    public WebDriver instantiate(TestConfig launchOptions) {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver((FirefoxOptions) getCapabilities(launchOptions));
    }
    @Override
    public Capabilities getCapabilities(TestConfig launchOptions) {
        FirefoxProfile browserProfile = new FirefoxProfile();
        browserProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
        browserProfile.setPreference("browser.download.folderList", 2);
        browserProfile.setPreference("browser.download.manager.showWhenStarting", false);
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(browserProfile);
        return options;
    }
}
