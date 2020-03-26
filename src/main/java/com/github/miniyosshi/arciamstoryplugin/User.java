package com.github.miniyosshi.arciamstoryplugin;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public abstract class User {
	private Player player;
	private String name;
	private Location savedLocation;
	
	private ChapterSection chapterSection;
	private Birthday birthday;
	
	//private AreaData pastarea;
	//private boolean instoryevent = false;
	
	
	//constructor
	public User(Player player) {
		this.player = player;
		Users.addUser(this);
	}
	/*
	public User(String name, Location savedLocation, int chapter, int section, int[] birthday){
		this.name = name;
		this.savedLocation = savedLocation;
		this.chapter = chapter;
		this.section = section;
		this.birthday = birthday;
	}
	*/
	
	public String getName() {
		return player.getName();
	}
	
	public void saveCurrentLocation() {
		this.savedLocation = player.getLocation();
		//CSVExporter.exportCSV("UserData");
	}
	

}
