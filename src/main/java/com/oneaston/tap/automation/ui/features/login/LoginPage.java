package com.oneaston.tap.automation.ui.features.login;

import com.oneaston.tap.automation.ui.features.Page;
import com.oneaston.tap.automation.ui.features.dashboard.DashboardPage;
import com.oneaston.tap.automation.ui.seleniumutils.WaitMechanism;
import com.oneaston.tap.automation.ui.seleniumutils.WebElementLocator;
import com.oneaston.tap.automation.ui.seleniumutils.webinteractions.ClickInteraction;
import com.oneaston.tap.automation.ui.seleniumutils.webinteractions.TypeInteraction;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class LoginPage extends Page {

    final TypeInteraction typeInteraction;
    final ClickInteraction clickInteraction;
    final WaitMechanism waitMechanism;
    final WebElementLocator webElementLocator;

    public LoginPage(WebDriver driver) {
        super(driver);
        typeInteraction = new TypeInteraction(driver);
        clickInteraction = new ClickInteraction(driver);
        waitMechanism = new WaitMechanism(driver);
        webElementLocator = new WebElementLocator(driver);
    }

    public DashboardPage login(String usercode, String password) {
        typeInteraction.type("signOnName", usercode);
        typeInteraction.type("password", password);
        clickInteraction.clickElement("sign-in");
        return new DashboardPage(driver);
    }

    private void checkIfSessionExist() {
        try {
            if (webElementLocator.findElement("//b[contains(text(),'Opening a new session')]").isDisplayed()) {
                clickInteraction.clickElement("//button[text()='Confirm']");
            }
        } catch (NoSuchElementException ignored) {
        }
    }

    public String getErrorMessage() {
        waitMechanism.waitUntilPageLoadIsComplete();
        return webElementLocator.findElement("C1__HEAD_9EBD85267107025A128570").getText();
    }

}
