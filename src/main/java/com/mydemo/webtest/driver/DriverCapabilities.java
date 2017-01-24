package com.mydemo.webtest.driver;

import ru.yandex.qatools.properties.annotations.Resource;

import java.io.File;

@Resource.Classpath("webtest.properties")
public class DriverCapabilities {

	private static final String slash = File.separator;
}