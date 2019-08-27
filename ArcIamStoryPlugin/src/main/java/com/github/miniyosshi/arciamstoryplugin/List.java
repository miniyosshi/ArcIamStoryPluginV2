package com.github.miniyosshi.arciamstoryplugin;

import java.util.ArrayList;

public class List {
	public static ArrayList<AreaData> areadata = new ArrayList<AreaData>();
	public static ArrayList<ChapterData> chapterdata = new ArrayList<ChapterData>();
	public static ArrayList<ScenarioData> scenariodata = new ArrayList<ScenarioData>();
	public static ArrayList<User> userdata = new ArrayList<User>();
	
	
	static AreaData getAreaData(String name) {
		for (AreaData a : areadata) {
			if (a.getName().equals(name)) {
				return a;
			}
		}
		//NULL
		return null;
	}
	
	
	
}
