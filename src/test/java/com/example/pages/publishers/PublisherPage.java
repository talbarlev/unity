package com.example.pages.publishers;

import com.example.data.publisher.PublisherData;
import com.example.pages.common.ListBasePage;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class PublisherPage extends ListBasePage {
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_EMAIL = "Email";
    private static final String COLUMN_ID = "#";

    public PublisherPage(WebDriver driver) {
        super(driver);
    }

    public PublisherData getPublisherByName(String name) {
        Map<String, String> rowData = this.getRowDataByText(name);

        return new PublisherData.Builder()
                .name(rowData.get(COLUMN_NAME))
                .email(rowData.get(COLUMN_EMAIL))
                .id(rowData.get(COLUMN_ID))
                .build();
    }
}
