package com.mydemo.webtest.properties;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.Sources;

@LoadPolicy(Config.LoadType.MERGE)
@Sources({"system:properties", "classpath:DriverConfig.properties"})
public interface DriverConfig extends Config {

    @Key("browser.name")
    String browserName();

    @Key("headless")
    boolean browserHeadless();

    @Key("developer.tools")
    boolean devTools();

    @Key("device.emulation.type")
    String deviceType();

    @Key("locale")
    String deviceLocale();
}
