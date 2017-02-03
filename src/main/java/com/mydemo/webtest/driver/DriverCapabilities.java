package com.mydemo.webtest.driver;

import ru.qatools.properties.Property;
import ru.qatools.properties.Resource;


@Resource.Classpath("webtest.properties")
public interface DriverCapabilities {

//	private static final String FILE_SEPARATOR = File.separator;

//	@Property("driver.local")
//	private boolean driverLocal;

	@Property("environment.name")
	String getEnvName();
//	private String envName;

//	public boolean isDriverLocal() {
//		return driverLocal;
//	}

//	public String getEnvName() {
//		return envName;
//	}
}