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
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.Map;

public class CashAccountStepDefinitions {

    Map<String, String> testData;
    Scenario scenario;
    String arrangementId = "";
    CurrentAccountPage currentAccountPage;
    LoginPage loginPage;

    @When("user create new cash account {string}")
    public void userCreateNewCashAccount(String currency) {
        currentAccountPage = new CurrentAccountPage(WebDriverFactory.getDriver());
        testData = GetDataFromExcelFile.getData("currency", currency, "src/test/resources/testdata/features/account/account_creation.xlsx");
        currentAccountPage.createAccount(testData);
    }

    @Then("account arrangement should be created")
    public void accountArrangementShouldBeCreated() {
        try {
            arrangementId = currentAccountPage.validateAccount();
            scenario.log("Created arrangement ID: " + arrangementId);
            currentAccountPage.closeWindow("AA ARRANGEMENT ACTIVITY");
            currentAccountPage.switchToWindow("T24");
            DashboardPage dashboardPage = new DashboardPage(WebDriverFactory.getDriver());
            loginPage = dashboardPage.clickLogout();
        } catch (Exception e) {
            AssertionUtility.reportFail("Error in creating account | " + e.getMessage());
        }
    }

    @When("admin user authorize the record")
    public void adminUserAuthorizeTheRecord() {
        loginPage.openPage(System.getProperty("url")).login("CATRINA.AUT", "123456").clickUserMenu().clickRetailOperations().clickFindAccount()
                .clickUnauthorisedTab().selectArangementOverview(arrangementId).clickCheckButton().clickAuthorizeDeal();
    }

    @Then("account status should be {string}")
    public void accountStatusShouldBe(String expectedStatus) {
        FindAccountPage findAccountPage = new FindAccountPage(WebDriverFactory.getDriver());
        String status = findAccountPage.clickAuthorisedTab().getArrangementStatus(arrangementId);
        AssertionUtility.assertTrue(status.equalsIgnoreCase(expectedStatus), "Arrangement status is not yet " + expectedStatus);
    }

    @Then("user should see an error message: {string}")
    public void userShouldSeeAnErrorMessage(String expectedError) {
        String actualError = currentAccountPage.getInteraction.getElementText("//table[@id='errors']/descendant::span[contains(text(), '" + expectedError + "')]");
        AssertionUtility.assertTextEquals(actualError, expectedError);
    }

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

    @When("user create cash account {string}")
    public void userCreateCashAccount(String currency) {
        currentAccountPage = new CurrentAccountPage(WebDriverFactory.getDriver());
        testData = GetDataFromExcelFile.getData("currency", currency, "src/test/resources/testdata/features/account/account_creation.xlsx");
        currentAccountPage.createAccount(testData);
    }

    @When("user create cash account with {string} using client {string} as {string}")
    public void userCreateCashAccountWithUsingClientAs(String currency, String customerID, String customerRole) {
        try {
            currentAccountPage = new CurrentAccountPage(WebDriverFactory.getDriver());
            testData = createCustomerTestData(currency, customerID, customerRole);
            currentAccountPage.createAccount(testData);
        } catch (Exception e) {
            AssertionUtility.reportFail("Error in creating account | " + e.getMessage());
        }
    }

    private Map<String, String> createCustomerTestData(String currency, String customerID, String customerRole) {
        Map<String, String> testdata = new HashMap<>();
        testdata.put("currency", currency);
        testdata.put("customerID", customerID);
        testdata.put("customerRole", customerRole);
        return testdata;
    }
}
