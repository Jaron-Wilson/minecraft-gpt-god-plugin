package com.jaron.theAIgodPlugin.testingJSON;

public class RequestBuilder {
    public GenerateContentRequest buildRequest(String prompt) {
        GenerateContentRequest request = new GenerateContentRequest();
        request.setPrompt(prompt);
        // Set other request fields as needed
        return request;
    }
}