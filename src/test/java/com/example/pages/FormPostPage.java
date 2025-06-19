package com.example.pages;

import com.example.data.JsonItemData;
import com.example.data.PostData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormPostPage extends FormBasePage {
    private By titleInput = By.id("title");
    private By contentInput = By.id("content");
    private By publishedCheckbox = By.id("published");
    private By addJSON = By.cssSelector("[data-testid='someJson-add']");
    private By jsonNumber = By.cssSelector("#someJson\\.0\\.number");
    private By jsonString = By.cssSelector("#someJson\\.0\\.string");
    private By jsonBoolean = By.cssSelector("#someJson\\.0\\.boolean");
    private By jsonDate = By.cssSelector(".adminjs_DatePicker input"); // FLAKEY


    public FormPostPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void create(Object data) {
        if (!(data instanceof PostData)) {
            throw new IllegalArgumentException("Expected PostData");
        }

        PostData postData = (PostData) data;

        enterTitle(postData.getTitle());
        enterContent(postData.getContent());
        selectStatus(postData.getStatus());
        setPublished(postData.isPublished());
        choosePublisher(postData.getPublisher());

        clickAddJson();

        JsonItemData jsonItemData = postData.getJsonItems().get(0);

        setJsonString(jsonItemData.getString());
        setJsonNumber(jsonItemData.getNumber());
        setJsonBoolean(jsonItemData.getBool());
         setJsonDate(jsonItemData.getDate());

        this.clickOnSave();
    }


    private void enterTitle(String title) {
        safeTypeText(titleInput, title);
    }

    private void enterContent(String content) {
        safeTypeText(contentInput, content);
    }

    private void selectStatus(String status) {
        selectReactOptionByLabel("Status", status);
    }

    // Validate already checked
    private void setPublished(boolean toPublished) {
        if (toPublished) {
            safeClick(publishedCheckbox);
        }
    }

    private void choosePublisher(String publisherName) {
        selectReactOptionByLabel("Publisher", publisherName);
    }

    private void clickAddJson() {
        safeClick(addJSON);
    }

    private void setJsonString(String string) {
        safeTypeText(jsonString, string);
    }

    private void setJsonNumber(int number) {
        safeTypeText(jsonNumber, String.valueOf(number));
    }

    private void setJsonBoolean(boolean bool) {
        safeClick(jsonBoolean);
    }

    private void setJsonDate(String date) {
        safeTypeText(jsonDate, date);
    }
}


