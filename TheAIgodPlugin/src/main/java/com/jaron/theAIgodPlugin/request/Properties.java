package com.jaron.theAIgodPlugin.request;

public class Properties {
    private SubProperties commands;
    private SubProperties tellPlayers;
    private Type response;


    public SubProperties getCommands() {
        return commands;
    }

    public void setCommands(SubProperties commands) {
        this.commands = commands;
    }

    public SubProperties getTellPlayers() {
        return tellPlayers;
    }

    public void setTellPlayers(SubProperties tellPlayers) {
        this.tellPlayers = tellPlayers;
    }

    public Type getResponse() {
        return response;
    }

    public void setResponse(Type response) {
        this.response = response;
    }
}