//package com.example.apis;
//
//import io.restassured.response.Response;
//
//import java.util.Map;
//
//import static io.restassured.RestAssured.given;
//
//public class PostClient extends HttpClient {
//
//    private static final String RESOURCE_PATH = "/admin/api/resources/Post/actions/new";
//
//    public PostClient(String baseUrl, String token) {super(baseUrl, token);
//    }
//
//    public Response createNewPost(Map<String, Object> formFields) {
//        return given()
//                .log().all()
//                .baseUri(baseUrl)
//                .header("Authorization", "Bearer " + token)
//                .multiPart("title", formFields.get("title").toString())
//                .multiPart("content", formFields.get("content").toString())
//                .multiPart("status", formFields.get("status").toString())
//                .multiPart("published", formFields.get("published").toString())
//                .multiPart("publisher", formFields.get("publisher").toString())
//                .when()
//                .post(RESOURCE_PATH)
//                .then()
//                .log().all()
//                .extract()
//                .response();
//    }
//}
