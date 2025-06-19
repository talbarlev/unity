package com.example.pages;

import com.example.components.SideNavbar;
import org.openqa.selenium.WebDriver;

public abstract class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public SideNavbar sideNavebar() {
        return new SideNavbar(driver);
    }
}

