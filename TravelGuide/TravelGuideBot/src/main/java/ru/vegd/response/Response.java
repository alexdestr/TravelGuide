package ru.vegd.response;

import java.util.Objects;

public class Response {
    private String name;
    private String description;

    public Response() {
    }

    public Response(String name, String description) {
        this.name = name;
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return name.equals(response.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
