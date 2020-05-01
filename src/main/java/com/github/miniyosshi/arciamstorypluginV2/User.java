package com.github.miniyosshi.arciamstorypluginV2;

import java.util.Optional;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class User extends Element {
	@JsonIgnore
	protected Player player;
	
	@JsonProperty
	protected UserInfo userInfo;
	
	public User(String name, Player player, UserInfo userInfo) {
		this.name = name;
		this.player = player;
		setInfo(userInfo);
		Users.getInstance().add(this);
	}
	
	@JsonCreator
	public User(String name, UserInfo userInfo) {
		this.name = name;
		this.userInfo = userInfo;
		Users.getInstance().add(this);
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public void setInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	@JsonIgnore
	public Optional<DesignatedArea> isIn() {
		Location location = player.getLocation();
		DesignatedAreas das = DesignatedAreas.getInstance();
		return das.getElementBy(location);
	}
	
	public boolean isIn(DesignatedArea designatedArea) {
		Location location = player.getLocation();
		return designatedArea.contains(location);
	}
	
	@JsonIgnore
	public boolean isInTheSameDesignatedArea() {
		Optional<DesignatedArea> presentArea = this.isIn();
		return userInfo.pastDesignatedAreaEquals(presentArea);
	}
	
	public void setHereAsPastDesignatedArea() {
		userInfo.setPastDesignatedArea(this.isIn());
	}
	
	@JsonIgnore
	public boolean isInStoryEvent() {
		return userInfo.isInStoryEvent();
	}
	
	public void teleportToLobby() {
		DesignatedSpots.getInstance().getElementBy("Lobby").ifPresent(v ->{
			player.teleport(v.getLocation());
		});
	}
	
	public void continueAfterDeath() {
		//Teleport to hospital
		DesignatedSpots dss = DesignatedSpots.getInstance();
		Optional<DesignatedSpot> hospital = dss.getElementBy("Hospital");
		hospital.ifPresent(v -> {player.teleport(v.getLocation());});
		if (this instanceof HardModeUser) {
			//一応インベントリ抽出保存
			
			player.getInventory().clear();
			//ステータス初期化
			
		}
		//Zombie
		generateCustomZombie();
	}
	
	public void generateCustomZombie() {
		//ゾンビ出現（プレーヤーと同じステータス）
		ItemStack skull = new ItemStack(Material.SKELETON_SKULL);
		SkullMeta sm = (SkullMeta) skull.getItemMeta();
		sm.setOwningPlayer(player);
		skull.setItemMeta(sm);
		
		Location location =player.getLocation();
		
		Zombie s = (Zombie) player.getWorld().spawnEntity(location, EntityType.ZOMBIE);
		s.setCustomNameVisible(true);
		s.setCustomName(player.getName() + "の哀れな姿");
		s.getEquipment().setHelmet(skull);
		s.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SWORD,1));
		
		//ステータス
		s.setHealth(1);
		// walk speed
	}
	

}
