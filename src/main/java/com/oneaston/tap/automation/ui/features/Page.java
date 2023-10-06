package com.oneaston.tap.automation.ui.features;

import com.oneaston.tap.automation.ui.features.login.LoginPage;
import com.oneaston.tap.automation.ui.seleniumutils.WebElementLocator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class Page {

    public final WebElementLocator webElementLocator;
    protected final WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver;
        webElementLocator = new WebElementLocator(driver);
    }

    public LoginPage openPage(String url) {
        driver.get(url);
        return new LoginPage(driver);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void switchToFrame(String locator) {
        driver.switchTo().frame(webElementLocator.findElement(locator));
    }

    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
    }

    public void switchToWindow(String windowTitle) {
        driver.switchTo().window(getWindow(windowTitle));
        driver.manage().window().maximize();
    }

    private String getWindow(String title) {
        Set<String> windowHandles = driver.getWindowHandles();
        String window = "";
        for (String windowHandle : windowHandles) {
            window = checkIfHandleIsEqualToTitle(windowHandle, title);
        }
        return window;
    }

    private String checkIfHandleIsEqualToTitle(String windowHandle, String title) {
        if (driver.switchTo().window(windowHandle).getTitle().contains(title)) {
            return windowHandle;
        } else {
            return "";
        }
    }

    public void acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

}
