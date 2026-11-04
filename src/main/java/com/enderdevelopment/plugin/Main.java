package com.enderdevelopment.plugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player deadPlayer = event.getEntity();
        String deathMessage = event.getDeathMessage();

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getWorld().equals(deadPlayer.getWorld()) &&
                player.getLocation().distance(deadPlayer.getLocation()) <= 500) {
                player.sendMessage(deathMessage);
            }
        }

        event.setDeathMessage(null);
    }
}