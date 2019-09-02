package com.github.miniyosshi.arciamstoryplugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import com.github.miniyosshi.economy.*;

public class OnPlayerJoin implements Listener {
	
	public OnPlayerJoin(Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerJoin (PlayerJoinEvent e) {
		
		boolean a = false;
				
		for (User u : List.userdata) {
			if (u.getName().equals(e.getPlayer().getName())) {
				
				u.setPlayer(e.getPlayer()); //StringだけでなくPlayer型も登録
				u.setPastarea(u.isInAreaOf());
				System.out.println(u.getName()+" has logged in "+u.getPastArea().getName());
				
				a = true;
				
				break;
			}
		}
		
		//初回ログインの人向け初期化
		if(a == false){
			World world = Bukkit.getServer().getWorld(List.areadata.get(0).getcornerA().getWorld().getName());
			Location loc = new Location(world,0,0,0);
			User newuser = new User(e.getPlayer().getName(), loc, 1, 1);
			List.userdata.add(newuser);	
			
			Account newaccount = new Account(e.getPlayer().getName(), 0);
			List.moneyaccount.add(newaccount);
			
			CSVExporter.exportCSV(CSVFiles.UserData.toString());
			CSVExporter.exportCSV(CSVFiles.MoneyAccount.toString());
			
			//StringだけでなくPlayer型も登録
			newuser.setPlayer(e.getPlayer());
			newuser.setPastarea(newuser.isInAreaOf());
			System.out.println(e.getPlayer().getName()+" has newly logged in the server!");
		}
		
	}

}
