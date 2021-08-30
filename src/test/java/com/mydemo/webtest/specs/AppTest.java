package com.mydemo.webtest.specs;

import com.microsoft.playwright.*;
import com.mydemo.webtest.browser.BrowserConfig;
import com.mydemo.webtest.browser.BrowserManager;
import com.mydemo.webtest.pages.LoginPage;
import com.mydemo.webtest.pages.SignUpPage;
import com.mydemo.webtest.testng.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.nio.file.Paths;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;


public class AppTest extends BaseTest {
    private static final Logger LOGGER = LogManager.getLogger();


    @Test
    public void anotherTestPropertyFile() throws Exception {
        LOGGER.info("Another test method logging test");
        assertThat(constants.getEnvName(), containsString("act"));

    }

    @Test
    public void demoPlaywright() throws Exception {
        Playwright playwright = Playwright.create();
        List<BrowserType> browserTypes = Arrays.asList(
//              playwright.chromium()
				playwright.webkit()
//				playwright.firefox()
        );
        for (BrowserType browserType : browserTypes) {
            Browser browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext context = browser.newContext(
                    new Browser.NewContextOptions().setViewportSize(1280, 720));
            Page page = context.newPage();
            page.navigate("https://google.com");
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot-" + browserType.name() + ".png")));
            browser.close();
        }
        playwright.close();
    }

    @Test
    public void samplePlaywrightTest() throws Exception {
        Playwright playwright = Playwright.create();
        BrowserType browserType = playwright.chromium();

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions();

//        contextOptions.setRecordHar().withPath(Paths.get("test.har"));
        contextOptions.setIgnoreHTTPSErrors(true);

//        options.withSlowMo(10000);
        options.setChromiumSandbox(false);
        options.setHeadless(false);

        Browser browser = browserType.launch(options);
        BrowserContext browserContext = browser.newContext(contextOptions);
        Page page = browserContext.newPage();

        page.route("**", route -> {
//            System.out.println("[URL] => [" + route.request().url() + "]");
//            System.out.println("[Request headers] => [" + route.request().headers() + "]");
//            System.out.println("[Request postDate] => [" + route.request().postData() + "]");
            LOGGER.info("[URL] => [{}]", route.request().url());
//            LOGGER.info("Request headers] => [{}]", route.request().headers());
//            LOGGER.info("Request postDate] => [{}]", route.request().postData());
            route.resume();
        });

        page.navigate("https://10.204.55.187:9443");




        page.fill("#login-input-email", "redowl@redowl.com");
        page.fill("#login-input-password", "redowl");
        page.click("text='Sign In'");

//        Deferred<Response> response = page.waitForResponse("**/*");
//        System.out.println("***URL*** => " + response.get().url());
//        LOGGER.info("***Response headers***] => [{}]", response.get().headers());
//        System.out.println(new String(response.get().body(), StandardCharsets.UTF_8));

        browserContext.close();
        browser.close();
        playwright.close();
    }

    static <K,V> Map<K, V> mapOf(Object... entries) {
        Map result = new HashMap();
        for (int i = 0; i + 1 < entries.length; i += 2) {
            result.put(entries[i], entries[i + 1]);
        }
        return result;
    }



    @Test
    public void testBrowserInit() throws Exception {
        BrowserConfig.localPage = new BrowserManager().initializePlaywright();
        BrowserConfig.localPage.navigate("https://google.com");
        BrowserConfig.localPage.navigate(constants.getEnvUrl());
//        Thread.sleep(5000);
        LoginPage loginPage = new LoginPage();
        loginPage.logIn("Katharina_Bernier", "s3cret");
    }

    @Test
    public void testSignUp() throws Exception {
        BrowserConfig.localPage = new BrowserManager().initializePlaywright();
        BrowserConfig.localPage.navigate(constants.getEnvUrl());
        LoginPage loginPage = new LoginPage();
        var signUpPage = loginPage.openSignUpPage();
        signUpPage.signUp("test-2", "qa", "test-2_qa", "secret", "secret");

    }

}
