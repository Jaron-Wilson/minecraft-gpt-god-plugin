package com.jaron.theAIgodPlugin.request;

public class Contents {
    private String role;
    private Parts[] parts;

    public Contents(String role, Parts[] parts) {
        this.role = role;
        this.parts = parts;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Parts[] getParts() {
        return parts;
    }

    public void setParts(Parts[] parts) {
        this.parts = parts;
    }
}