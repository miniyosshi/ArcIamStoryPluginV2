package com.github.miniyosshi.arciamstoryplugin;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class User   {
	Player player;
	String name;
	Location savedlocation;
	int chapter;
	int section;
	AreaData pastarea;
	
	
	User(String name, Location savedlocation, int chapter, int section){
		this.name = name;
		this.savedlocation = savedlocation;
		this.chapter = chapter;
		this.section = section;
	}
	
	User(Player player){
		this.player = player;
	}
	
	
	String getName() {
		return name;
	}
	
	/*
	@SuppressWarnings("deprecation")
	Player getPlayer() {
		return Bukkit.getPlayer(name);
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

		
	public boolean isInAreaOf(AreaData area) {
		 //プレーヤーが特定の場所にいるかどうか返す
		return numberBetweeness(player.getLocation().getX(), area.cornerA.getX(), area.cornerB.getX())
				 && numberBetweeness(player.getLocation().getZ(), area.cornerA.getZ(), area.cornerB.getZ());
	 }
		
	public AreaData isInAreaOf() {
		 //プレーヤーがどの場所にいるか返す(データセットで帰ってくる)
		 for (AreaData area : CSVReader.areadata) {
			 
			 if (isInAreaOf(area) == true) {
				 return area;
			 }
		 }
		 return null; //天才
	}
	
	public void saveLocation() {
		for (User u : CSVReader.userdata) {
			if (u.getName().equals(name)) {
				u.savedlocation = u.player.getLocation();
				
				//ここでcsvに書き込み
				CSVEditor.exportCSV();
				
			}
		}
	}
		


}
