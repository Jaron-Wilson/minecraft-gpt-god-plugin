package com.jaron.theAIgodPlugin.testingJSON;

import com.google.gson.annotations.SerializedName;

public class GenerateContentRequest {
    @SerializedName("prompt")
    private String prompt;

    // Other request fields as needed
    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}
