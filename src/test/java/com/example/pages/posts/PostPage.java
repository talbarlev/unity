package com.example.pages.posts;

import com.example.data.post.PostData;
import com.example.pages.common.ListBasePage;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class PostPage extends ListBasePage {

    private static final String COLUMN_TITLE = "Title";
    private static final String COLUMN_CONTENT = "Content";
    private static final String COLUMN_STATUS = "Status";
    private static final String COLUMN_PUBLISHED = "Published";
    private static final String COLUMN_PUBLISHER = "Publisher";
    private static final String COLUMN_ID = "#";

    public PostPage(WebDriver driver) {
        super(driver);
    }

    public PostData getPostByTitle(String title) {
        Map<String, String> rowData = this.getRowDataByText(title);

        System.out.println("📄 rowData: " + rowData);

        boolean isPublished = rowData.getOrDefault(COLUMN_PUBLISHED, "").equalsIgnoreCase("true");

        return new PostData.Builder()
                .title(rowData.get(COLUMN_TITLE))
                .content(rowData.get(COLUMN_CONTENT))
                .status(rowData.get(COLUMN_STATUS))
                .published(isPublished)
                .publisher(rowData.get(COLUMN_PUBLISHER))
                .id(rowData.get(COLUMN_ID))
                .build();
    }
}
