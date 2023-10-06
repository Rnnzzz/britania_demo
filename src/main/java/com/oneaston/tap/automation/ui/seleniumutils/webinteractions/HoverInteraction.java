package com.oneaston.tap.automation.ui.seleniumutils.webinteractions;

import com.oneaston.tap.automation.ui.seleniumutils.WaitMechanism;
import com.oneaston.tap.automation.ui.seleniumutils.WebElementLocator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HoverInteraction {

    final WebElementLocator webElementLocator;
    final WaitMechanism waitMechanism;
    final Actions actions;

    public HoverInteraction(WebDriver driver) {
        webElementLocator = new WebElementLocator(driver);
        waitMechanism = new WaitMechanism(driver);
        actions = new Actions(driver);
    }

    public HoverInteraction hoverToElement(String locator) {
        actions.moveToElement(webElementLocator.findElement(locator));
        return this;
    }

}
