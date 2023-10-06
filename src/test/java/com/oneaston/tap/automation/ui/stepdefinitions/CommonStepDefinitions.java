package com.oneaston.tap.automation.ui.stepdefinitions;

import com.oneaston.tap.automation.ui.features.Page;
import com.oneaston.tap.automation.ui.features.dashboard.DashboardPage;
import com.oneaston.tap.automation.ui.utils.dataprovider.TestProperties;
import com.oneaston.tap.automation.ui.utils.webdriver.WebDriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CommonStepDefinitions {

    protected TestProperties testProperties;
    Page page = new Page(WebDriverFactory.getDriver());
    DashboardPage dashboardPage;

    public CommonStepDefinitions() {
        testProperties = TestProperties.getInstance();
    }

    @Given("I login as Admin user")
    public void iLoginAsRMUser() {
        page.openPage(System.getProperty("tap_url")).login(testProperties.getProperty("uxp_user"), testProperties.getProperty("uxp_pass"));
    }

    @Given("I login as {string},{string}")
    public void iLoginAs(String username, String password) {
        dashboardPage = page.openPage(System.getProperty("url")).login(username, password);
    }

    @When("I search for {string}")
    public void iSearchFor(String text) {
        dashboardPage.search(text);
    }

    @Given("create actor logged in to the system")
    public void userLoggedInWithCreateAuthority() {
        dashboardPage = page.openPage(System.getProperty("url")).login(testProperties.getProperty("create_user"), testProperties.getProperty("create_pass"));
    }

    @And("navigate to Cash Account screen")
    public void navigateToCashAccountScreen() {
        dashboardPage.clickUserMenu().clickRetailOperations().clickProductCatalog().clickCurrentAccounts().openCurrentAccountForm();
    }

    @Then("user should see an error message: {string}")
    public void userShouldSeeAnErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}
