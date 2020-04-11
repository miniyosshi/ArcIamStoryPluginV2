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

public abstract class User extends MapElement {
	@JsonIgnore
	protected Player player;
	
	protected UserInfo userInfo;
	
	//private boolean instoryevent = false;
	
	/*
	public User(Player player) {
		this.player = player;
		UserRepository.addUser(this);
	}
	*/
	
	public User(Player player, UserInfo userInfo) {
		this.player = player;
		setInfo(userInfo);
		Users ur = Users.getInstance();
		ur.add(this);
	}
	
	@JsonCreator
	public User(UserInfo userInfo) {
		this.userInfo = userInfo;
		Users.getInstance().add(this);
	}
	
	public void setInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	public Optional<DesignatedArea> isIn() {
		Location location = player.getLocation();
		DesignatedAreas das = DesignatedAreas.getInstance();
		return das.getElementBy(location);
	}
	
	public boolean isIn(DesignatedArea designatedArea) {
		Location location = player.getLocation();
		return designatedArea.contains(location);
	}
	
	public boolean isInTheSameDesignatedArea() {
		Optional<DesignatedArea> presentArea= this.isIn();
		return userInfo.pastDesignatedAreaEquals(presentArea);
	}
	
	public void setHereAsPastDesignatedArea() {
		userInfo.setPastDesignatedArea(this.isIn());
	}
	
	public void saveCurrentLocation() {
		userInfo.setSavedLocation(player.getLocation());
		//CSVExporter.exportCSV("UserData");
	}
	
	
	public void continueAfterDeath() {
		//Teleport to hospital
		DesignatedSpots dss = DesignatedSpots.getInstance();
		Optional<DesignatedSpot> ds = dss.getElementBy("hospital");
		ds.ifPresent(v -> {player.teleport(v.getLocation());});
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
