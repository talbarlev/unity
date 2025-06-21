package com.example.apis.HTTPclients;

import com.example.utills.TestLogger;
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
        TestLogger.step("📤 GET -> " + baseUrl + endpoint);

        return given()
                .contentType("application/json")
                .cookie("adminjs", token)
                .when()
                .get(baseUrl + endpoint)
                .then()
                .extract()
                .response();
    }

    protected Response postRequest(String endpoint, Object body) {
        TestLogger.step("📤 POST -> " + baseUrl + endpoint);
        TestLogger.json(body, "Request Body");

        return given()
                .contentType("application/json")
                .cookie("adminjs", token)
                .body(body)
                .when()
                .post(baseUrl + endpoint)
                .then()
                .extract()
                .response();
    }

    protected Response postRequest(String endpoint) {
        TestLogger.step("📤 POST -> " + baseUrl + endpoint);

        return given()
                .contentType("application/json")
                .cookie("adminjs", token)
                .when()
                .post(baseUrl + endpoint)
                .then()
                .extract()
                .response();
    }
}