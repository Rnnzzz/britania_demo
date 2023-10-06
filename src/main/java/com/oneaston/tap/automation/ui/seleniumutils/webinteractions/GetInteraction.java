package com.oneaston.tap.automation.ui.seleniumutils.webinteractions;

import com.oneaston.tap.automation.ui.seleniumutils.WaitMechanism;
import com.oneaston.tap.automation.ui.seleniumutils.WebElementLocator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class GetInteraction {

    final WebDriver driver;
    final WaitMechanism waitMechanism;
    final ClickInteraction clickInteraction;
    final WebElementLocator webElementLocator;

    public GetInteraction(WebDriver driver) {
        this.driver = driver;
        webElementLocator = new WebElementLocator(driver);
        waitMechanism = new WaitMechanism(driver);
        clickInteraction = new ClickInteraction(driver);
    }

    /**
     * Get element's attribute by providing element locator as parameter
     *
     * @param locator
     * @param attribute
     * @return
     */
    public String getElementAttribute(String locator, String attribute) {
//        try {
        WebElement element = webElementLocator.findElement(locator);
        return element.getAttribute(attribute);
//        } catch (NoSuchElementException noSuchElementException) {
//            AssertionUtil.reportFail(driver, logger, "Element not found " + noSuchElementException.getMessage());
//        } catch (Exception e) {
//            AssertionUtil.reportFail(driver, logger, "Error in getting element " + e.getMessage());
//        }
//        return null;
    }

    /**
     * Get element's attribute by providing web element as parameter
     *
     * @param element
     * @param attribute
     * @return
     */
    public String getElementAttribute(WebElement element, String attribute) {
//        try {
        return element.getAttribute(attribute);
//        } catch (NoSuchElementException noSuchElementException) {
//            AssertionUtil.reportFail(driver, logger, "Element not found " + noSuchElementException.getMessage());
//        } catch (Exception e) {
//            AssertionUtil.reportFail(driver, logger, "Error in getting element " + e.getMessage());
//        }
//        return null;
    }

    /**
     * Get element's visible text by providing locator as parameter
     *
     * @param locator
     * @return
     */
    public String getElementText(String locator) {
//        try {
        WebElement element = webElementLocator.findElement(locator);
        return element.getText();
//        } catch (NoSuchElementException noSuchElementException) {
//            AssertionUtil.reportFail(driver, logger, "Element not found " + noSuchElementException.getMessage());
//        } catch (Exception e) {
//            AssertionUtil.reportFail(driver, logger, "Error in getting element " + e.getMessage());
//        }
//        return null;
    }

    /**
     * Get element's visible text
     *
     * @param element
     * @return
     */
    public String getElementText(WebElement element) {
//        try {
        return element.getText();
//        } catch (NoSuchElementException noSuchElementException) {
//            AssertionUtil.reportFail(driver, logger, "Element not found " + noSuchElementException.getMessage());
//        } catch (Exception e) {
//            AssertionUtil.reportFail(driver, logger, "Error in getting element " + e.getMessage());
//        }
//        return null;
    }

    /**
     * Get all elements in select options by providing locator as parameter
     *
     * @param locator
     * @return
     */
    public List<WebElement> getAllOptionsInSelectElement(String locator) {
//        try {
        waitMechanism.waitFor(1000);
        Select select = new Select(webElementLocator.findElement(locator));
        return select.getOptions();
//        } catch (NoSuchElementException noSuchElementException) {
//            AssertionUtil.reportFail(driver, logger, "Element not found " + noSuchElementException.getMessage());
//        } catch (Exception e) {
//            AssertionUtil.reportFail(driver, logger, "Error in selecting element " + e.getMessage());
//        }
//        return null;
    }

    /**
     * Get all values in select options by providing locator as parameter
     *
     * @param locator
     * @return
     */
    public List<String> getAllValuesInSelectElement(String locator) {
//        try {
        clickInteraction.clickElement(locator);
        Select select = new Select(webElementLocator.findElement(locator));
        List<WebElement> elements = select.getOptions();
        List<String> values = getStrings(elements);
        return values;
//        } catch (NoSuchElementException noSuchElementException) {
//            AssertionUtil.reportFail(driver, logger, "Element not found " + noSuchElementException.getMessage());
//        } catch (Exception e) {
//            AssertionUtil.reportFail(driver, logger, "Error in selecting element " + e.getMessage());
//        }
//        return null;
    }

    private List<String> getStrings(List<WebElement> elements) {
        List<String> values = new ArrayList<>();
        for (WebElement element : elements) {
            values.add(element.getText());
        }
        return values;
    }

    public WebElement getFirstSelectedOptionsInSelectElement(String locator) {
//        try {
        Select select = new Select(webElementLocator.findElement(locator));
        return select.getFirstSelectedOption();
//        } catch (NoSuchElementException noSuchElementException) {
//            AssertionUtil.reportFail(driver, logger, "Element not found " + noSuchElementException.getMessage());
//        } catch (Exception e) {
//            AssertionUtil.reportFail(driver, logger, "Error in selecting element " + e.getMessage());
//        }
//        return null;
    }

}
