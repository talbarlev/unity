package com.example.pages;

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

    public void clickOnEdit(String expectedText) {
        hoverActionsButtonInRow(expectedText);

        safeClick(editButton);
    }

    public void hoverActionsButtonInRow(String expectedText) {
        WebElement matchingRow = findMatchingRow(expectedText);

        WebElement actionsButton = matchingRow.findElement(settingsButton);

        wait.until(ExpectedConditions.visibilityOf(actionsButton));

        hoverOnElement(actionsButton);
    }

    private void waitForTableToLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(rows));
        wait.until(ExpectedConditions.visibilityOfElementLocated(headers));
    }

    private List<String> extractHeaders() {
        List<WebElement> headerElements = driver.findElements(headers);

        System.out.println("🧠 Number of headers: " + headerElements.size());
        for (WebElement header : headerElements) {
            System.out.println("📋 Header: " + header.getText());
        }

        return headerElements.stream()
                .map(el -> el.getText().trim())
                .toList();
    }

    private WebElement findMatchingRow(String expectedText) {
        List<WebElement> rows =  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(this.rows));

        for (WebElement row : rows) {
            String rowText = row.getText();
            if (rowText.contains(expectedText)) {
                System.out.println("🔍 Matching row found: " + rowText);
                return row;
            }
        }

        throw new RuntimeException("❌ Row with text '" + expectedText + "' was not found.");
    }

    private Map<String, String> extractRowData(WebElement row, List<String> headers) {
        List<WebElement> cells = row.findElements(this.cells);
        Map<String, String> rowData = new HashMap<>();

        for (int i = 0; i < Math.min(headers.size(), cells.size()); i++) {
            String header = headers.get(i);
            String cellValue = cells.get(i).getText().trim();

            if (!header.isEmpty()) {
                rowData.put(header, cellValue);
                System.out.println("➡️ " + header + " = " + cellValue);
            } else {
                System.out.println("⚠️ Skipping cell with empty header: '" + cellValue + "'");
            }
        }

        System.out.println("✅ Final rowData: " + rowData);
        return rowData;
    }
}
