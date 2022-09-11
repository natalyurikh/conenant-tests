package com.natalya.challenge.covenant.scenario;

import com.natalya.challenge.covenant.core.driver.DriverManager;
import com.natalya.challenge.covenant.core.driver.TestConfig;
import com.natalya.challenge.covenant.reporting.TestListener;
import java.io.IOException;
import java.util.Properties;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@Log4j2
@Listeners({TestListener.class})
public class BaseUiTest {

    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected TestConfig testConfig;

    @BeforeTest
    @Parameters({"environment"})
    public void setUp(@Optional("local") String environment) throws IOException {

        String configPath = "config/%s.properties";
        log.info("Loading properties: {}", String.format(configPath, environment));
        Properties p = new Properties();
        p.load(ClassLoader.getSystemResourceAsStream(String.format(configPath, environment)));
        testConfig = TestConfig.fromProperties(p);
        driver.set(DriverManager.createDriver(testConfig));
    }

    @AfterClass(alwaysRun = true)
    public void cleanUp() {
        DriverManager.stopDriver();
    }
}
