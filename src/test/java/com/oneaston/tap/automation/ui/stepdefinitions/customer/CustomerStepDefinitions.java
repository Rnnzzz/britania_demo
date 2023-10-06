package com.oneaston.tap.automation.ui.stepdefinitions.customer;

import com.oneaston.tap.automation.ui.features.customer.CustomerCreationPage;
import com.oneaston.tap.automation.ui.features.customer.CustomerSearchPage;
import com.oneaston.tap.automation.ui.utils.webdriver.WebDriverFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CustomerStepDefinitions {

    CustomerSearchPage customerSearchPage = new CustomerSearchPage(WebDriverFactory.getDriver());

    @When("I create customer")
    public void iCreateNewCustomer() {
        CustomerCreationPage customerCreationPage = customerSearchPage.clickCreateNewDeal();
        System.out.printf(customerCreationPage.getCurrentUrl());
    }

}
