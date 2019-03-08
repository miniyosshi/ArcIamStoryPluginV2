package com.github.miniyosshi.arciamstoryplugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class OnPlayerJoin implements Listener {
	
	public OnPlayerJoin(Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerJoin (PlayerJoinEvent e) {
		
		boolean a = false;
		
		//System.out.print("ここのワールドは"+e.getPlayer().getWorld().getName());
		
		for (User u : CSVReader.userdata) {
			if (u.getName().equals(e.getPlayer().getName())) {
				
				u.player = e.getPlayer(); //StringだけでなくPlayer型も登録
				u.pastarea = u.isInAreaOf();
				System.out.println(u.getName()+"のログイン時のエリアは"+u.pastarea.getName());
				
				
				
				//System.out.println(CSVReader.userdata.get(0).name+" "+CSVReader.userdata.get(0).pastarea.getName());
				//userdataの表への書き込みもできてるっぽい？
				
				a = true;
				
				break;
			}
		}
		
		//初回ログインの人向け
		if(a == false){
			World world = Bukkit.getServer().getWorld(CSVReader.areadata.get(0).getName());
			Location loc = new Location(world,0,0,0);
			User newuser = new User(e.getPlayer().getName(), loc, 1, 1);
			CSVReader.userdata.add(newuser);
			CSVEditor.exportCSV();
			
			newuser.player = e.getPlayer(); //StringだけでなくPlayer型も登録
			newuser.pastarea = newuser.isInAreaOf();
			System.out.println("お初の方のログインエリアは"+newuser.pastarea.getName());
		}
		
	}

}
