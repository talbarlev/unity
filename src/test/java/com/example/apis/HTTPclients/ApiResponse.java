package com.example.apis.HTTPclients;

import java.util.Map;

public class ApiResponse {
    private int statusCode;
    private Map<String, Object> data;

    public ApiResponse(int statusCode, Map<String, Object> data) {
        this.statusCode = statusCode;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public String getString(String key) {
        return data.get(key).toString();
    }

    public <T> T get(String key, Class<T> clazz) {
        return clazz.cast(data.get(key));
    }
}
