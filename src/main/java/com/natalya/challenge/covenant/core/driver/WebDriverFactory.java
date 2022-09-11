package com.natalya.challenge.covenant.core.driver;

import org.openqa.selenium.WebDriver;

public class WebDriverFactory {

    public static WebDriver createDriver(TestConfig launchOptions) {
        switch (launchOptions.getBrowserType()) {
            case CHROME:
                return new ChromeBrowser().instantiate(launchOptions);
            case FIREFOX:
                return new FirefoxBrowser().instantiate(launchOptions);
            default:
                throw new IllegalArgumentException(
                        "Browser type " + launchOptions.getBrowserType() + " is not defined");
        }
    }
}
