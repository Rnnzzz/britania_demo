package com.oneaston.tap.automation.ui.features.dashboard;

import com.oneaston.tap.automation.ui.features.Page;
import com.oneaston.tap.automation.ui.seleniumutils.WaitMechanism;
import com.oneaston.tap.automation.ui.seleniumutils.webinteractions.ClickInteraction;
import com.oneaston.tap.automation.ui.seleniumutils.webinteractions.GetInteraction;
import com.oneaston.tap.automation.ui.seleniumutils.webinteractions.SelectInteraction;
import com.oneaston.tap.automation.ui.seleniumutils.webinteractions.TypeInteraction;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class CurrentAccountPage extends Page {

    TypeInteraction typeInteraction;
    ClickInteraction clickInteraction;
    WaitMechanism waitMechanism;
    SelectInteraction selectInteraction;
    GetInteraction getInteraction;

    public CurrentAccountPage(WebDriver driver) {
        super(driver);
        typeInteraction = new TypeInteraction(driver);
        waitMechanism = new WaitMechanism(driver);
        clickInteraction = new ClickInteraction(driver);
        selectInteraction = new SelectInteraction(driver);
        getInteraction = new GetInteraction(driver);
    }

    public String createAccount(Map<String, String> data) {
        switchToWindow("AA ARRANGEMENT ACTIVITY");
        return populateFields(data);
    }

    private String populateFields(Map<String, String> data) {
        typeInteraction.type("fieldName:CUSTOMER:1", data.get("customerID"));
        typeInteraction.type("fieldName:CURRENCY", data.get("currency"));
        selectRole(data.get("customerRole"));
        typeInteraction.type("fieldName:REASON", data.get("reason"));
        clickInteraction.clickElement("//a[@title='Validate a deal']");
        waitMechanism.waitUntilElementIsPresent(webElementLocator.setLocatorType("disabled_ARRANGEMENT"));
        String arrangementID = getInteraction.getElementText("//span[@id='disabled_ARRANGEMENT']");
        clickInteraction.clickElement("//a[@title='Commit the deal']");
        waitMechanism.waitUntilElementIsPresent(webElementLocator.setLocatorType("disabled_ARRANGEMENT"));
        selectInteraction.selectElementByValue("//select[contains(@id, 'warningChooser')]", "RECEIVED");
        clickInteraction.clickElement("//a[text()='Accept Overrides']");
        waitMechanism.waitUntilElementIsPresent(webElementLocator.setLocatorType("//td[contains(text(), 'Txn Complete')]"));
        return arrangementID;
    }

    private void selectRole(String targetRole) {
        clickInteraction.clickElement("//img[@dropfield='fieldName:CUSTOMER.ROLE:1']/parent::a[@title='Dropdown List']");
        clickInteraction.clickElement("//tr[contains(@id, 'dropDownRow')]/descendant::b[text()='" + targetRole.toUpperCase().replace(" ", ".") + "']");
    }


}
