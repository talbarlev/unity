package com.example.utills;

import com.example.apis.HTTPclients.PostClient;
import com.example.apis.HTTPclients.PublisherClient;

public class CommomAPI {

    public static void deletePost(String postId, String publisherId, String cookie) {
        PostClient postClient = new PostClient(cookie);
        PublisherClient publisherClient = new PublisherClient(cookie);

        postClient.deletePostById(postId);
        publisherClient.deletePublisherById(publisherId);
    }
}