package com.github.miniyosshi.arciamstoryplugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import com.github.miniyosshi.economy.*;

public class OnPlayerTradewithVillager implements Listener {
	public OnPlayerTradewithVillager(Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void clickEvent(InventoryClickEvent e) {
		
		Player p = (Player) e.getWhoClicked();
		ItemStack item = e.getCurrentItem();
				
		if(e.getInventory().getType().equals(InventoryType.MERCHANT)) {
			
			//payment
			if(User.getUser(p)>= item.getItemMeta().get) {
				Wallet a = 
				User.getUser(p)
				
				p.getInventory().setItem(1, e.getCurrentItem());
				p.sendMessage(e.getCurrentItem()+"を購入しました。");
			}
			else {
				p.sendMessage("お金が足りません。");
			}
			
			
		}
		
	}
	
}
