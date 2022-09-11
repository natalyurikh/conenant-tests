package com.natalya.challenge.covenant.core.driver;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return webDriver.get();
    }

    public static void setDriver(WebDriver driver) {
        webDriver.set(driver);
    }

    public static WebDriver createDriver(TestConfig launchOptions) {
        switch (launchOptions.getBrowserType()) {
            case CHROME:
                setDriver(new ChromeBrowser().instantiate(launchOptions));
                break;
            case FIREFOX:
                setDriver(new FirefoxBrowser().instantiate(launchOptions));
                break;
            default:
                throw new IllegalArgumentException(
                        "Browser type " + launchOptions.getBrowserType() + " is not defined");
        }

        return getDriver();
    }

    public static void stopDriver() {
        if (null != webDriver.get()) {
            webDriver.get().quit();
            webDriver.set(null);
        }
    }
}
