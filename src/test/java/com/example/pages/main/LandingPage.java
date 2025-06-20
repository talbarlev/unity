package com.example.pages.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage extends HomePage {
    public LandingPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return null;
    }
}
