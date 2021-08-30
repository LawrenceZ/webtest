package com.mydemo.webtest.properties;

import org.aeonbits.owner.ConfigFactory;

public class Constants {
    TestConfig testConfig = ConfigFactory.create(TestConfig.class);
    DriverConfig driverConfig = ConfigFactory.create(DriverConfig.class);

    // Playwright related config
    private String browserName = driverConfig.browserName();
    private boolean browserHeadlessMode = driverConfig.browserHeadless();
    private boolean devToolsMode = driverConfig.devTools();
    private String deviceType = driverConfig.deviceType();
    private String deviceLocale = driverConfig.deviceLocale();

    // test related config
    private String envName = testConfig.envName();
    private String envUrl = testConfig.envUrl();


    public boolean getDevToolsMode() {
        return devToolsMode;
    }

    public void setDevToolsMode(boolean devToolsMode) {
        this.devToolsMode = devToolsMode;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceLocale() {
        return deviceLocale;
    }

    public void setDeviceLocale(String deviceLocale) {
        this.deviceLocale = deviceLocale;
    }

    public String getEnvName() {
        return envName;
    }

    public void setEnvName(String envName) {
        this.envName = envName;
    }

    public String getEnvUrl() {
        return envUrl;
    }

    public void setEnvUrl(String envUrl) {
        this.envUrl = envUrl;
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public boolean getBrowserHeadlessMode() {
        return browserHeadlessMode;
    }

    public void setBrowserHeadlessMode(Boolean isBrowserHeadless) {
        this.browserHeadlessMode = isBrowserHeadless;
    }
}
