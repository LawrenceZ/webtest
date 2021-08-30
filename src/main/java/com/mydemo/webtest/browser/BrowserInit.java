package com.mydemo.webtest.browser;

import com.microsoft.playwright.*;

public class BrowserInit {

    public Browser getBrowser(String browserName, BrowserType.LaunchOptions launchOptions) {
        BrowserConfig.playwright = Playwright.create();
        BrowserType browserType;
        switch (browserName.toLowerCase()) {
            case "chrome":
                browserType = BrowserConfig.playwright.chromium();
                break;
            case "firefox":
                browserType = BrowserConfig.playwright.firefox();
                break;
            case "webkit":
                browserType = BrowserConfig.playwright.webkit();
                break;
            default:
                throw new IllegalStateException("Cannot find appropriate browser type for " + browserName);
        }
        return browserType.launch(launchOptions);
    }

    public BrowserContext getBrowserContext(Browser browser, Browser.NewContextOptions newContextOptions) {
        return browser.newContext(newContextOptions);
    }

    public Page getPage(BrowserContext browserContext) {
        return browserContext.newPage();
    }
}
