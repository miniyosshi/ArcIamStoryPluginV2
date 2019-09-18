package com.github.miniyosshi.arciamstoryplugin;

import java.util.Timer;

public class StoryProcessor {
	
	
	public static void processLine(User u) {
		
		int i = 0;
		int lineno = 0;
		//どこの行数からはじまるか
		while(List.chapterdata.get(i).getChapter()<=u.getChapter()&&List.chapterdata.get(i).getSection()<u.getSection() ) {
			lineno += List.chapterdata.get(i).getNumberOfLines();
			i++;
		}
		
		ChapterData cd = List.chapterdata.get(i);
		
		//timertask, timer はインスタンスの再利用ができないらしい
		
		
		u.getPlayer().setWalkSpeed(0);
		
		//視点変更
		if(cd.getViewPoint() != null) {
			u.getPlayer().teleport(cd.getViewPoint());
		}
		
		
		Timer timer = new Timer();
		TimerTaskLine ttl = new TimerTaskLine(u, cd, lineno, cd.getNumberOfLines(), timer);
		
		
		
		timer.schedule(ttl,0,5000);			
	}
	
	
	
	public static void eventCheck(User u, String trigger, String triggerobject) {
		
		for(ChapterData cd: List.chapterdata) {
			if(cd.getChapter()==u.getChapter()&&cd.getSection()==u.getSection()) {
				
				if(trigger.equals("click")&&cd.getTrigger().equals("click")) {
					
					if(cd.getTriggerObject().equals(triggerobject)) {
						
						u.getPlayer().sendMessage("clickイベントあり");
						u.setInStoryEvent(true);
						processLine(u);
						//System.out.println("よりあとの部分が先に出る。")
						eventCheck(u, "auto", "auto");
						
												
					}
									
				}
				
				if(trigger.equals("enter")&&cd.getTrigger().equals("enter")) {
					
					if(cd.getTriggerObject().equals(triggerobject)) {

						u.getPlayer().sendMessage("enterイベントあり");
						u.setInStoryEvent(true);
						processLine(u);
						
						eventCheck(u, "auto", "auto");
					}
				}
				
				if(trigger.equals("auto")&&cd.getTrigger().equals("auto")) {
					u.getPlayer().sendMessage("autoイベントあり");
					u.setInStoryEvent(true);
					processLine(u);
					
					eventCheck(u, "auto", "auto");
					
				}
				
				
				
			}
		}
		
		
		
	}
	
}
