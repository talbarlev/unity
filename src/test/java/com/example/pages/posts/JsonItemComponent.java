package com.example.pages.posts;

import com.example.data.post.JsonItemData;
import com.example.pages.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JsonItemComponent extends BasePage {
    private final int index;
private final By sectionLocator = By.cssSelector("[data-testid='property-edit-someJson']");

    public JsonItemComponent(WebDriver driver, int index) {
        super(driver);
        this.index = index;
    }

    public void fill(JsonItemData item) {
        safeTypeText(jsonNumber(), String.valueOf(item.getNumber()));
        safeTypeText(jsonString(), item.getString());

        if (item.getBool()) {
            safeClick(jsonBoolean()); // רק אם true
        }

        safeTypeText(jsonDate(), item.getDate());
    }

    private By jsonString() {
        return By.cssSelector("#someJson\\." + index + "\\.string");
    }

    private By jsonNumber() {
        return By.cssSelector("#someJson\\." + index + "\\.number");
    }

    private By jsonBoolean() {
        return By.cssSelector("#someJson\\." + index + "\\.boolean");
    }

    private By jsonDate() {
        return By.xpath("//label[@for='someJson." + index + ".date']/following::input[1]");
    }

    @Override
    protected By getPageIdentifier() {
        return this.sectionLocator;
    }
}
