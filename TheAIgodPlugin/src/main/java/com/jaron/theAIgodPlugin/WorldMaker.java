package com.jaron.theAIgodPlugin;

import org.bukkit.WorldCreator;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class WorldMaker implements Listener {

    @EventHandler
    public void onPlayerLogin(AsyncPlayerPreLoginEvent event) {
        if (event.getName().equals("thestinkiest")){
            event.setLoginResult(AsyncPlayerPreLoginEvent.Result.ALLOWED);
            System.out.println("we rejected him");
        }else{
            System.out.println("Let em through!");
        }
    }

}