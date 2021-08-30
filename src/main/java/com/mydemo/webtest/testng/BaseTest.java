package com.mydemo.webtest.testng;

import com.mydemo.webtest.listener.TestConfigListener;
import com.mydemo.webtest.listener.TestListener;
import com.mydemo.webtest.properties.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.lang.reflect.Method;

@Listeners({TestConfigListener.class, TestListener.class})
public class BaseTest {
    private static final Logger LOGGER = LogManager.getLogger();
    protected Constants constants;
    protected String testName;

    protected BaseTest() {
        this.constants = new Constants();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method, ITestContext iTestContext) throws Exception {
        this.testName = method.getName();
        LOGGER.debug("[Before Test Method] Environment Name [{}]", constants.getEnvName());
        LOGGER.debug("[Before Test Method] Environment Base URL [{}]", constants.getEnvUrl());
        LOGGER.debug("[Before Test Method] Browser Name [{}]", constants.getBrowserName());
        LOGGER.debug("[Before Test Method] Browser Headless [{}]", constants.getBrowserHeadlessMode());
    }
}
