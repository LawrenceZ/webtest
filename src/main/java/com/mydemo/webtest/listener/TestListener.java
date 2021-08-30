package com.mydemo.webtest.listener;

import com.microsoft.playwright.Page;
import com.mydemo.webtest.browser.BrowserConfig;
import com.mydemo.webtest.utils.jodatime.JDate;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class TestListener extends TestListenerAdapter {

    private static final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
    private static final Logger LOGGER = LogManager.getLogger();

    static {
        System.setProperty(ESCAPE_PROPERTY, "false");
    }

    private final String slash = File.separator;

    @Override
    public void onTestStart(ITestResult result) {
        super.onTestStart(result);
        String testClass = result.getTestClass().getName().replaceAll(".+\\.", "");
        String testName = result.getMethod().getMethodName();
        LOGGER.info("[TEST START] [{}]->[{}]", testClass, testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        super.onTestSuccess(result);
        String testClass = result.getTestClass().getName().replaceAll(".+\\.", "");
        String testName = result.getMethod().getMethodName();
        LOGGER.info("[TEST PASS] [{}]->[{}]", testClass, testName);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        super.onTestFailure(result);
        String testClass = result.getInstanceName().replaceAll(".+\\.", "");
        String testName = result.getMethod().getMethodName();
        //Timestamp the screen shot
        String timeStamp = JDate.getDateWithTime("MM-dd-yy-HH-mm-ss");
        String fileName = testName + "_" + timeStamp + "_TestFAILED";
        File destination = getUniqueFileName(fileName);
        Page page = BrowserConfig.localPage;
        page.screenshot(new Page.ScreenshotOptions().setPath(destination.toPath()).setFullPage(false));
        LOGGER.error("[TEST FAIL] [{}]->[{}]", testClass, testName);
    }


    private File getUniqueFileName(String possibleFileName) {
        String root = System.getProperty("user.dir");
        String baseDir = root + slash + "target" + slash + "surefire-reports" + slash + "html" + slash;
        File tmpFile = new File(baseDir + possibleFileName + ".png");
        int i = 1;
        while (tmpFile.exists()) {
            tmpFile = new File(baseDir + possibleFileName + i + ".png");
            i++;
        }
        return tmpFile;
    }

    private void copyScreenToDestinationLocation(File tmpFile, File destination) {
        try {
            FileUtils.copyFile(tmpFile, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setReportInfo(String testName, File pathToScreen, String typeOfFail) {
        Reporter.log("<table class=\"tg\"><tbody>");
        Reporter.log("<td style=\"text-align:center\"> " + typeOfFail + ": <span style=\"font-weight:bold\">" + testName + "</span><br>is failed (click on the image below for reference)<br><br><a target=\"_blank\" href=\"" + pathToScreen.getName() + "\">");
        Reporter.log("<img width=\"100\" height=\"100\" src=\"" + pathToScreen.getName() + "\" alt=\"screenshot at " + pathToScreen.getAbsolutePath() + "\"/></a></td></tbody></table>");
    }
}
