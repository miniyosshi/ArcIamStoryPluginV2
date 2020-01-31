package com.github.miniyosshi.arciamstoryplugin;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class User implements IsInAreaOf {
	private Player player;
	private String name;
	private Location savedlocation;
	private int chapter;
	private int section;
	
	private int[] birthday;
	private boolean hardmode;
	
	
	private AreaData pastarea;
	private boolean instoryevent = false;
	
	
	User(String name, Location savedlocation, int chapter, int section, int[] birthday, boolean hardmode){
		this.name = name;
		this.savedlocation = savedlocation;
		this.chapter = chapter;
		this.section = section;
		this.birthday = birthday;
		this.hardmode = hardmode;
	}
	
	User(Player player){
		this.player = player;
	}
	
	//User→Player
	public Player getPlayer() {
		return player;
	}
	
	//Player→User
	public static User getUser(Player p) {
		for (User u : List.userdata) {
			if (u.getName().equals(p.getPlayer().getName())) {
				return u;
			}
		}
		System.out.println("Player to User error. So NULL is returned.");
		return null;
	}
	
	//String(Name)→User
	public static User getUser(String  name) {
		for (User u : List.userdata) {
			if (u.getName().equals(name)) {
				return u;
			}
		}
		System.out.println("String(Name) to User error. So NULL is returned.");
		return null;
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
	
	int[] getBirthday() {
		return birthday;
	}
	
	boolean getHardmode() {
		return hardmode;
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
	
	void saveCurrentLocation() {
		this.savedlocation = player.getLocation();
		CSVExporter.exportCSV("UserData");
	}
	
	void setChapter(int i) {
		chapter = i;
	}
	void setSection(int i) {
		section = i;
	}
	
	void setBirthday(int year, int month, int day) {
		birthday[0] = year;
		birthday[1] = month;
		birthday[2] = day;
	}
	
	void setHardmode(boolean x) {
		hardmode = x; 
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
		
		for(int i=0; i<List.chapterdata.size(); i++) {
			//chapter,sectionが一致したら
			if(List.chapterdata.get(i).getChapter() == getChapter()&&List.chapterdata.get(i).getSection()== getSection()) {
				
				ChapterData ncd = List.chapterdata.get(i+1);
				
				setChapter(ncd.getChapter());
				setSection(ncd.getSection());
				/*
				//次のchapterかどうか
				if(List.chapterdata.get(i).getChapter()==List.chapterdata.get(i+1).getChapter()) {
					setSection(getSection()+1);
				}
				else {
					setChapter(getChapter()+1);
					setSection(1);
				}
				*/
				
			break;
			}	
		}
		CSVExporter.exportCSV(CSVFiles.UserData.toString());
	}
	
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
		 for (AreaData area : List.areadata) {
			 
			 if (isInAreaOf(area) == true) {
				 return area;
			 }
		 }
		 return List.areadata.get(0);
	}
		


}
