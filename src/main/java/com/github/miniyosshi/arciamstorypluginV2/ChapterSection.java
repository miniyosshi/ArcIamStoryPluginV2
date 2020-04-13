package com.github.miniyosshi.arciamstorypluginV2;

public class ChapterSection {
	
	private int[] chapterSection = new int[2];
	
	public ChapterSection(int chapter, int section) {
		chapterSection[0] = chapter;
		chapterSection[1] = section;
	}
	
	/*
	public void addChapterSectionNumber() {
		for(int i=0; i<List.chapterdata.size(); i++) {
			//chapter,sectionが一致したら
			if(List.chapterdata.get(i).getChapter() == getChapter()&&List.chapterdata.get(i).getSection()== getSection()) {
				
				ChapterData ncd = List.chapterdata.get(i+1);
				
				setChapter(ncd.getChapter());
				setSection(ncd.getSection());
				
				//次のchapterかどうか
				if(List.chapterdata.get(i).getChapter()==List.chapterdata.get(i+1).getChapter()) {
					setSection(getSection()+1);
				}
				else {
					setChapter(getChapter()+1);
					setSection(1);
				}
				
				
			break;
			}	
		}
		CSVExporter.exportCSV(CSVFiles.UserData.toString());
	}
	*/
	
	/*
	public void subtractChapterSectionNumber() {
		for(int i=0; i<List.chapterdata.size(); i++) {
			//chapter,sectionが一致したら
			if(List.chapterdata.get(i).getChapter() == getChapter()&&List.chapterdata.get(i).getSection()== getSection()) {
				
				ChapterData pcd = List.chapterdata.get(i-1);
				
				setChapter(pcd.getChapter());
				setSection(pcd.getSection());
				
			break;
			}
		}
			CSVExporter.exportCSV(CSVFiles.UserData.toString());
	}
	*/
	
	//座標判定メソッド改訂版
	
	//ある数字がある二つの数字の間にあるかどうかp--a--q,q--a--p
	public static boolean numberBetweeness (double a, double p, double q) {
		if ((p <= a && a <= q) || (q <= a && a <= p))
			return true;
		else
			return false;
		}
	
	

}
