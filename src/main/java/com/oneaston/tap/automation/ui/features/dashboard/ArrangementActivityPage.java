package com.oneaston.tap.automation.ui.features.dashboard;

import com.oneaston.tap.automation.ui.features.Page;
import com.oneaston.tap.automation.ui.seleniumutils.WaitMechanism;
import com.oneaston.tap.automation.ui.seleniumutils.webinteractions.ClickInteraction;
import com.oneaston.tap.automation.ui.seleniumutils.webinteractions.TypeInteraction;
import org.openqa.selenium.WebDriver;

public class ArrangementActivityPage extends Page {
    TypeInteraction typeInteraction;
    ClickInteraction clickInteraction;
    WaitMechanism waitMechanism;

    public ArrangementActivityPage(WebDriver driver) {
        super(driver);
        typeInteraction = new TypeInteraction(driver);
        waitMechanism = new WaitMechanism(driver);
        clickInteraction = new ClickInteraction(driver);
        switchToWindow("AA ARRANGEMENT ACTIVITY");
    }

    public ArrangementActivityPage clickAuthorizeDeal() {
        switchToWindow("AA ARRANGEMENT ACTIVITY");
        waitMechanism.waitUntilElementIsPresent(webElementLocator.setLocatorType("//a[@title='Arrangement']"));
        clickInteraction.clickElement("//a[@title='Authorises a deal']");
        return this;
    }
}
