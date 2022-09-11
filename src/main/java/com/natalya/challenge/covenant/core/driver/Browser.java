package com.natalya.challenge.covenant.core.driver;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

public interface Browser {

    WebDriver instantiate(TestConfig launchOptions);
    Capabilities getCapabilities(TestConfig launchOptions);
}
