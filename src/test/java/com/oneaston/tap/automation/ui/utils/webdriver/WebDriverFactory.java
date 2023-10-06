package com.oneaston.tap.automation.ui.utils.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {

    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void setUpDriver() {
        WebDriver driverConfig;
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-size=1920x800", "--ignore-ssl-errors=yes",
                "--ignore-certificate-errors", "--disable-dev-shm-usage", "--remote-allow-origins=*");
        driverConfig = new ChromeDriver(options);
        driverConfig.manage().window().maximize();
        driver.set(driverConfig);
    }

    public static void tearDown() {
        if (getDriver() != null) {
            getDriver().close();
            getDriver().quit();
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }
}
