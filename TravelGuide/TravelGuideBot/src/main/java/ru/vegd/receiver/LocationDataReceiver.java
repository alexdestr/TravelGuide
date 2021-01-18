package ru.vegd.receiver;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;
import ru.vegd.bot.TravelGuideBot;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class LocationDataReceiver {

    private final static org.slf4j.Logger logger =
            LoggerFactory.getLogger(LocationDataReceiver.class);

    private static final String URL = "http://localhost:8080/location/get?name=";

    public JsonObject getLocationInformation(String name) throws IOException {
        String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8);
        HttpGet request = new HttpGet(URL + encodedName);
        JsonObject answer = null;

        // add request headers
        request.addHeader("accept", "application/json");

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                answer = (JsonObject) JsonParser.parseString(result);
            }
        }
        return answer;
    }
}
