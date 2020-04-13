package com.github.miniyosshi.arciamstorypluginV2;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
//import org.bukkit.entity.*;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.miniyosshi.arciamstorypluginV2.listeners.OnPlayerClickEntityEvent;
import com.github.miniyosshi.arciamstorypluginV2.listeners.OnPlayerDeath;
import com.github.miniyosshi.arciamstorypluginV2.listeners.OnPlayerEnterDesignatedArea;
import com.github.miniyosshi.arciamstorypluginV2.listeners.OnPlayerJoin;

public class ArcIamStoryPluginV2 extends JavaPlugin {
	
	//初期設定
	//メッセージ接頭文字列
	public static String prefix = ChatColor.GOLD + "[ArcIamPlugin]";
	
	
	@Override
	public void onEnable() {
		
		getLogger().info("This server uses "+System.getProperty("file.encoding")+" as the default char code.");
		//Read JSON and CSV
		DesignatedAreas.getInstance().importAllFromDefaultFolder(DesignatedArea.class);
		DesignatedSpots.getInstance().importAllFromDefaultFolder(DesignatedSpot.class);
		LLItems.getInstance().importAllFromDefaultFolder(LLItem.class);
		NPCs.getInstance().importAllFromDefaultFolder(NPC.class);
		Users.getInstance().importAllFromDefaultFolder(User.class);
		getLogger().info("File reading process is finished.");
		
		//EventSet
		new OnPlayerClickEntityEvent(this);
		new OnPlayerEnterDesignatedArea(this);
		new OnPlayerJoin(this);
		//new OnPlayerLogout(this);
		new OnPlayerDeath(this);
		//new OnPlayerTradewithVillager(this);
		
		//commandSet
		/*
		for (Commands cmd : Commands.values()) {
			getCommand(cmd.toString()).setExecutor(new OnCommand());
		}
		*/
		
		/*
		getCommand("charactercode").setExecutor(new OnCommand());
		getCommand("deletearea").setExecutor(new OnCommand());
		getCommand("reloadcsv").setExecutor(new OnCommand());
		getCommand("setarea").setExecutor(new OnCommand());
		getCommand("showarea").setExecutor(new OnCommand());
		getCommand("userlist").setExecutor(new OnCommand());
		*/		
		
		/*
		getLogger().info("PvPを無効にします。");
		for(World w : Bukkit.getServer().getWorlds()) {
			w.setPVP(false);
		}
		*/
		
		getLogger().info("Plugin ArcIamStoryPlugin has been enabled.");
	}
	
	@Override
	public void onDisable() {	
		
		//export all
		
		getLogger().info("Plugin ArcIamStoryPlugin has been disabled.");
	}
	
		
}
	
	
