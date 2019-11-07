package com.emtowero.towero.provider;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HttpProvider {
    @Value("${tba-auth-key}")
    private String tbaAuthKey;

    private OkHttpClient client = new OkHttpClient();

    public String getTbaRequest(String api) {
        Headers headers = new Headers.Builder()
                .add("content-type", "application/json")
                .add("X-TBA-Auth-Key", this.tbaAuthKey)
                .build();

        String baseUrl = "https://www.thebluealliance.com/api/v3/";

        Request request = new Request.Builder()
                .headers(headers)
                .url(baseUrl + api)
                .build();

        try (Response response = this.client.newCall(request).execute()) {
            assert response.body() != null;
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
