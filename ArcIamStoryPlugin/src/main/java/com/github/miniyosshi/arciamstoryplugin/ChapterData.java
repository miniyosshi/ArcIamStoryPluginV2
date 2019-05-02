package com.github.miniyosshi.arciamstoryplugin;


public class ChapterData {
	private int chapter;
	private int section;
	private String trigger;
	private String triggerobject;
	private int numberofline;
	private String title;
	private String goal;
	
	
	ChapterData(int chapter, int section, String trigger, String triggerobject, int numberofline, String title, String goal){
		this.chapter = chapter;
		this.section = section;
		this.trigger = trigger;
		this.triggerobject = triggerobject;
		this.numberofline = numberofline;
		this.title = title;
		this.goal = goal;		
		
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
	
	
}
