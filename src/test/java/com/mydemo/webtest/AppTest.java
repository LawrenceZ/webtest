package com.mydemo.webtest;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class AppTest {

	@Test
	public void simpleTest() throws Exception {
		MatcherAssert.assertThat("actual tesst", Matchers.containsString("tesst"));
	}
}
