package com.oneaston.tap.automation.ui.utils.excelfileutils;

import java.io.IOException;
import java.util.Map;

public class GetDataFromExcelFile {

    public static Map<String, String> getData(String targetHeader, String keyValue, String testDataDirectory) {
        try {
            ExcelReader excelReader = new ExcelReader(System.getProperty("user.dir") + "/" + testDataDirectory);
            Map<String, String> data = excelReader.getTestDataUsingHeaderAndKeyValue(0, targetHeader, keyValue);
            excelReader.closeWorkbook();
            return data;
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

}
