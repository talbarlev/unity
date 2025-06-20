package com.example.pages.common;

import com.example.pages.main.HomePage;
import com.example.utills.TestLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ListBasePage extends HomePage {

    protected By headers = By.cssSelector("thead td");
    protected By cells = By.tagName("td");
    protected By createNewButton = By.cssSelector("[data-testid='action-new']");
    protected By rows = By.cssSelector("tbody tr");
    protected By settingsButton = By.cssSelector("[data-testid='actions-dropdown'] svg");
    protected By editButton = By.cssSelector("[data-testid='action-edit']");

    public ListBasePage(WebDriver driver) {
        super(driver);
    }

    public void clickOnCreate() {
        safeClick(createNewButton);
    }

    public Map<String, String> getRowDataByText(String expectedText) {
        waitForTableToLoad();

        List<String> headerTexts = extractHeaders();
        WebElement matchingRow = findMatchingRow(expectedText);

        return extractRowData(matchingRow, headerTexts);
    }

    public void clickOnEditInRow(String expectedText) {
        hoverActionsButtonInRow(expectedText);

        WebElement editButtonOfRow = findMatchingRow(expectedText).findElement(editButton);

        safeClick(editButtonOfRow);
    }

    public void hoverActionsButtonInRow(String expectedText) {
        WebElement matchingRow = findMatchingRow(expectedText);

        WebElement actionsButton = matchingRow.findElement(settingsButton);
        wait.until(ExpectedConditions.visibilityOf(actionsButton));
        hoverOnElement(actionsButton);
    }

    private void waitForTableToLoad() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(headers));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(rows));
    }

    private List<String> extractHeaders() {
        List<WebElement> headerElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(headers));
        TestLogger.info("🧠 Number of headers: " + headerElements.size());
        for (WebElement header : headerElements) {
            TestLogger.info("📋 Header: " + header.getText());
        }
        return headerElements.stream()
                .map(el -> el.getText().trim())
                .toList();
    }

    private WebElement findMatchingRow(String expectedText) {
        List<WebElement> allRows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(rows));

        for (WebElement row : allRows) {
            String rowText = safeGetText(row);
            if (rowText.contains(expectedText)) {
                return row;
            }
        }

        TestLogger.error("❌ Row with text '" + expectedText + "' was not found.");
        throw new RuntimeException("❌ Row with text '" + expectedText + "' was not found.");
    }

    private Map<String, String> extractRowData(WebElement row, List<String> headers) {
        List<WebElement> cellElements = row.findElements(cells);
        Map<String, String> rowData = new HashMap<>();

        for (int i = 0; i < Math.min(headers.size(), cellElements.size()); i++) {
            String header = headers.get(i);
            String cellValue = safeGetText(cellElements.get(i));

            if (!header.isEmpty()) {
                rowData.put(header, cellValue);
                TestLogger.step(header + " = " + cellValue);
            } else {
                TestLogger.warning("Skipping cell with empty header: '" + cellValue + "'");
            }
        }

        TestLogger.json(rowData, "✅ Final rowData");
        return rowData;
    }
}
