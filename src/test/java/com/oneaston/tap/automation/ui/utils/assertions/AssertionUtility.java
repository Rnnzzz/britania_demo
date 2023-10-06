package com.oneaston.tap.automation.ui.utils.assertions;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class AssertionUtility {

    public static void assertElementDisplayed(WebElement element) {
        Assert.assertTrue(element.isDisplayed(), "Element is not displayed.");
    }

    public static void assertTextEquals(String actualText, String expectedText) {
        Assert.assertEquals(actualText, expectedText, "Text mismatch.");
    }

    public static void assertTrue(boolean condition, String errorMsg) {
        Assert.assertTrue(condition, errorMsg);
    }
}
