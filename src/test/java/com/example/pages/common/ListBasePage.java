package com.example.pages.common;

import com.example.pages.main.HomePage;
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
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(headers));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(rows));
    }

    private List<String> extractHeaders() {
        List<WebElement> headerElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(headers));

        System.out.println("🧠 Number of headers: " + headerElements.size());
        for (WebElement header : headerElements) {
            System.out.println("📋 Header: " + header.getText());
        }

        return headerElements.stream()
                .map(el -> el.getText().trim())
                .toList();
    }

    private WebElement findMatchingRow(String expectedText) {
        List<WebElement> allRows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(rows));

        for (WebElement row : allRows) {
            wait.until(ExpectedConditions.visibilityOf(row));
            String rowText = safeGetText(row);

            if (rowText.contains(expectedText)) {
                System.out.println("🔍 Matching row found: " + rowText);
                return row;
            }
        }

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
                System.out.println("➡️ " + header + " = " + cellValue);
            } else {
                System.out.println("⚠️ Skipping cell with empty header: '" + cellValue + "'");
            }
        }

        System.out.println("✅ Final rowData: " + rowData);
        return rowData;
    }
}
