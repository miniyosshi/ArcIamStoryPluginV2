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
	
	
	
	public static void eventCheck(String trigger) {
		
		if(trigger.equals("click")) {
			
		}
		
		if(trigger.equals("enter")) {
			
		}
	}
	
}
