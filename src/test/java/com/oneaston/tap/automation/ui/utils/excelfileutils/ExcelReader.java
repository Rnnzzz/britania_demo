package com.oneaston.tap.automation.ui.utils.excelfileutils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ExcelReader {

    private Workbook workbook;

    public ExcelReader(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getScenarioData(String sheetName, String targetColumn) throws NullPointerException {
        Map<String, String> scenarioData = new HashMap<>();
        Sheet sheet = workbook.getSheet(sheetName);

        Row headerRow = sheet.getRow(0); // Header Row

        for (int rowIndex = headerRow.getRowNum(); rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row dataRow = sheet.getRow(getDataRow(targetColumn, sheet)); //Data Row
            scenarioData = getData(headerRow, dataRow);
        }

        return scenarioData;
    }

    private int getDataRow(String uniqueValue, Sheet sheet) throws NullPointerException {
        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            if (sheet.getRow(rowIndex).getCell(0).getStringCellValue().equals(uniqueValue)) {
                return rowIndex;
            }
        }
        return 0;
    }

    public Map<String, String> getTestDataUsingHeaderAndKeyValue(int sheetIndex, String targetHeaderName, String searchValue) throws IOException {
        Map<String, String> testData = new HashMap<>();
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        Row headerRow = sheet.getRow(0);

        int targetColumn = getTargetColumnIndex(targetHeaderName, headerRow);
        if (targetColumnNotFound(targetColumn)) {
            workbook.close();
            return testData;
        }

        testData = getTestData(searchValue, sheet, targetColumn, headerRow);

        workbook.close();
        return testData;
    }

    private boolean targetColumnNotFound(int searchColumnIndex) {
        return searchColumnIndex == -1;
    }

    private int getTargetColumnIndex(String targetHeaderName, Row headerRow) {
        int columnIndex = 0, headerIndex = -1;
        for (Cell headerCell : headerRow) {
            if (currentCellValueIsEqualToTargetHeader(targetHeaderName, headerCell)) {
                headerIndex = columnIndex;
                break;
            }
            columnIndex++;
        }
        return headerIndex;
    }

    private boolean currentCellValueIsEqualToTargetHeader(String targetHeaderName, Cell headerCell) {
        return headerCell.getCellType() == CellType.STRING && headerCell.getStringCellValue().equals(targetHeaderName);
    }

    private Map<String, String> getTestData(String targetValue, Sheet sheet, int targetValueColumnIndex, Row headerRow) {
        Map<String, String> rowData = new HashMap<>();

        for (Row currentRow : sheet) {
            Cell currentCell = currentRow.getCell(targetValueColumnIndex, Row.MissingCellPolicy.RETURN_NULL_AND_BLANK);
            if (currentCellIsNotNull(currentCell)) {
                String cellValue = getCellValue(currentCell);
                if (currentCellIsEqualToTargetKey(targetValue, cellValue)) {
                    populateDataWithHeaderAndCorrespondingValue(headerRow, currentRow, rowData);
                    break;
                }
            }
        }
        return rowData;
    }

    private boolean currentCellIsNotNull(Cell currentCell) {
        return currentCell != null;
    }

    private void populateDataWithHeaderAndCorrespondingValue(Row headerRow, Row currentRow, Map<String, String> rowData) {
        Iterator<Cell> cellIterator = currentRow.iterator();
        int headerIndex = 0;
        while (cellIterator.hasNext()) {
            putData(headerRow, rowData, cellIterator, headerIndex);
            headerIndex++;
        }
    }

    private void putData(Row headerRow, Map<String, String> rowData, Iterator<Cell> cellIterator, int headerIndex) {
        Cell cell = cellIterator.next();
        String header = headerRow.getCell(headerIndex).getStringCellValue();
        String value = "";
        if (cell.getCellType() == CellType.STRING) {
            value = cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            value = String.valueOf(cell.getNumericCellValue());
        }
        rowData.put(header, value);
    }

    private boolean currentCellIsEqualToTargetKey(String targetValue, String cellValue) {
        return cellValue.equals(targetValue);
    }

    private String getCellValue(Cell searchCell) {
        String cellValue = "";
        if (searchCell.getCellType() == CellType.STRING) {
            cellValue = searchCell.getStringCellValue();
        } else if (searchCell.getCellType() == CellType.NUMERIC) {
            cellValue = String.valueOf(searchCell.getNumericCellValue());
        }
        return cellValue;
    }

    private Map<String, String> getData(Row headerRow, Row dataRow) {
        Map<String, String> scenarioData = new HashMap<>();

        for (int cellIndex = 0; cellIndex < headerRow.getLastCellNum(); cellIndex++) {
            Cell headerCell = headerRow.getCell(cellIndex);
            Cell dataCell = dataRow.getCell(cellIndex);

            if (headerCell != null && dataCell != null) {
                scenarioData.put(headerCell.getStringCellValue(), dataCell.getStringCellValue());
            }
        }
        return scenarioData;
    }


    private int getScenarioNameIndex(String scenarioName, Sheet sheet) {
        try {
            int scenarioIndex = 0;

            do {
                System.out.println("Checking scenario: " + scenarioName + " row number: " + scenarioIndex);
                if (sheet.getRow(scenarioIndex).getCell(0).getStringCellValue().equals(scenarioName)) {
                    break;
                }
                scenarioIndex++;
            } while (true);
            return scenarioIndex;
        } catch (NullPointerException nullPointerException) {
            Assert.fail("Error in getting data - scenario not found in the provided file");
            return 0;
        }
    }


    public void closeWorkbook() {
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

