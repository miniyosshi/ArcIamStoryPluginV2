package com.github.miniyosshi.arciamstoryplugin;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class ArcIamStoryPlugin extends JavaPlugin {
	
	//初期設定
	//メッセージ接頭文字列
	public static String prefix = ChatColor.GOLD + "[ArcIam]";
	
	
	
	@Override
	public void onEnable() {
				
		System.out.println("This server uses "+System.getProperty("file.encoding")+" as a default character code.");
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
	
	
