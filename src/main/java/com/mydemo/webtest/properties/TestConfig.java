package com.mydemo.webtest.properties;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.Sources;

@LoadPolicy(Config.LoadType.MERGE)
@Sources({"system:properties", "classpath:TestConfig.properties"})
public interface TestConfig extends Config {


    @Key("env.name")
    String envName();

    @Key("env.url")
    String envUrl();
}