package com.oneaston.tap.automation.ui.features.dashboard;

import com.oneaston.tap.automation.ui.features.Page;
import com.oneaston.tap.automation.ui.seleniumutils.WaitMechanism;
import com.oneaston.tap.automation.ui.seleniumutils.webinteractions.ClickInteraction;
import com.oneaston.tap.automation.ui.seleniumutils.webinteractions.TypeInteraction;
import org.openqa.selenium.WebDriver;

public class ArrangementOverviewPage extends Page {
    TypeInteraction typeInteraction;
    ClickInteraction clickInteraction;
    WaitMechanism waitMechanism;

    public ArrangementOverviewPage(WebDriver driver) {
        super(driver);
        typeInteraction = new TypeInteraction(driver);
        waitMechanism = new WaitMechanism(driver);
        clickInteraction = new ClickInteraction(driver);
        switchToWindow("Arrangement Overview (Accounts)");
    }

    public ArrangementActivityPage clickCheckButton() {
        waitMechanism.waitUntilElementIsPresent(webElementLocator.setLocatorType("//a[@title='Select Drilldown']"));
        clickInteraction.clickElement("//a[@title='Select Drilldown']");
        return new ArrangementActivityPage(driver);
    }
}
