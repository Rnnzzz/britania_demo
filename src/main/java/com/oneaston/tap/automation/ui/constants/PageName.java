package com.oneaston.tap.automation.ui.constants;

public enum PageName {
    BONDS("Bonds"),
    FUNDS("Funds");
    // Add more pages here


    private final String pageName;

    PageName(String pageName) {
        this.pageName = pageName;
    }

    public String getPageName() {
        return pageName;
    }
}
