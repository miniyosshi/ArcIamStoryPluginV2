package com.github.miniyosshi.arciamstoryplugin;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class User implements IsInAreaOf {
	private Player player;
	private String name;
	private Location savedlocation;
	private int chapter;
	private int section;
	private AreaData pastarea;
	private boolean instoryevent = false;
	
	
	User(String name, Location savedlocation, int chapter, int section){
		this.name = name;
		this.savedlocation = savedlocation;
		this.chapter = chapter;
		this.section = section;
	}
	
	User(Player player){
		this.player = player;
	}
	
	//User→Player
	Player getPlayer() {
		return player;
	}
	
	//Player→User
	static User getUser(Player p) {
		for (User u : CSVReader.userdata) {
			if (u.getName().equals(p.getPlayer().getName())) {
				return u;
			}
		}
		System.out.println("Player to User error. So 0th player is returned.");
		return 	CSVReader.userdata.get(0);	
	}
		
	String getName() {
		return name;
	}
	int getChapter() {
		return chapter;
	}
	int getSection() {
		return section;
	}
	Location getSavedLocation() {
		return savedlocation;
	}
	
	boolean getInStoryEvent() {
		return instoryevent;
	}
	
	AreaData getPastArea() {
		return pastarea;
	}
	
	void setPlayer(Player player) {
		this.player = player;
	}
	
	void setChapter(int i) {
		chapter = i;
	}
	void setSection(int i) {
		section = i;
	}
	
	void setInStoryEvent(boolean b) {
		instoryevent = b;
	}
	
	void setPastarea(AreaData a) {
		pastarea = a;
	}
	
	/*
	@SuppressWarnings("deprecation")
	Player getPlayer() {
		return Bukkit.getPlayer(name);
	}
	*/

	public void addChapterSectionNumber() {
		
		for(int i=0; i<100; i++) {
			//chapter,sectionが一致したら
			if(CSVReader.chapterdata.get(i).getChapter()== getChapter()&&CSVReader.chapterdata.get(i).getSection()== getSection()) {
				//次のchapterかどうか
				if(CSVReader.chapterdata.get(i).getChapter()==CSVReader.chapterdata.get(i+1).getChapter()) {
					setSection(getSection()+1);
				}
				else {
					setChapter(getChapter()+1);
					setSection(1);
				}
			break;
			}	
		}
		CSVExporter.exportCSV("UserData.csv");
	}
	
	
	//座標判定メソッド改訂版
	
	//ある数字がある二つの数字の間にあるかどうかp--a--q,q--a--p
	public static boolean numberBetweeness (double a, double p, double q) {
		if ((p <= a && a <= q) || (q <= a && a <= p))
			return true;
		else
			return false;
		}

	
	@Override
	public boolean isInAreaOf(AreaData a) {
		 //プレーヤーが特定の場所にいるかどうか返す
		Location loc = player.getLocation();
		
		return numberBetweeness(loc.getX(), a.getcornerA().getX(), a.getcornerB().getX())
				 && numberBetweeness(loc.getZ(), a.getcornerA().getZ(), a.getcornerB().getZ());
	 }
	
	@Override
	public AreaData isInAreaOf() {
		 //プレーヤーがどの場所にいるか返す(データセットで帰ってくる)
		 for (AreaData area : CSVReader.areadata) {
			 
			 if (isInAreaOf(area) == true) {
				 return area;
			 }
		 }
		 System.out.println("Such AreaData does not exist. So 0th AreaData is returned.");
		 return CSVReader.areadata.get(0);
	}
	
	public void saveLocation() {
		for (User u : CSVReader.userdata) {
			if (u.getName().equals(name)) {
				u.savedlocation = u.player.getLocation();
				
				//ここでcsvに書き込み
				CSVExporter.exportCSV("UserData.csv");
				
			}
		}
	}
		


}
