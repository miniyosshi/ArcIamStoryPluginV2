package com.github.miniyosshi.arciamstoryplugin;

import org.bukkit.Location;

public class ViewPoint {
	private int chapter;
	private int section;
	private Location loc;
	
	ViewPoint(int chapter, int section, Location loc){
		this.chapter = chapter;
		this.section = section;
		this.loc = loc;
	}
	
	
}
