package com.mydemo.webtest.browser;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.mydemo.webtest.properties.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BrowserManager {
    private static final Logger LOGGER = LogManager.getLogger(BrowserManager.class);
    private Constants constants = new Constants();

    public Page initializePlaywright() {

        //Initalize the browser
        BrowserInit browserInit = new BrowserInit();

        //Set the launch Options
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions.headless = constants.getBrowserHeadlessMode();
        launchOptions.devtools = constants.getDevToolsMode();

        //Get Browser
        var browser  = browserInit.getBrowser(constants.getBrowserName(), launchOptions);

        //Get browserContext
        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions();
        contextOptions.locale = constants.getDeviceLocale();

        var context = browserInit.getBrowserContext(browser, contextOptions);

        //Get Page
        LOGGER.debug("Browser is initialized");
        return browserInit.getPage(context);

    }
}
