package com.natalya.challenge.covenant.reporting;


import com.google.common.base.Strings;
import lombok.extern.log4j.Log4j2;
import org.testng.IConfigurationListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

@Log4j2
public class TestListener extends TestListenerAdapter implements ITestListener, IConfigurationListener {

    protected static final String METHOD_STARTED = "METHOD STARTED";
    protected static final String METHOD_SUCCESS = "METHOD SUCCESS";
    protected static final String METHOD_FAILED = "METHOD FAILED";
    protected static final String METHOD_SKIPPED = "METHOD SKIPPED";
    protected static final String CONFIGURATION_STARTED = "CONFIGURATION STARTED";
    protected static final String CONFIGURATION_SUCCESS = "CONFIGURATION SUCCESS";
    protected static final String CONFIGURATION_FAILED = "CONFIGURATION FAILED";
    protected static final String CONFIGURATION_SKIPPED = "CONFIGURATION SKIPPED";

    @Override
    public void beforeConfiguration(ITestResult iTestResult) {
        log.info(buildMessage(CONFIGURATION_STARTED, buildMethodName(iTestResult)));
    }

    @Override
    public void onConfigurationSuccess(ITestResult iTestResult) {
        log.info(buildMessage(CONFIGURATION_SUCCESS, buildMethodName(iTestResult)));
    }

    @Override
    public void onConfigurationFailure(ITestResult iTestResult) {
        log.info(buildMessage(CONFIGURATION_FAILED, buildMethodName(iTestResult)));
        processTestResult(iTestResult);
    }

    @Override
    public void onConfigurationSkip(ITestResult iTestResult) {
        log.info(buildMessage(CONFIGURATION_SKIPPED, buildMethodName(iTestResult)));
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info(buildMessage(METHOD_STARTED, buildMethodName(result)));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.info(buildMessage(METHOD_SUCCESS, buildMethodName(iTestResult)));
        processTestResult(iTestResult);
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.info(buildMessage(METHOD_FAILED, buildMethodName(iTestResult)));
        processTestResult(iTestResult);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.info(buildMessage(METHOD_SKIPPED, buildMethodName(iTestResult)));
        processTestResult(iTestResult);
    }
    protected String buildMessage(String prefix, String msg) {
        return String.format("%s: %s", prefix, msg);
    }
    protected String buildMethodName(ITestResult iTestResult) {
        StringBuilder builder = new StringBuilder();
        builder.append(iTestResult.getTestClass().getRealClass().getSimpleName());
        builder.append(".");
        builder.append(iTestResult.getMethod().getMethodName());
        String description = iTestResult.getMethod().getDescription();
        if (!Strings.isNullOrEmpty(description)) {
            builder.append(" - ");
            builder.append(description);
        }
        return builder.toString();
    }
    protected void processTestResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {

            if (result.getThrowable() != null) {
                log.debug(result.getThrowable().getMessage());
                result.getThrowable().printStackTrace();
            }
        }
    }
}

