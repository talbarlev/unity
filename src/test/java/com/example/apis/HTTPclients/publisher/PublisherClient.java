package com.example.apis.HTTPclients.publisher;


import com.example.apis.HTTPclients.HttpClient;
import com.example.data.common.Properties;
import io.restassured.response.Response;

public class PublisherClient extends HttpClient {

    public PublisherClient(String token) {
        super(token);
        setBaseUrl(Properties.BASEURL_API + "Publisher");
    }

    public Response createPublisher(Object requestBody) {
        return postRequest("/actions/new", requestBody);
    }

    public Response deletePublisherById(String id) {
        return postRequest("/bulk/bulkDelete?recordIds=" + id);
    }
}
