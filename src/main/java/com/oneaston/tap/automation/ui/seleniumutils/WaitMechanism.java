package com.oneaston.tap.automation.ui.seleniumutils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitMechanism {
    final WebDriver driver;

    public WaitMechanism(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Wait for the page state and javascript resource to completely load
     */
    public void waitUntilPageLoadIsComplete() {
        try {
            waitUntilPageDocumentIsComplete();
        } catch (Exception ignored) {

        }
    }

    void waitUntilPageDocumentIsComplete() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        ExpectedCondition<Boolean> pageStateIsComplete = arg0 -> {
            String pageState = (String) js.executeScript("return document.readyState;");
            return pageState.equals("complete");
        };
        wait.until(pageStateIsComplete);
        ExpectedCondition<Boolean> pageJSIComplete = arg0 ->
                (Boolean) js.executeScript("return window.jQuery != undefined && jQuery.active == 0;");
        wait.until(pageJSIComplete);
    }

    public void waitUntilElementIsPresent(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(60)).
                until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitUntilElementIsInvisible(WebElement element) {
        try {
            waitFor(400);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            ExpectedCondition<Boolean> pageJSIComplete = isDisabled ->
                    (Boolean) element.isDisplayed();
            System.out.println(element.isDisplayed());
            wait.until(pageJSIComplete);
        } catch (Exception ignored) {
        }
    }

    public void waitFor(long milli) {
        try {
            Thread.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsVisible(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilElementIsRefreshed(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
    }
}
