//aaaa

package com.github.miniyosshi.arciamstoryplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class ArcIamStoryPlugin extends JavaPlugin {

	@Override
	public void onEnable() {
		
		//CSV reader
		CSVReader cr = new CSVReader();
		cr.read("AreaData.csv");
		cr.read("PlayerData.csv");
		
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
	
	
