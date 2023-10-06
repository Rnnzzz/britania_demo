package com.oneaston.tap.automation.ui.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        plugin = {"pretty",
                "html:target/cucumber-reports/html-results/cucumber_result.html",
                "json:target/cucumber-reports/json-result/cucumber_result.json",
                "timeline:target/cucumber-reports/timeline-results"},
        features = {"src/test/resources/features"},
        glue = {"com.oneaston.tap.automation.ui.stepdefinitions",
                "com.oneaston.tap.automation.ui.hooks.*"},
        monochrome = true,
        publish = true)

public class RunCucumberTest extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
