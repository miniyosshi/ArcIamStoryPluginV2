package com.github.miniyosshi.arciamstoryplugin;

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
			}	
		}	
	}
	
	public static void eventCheck(User u, String trigger) {
		
		for(ChapterData cd: CSVReader.chapterdata) {
			if(cd.getChapter()==u.getChapter()&&cd.getSection()==u.getSection()) {
				
				if(trigger.equals("click")&&cd.getTrigger().equals("click")) {
					u.getPlayer().sendMessage("clickイベント");
					
					
					
				}
				
				if(trigger.equals("enter")&&cd.getTrigger().equals("enter")) {
					u.getPlayer().sendMessage("enterイベント");
				}
				
				if(trigger.equals("auto")&&cd.getTrigger().equals("auto")) {
					u.getPlayer().sendMessage("autoイベント");
				}
				
				
				
			}
		}
		
		
		
	}
	
}
