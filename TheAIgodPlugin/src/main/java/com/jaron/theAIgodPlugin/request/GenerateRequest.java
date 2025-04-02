package com.jaron.theAIgodPlugin.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GenerateRequest {
//    Contents[]
    private Contents[] contents;
//    systemInstruction
    private SystemInstruction systemInstruction;
//    generationConfig
    private GenerationConfig generationConfig;

    public GenerateRequest(Contents[] contents, SystemInstruction systemInstruction, GenerationConfig generationConfig) {
        this.contents = contents;
        this.systemInstruction = systemInstruction;
        this.generationConfig = generationConfig;
    }




    public Contents[] getContents() {
        return contents;
    }

    public void setContents(Contents[] contents) {
        this.contents = contents;
    }

    public SystemInstruction getSystemInstruction() {
        return systemInstruction;
    }

    public void setSystemInstruction(SystemInstruction systemInstruction) {
        this.systemInstruction = systemInstruction;
    }

    public GenerationConfig getGenerationConfig() {
        return generationConfig;
    }

    public void setGenerationConfig(GenerationConfig generationConfig) {
        this.generationConfig = generationConfig;
    }
}