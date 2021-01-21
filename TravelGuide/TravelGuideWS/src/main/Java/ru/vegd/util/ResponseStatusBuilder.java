package ru.vegd.util;

import com.google.gson.JsonObject;

public class ResponseStatusBuilder {
    public static JsonObject getStatusCode(Integer status) {
        JsonObject responseStatus = new JsonObject();
        responseStatus.addProperty("status", status);

        return responseStatus;
    }
}
