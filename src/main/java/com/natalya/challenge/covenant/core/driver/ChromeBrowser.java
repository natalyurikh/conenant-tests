package com.natalya.challenge.covenant.core.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.HashMap;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeBrowser implements Browser {
    @Override
    public WebDriver instantiate(TestConfig launchOptions) {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver((ChromeOptions) getCapabilities(launchOptions));
    }
    @Override
    public Capabilities getCapabilities(TestConfig launchOptions) {
        ChromeOptions options = new ChromeOptions();
        var chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        chromePrefs.put("profile.content_settings.pattern_pairs.*.multiple-automatic-downloads", 1);
        chromePrefs.put("download.prompt_for_download", false);
        chromePrefs.put("safebrowsing.enabled", "false");
        chromePrefs.put("profile.default_content_settings.cookies", 2);
        chromePrefs.put("download_restrictions", 0);
        chromePrefs.put("download.default_directory", launchOptions.getBrowserDownloadPath());
        options.addArguments("--test-type");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-extensions");
        options.addArguments("disable-infobars");
        options.setAcceptInsecureCerts(true);
        options.setExperimentalOption("prefs", chromePrefs);
        return options;
    }
}