package com.jaron.theAIgodPlugin;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jaron.theAIgodPlugin.request.*;
import com.jaron.theAIgodPlugin.response.GenerateResponse;
import com.jaron.theAIgodPlugin.testingJSON.APIClient;
import com.jaron.theAIgodPlugin.testingJSON.GenerateContentRequest;
import com.jaron.theAIgodPlugin.testingJSON.RequestBuilder;
import org.bukkit.Color;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.*;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.bukkit.Bukkit.dispatchCommand;

public final class TheAIgodPlugin extends JavaPlugin implements Listener {

    private String apiKey;
    RequestBuilder requestBuilder = new RequestBuilder();

    List<String> conversationHistory = new ArrayList<>();
    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("The AI god has awoken.");
        JavaPlugin.getPlugin(TheAIgodPlugin.class).getConfig();
        FileConfiguration config;

        config = getConfig();
        saveDefaultConfig();

        chooseBehaviours(config.getStringList("potentialBehaviors"));


//        getServer().getPluginManager().registerEvents(this, this);
        WorldMaker worldMaker = new WorldMaker();
        getServer().getPluginManager().registerEvents(worldMaker, this);

        // Schedule the command to be executed after 1 tick
        Bukkit.getScheduler().runTaskLater(this , () -> {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "say hello!");
        }, 1);

//        getServer().dispatchCommand(getServer().getConsoleSender(), "/say Welcome Losers!");


    }

    private String turnToMinecraftCommand(String command) throws IOException, InterruptedException {
        System.out.println("\nCalling Minecraft Command\n");
        APIClient apiClient = new APIClient();
        FileConfiguration config = JavaPlugin.getPlugin(TheAIgodPlugin.class).getConfig();

        String instructions = "You are to make this query into a minecraft 1.20+ command with no explanations just solely give me the command: ";

        GenerateContentRequest request = requestBuilder.buildRequest(instructions+ command);

        String response = apiClient.makeRequest(request, config.getString("apiKey"));
//        System.out.println(response);

        String extractedPrompt = extractVar(response);
        System.out.println("EXSKDALSDJLSKDJLASKJDLKSAJDLKASJD\n: " + extractedPrompt);

        return extractedPrompt;
    }

    @EventHandler
    private void onPlayerJoinEvent(PlayerJoinEvent event) throws IOException, InterruptedException {
        Player player = event.getPlayer();
        GenerateContentRequest request = requestBuilder.buildRequest(player.getDisplayName() + " has joined the game, anything you want to say to them?");
        setCommands(callBardAttempt(request), player);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("The AI god has died.\n\n\n");

        for (String s : conversationHistory) {
            System.out.println(s);
        }

        conversationHistory.clear();
    }


    private String callBardAttempt(GenerateContentRequest request) throws IOException, InterruptedException {
        System.out.println("\ncalling bard attempt\n");
        APIClient apiClient = new APIClient();
        FileConfiguration config = JavaPlugin.getPlugin(TheAIgodPlugin.class).getConfig();

        String response = null;
        try {
            System.out.println("Requested Prompt: " +request.getPrompt());
            conversationHistory.add(request.getPrompt());
            response = apiClient.makeRequest(request, config.getString("apiKey"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String extractedPrompt = extractVar(response);
        conversationHistory.add(extractedPrompt);

        String newExtractedPrompt = turnToMinecraftCommand(extractedPrompt);
        System.out.println("New Extracted Prompt: " + newExtractedPrompt);

        return newExtractedPrompt;
    }

    private void chooseBehaviours(List<String> behaviours){
        Collections.shuffle(behaviours);
        int dislikeCount = 2;
        int likeCount = 2;
        // Have to make sure we don't request more behaviors than actually exist.
        if (behaviours.size() >= likeCount + dislikeCount) {
            List<String> likes = behaviours.subList(0, likeCount);
            List<String> dislikes = behaviours.subList(likeCount, likeCount + dislikeCount);

            System.out.println("The AI god likes the following behaviors: " + likes);
            conversationHistory.add("You love these following behaviors: " + likes);
            System.out.println("The AI god dislikes the following behaviors: " + dislikes);
            conversationHistory.add("You hate these following behaviors: " + dislikes);
        } else {
            System.out.println("Not enough behaviors to satisfy the AI god's preferences.");
        }
    }

    private void setCommands(String response, Player player){
        CommandSender sender;
        String command = response.replace("```", "");
        System.out.println("Im sending this command:" + command);

        Bukkit.getScheduler().runTaskLater(this , () -> {
                Bukkit.getServer().dispatchCommand(player, command);
            }, 1);

//        Bukkit.dispatchCommand(player, "/"+command);
    }

    @EventHandler
    public void onMessageReceived(PlayerChatEvent event) throws IOException, InterruptedException {
        Player player = event.getPlayer();
        String message = event.getMessage();

        // Send the player's message first
            player.sendMessage(player.getDisplayName() + ": " +message);
            event.setCancelled(true);

            GenerateContentRequest request = requestBuilder.buildRequest(player.getDisplayName() + ", sent the message of: " +
                    message + ", Please respond with minecraft 1.20+ commands, dont add any extra text like explanations just respond with minecraft commands! EX: tellraw @a[name=thestinkiest] [{text:Welcome to the game, thestinkiest! I'm excited to have you join us. , color:green, bold:true},{text:Let me know if you need any help getting started or if you have any questions. I'm happy to assist in any way I can., color:gold},{text:What kind of game are we playing? I'm curious! , color:light_purple}]");
            String response = callBardAttempt(request);


        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), response);




//            String command = response.replace("/","").replace("```","").replace("'''","");
//            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);


//        System.out.println(response);
//        Bukkit.broadcastMessage(response);

            // Send the command as "Boss"
//            setCommands(response, player);

//        Bukkit.dispatchCommand(player, response.replace("/", ""));




    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        if (victim.getKiller() == null) {
            Bukkit.broadcastMessage("The god has witnessed the death of " + victim.getName() + ". Justice will be served.");


        } else {
            Player killer = victim.getKiller();
            Bukkit.broadcastMessage("The god has witnessed the death of " + victim.getName() + " at the hands of " + killer.getName() + ". Justice will be served.");
        }
    }


    public static String extractVar(String line){
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(line).getAsJsonObject();

        JsonArray candidates = jsonObject.getAsJsonArray("candidates");
        for (JsonElement candidate : candidates) {
            JsonObject candidateObject = candidate.getAsJsonObject();
            JsonObject contentObject = candidateObject.getAsJsonObject("content");
            JsonArray parts = contentObject.getAsJsonArray("parts");
            for (JsonElement part : parts) {
                JsonObject partObject = part.getAsJsonObject();
                return partObject.get("text").getAsString();
            }
        }
        return null;
    }

}
