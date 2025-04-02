//package com.jaron.theAIgodPlugin.testingJSON;
//
//import com.google.gson.*;
//
//import java.io.IOException;
//
//public class Main {
//    public static void main(String[] args) throws IOException, InterruptedException {
//        RequestBuilder requestBuilder = new RequestBuilder();
//        GenerateContentRequest request = requestBuilder.buildRequest("Explain how AI works in 40 words");
//
//        APIClient apiClient = new APIClient();
//        String response = apiClient.makeRequest(request, "");
////        System.out.println(response);
//
//
//        String var = extractVar(response);
//        System.out.println(var);
//
//    }
//
//
//}