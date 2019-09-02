package com.github.miniyosshi.arciamstoryplugin;

import org.bukkit.Location;

public class ChapterData {
	private int chapter;
	private int section;
	private String trigger;
	private String triggerobject;
	private int numberofline;
	private String title;
	private String goal;
	private Location viewpoint;
	
	
	ChapterData(int chapter, int section, String trigger, String triggerobject, int numberofline, String title, String goal){
		this.chapter = chapter;
		this.section = section;
		this.trigger = trigger;
		this.triggerobject = triggerobject;
		this.numberofline = numberofline;
		this.title = title;
		this.goal = goal;			
	}
	
	ChapterData(int chapter, int section, Location loc){
		
	}
	
	int getChapter() {
		return chapter;
	}
	int getSection() {
		return section;
	}
	
	String getTrigger() {
		return trigger;
	}
	
	String getTriggerObject() {
		return triggerobject;
	}
	
	int getNumberOfLines() {
		return numberofline;
	}
	

	String getTitle() {
		return title;
	}
	
	String getGoal() {
		return goal;
	}
	
	void setViewPoint(Location loc) {
		this.viewpoint = loc;
	}
	
	Location getViewPoint() {
		return viewpoint;
	}
	
	
}
