package com.github.miniyosshi.arciamstorypluginV2;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public abstract class User {
	private Player player;
	private Location savedLocation;
	private ChapterSection chapterSection;
	private Birthday birthday;
	
	//private AreaData pastarea;
	//private boolean instoryevent = false;
	
	
	//constructor
	/*
	public User(Player player) {
		this.player = player;
		Users.addUser(this);
	}
	*/
	public User(Player player, Location savedLocation, ChapterSection chapterSection, Birthday birthday){
		this.player = player;
		this.savedLocation = savedLocation;
		this.chapterSection = chapterSection;
		this.birthday = birthday;
		Users.addUser(this);
	}
	
	public String getName() {
		return player.getName();
	}
	
	public void saveCurrentLocation() {
		this.savedLocation = player.getLocation();
		//CSVExporter.exportCSV("UserData");
	}
	

}
