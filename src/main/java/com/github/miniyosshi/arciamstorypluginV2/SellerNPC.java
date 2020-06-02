package com.github.miniyosshi.arciamstorypluginV2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SellerNPC extends NPC {
	
	private Inventory inventory = Bukkit.createInventory(null, 18, "SHOP");
	private List<LLItem> items = new ArrayList<LLItem>();
	
	@JsonCreator
	public SellerNPC(@JsonProperty("name")String name,
					 @JsonProperty("hasAI")boolean hasAI, @JsonProperty("target")Optional<LivingEntity> target,
					 @JsonProperty("items")List<LLItem> items) {
		super(EntityType.VILLAGER, name, true, hasAI, target);
		this.items = items;
		
		for(LLItem item : items) {
			int i = 0;
			inventory.setItem(i, item.generate(10));
			i++;
		}		
	}
	
	public void openInventoryTo(Player player) {
		player.openInventory(inventory);
	}
	
	public void sell(LLItem item, User user) {
		if(items.contains(item)) {
			user.buy(item);
		}else {
			user.sendMessage("それは売っていないよ。");
		}
	}
	
	

}
