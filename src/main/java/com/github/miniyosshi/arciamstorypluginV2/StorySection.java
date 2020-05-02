package com.github.miniyosshi.arciamstorypluginV2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bukkit.Location;

public class StorySection extends Element {
	
	private int[] chapterSectionNumber = new int[2];
	private String title;
	private String goal;
	private String triggerAction;
	private String triggerObject;
	//private int numberofline;
	//private List<String> sentences = new ArrayList<String>();
	private Optional<Location> viewpoint;
	
	public StorySection(int[] chapterSectionNumber, String title, String goal, String triggerAction,
			String triggerObject, Optional<Location> viewpoint) {
		this.chapterSectionNumber = chapterSectionNumber;
		this.title = title;
		this.goal = goal;
		this.triggerAction = triggerAction;
		this.triggerObject = triggerObject;
		this.viewpoint = viewpoint;
	}
	
	public boolean hasNext() {
		int currentIndex = StorySections.getInstance().indexOf(this);
		if(currentIndex == -1) {
			System.out.println("There is no such a story section.");
			return false;
		}
		if(currentIndex >= StorySections.getInstance().size()-1) {
			System.out.println("There is no more story section.");
			return false;
		}
		return true;
	}
	
	public int[] getChapterSectionNumber() {
		return chapterSectionNumber;
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
	/*
	public static boolean numberBetweeness (double a, double p, double q) {
		if ((p <= a && a <= q) || (q <= a && a <= p))
			return true;
		else
			return false;
	}
	*/
	
	

}