package com.github.miniyosshi.arciamstorypluginV2.listeners;

import java.util.Optional;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;

import com.github.miniyosshi.arciamstorypluginV2.User;
import com.github.miniyosshi.arciamstorypluginV2.Users;

public class OnPlayerDeath implements Listener {
	
	public OnPlayerDeath(Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerDeath (PlayerDeathEvent e) {
		Player player = e.getEntity();
		Users users = Users.getInstance();
		Optional<User> user = users.getElementBy(player);
		user.ifPresent(v -> v.continueAfterDeath());
	}
}
