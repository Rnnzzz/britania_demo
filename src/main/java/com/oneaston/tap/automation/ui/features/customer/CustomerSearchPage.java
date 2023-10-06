package com.oneaston.tap.automation.ui.features.customer;

import com.oneaston.tap.automation.ui.features.Page;
import com.oneaston.tap.automation.ui.seleniumutils.webinteractions.ClickInteraction;
import org.openqa.selenium.WebDriver;

public class CustomerSearchPage extends Page {

    ClickInteraction clickInteraction;
    public CustomerSearchPage(WebDriver driver) {
        super(driver);
        clickInteraction = new ClickInteraction(driver);
    }

    public CustomerCreationPage clickCreateNewDeal() {
        clickInteraction.clickElement("//a[@title='New Deal']");
        return new CustomerCreationPage(driver);
    }

}
