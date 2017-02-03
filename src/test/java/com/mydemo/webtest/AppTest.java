package com.mydemo.webtest;

import com.mydemo.webtest.driver.DriverCapabilities;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import ru.qatools.properties.PropertyLoader;

public class AppTest {

	@Test
	public void simpleTest() throws Exception {
		DriverCapabilities config = PropertyLoader.newInstance().populate(DriverCapabilities.class);

		String env = config.getEnvName();
		System.out.println(env);
		MatcherAssert.assertThat("actual tesst", Matchers.containsString("tesst"));
	}
}
