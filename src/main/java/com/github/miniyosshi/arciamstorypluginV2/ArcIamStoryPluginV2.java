package com.github.miniyosshi.arciamstorypluginV2;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.miniyosshi.arciamstorypluginV2.listeners.*;

public class ArcIamStoryPluginV2 extends JavaPlugin {
	
	//初期設定
	//メッセージ接頭文字列
	public static String prefix = ChatColor.GOLD + "[ArcIamPlugin]";
	
	@Override
	public void onEnable() {
		
		getLogger().info("This server uses "+System.getProperty("file.encoding")+" as the default char code.");
		//Read JSON and CSV
		//MainScenarioBook.getInstance().importAllFromDefaultFolder(ScenarioSentences.class);
		VariousScenarioBook.getInstance().importAllFromDefaultFolder();
		DesignatedAreas.getInstance().importAllFromDefaultFolder(DesignatedArea.class);
		DesignatedSpots.getInstance().importAllFromDefaultFolder(DesignatedSpot.class);
		LLItems.getInstance().importAllFromDefaultFolder(LLItem.class);
		NPCs.getInstance().importAllFromDefaultFolder(NPC.class);
		// StorySection depends on DesignatedSpot, NPC
		//MainStorySections.getInstance().importAllFromDefaultFolder(StorySection.class);
		VariousStorySections.getInstance().importAllFromDefaultFolder();
		// User depends on StorySection and DesignatedArea
		Users.getInstance().importAllFromDefaultFolder(User.class);
		
		getLogger().info("File reading process is finished.");
		
		//EventSet
		new OnEntityDeath(this);
		new OnPlayerClickEntityEvent(this);
		new OnPlayerEnterDesignatedArea(this);
		new OnPlayerJoin(this);
		new OnPlayerLogout(this);
		new OnPlayerDeath(this);
		//new OnPlayerTradewithVillager(this);
		
		getCommand("ll").setExecutor(new ASPCommandExecutor());
		
		/*
		for (Commands cmd : Commands.values()) {
			getLogger().info(cmd.toString());
			getCommand(cmd.toString()).setExecutor(new ASPCommandExecutor());
		}
		*/
		/*
		getCommand("charactercode").setExecutor(new OnCommand());
		getCommand("deletearea").setExecutor(new OnCommand());
		*/	
		
		getLogger().info("Plugin ArcIamStoryPlugin has been enabled.");
	}
	
	@Override
	public void onDisable() {	
		
		//export all
		DesignatedAreas.getInstance().exportAllToDefaultFolder();
		DesignatedSpots.getInstance().exportAllToDefaultFolder();
		LLItems.getInstance().exportAllToDefaultFolder();
		NPCs.getInstance().exportAllToDefaultFolder();
		Users.getInstance().exportAllToDefaultFolder();
		
		getLogger().info("Plugin ArcIamStoryPlugin has been disabled.");
	}
	
		
}
	
	
