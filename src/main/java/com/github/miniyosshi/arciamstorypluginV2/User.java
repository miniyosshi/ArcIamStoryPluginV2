package com.github.miniyosshi.arciamstorypluginV2;

import java.util.Optional;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
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
	protected Optional<Player> player = Optional.empty();
	
	@JsonProperty
	protected UserInfo userInfo = new UserInfo();
	
	public User(String name, Player player, UserInfo userInfo) {
		this.name = name;
		this.player = Optional.of(player);
		setInfo(userInfo);
		Users.getInstance().add(this);
	}
	
	public User(String name, UserInfo userInfo) {
		this.name = name;
		this.userInfo = userInfo;
		Users.getInstance().add(this);
	}
	
	public void setPlayer(Player player) {
		this.player = Optional.of(player);
	}
	
	public void setInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	@JsonIgnore
	public Optional<DesignatedArea> isIn() {
		return player.map(v->{
			Location location = v.getLocation();
			DesignatedAreas das = DesignatedAreas.getInstance();
			return das.getElementBy(location);
		}).orElse(Optional.empty());
	}
	
	public boolean isIn(DesignatedArea designatedArea) {
		return player.map(v->{
			Location location = v.getLocation();
			return designatedArea.contains(location);
		}).orElse(false);
		
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
		teleportTo("Lobby");
	}
	
	public void teleportTo(String spotName) {
		DesignatedSpots.getInstance().getElementBy(spotName).ifPresent(v ->{
			player.ifPresent(w->{
				w.teleport(v.getLocation());
			});
		});
	}
	
	public void teleportToRandomSpotIn(DesignatedArea da) {
		player.ifPresent(v->{
			v.teleport(da.getRandomLocation());
		});
	}
	
	public void continueAfterDeath() {
		teleportTo("Hospital");
		if (this instanceof HardModeUser) {
			//一応インベントリ抽出保存
			
			//player.getInventory().clear();
			//ステータス初期化
			
		}
		//Zombie
		generateCustomZombie();
	}
	
	public void generateCustomZombie() {
		//ゾンビ出現（プレーヤーと同じステータス）
		player.ifPresent(v->{
			ItemStack skull = new ItemStack(Material.SKELETON_SKULL);
			SkullMeta sm = (SkullMeta) skull.getItemMeta();
			sm.setOwningPlayer(v);
			System.out.println(sm.getOwningPlayer().getName());
			skull.setItemMeta(sm);
			
			Location location = v.getLocation();
			
			Zombie s = (Zombie) v.getWorld().spawnEntity(location, EntityType.ZOMBIE);
			s.setCustomNameVisible(true);
			s.setCustomName(v.getName() + "の哀れな姿");
			s.getEquipment().setHelmet(skull);
			s.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SWORD,1));
			
			//Mob m = s;
			
			//ステータス
			s.setHealth(0.5);
			// walk speed
		});
		
	}
	

}
