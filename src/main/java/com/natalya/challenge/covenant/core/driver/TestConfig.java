package com.natalya.challenge.covenant.core.driver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class TestConfig {

    @NonNull
    private final BrowserType browserType;
    @NonNull
    private final DriverLaunchType launchType;
    private final URL seleniumServerUrl;
    private final String browserDownloadPath;
    private final String baseUrl;
    private final String targetHostName;
    private final String sshUser;
    private final String sshPassword;

    public static TestConfig fromProperties(Properties properties) throws MalformedURLException {
        return TestConfig.builder()
                .browserType(BrowserType.valueOf(properties.getProperty("browser").toUpperCase()))
                .launchType(DriverLaunchType.valueOf(properties.getProperty("browser.launch.type").toUpperCase()))
                .seleniumServerUrl(null == properties.getProperty("url") ? null :
                        new URL(properties.getProperty("url").toUpperCase()))
                .browserDownloadPath(properties.getProperty("browser.download.path"))
                .baseUrl(properties.getProperty("baseUrl"))
                .targetHostName(properties.getProperty("targetHostName"))
                .sshUser(properties.getProperty("sshUser"))
                .sshPassword(properties.getProperty("sshPassword"))
                .build();
    }
}
