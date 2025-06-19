package com.example.tests;

import com.example.apis.AuthUtills;
import com.example.apis.PublisherClient;
import com.example.base.BaseTest;
import com.example.data.PublisherData;
import com.example.utills.DataGenerator;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class APItests extends BaseTest {
    static String cookie;

    @BeforeTest
    public void setUp() {
        cookie = AuthUtills.getSessionCookie();
    }

    @Test
    public void testCreatePublisher() {
        PublisherClient api = new PublisherClient(cookie);

        PublisherData publisherDataForm = new PublisherData.Builder()
                .name(DataGenerator.generateUniqueName("HaimSheliAPI"))
                .email(DataGenerator.generateUniqueEmail("haimi"))
                .build();

        Response res = api.createPublisher(publisherDataForm);

        res.then().statusCode(200);
        System.out.println("📦 Response: " + res.asPrettyString());
    }
}
