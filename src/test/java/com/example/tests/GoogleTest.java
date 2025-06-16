package com.example.tests;

import com.example.base.BaseTest;
import org.testng.annotations.Test;

public class GoogleTest extends BaseTest {

    @Test
    public void openGoogle() {
        driver.get("https://www.google.com");
    }
}
