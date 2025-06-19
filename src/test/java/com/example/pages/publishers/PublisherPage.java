package com.example.pages.publishers;

import com.example.data.PublisherData;
import com.example.pages.common.ListBasePage;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class PublisherPage extends ListBasePage {
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_EMAIL = "Email";
    private static final String COLUMN_COUNT = "#";

    public PublisherPage(WebDriver driver) {
        super(driver);
    }

    public PublisherData getPublisherByName(String name) {
        Map<String, String> rowData =  this.getRowDataByText(name);

        // Debug output
        System.out.println("🔍 rowData: " + rowData);

        String countText = rowData.get("#");
        System.out.println("📦 Extracted count text: '" + countText + "'");

        int count = Integer.valueOf(countText); // This is where the crash happens if countText is null


        return new PublisherData.Builder()
                .name(rowData.get(COLUMN_NAME)) // שם הכותרת המדויק בטבלה
                .email(rowData.get(COLUMN_EMAIL))
                .count(Integer.valueOf(rowData.get(COLUMN_COUNT))) // לדוגמה - עמודה שמכילה מספר
                .build();
    }

}
