package com.oneaston.tap.automation.ui.features.dashboard;

import com.oneaston.tap.automation.ui.features.Page;
import com.oneaston.tap.automation.ui.features.login.LoginPage;
import com.oneaston.tap.automation.ui.seleniumutils.WaitMechanism;
import com.oneaston.tap.automation.ui.seleniumutils.webinteractions.ClickInteraction;
import com.oneaston.tap.automation.ui.seleniumutils.webinteractions.TypeInteraction;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends Page {

    TypeInteraction typeInteraction;
    ClickInteraction clickInteraction;
    WaitMechanism waitMechanism;

    public DashboardPage(WebDriver driver) {
        super(driver);
        typeInteraction = new TypeInteraction(driver);
        waitMechanism = new WaitMechanism(driver);
        clickInteraction = new ClickInteraction(driver);
    }

    public void search(String text) {
        switchToFrame("//frame[contains(@id, 'banner')]");
        typeInteraction.type("commandValue", text + Keys.RETURN);
        waitMechanism.waitUntilPageLoadIsComplete();
        switchToWindow(text);
    }

    public DashboardPage clickUserMenu() {
        switchToFrame("//frame[contains(@id, 'menu')]");
        clickInteraction.clickElement("//span[text()='User Menu']");
        return this;
    }

    public DashboardPage clickRetailOperations() {
        clickInteraction.clickElement("//span[text()='User Menu']/following-sibling::ul/descendant::span[text()='Retail Operations']");
        return this;
    }

    public ProductCatalogPage clickProductCatalog() {
        clickInteraction.clickElement("//span[text()='Retail Operations']/following-sibling::ul/descendant::a[text()='Product Catalog ']");
        return new ProductCatalogPage(driver);
    }


    public LoginPage clickLogout() {
        switchToFrame("//frame[contains(@id, 'banner')]");
        clickInteraction.clickElement("//a[@title='Sign off']");
        return new LoginPage(driver);
    }

    public FindAccountPage clickFindAccount() {
        clickInteraction.clickElement("//span[text()='Retail Operations']/following-sibling::ul/descendant::a[text()='Find Account ' and contains(@onclick, 'COS AA.FIND.ARRANGEMENT.AR')]");
        return new FindAccountPage(driver);
    }
}
