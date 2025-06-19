package com.example.tests;

import com.example.apis.AuthUtills;
import com.example.apis.PostClient;
import com.example.apis.PublisherClient;
import com.example.base.BaseTest;
import com.example.data.JsonItemData;
import com.example.data.PostData;
import com.example.data.PublisherData;
import com.example.utills.DataGenerator;
import io.restassured.response.Response;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.assertEquals;

public class APItests extends BaseTest {
    static String cookie;

    @BeforeTest
    public void setUp() {
        cookie = AuthUtills.getSessionCookie();
    }

    @Test
    public void testCreatePublisher() {
        String dataPath = "record.params";

        PublisherClient publisherClient = new PublisherClient(cookie);
        PostClient postClient = new PostClient(cookie);

        PublisherData publisherDataForm = new PublisherData.Builder()
                .name(DataGenerator.generateUniqueName("HaimSheliAPI"))
                .email(DataGenerator.generateUniqueEmail("haimi"))
                .build();

        Response res = publisherClient.createPublisher(publisherDataForm);
        res.then().statusCode(200);
        System.out.println("📦 Publisher Response: " + res.asPrettyString());

        String idOfPublisher = res.path("record.params.id").toString();

        PostData postData = new PostData.Builder()
                .title(DataGenerator.generateUniqueName("HaimSheliAPI"))
                .content("1")
                .status("ACTIVE")
                .published(true)
                .publisher(idOfPublisher)
                .addJsonItem(new JsonItemData.Builder()
                        .number(DataGenerator.randonNumber(1, 10))
                        .string(DataGenerator.generateUniqueName("string"))
                        .bool(true)
                        .date(DataGenerator.generateTimestamp())
                        .build())
                .build();

        Response res2 = postClient.createNewPost(postData);
        res2.then().statusCode(200);
        System.out.println("📦 Post Create Response: " + res2.asPrettyString());

        // ⬇️ תיקון כאן - שימוש בתגובה של יצירת הפוסט (res2) ולא ביצירת הpublisher
        String idOfPost = res2.path("record.params.id").toString();

        PostData editData = new PostData.Builder()
                .title("Updated Title")
                .content("Updated Content")
                .status("REMOVED")
                .published(true)
                .publisher(idOfPublisher)
                .build();

        Response res3 = postClient.editPostById(editData, idOfPost);
        res3.then().statusCode(200);
        System.out.println("📦 Post Edit Response: " + res3.asPrettyString());

        Response res4 = postClient.getPostById(idOfPost);
        res4.then().statusCode(200);

        Map<String, Object> postParams = res4.path("record.params");
        String title = (String) postParams.get("title");
        String content = (String) postParams.get("content");
        String status = (String) postParams.get("status");

        assertEquals(title, editData.getTitle());
        assertEquals(res2.path("record.params.status"), "ACTIVE");
        assertEquals(status, "REMOVED");
    }

    @AfterTest
    public void deleteAllData() {


    }
}

