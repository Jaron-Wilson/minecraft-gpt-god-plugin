package com.jaron.theAIgodPlugin.testingJSON;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIClient {
    public String makeRequest(GenerateContentRequest request, String apiKey) throws IOException, InterruptedException {
        Gson gson = new Gson();

        URI url = URI.create("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + apiKey);
//        URL url = new URL("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=AIzaSyDFF5YeurMrkvKdAkriYxYUSjBGey2dsOI");
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(url)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{\"contents\":[{\"parts\":[{\"text\":\" "+ request.getPrompt() + "\"}]}]}"))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Error making API request: " + response.statusCode());
        }

        return response.body();
    }


}