package com.example.pages.main;

import com.example.pages.common.BasePage;
import org.openqa.selenium.WebDriver;

public abstract class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public SideNavbar sideNavebar() {
        return new SideNavbar(driver);
    }
}