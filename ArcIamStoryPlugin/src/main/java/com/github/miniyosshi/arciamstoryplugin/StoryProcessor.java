package com.github.miniyosshi.arciamstoryplugin;

import java.util.Timer;

public class StoryProcessor {
	
	public static void addChapterSectionNumber(User u) {
		
		for(int i=0; i<100; i++) {
			//chapter,sectionが一致したら
			if(CSVReader.chapterdata.get(i).getChapter()==u.getChapter()&&CSVReader.chapterdata.get(i).getSection()==u.getSection()) {
				//次のchapterかどうか
				if(CSVReader.chapterdata.get(i).getChapter()==CSVReader.chapterdata.get(i+1).getChapter()) {
					u.setSection(u.getSection()+1);
				}
				else {
					u.setChapter(u.getChapter()+1);
					u.setSection(1);
				}
			break;
			}	
		}
		CSVExporter.exportCSV("UserData.csv");
	}
	
	public static void processLine(User u) {
		
		int i = 0;
		int lineno = 0;
		while(CSVReader.chapterdata.get(i).getChapter()<=u.getChapter()&&CSVReader.chapterdata.get(i).getSection()<u.getSection() ) {
			lineno += CSVReader.chapterdata.get(i).getNumberOfLines();
			i++;
		}
		
		
		//timertask, timer はインスタンスの再利用ができないらしい
		
		Timer timer = new Timer();
		TimerTaskLine ttl = new TimerTaskLine(u, lineno, CSVReader.chapterdata.get(i).getNumberOfLines(), timer);
		timer.schedule(ttl,0,5000);	
				
	}
	
	
	
	public static void eventCheck(User u, String trigger, String triggerobject) {
		
		for(ChapterData cd: CSVReader.chapterdata) {
			if(cd.getChapter()==u.getChapter()&&cd.getSection()==u.getSection()) {
				
				if(trigger.equals("click")&&cd.getTrigger().equals("click")) {
					
					if(cd.getTriggerObject().equals(triggerobject)) {
						
						u.getPlayer().sendMessage("clickイベントあり");
						u.setInStoryEvent(true);
						processLine(u);
						System.out.println("よりあとの部分が先に出る。");
						//addChapterSectionNumber(u);
						//eventCheck(u, "auto", "auto");
						
												
					}
									
				}
				
				if(trigger.equals("enter")&&cd.getTrigger().equals("enter")) {
					
					if(cd.getTriggerObject().equals(triggerobject)) {

						u.getPlayer().sendMessage("enterイベントあり");
						
						processLine(u);
						addChapterSectionNumber(u);
						eventCheck(u, "auto", "auto");
					}
				}
				
				if(trigger.equals("auto")&&cd.getTrigger().equals("auto")) {
					u.getPlayer().sendMessage("autoイベントあり");
					
					processLine(u);
					addChapterSectionNumber(u);
					eventCheck(u, "auto", "auto");
					
				}
				
				
				
			}
		}
		
		
		
	}
	
}
