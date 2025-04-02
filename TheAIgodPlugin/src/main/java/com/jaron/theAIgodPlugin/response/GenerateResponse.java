package com.jaron.theAIgodPlugin.response;

import java.util.List;

public class GenerateResponse {
    private List<String> commands;
    private List<String> tellPlayers;
    private String response;

    public GenerateResponse(List<String> commands, List<String> tellPlayers, String response) {
        this.commands = commands;
        this.tellPlayers = tellPlayers;
        this.response = response;
    }

    // Getters and setters

    public List<String> getCommands() {
        return commands;
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }

    public List<String> getTellPlayers() {
        return tellPlayers;
    }

    public void setTellPlayers(List<String> tellPlayers) {
        this.tellPlayers = tellPlayers;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
