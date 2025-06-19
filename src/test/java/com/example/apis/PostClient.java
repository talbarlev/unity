package com.example.apis;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

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

    public Response editPostById(Object requestBody, String id) {
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> body = mapper.convertValue(requestBody, Map.class);
        body.put("recordId", id);

        return postRequest("/records/" + id + "/edit", requestBody);
    }

}
