package com.jaron.theAIgodPlugin.testingJSON;

import com.google.gson.annotations.SerializedName;

public class GenerateContentResponse {
    @SerializedName("content")
    private String content;

    // Other response fields as needed
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}