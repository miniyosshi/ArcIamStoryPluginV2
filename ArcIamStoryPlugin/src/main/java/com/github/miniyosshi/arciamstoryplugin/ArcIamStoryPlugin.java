package com.github.miniyosshi.arciamstoryplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class ArcIamStoryPlugin extends JavaPlugin {
	
	@Override
	public void onEnable() {
		
		/*
		//file check
		File f1 = new File("AreaData.csv");
		File f2 = new File("ChapterData.csv");
		File f3 = new File("ScenarioData.csv");
		File f4 = new File("UserData.csv");
		
		if (f1.exists()&&f2.exists()&&f3.exists()&&f4.exists()){
			//CSVReader
		}
		else{ 
		    System.out.println("CSVファイルのどれかが存在しません");
		}
		*/
		System.out.println(System.getProperty("file.encoding"));
		//CSVReader
		CSVReader.read("AreaData.csv");
		CSVReader.read("ChapterData.csv");
		CSVReader.read("ScenarioData.csv");
		CSVReader.read("UserData.csv");
		
		
		//EventSet
		new ClickEvent(this);
		new EnterAreaEvent(this);
		new OnPlayerJoin(this);
		new OnPlayerLogout(this);
		
		//commnadSet
		getCommand("charactercode").setExecutor(new OnCommand());
		getCommand("deletearea").setExecutor(new OnCommand());
		getCommand("reloadcsv").setExecutor(new OnCommand());
		getCommand("setarea").setExecutor(new OnCommand());
		getCommand("showarea").setExecutor(new OnCommand());
		getCommand("userlist").setExecutor(new OnCommand());

		getLogger().info("Plugin ArcIamStoryPlugin has been enabled.");
		
	}
	
	@Override
	public void onDisable() {
		getLogger().info("Plugin ArcIamStoryPlugin has been disabled.");
	}
	
		
}
	
	
