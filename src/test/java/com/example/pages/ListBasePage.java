package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class ListBasePage extends HomePage {

    protected By headers = By.cssSelector("thead td");
    protected By cellsLocator = By.tagName("td");
    protected By createNewButton = By.cssSelector("[data-testid='action-new']");
    protected By rowsLocator = By.cssSelector("tbody tr");

    public ListBasePage(WebDriver driver) {
        super(driver);
    }

    public void clickOnCreate() {
        this.click(driver.findElement(createNewButton));
    }

    public Map<String, String> getRowDataByText(String expectedText) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(rowsLocator));
        wait.until(ExpectedConditions.visibilityOfElementLocated(headers));

        List<WebElement> rows = driver.findElements(rowsLocator);
        List<WebElement> headerElements = driver.findElements(headers);

        System.out.println("🧠 Number of headers: " + headerElements.size());
        for (WebElement h : headerElements) {
            System.out.println("📋 Header: " + h.getText());
        }

        for (WebElement row : rows) {
            String rowText = row.getText();
            if (rowText.contains(expectedText)) {
                System.out.println("🔍 Matching row found: " + rowText);

                List<WebElement> cells = row.findElements(cellsLocator);
                Map<String, String> rowData = new HashMap<>();

                for (int i = 0; i < Math.min(headerElements.size(), cells.size()); i++) {
                    String header = headerElements.get(i).getText().trim();
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

        // No matching row
        throw new RuntimeException("❌ Row with text '" + expectedText + "' was not found.");
    }


}
