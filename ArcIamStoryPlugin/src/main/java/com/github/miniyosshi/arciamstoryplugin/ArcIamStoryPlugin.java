package com.github.miniyosshi.arciamstoryplugin;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

public class ArcIamStoryPlugin extends JavaPlugin {

	@Override
	public void onEnable() {
		
		
		CSVReader cr = new CSVReader();
		
		//file check
		File f1 = new File("AreaData.csv");
		File f2 = new File("PlayerData.csv");
		if (f1.exists()&&f2.exists()){
			//CSVReader
			cr.read("AreaData.csv");
			cr.read("PlayerData.csv");
		}
		else{ 
		    System.out.println("CSVファイルのどれかが存在しません");
		}
		
		//EventSet
		new EnterAreaEvent(this);
		new OnPlayerJoin(this);

		getLogger().info("Plugin ArcIamStoryPlugin has been enabled.");
	}
	
	@Override
	public void onDisable() {
		getLogger().info("Plugin ArcIamStoryPlugin has been disabled.");
	}
	
	
	
	/*
	@EventHandler
	public void onEnterArea(PlayerMoveEvent e) {
		
		System.out.println("aaaaa");
		System.out.println(eae.enterAreaEvent().getName()+"に移動しました");
	}
	*/
	
		
}
	
	
