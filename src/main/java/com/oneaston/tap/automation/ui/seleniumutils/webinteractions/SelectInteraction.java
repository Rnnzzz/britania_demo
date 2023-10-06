package com.oneaston.tap.automation.ui.seleniumutils.webinteractions;

import com.oneaston.tap.automation.ui.seleniumutils.WaitMechanism;
import com.oneaston.tap.automation.ui.seleniumutils.WebElementLocator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectInteraction {
    final WaitMechanism waitMechanism;
    final WebElementLocator webElementLocator;
    WebDriver driver;

    public SelectInteraction(WebDriver driver) {
        this.driver = driver;
        webElementLocator = new WebElementLocator(driver);
        waitMechanism = new WaitMechanism(driver);
    }

    /**
     * Select value in element list by providing locator and value
     *
     * @param locator
     * @param value
     */
    public void selectElementByValue(String locator, String value) {
//        try {
        waitMechanism.waitUntilPageLoadIsComplete();
        Select select = new Select(webElementLocator.findElement(locator));
        select.selectByValue(value);
//        } catch (NoSuchElementException noSuchElementException) {
//            AssertionUtil.reportFail(driver, logger, "Element not found " + noSuchElementException.getMessage());
//        } catch (Exception e) {
//            AssertionUtil.reportFail(driver, logger, "Error in selecting element " + e.getMessage());
//        }
    }

    /**
     * Select value in element list by providing locator and visible text
     *
     * @param locator
     * @param text
     */
    public void selectElementByText(String locator, String text) {
//        try {
        waitMechanism.waitUntilPageLoadIsComplete();
        Select select = new Select(webElementLocator.findElement(locator));
        select.selectByVisibleText(text);
//        } catch (NoSuchElementException noSuchElementException) {
//            AssertionUtil.reportFail(driver, logger, "Element not found " + noSuchElementException.getMessage());
//        } catch (Exception e) {
//            AssertionUtil.reportFail(driver, logger, "Error in selecting element " + e.getMessage());
//        }
    }

    /**
     * Select value in element list by providing visible text
     *
     * @param element
     * @param text
     */
    public void selectElementByText(WebElement element, String text) {
//        try {
        waitMechanism.waitUntilPageLoadIsComplete();
        Select select = new Select(element);
        select.selectByVisibleText(text);
//            AssertionUtil.reportInfo(logger, "Selected: " + text);
//        } catch (NoSuchElementException noSuchElementException) {
//            AssertionUtil.reportFail(driver, logger, "Element not found " + noSuchElementException.getMessage());
//        } catch (Exception e) {
//            AssertionUtil.reportFail(driver, logger, "Error in selecting element " + e.getMessage());
//        }
    }

    /**
     * Select value in element list by providing locator and index value as parameters
     *
     * @param locator
     * @param value
     */
    public void selectElementByIndex(String locator, int value) {
//        try {
        waitMechanism.waitUntilPageLoadIsComplete();
        Select select = new Select(webElementLocator.findElement(locator));
        select.selectByIndex(value);
//        } catch (NoSuchElementException noSuchElementException) {
//            AssertionUtil.reportFail(driver, logger, "Element not found " + noSuchElementException.getMessage());
//        } catch (Exception e) {
//            AssertionUtil.reportFail(driver, logger, "Error in selecting element " + e.getMessage());
//        }
    }
}
