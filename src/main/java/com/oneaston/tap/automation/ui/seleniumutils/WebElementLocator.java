package com.oneaston.tap.automation.ui.seleniumutils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebElementLocator {

    private final WebDriver driver;
    private final WaitMechanism waitMechanism;

    public WebElementLocator(WebDriver driver) {
        this.driver = driver;
        waitMechanism = new WaitMechanism(driver);
    }

    public WebElement findElement(String locator) {
        waitMechanism.waitUntilElementIsPresent(setLocatorType(locator));
        WebElement element = driver.findElement(setLocatorType(locator));
        waitMechanism.waitUntilElementIsVisible(element);
        scrollIntoView(element);
        return element;
    }


    public WebElement findElement(WebElement element, String locator) {
//        try {
        scrollIntoView(element);
        return element.findElement(By.xpath(locator));
//        } catch (NoSuchElementException noSuchElementException) {
//            AssertionUtil.reportFail(driver, logger, "Unable to find element with locator - " + locator);
//        } catch (Exception e) {
//            AssertionUtil.reportFail(driver, logger, "Error in finding element - " + e.getMessage());
//        }
//        return null;
    }

    public List<WebElement> findElements(String locator) {
        List<WebElement> elements = driver.findElements(setLocatorType(locator));
        scrollIntoView(elements.get(0));
        return elements;
    }

    public By setLocatorType(String locator) {
        if (locator.startsWith("//")) {
            return By.xpath(locator);
        } else {
            return By.id(locator);
        }
    }

    private void scrollIntoView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoViewIfNeeded();", element);
    }

}
