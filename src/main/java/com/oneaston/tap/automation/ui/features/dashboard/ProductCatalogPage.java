package com.oneaston.tap.automation.ui.features.dashboard;

import com.oneaston.tap.automation.ui.features.Page;
import com.oneaston.tap.automation.ui.seleniumutils.WaitMechanism;
import com.oneaston.tap.automation.ui.seleniumutils.webinteractions.ClickInteraction;
import com.oneaston.tap.automation.ui.seleniumutils.webinteractions.TypeInteraction;
import org.openqa.selenium.WebDriver;

public class ProductCatalogPage extends Page {
    TypeInteraction typeInteraction;
    ClickInteraction clickInteraction;
    WaitMechanism waitMechanism;

    public ProductCatalogPage(WebDriver driver) {
        super(driver);
        typeInteraction = new TypeInteraction(driver);
        waitMechanism = new WaitMechanism(driver);
        clickInteraction = new ClickInteraction(driver);
        switchToWindow("AA Product Catalog");
    }

    public ProductCatalogPage clickCurrentAccounts() {
        clickInteraction.clickElement("//tr[@originalclass='colour0']/descendant::td[text()='Current Accounts']/a");
        return this;
    }

    public CurrentAccountPage openCurrentAccountForm() {
        clickInteraction.clickElement("//td[text()='Current Account']//following-sibling::td/a[@title='New Arrangement']");
        return new CurrentAccountPage(driver);
    }


}
