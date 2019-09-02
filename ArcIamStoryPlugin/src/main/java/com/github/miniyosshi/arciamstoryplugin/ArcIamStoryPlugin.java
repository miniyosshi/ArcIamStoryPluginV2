package com.github.miniyosshi.arciamstoryplugin;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class ArcIamStoryPlugin extends JavaPlugin {
	
	//初期設定
	//メッセージ接頭文字列
	public static String prefix = ChatColor.GOLD + "[ArcIamPlugin]";
	
	
	@Override
	public void onEnable() {
				
		System.out.println("This server uses "+System.getProperty("file.encoding")+" as a default character code.");
		//CSVReader
		/*
		CSVReader.read("AreaData");
		CSVReader.read("ChapterData");
		CSVReader.read("ScenarioData");
		CSVReader.read("UserData");
		*/
		for (CSVFiles f : CSVFiles.values()) {
			CSVReader.read(f.toString());
		}
		
		
		
		//EventSet
		new OnPlayerClickEvent(this);
		new EnterAreaEvent(this);
		new OnPlayerJoin(this);
		new OnPlayerLogout(this);
		
		//commnadSet
		for (Commands cmd : Commands.values()) {
			getCommand(cmd.toString()).setExecutor(new OnCommand());
		}
		
		/*
		getCommand("charactercode").setExecutor(new OnCommand());
		getCommand("deletearea").setExecutor(new OnCommand());
		getCommand("reloadcsv").setExecutor(new OnCommand());
		getCommand("setarea").setExecutor(new OnCommand());
		getCommand("showarea").setExecutor(new OnCommand());
		getCommand("userlist").setExecutor(new OnCommand());
		*/

		getLogger().info("Plugin ArcIamStoryPlugin has been enabled.");
		
	}
	
	@Override
	public void onDisable() {
		getLogger().info("Plugin ArcIamStoryPlugin has been disabled.");
	}
	
		
}
	
	
