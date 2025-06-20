package com.example.pages.publishers;

import com.example.data.publisher.PublisherData;
import com.example.pages.common.ListBasePage;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class PublisherPage extends ListBasePage {
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_EMAIL = "Email";

    public PublisherPage(WebDriver driver) {
        super(driver);
    }

    public PublisherData getPublisherByName(String name) {
        Map<String, String> rowData =  this.getRowDataByText(name);

        System.out.println("🔍 rowData: " + rowData);

        String countText = rowData.get("#");
        System.out.println("📦 Extracted count text: '" + countText + "'");



        return new PublisherData.Builder()
                .name(rowData.get(COLUMN_NAME)) // שם הכותרת המדויק בטבלה
                .email(rowData.get(COLUMN_EMAIL))
                .build();
    }

}
