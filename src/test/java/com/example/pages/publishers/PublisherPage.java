package com.example.pages.publishers;

import com.example.data.publisher.PublisherData;
import com.example.pages.common.ListBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class PublisherPage extends ListBasePage {
    private final String COLUMN_NAME = "Name";
    private final String COLUMN_EMAIL = "Email";
    private final By listSection = By.cssSelector("section[data-css='Publisher-list']");

    public PublisherPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return this.listSection;
    }

    public PublisherData getPublisherByName(String name) {
        Map<String, String> rowData = this.getRowDataByText(name);

        // Debug output
        System.out.println("🔍 rowData: " + rowData);

        String countText = rowData.get("#");
        System.out.println("📦 Extracted count text: '" + countText + "'");

        return new PublisherData.Builder()
                .name(rowData.get(COLUMN_NAME))
                .email(rowData.get(COLUMN_EMAIL))
                .build();
    }

}
