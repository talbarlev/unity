package com.example.apis;

import io.restassured.response.Response;

public class PublisherClient extends HttpClient {

    public PublisherClient(String token) {
        super(token);
        setBaseUrl("http://localhost:3000/admin/api/resources/Publisher");
    }

    public Response createPublisher(Object requestBody) {
        return postRequest("/actions/new", requestBody);
    }

    public Response getPublisher(int id) {
        return getRequest("/" + id);
    }
}