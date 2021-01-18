package ru.vegd.response;


import com.google.gson.JsonObject;

public class ResponseBuilder {
    public Response build(JsonObject json) {
        Response response = new Response();
        if (json.get("status").getAsInt() == 200) {
            if (!json.get("name").isJsonNull()) {
                response.setName(json.get("name").getAsString().replaceAll("\"", ""));
            } else {
                response.setName(null);
            }

            if (!json.get("description").isJsonNull()) {
                response.setDescription(json.get("description").getAsString().replaceAll("\"", ""));
            } else {
                response.setDescription(null);
            }
        }

        return response;
    }
}
