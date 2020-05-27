package com.github.miniyosshi.arciamstorypluginV2;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LLItem extends Element {
	@JsonProperty
	private Material material;
	//metaはPlayerとかと同じ感じ
	//private ItemMeta meta;
	@JsonProperty
	private double soldPrice;
	
	@JsonCreator
	public LLItem(@JsonProperty("name")String name, @JsonProperty("material")Material material,
				  @JsonProperty("soldPrice")double soldPrice) {
		this.name = name;
		this.material = material;
		this.soldPrice = soldPrice;
		LLItems.getInstance().add(this);
	}
	
	public double getSoldPrice() {
		return soldPrice;
	}
	
	public ItemStack generate(int amount) {
		ItemStack is = new ItemStack(material, amount);
		//is.setItemMeta(meta);
		return is;
	}
	
	public void setItemMeta(ItemMeta meta) {
		//this.meta = meta;
	}


	

}
