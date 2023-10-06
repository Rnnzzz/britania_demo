package com.oneaston.tap.automation.ui.stepdefinitions;

import com.oneaston.tap.automation.ui.features.dashboard.CurrentAccountPage;
import com.oneaston.tap.automation.ui.features.dashboard.DashboardPage;
import com.oneaston.tap.automation.ui.features.dashboard.FindAccountPage;
import com.oneaston.tap.automation.ui.features.login.LoginPage;
import com.oneaston.tap.automation.ui.utils.assertions.AssertionUtility;
import com.oneaston.tap.automation.ui.utils.excelfileutils.GetDataFromExcelFile;
import com.oneaston.tap.automation.ui.utils.webdriver.WebDriverFactory;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;

public class CashAccountStepDefinitions {

    Map<String, String> testData;
    Scenario scenario;
    String arrangementId = "";
    CurrentAccountPage currentAccountPage;
    LoginPage loginPage;

    @Given("user create new cash account {string}")
    public void userCreateNewCashAccount(String currency) {
        currentAccountPage = new CurrentAccountPage(WebDriverFactory.getDriver());
        testData = GetDataFromExcelFile.getData("currency", currency, "src/test/resources/testdata/features/account/account_creation.xlsx");
        arrangementId = currentAccountPage.createAccount(testData);
    }

    @Then("account arrangement should be created")
    public void accountArrangementShouldBeCreated() {
        scenario.log("Created arrangement ID: " + arrangementId);
        currentAccountPage.switchToWindow("T24");
        DashboardPage dashboardPage = new DashboardPage(WebDriverFactory.getDriver());
        loginPage = dashboardPage.clickLogout();
    }

    @When("admin user authorize the record")
    public void adminUserAuthorizeTheRecord() {
        loginPage.openPage(System.getProperty("url")).login("CATRINA.AUT", "123456").clickUserMenu().clickRetailOperations().clickFindAccount()
                .clickUnauthorisedTab().selectArangementOverview(arrangementId).clickCheckButton().clickAuthorizeDeal();
    }

    @Then("account status should be {string}")
    public void accountStatusShouldBe(String expectedStatus) {
        FindAccountPage findAccountPage = new FindAccountPage(WebDriverFactory.getDriver());
        String status = findAccountPage.clickAuthorisedTab().clickSearchIcon().getArrangementStatus(arrangementId);
        AssertionUtility.assertTrue(status.equalsIgnoreCase(expectedStatus), "Arrangement status is not yet " + expectedStatus);

    }


    @When("user create new cash account with no customerID and currency")
    public void userCreateNewCashAccountWithNoCustomerIDAndCurrency() {

    }

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

}
