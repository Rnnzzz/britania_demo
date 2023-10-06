package com.oneaston.tap.automation.ui.model;

public class ScenarioData {

    private String pageName;
    private String scenarioName;
    private String valueName;
    private String value;
    private String expectedResult;

    public ScenarioData(String pageName, String scenarioName, String valueName, String value, String expectedResult) {
        this.pageName = pageName;
        this.scenarioName = scenarioName;
        this.valueName = valueName;
        this.value = value;
        this.expectedResult = expectedResult;
    }

    public String getPageName() {
        return pageName;
    }

    public String getScenarioName() {
        return scenarioName;
    }

    public String getValueName() {
        return valueName;
    }

    public String getValue() {
        return value;
    }

    public String getExpectedResult() {
        return expectedResult;
    }
}
