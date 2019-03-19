package com.github.miniyosshi.arciamstoryplugin;


public class ChapterData {
	int chapter;
	int section;
	String trigger;
	int numberofline;
	String title;
	String goal;
	
	
	ChapterData(int chapter, int section, String trigger, int numberofline, String title, String goal){
		this.chapter = chapter;
		this.section = section;
		this.trigger = trigger;
		this.numberofline = numberofline;
		this.title = title;
		this.goal = goal;		
		
	}
	
	String getTitle() {
		return title;
	}
	
	String getGoal() {
		return goal;
	}
	
	String getTrigger() {
		return trigger;
	}
	
	int getChapter() {
		return chapter;
	}
	int getSection() {
		return section;
	}
}
