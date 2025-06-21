package com.example.tests;

import com.example.apis.AuthUtills;
import com.example.apis.HTTPclients.post.PostClient;
import com.example.apis.HTTPclients.post.PostCreateRequest;
import com.example.apis.HTTPclients.publisher.PublisherClient;
import com.example.apis.HTTPclients.publisher.PublisherCreateRequest;
import com.example.data.post.PostStatus;
import com.example.utills.CommonAPI;
import com.example.utills.DataGenerator;
import io.restassured.response.Response;
import org.testng.annotations.*;
import static org.testng.Assert.assertEquals;

public class APItests {
    static String cookie;
    private String createdPostId;
    private String createdPublisherId;

    @BeforeClass
    public void setUp() {
        cookie = AuthUtills.getSessionCookie();
    }

    @Test
    public void testCreatePublisher() {
        String publisherName = DataGenerator.generateUniqueName("HaimSheliPublisherAPI");
        String publisherEmail = DataGenerator.generateUniqueEmail("haimiPublisherAPI");
        String postTitle = DataGenerator.generateUniqueName("HaimSheliPostAPI");
        String postContent = DataGenerator.generateUniqueName("HaimSheliPostAPIWOOOO");

        PublisherClient publisherClient = new PublisherClient(cookie);
        PostClient postClient = new PostClient(cookie);

        PublisherCreateRequest createPublisherRequest = new PublisherCreateRequest(publisherName, publisherEmail);
        Response createPubliserResponse = publisherClient.createPublisher(createPublisherRequest);
        createdPublisherId = createPubliserResponse.path("record.params.id").toString();

        PostCreateRequest createPostData = new PostCreateRequest(postTitle, postContent, PostStatus.ACTIVE, true, createdPublisherId, null);
        Response createPostResponse = postClient.createNewPost(createPostData);

        createdPostId = createPostResponse.path("record.params.id").toString();

        PostCreateRequest editPostData = new PostCreateRequest(postTitle, postContent, PostStatus.REMOVED, true, createdPublisherId, null);
        Response editPostResponse = postClient.editPostById(editPostData, createdPostId);

        Response getPostByIdAfterEditResponse = postClient.getPostById(createdPostId);
        getPostByIdAfterEditResponse.then().statusCode(200);

        assertEquals(createPubliserResponse.statusCode(), 200);
        assertEquals(createPostResponse.statusCode(), 200);
        assertEquals(editPostResponse.statusCode(), 200);

        assertEquals(editPostResponse.path("record.params.title"), editPostData.getTitle());
        assertEquals(createPostResponse.path("record.params.status"), PostStatus.ACTIVE.toString());
        assertEquals(editPostResponse.path("record.params.status"), PostStatus.REMOVED.toString());
    }


    @AfterClass
    public void deleteAllData() {
       CommonAPI.deletePublisherAndPost(createdPostId, createdPublisherId, cookie);
    }
}