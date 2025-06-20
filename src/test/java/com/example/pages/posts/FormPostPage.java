package com.example.pages.posts;

import com.example.data.post.JsonItemData;
import com.example.data.post.PostData;
import com.example.pages.common.FormBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormPostPage extends FormBasePage {
    private final By titleInput = By.id("title");
    private final By contentInput = By.id("content");
    private final By publishedCheckbox = By.id("published");
    private final By addJSON = By.cssSelector("[data-testid='someJson-add']");
    private final By formLocator = By.cssSelector("form[data-css='Post-new-form']");

    public FormPostPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void create(Object data) {
        if (!(data instanceof PostData)) {
            throw new IllegalArgumentException("Expected PostData");
        }


        PostData postData = (PostData) data;

        fillBasicFields(postData);
        fillJsonItems(postData);
        clickOnSave();
    }

    private void fillBasicFields(PostData postData) {
        safeTypeText(titleInput, postData.getTitle());
        safeTypeText(contentInput, postData.getContent());
        selectStatus(postData.getStatus());

        if (postData.isPublished()) {
            safeClick(publishedCheckbox);
        }

        selectReactOptionByLabel("Publisher", postData.getPublisher());
    }


    private void fillJsonItems(PostData postData) {
        for (int i = 0; i < postData.getJsonItems().size(); i++) {
            JsonItemData item = postData.getJsonItems().get(i);
            clickAddJson();
            new JsonItemComponent(driver, i).fill(item); // ממלא אותו לפי האינדקס
        }
    }

    public void selectStatus(String status) {
        selectReactOptionByLabel("Status", status);
    }

    private void clickAddJson() {
        safeClick(addJSON);
    }
}