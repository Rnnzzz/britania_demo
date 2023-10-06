package com.oneaston.tap.automation.ui.features.dashboard;

import com.oneaston.tap.automation.ui.features.Page;
import com.oneaston.tap.automation.ui.seleniumutils.WaitMechanism;
import com.oneaston.tap.automation.ui.seleniumutils.webinteractions.ClickInteraction;
import com.oneaston.tap.automation.ui.seleniumutils.webinteractions.GetInteraction;
import com.oneaston.tap.automation.ui.seleniumutils.webinteractions.TypeInteraction;
import org.openqa.selenium.WebDriver;

public class FindAccountPage extends Page {
    TypeInteraction typeInteraction;
    ClickInteraction clickInteraction;
    GetInteraction getInteraction;
    WaitMechanism waitMechanism;

    public FindAccountPage(WebDriver driver) {
        super(driver);
        typeInteraction = new TypeInteraction(driver);
        waitMechanism = new WaitMechanism(driver);
        clickInteraction = new ClickInteraction(driver);
        getInteraction = new GetInteraction(driver);
        switchToWindow("AA Arrangement");
    }

    public FindAccountPage clickUnauthorisedTab() {
        clickInteraction.clickElement("//span[text()='Unauthorised']");
        return this;
    }

    public FindAccountPage clickAuthorisedTab() {
        switchToWindow("AA Arrangement");
        clickInteraction.clickElement("//span[text()='Authorised']");
        return this;
    }

    public FindAccountPage clickSearchIcon() {
        switchToWindow("AA Arrangement");
        clickInteraction.clickElement("//a[@title='Selection Screen']");
        return this;
    }

    public String getArrangementStatus(String arrangementID) {
        switchToWindow("AA Arrangement");
        typeInteraction.type("//label[text()='Arrangement']/ancestor::td/following-sibling::td/input[@type='text']", arrangementID);
        clickInteraction.clickElement("//a[@title='Run Selection']");
        waitMechanism.waitUntilElementIsPresent(webElementLocator.setLocatorType("//td[text()='" + arrangementID + "']"));
        return getInteraction.getElementText("//td[text()='" + arrangementID+ "']/following-sibling::td[5]");
    }

    public ArrangementOverviewPage selectArangementOverview(String arrangementID) {
        typeInteraction.type("//label[text()='Arrangement']/ancestor::td/following-sibling::td/input[@type='text']", arrangementID);
        clickInteraction.clickElement("//a[@title='Run Selection']");
        waitMechanism.waitUntilElementIsPresent(webElementLocator.setLocatorType("//td[text()='" + arrangementID + "']"));
        clickInteraction.clickElement("//td[text()='" + arrangementID + "']/following-sibling::td/a[@title='Overview']");
        return new ArrangementOverviewPage(driver);
    }

}
