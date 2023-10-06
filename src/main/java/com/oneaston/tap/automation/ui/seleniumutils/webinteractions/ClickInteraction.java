package com.oneaston.tap.automation.ui.seleniumutils.webinteractions;

import com.oneaston.tap.automation.ui.seleniumutils.WaitMechanism;
import com.oneaston.tap.automation.ui.seleniumutils.WebElementLocator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClickInteraction {

    final WebElementLocator webElementLocator;
    final WaitMechanism waitMechanism;

    public ClickInteraction(WebDriver driver) {
        webElementLocator = new WebElementLocator(driver);
        waitMechanism = new WaitMechanism(driver);
    }

    /**
     * Click element by providing locator as parameter
     *
     * @param locator String
     */
    public void clickElement(String locator) {
        waitMechanism.waitUntilElementIsPresent(webElementLocator.setLocatorType(locator));
        WebElement element = webElementLocator.findElement(locator);
        element.click();
    }

    /**
     * Click element by providing locator as parameter
     *
     * @param locator String
     */
    public void clickElementAfterRefreshed(String locator) {
        waitMechanism.waitUntilElementIsPresent(webElementLocator.setLocatorType(locator));
        WebElement element = webElementLocator.findElement(locator);
        waitMechanism.waitUntilElementIsRefreshed(element);
        element.click();
    }

}
