package com.example.apis.HTTPclients.post;

import com.example.apis.HTTPclients.HttpClient;
import com.example.data.common.Properties;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

import java.util.Map;


public class PostClient extends HttpClient {
    public PostClient(String token) {
        super(token);
        setBaseUrl(Properties.BASEURL_API + "Post");
    }

    public Response createNewPost(Object requestBody) {
        return postRequest("/actions/new", requestBody);
    }

    public Response getPostById(String id) {
        return getRequest("/records/" + id + "/show");
    }

    public Response editPostById(Object requestBody, String postId) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> body = mapper.convertValue(requestBody, Map.class);
        body.put("recordId", postId);

        return postRequest("/records/" + postId + "/edit", body);
    }

    public Response deletePostById(String postId) {
        return postRequest("/records/" + postId + "/delete");
    }
}