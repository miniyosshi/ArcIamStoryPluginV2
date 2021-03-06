package com.github.miniyosshi.arciamstorypluginV2;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class User extends Element {
	@JsonIgnore
	protected Optional<Player> player = Optional.empty();
	@JsonProperty
	protected UserInfo userInfo = new UserInfo();
	
	public User(String name, Player player, UserInfo userInfo) {
		this.name = name;
		this.player = Optional.of(player);
		this.userInfo = userInfo;
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
	
	
	/*
	 * Methods relating to DesignatedArea, teleport
	 */
	
	@JsonIgnore
	public Set<DesignatedArea> isIn() {
		return player.map(v->{
			Location location = v.getLocation();
			DesignatedAreas das = DesignatedAreas.getInstance();
			return das.getAllElementsBy(location);
		}).orElse(new HashSet<>());
	}
	
	public boolean isIn(DesignatedArea designatedArea) {
		return player.map(v->{
			Location location = v.getLocation();
			return designatedArea.contains(location);
		}).orElse(false);	
	}
	
	@JsonIgnore
	public boolean isInTheSameDesignatedArea() {
		Set<DesignatedArea> presentArea = this.isIn();
		return userInfo.pastDesignatedAreaEquals(presentArea);
	}
	
	public void setHereAsPastDesignatedArea() {
		userInfo.setPastDesignatedArea(this.isIn());
	}
	
	public Set<DesignatedArea> pastDesignatedAreaRetain(Set<DesignatedArea> presentArea){
		return userInfo.pastDesignatedAreaRetain(presentArea);
	}
	
	public Set<DesignatedArea> getUserNewlyEnteringDesignatedAreas(Set<DesignatedArea> presentArea){
		return userInfo.getUserNewlyEnteringDesignatedAreas(presentArea);
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
	
	public void teleportTo(DesignatedSpot ds) {
		player.ifPresent(v->{
			v.teleport(ds.getLocation());
		});
	}
	
	public void teleportToRandomSpotIn(DesignatedArea da) {
		player.ifPresent(v->{
			v.teleport(da.getRandomLocation());
		});
	}
	
	
	/*
	 * Methods relating to event
	 */
	
	@JsonIgnore
	public boolean isInStoryEvent() {
		return userInfo.isInStoryEvent();
	}
	
	public void setIsInStoryEvent(boolean b) {
		userInfo.setIsInStoryEvent(b);
	}
	
	@JsonIgnore
	public boolean logoutInStoryEvent() {
		return userInfo.logoutInStoryEvent();
	}
	
	public void setLogoutInStoryEvent(boolean b) {
		userInfo.setLogoutInStoryEvent(b);
	}
	
	public boolean checkEvent(String axis, String triggerAction, String triggerObject) {
		return userInfo.checkEvent(axis, triggerAction, triggerObject);
	}
	
	public Optional<String> checkEventandReturnAxis(String triggerAction, String triggerObject) {
		return userInfo.checkEventAndReturnAxis(triggerAction, triggerObject);
	}
	
	
	/*
	 * Methods relating to ChapterSection StoryEvents.
	 */
	
	public void setNextStorySection(String axis) {
		userInfo.setNextStorySection(axis);
	}
	
	public void processLine(String axis) {
		long ms = userInfo.runStorySentences(this, axis);
		int tick = (int) (20*ms/1000);
		player.ifPresent(v->{
			v.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, tick, 0));
			//視点変更
			this.teleportTo("");
			});
		// when story correctly end, setNextStorySection and setIsInStoryEvent(false) are automatically done
	}
	
	
	/*
	 * Methods relating to Assets and transaction.
	 */
	
	public String showCash() {
		return userInfo.showCash();
	}
	public String showDeposit() {
		return userInfo.showDeposit();
	}
	public void receive(double money) {
		sendMessage(userInfo.receive(money) ? money+" を受け取りました。" : "うまく受け取れませんでした。");
	}
	public boolean pay(double money) {
		boolean x = userInfo.pay(money);
		sendMessage(x ? money+" を支払いました。" : "うまく支払えませんでした。");
		return x;
	}
	public void withdraw(double money) {
		sendMessage(userInfo.withdraw(money) ? money+" を引き出しました。" : "うまく引き出せませんでした。");
	}
	public void deposit(double money) {
		sendMessage(userInfo.deposit(money) ? money+" を預けました。" : "うまく預けられませんでした。");
	}
	public void buy(LLItem item) {
		player.ifPresent(v->{
			if(pay(item.getSoldPrice())) {
				item.generate(1);
				//add item to inventory
			}
		});	
	}
	
	/*
	 * Methods relating to Ability
	 */
	
	public void setAbilityWalkspeed() {
		player.ifPresent(v->userInfo.reflectWalkspeed(v));
	}
	
	public void setWalkspeedZero() {
		player.ifPresent(v->v.setWalkSpeed(0));
	}
	
	
	
	public Optional<Mob> summonNPC(NPC npc) {
		return player.map(v->{
			Location loc = v.getLocation().add(v.getEyeLocation().toVector().normalize().multiply(2));
			Mob mob = npc.spawnAt(loc);
			System.out.println("mob " +mob);
			return mob;
		});
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
	
	public void sendMessage(String message) {
		player.ifPresent(v->v.sendMessage(message));
	}
	
	@JsonIgnore
	public boolean isOnline() {
		return player.map(v-> v.isOnline()).orElse(false);
	}
	
	public void sendMessage(String person, String message) {
		sendMessage(ChatColor.BLUE + "[" + person + "] " + ChatColor.WHITE + message);
	}

}
