package com.github.miniyosshi.arciamstorypluginV2;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class LLItem extends MapElement {
	private Material material;
	private ItemMeta meta;
	private double soldPrice;
	
	public LLItem(String name, Material material, ItemMeta meta) {
		this.name = name;
		this.material = material;
		this.meta = meta;
		LLItems items = LLItems.getInstance();
		items.add(this);
	}
	
	public void aaa() {
		Player p;
		//p.getInventory().getItemInMainHand().setItemMeta(itemMeta)
	}
	
	//@JsonIgnore
	
	

}
