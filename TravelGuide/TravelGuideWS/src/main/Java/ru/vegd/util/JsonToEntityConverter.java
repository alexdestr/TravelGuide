package ru.vegd.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.Map;

public class JsonToEntityConverter {
    public static Map convert(String json) throws JsonProcessingException {
        Type mapType = new TypeToken<Map<String, Object>>(){}.getType();
        Map<String, String[]> resultMap = new Gson().fromJson(json, mapType);

        return resultMap;
    }
}
