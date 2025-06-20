package com.example.apis.HTTPclients;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

import java.util.Map;


public class PostClient extends HttpClient {

    private static final String RESOURCE_PATH = "/admin/api/resources/Post/actions/new";

    public PostClient(String token) {
        super(token);
        setBaseUrl("http://localhost:3000/admin/api/resources/Post");

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

        return postRequest("/records/" + postId + "/edit", requestBody);
    }

    public Response deletePostById(String postId) {
        return deleteRequest("/records/" + postId + "/delete");
    }

}