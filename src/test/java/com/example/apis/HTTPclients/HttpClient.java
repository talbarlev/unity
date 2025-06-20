package com.example.apis.HTTPclients;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public abstract class HttpClient {
    protected String token;
    protected String baseUrl;

    public HttpClient(String token) {
        this.token = token;
    }

    protected void setBaseUrl(String url) {
        this.baseUrl = url;
    }

    protected Response getRequest(String endpoint) {
        return given()
                .contentType("application/json")
                .cookie("adminjs", token)
                .log().all()
                .when()
                .get(baseUrl + endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    protected Response postRequest(String endpoint, Object body) {
        return given()
                .contentType("application/json")
                .cookie("adminjs", token)
                .body(body)
                .log().all()
                .when()
                .post(baseUrl + endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

        protected Response postRequest(String endpoint) {
        return given()
                .contentType("application/json")
                .cookie("adminjs", token)
                .log().all()
                .when()
                .post(baseUrl + endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }
}