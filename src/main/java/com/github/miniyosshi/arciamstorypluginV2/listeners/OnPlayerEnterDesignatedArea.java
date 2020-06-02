package com.github.miniyosshi.arciamstorypluginV2.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Score;

import com.github.miniyosshi.arciamstorypluginV2.*;

public class OnPlayerEnterDesignatedArea implements Listener {
	
	public OnPlayerEnterDesignatedArea(Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void enterDesignatedAreaEvent(PlayerMoveEvent e) {
		
		Player player = e.getPlayer();
		
		Users.getInstance().getElementBy(player).ifPresent(user -> {
			Location pastLocation = e.getFrom();
			Location presentLocation = e.getTo();
			if (pastLocation != presentLocation) {
				if (!user.isInTheSameDesignatedArea()) {
					user.isIn().ifPresent(da ->{
						player.sendMessage(ChatColor.AQUA + "〈" +da.getName()+ "〉 " +"にやって来ました");
						player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);	
					});
					user.setHereAsPastDesignatedArea();
					
					//Scoreboard
					//Score s = player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore("場所:");
					//s.setScore(100);
					
					//StoryProcessor.eventCheck(u, "enter", presentarea.getName());
					
					
				}				
			}	
		});
	}
}
