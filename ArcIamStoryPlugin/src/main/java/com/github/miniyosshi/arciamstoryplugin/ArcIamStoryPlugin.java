package com.github.miniyosshi.arciamstoryplugin;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

public class ArcIamStoryPlugin extends JavaPlugin {
	
	@Override
	public void onEnable() {
		
		CSVReader cr = new CSVReader();
		
		//file check
		File f1 = new File("AreaData.csv");
		File f2 = new File("ChapterData.csv");
		File f3 = new File("UserData.csv");
		
		if (f1.exists()&&f2.exists()&&f3.exists()){
			//CSVReader
			cr.read("AreaData.csv");
			cr.read("ChapterData.csv");
			cr.read("UserData.csv");
		}
		else{ 
		    System.out.println("CSVファイルのどれかが存在しません");
		}
		
		//EventSet
		new ClickEvent(this);
		new EnterAreaEvent(this);
		new OnPlayerJoin(this);
		
		//commnadSet
		getCommand("setarea").setExecutor(new OnCommand());
		getCommand("showarea").setExecutor(new OnCommand());

		getLogger().info("Plugin ArcIamStoryPlugin has been enabled.");
		
	}
	
	@Override
	public void onDisable() {
		getLogger().info("Plugin ArcIamStoryPlugin has been disabled.");
	}
	
		
}
	
	
