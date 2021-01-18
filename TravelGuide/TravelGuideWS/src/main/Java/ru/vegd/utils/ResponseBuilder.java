package ru.vegd.utils;

import com.google.gson.JsonObject;
import org.springframework.http.HttpStatus;

public class ResponseBuilder {
    private String name;
    private String description;

    public JsonObject build(String name, String description) {
        this.setName(name);
        this.setDescription(description);

        JsonObject json = new JsonObject();
        json.addProperty("name", name);
        json.addProperty("description", description);
        json.addProperty("status", HttpStatus.OK.value());
        return json;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
