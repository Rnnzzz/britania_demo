package com.oneaston.tap.automation.ui.hooks;

import com.oneaston.tap.automation.ui.utils.webdriver.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Hooks {

    @Before
    public static void setUp() {
        {
            System.setProperty("url", "http://172.31.26.190:8080/BrowserWeb/servlet/BrowserServlet");
        }
        WebDriverFactory.setUpDriver();
    }

    @After
    public static void tearDown() {
        WebDriverFactory.tearDown();
    }

    @AfterStep
    public static void captureEvidence(Scenario scenario) {
        takeScreenshot(scenario);
    }

    private static void takeScreenshot(Scenario scenario) {
        final byte[] screenshot = ((TakesScreenshot) WebDriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", scenario.getName().toLowerCase().replace(" ", "_") + "_" + getTimeStamp());
    }

    public static String getTimeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("hhmmss");
        return (sdf.format(new Date()));
    }

}
