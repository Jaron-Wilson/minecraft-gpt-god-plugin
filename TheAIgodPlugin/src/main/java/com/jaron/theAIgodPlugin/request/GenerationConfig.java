package com.jaron.theAIgodPlugin.request;

public class GenerationConfig {
    private int temperature;
    private int topK;
    private double topP;
    private int maxOutputTokens;
    private String responseMimeType;
    private ResponseSchema responseSchema;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getTopK() {
        return topK;
    }

    public void setTopK(int topK) {
        this.topK = topK;
    }

    public double getTopP() {
        return topP;
    }

    public void setTopP(double topP) {
        this.topP = topP;
    }

    public int getMaxOutputTokens() {
        return maxOutputTokens;
    }

    public void setMaxOutputTokens(int maxOutputTokens) {
        this.maxOutputTokens = maxOutputTokens;
    }

    public String getResponseMimeType() {
        return responseMimeType;
    }

    public void setResponseMimeType(String responseMimeType) {
        this.responseMimeType = responseMimeType;
    }

    public ResponseSchema getResponseSchema() {
        return responseSchema;
    }

    public void setResponseSchema(ResponseSchema responseSchema) {
        this.responseSchema = responseSchema;
    }
}