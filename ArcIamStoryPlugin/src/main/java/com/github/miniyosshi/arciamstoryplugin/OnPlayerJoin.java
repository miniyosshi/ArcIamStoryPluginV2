package com.github.miniyosshi.arciamstoryplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import com.github.miniyosshi.economy.*;

public class OnPlayerJoin implements Listener {
	
	public OnPlayerJoin(Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerJoin (PlayerJoinEvent e) {
		
		//e.getPlayer().setWalkSpeed(0.2f);
		
		
		
		
		boolean a = false;
		
		for (User u : List.userdata) {
			if (u.getName().equals(e.getPlayer().getName())) {
				
				u.setPlayer(e.getPlayer()); //StringだけでなくPlayer型も登録
				u.setPastarea(u.isInAreaOf());
				
				e.getPlayer().teleport(u.getSavedLocation());
				
				a = true;
				createScoreboard(e.getPlayer());
				
				break;
			}
		}
		
		//初回ログインの人向け初期化
		if(a == false){
			World world = Bukkit.getServer().getWorld(List.areadata.get(0).getcornerA().getWorld().getName());
			
			Location loc = new Location(world,0,0,0);
			int[] x = {180, 180, 180};
			
			User newuser = new User(e.getPlayer().getName(), loc, 1, 1, x, false);
			List.userdata.add(newuser);	
			
			Account newaccount = new Account(e.getPlayer().getName(), 0);
			List.moneyaccount.add(newaccount);
			
			CSVExporter.exportCSV(CSVFiles.UserData.toString());
			CSVExporter.exportCSV(CSVFiles.MoneyAccount.toString());
			
			//StringだけでなくPlayer型も登録
			newuser.setPlayer(e.getPlayer());
			newuser.setPastarea(newuser.isInAreaOf());
			
			createScoreboard(e.getPlayer());
			
			System.out.println(e.getPlayer().getName()+" has newly logged in the server!");
		}
		
		
	}
	
	
	
	public void createScoreboard(Player p) {
			
			Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
			Objective o = sb.registerNewObjective("ArcIamAAA", "aaa", ChatColor.BLUE + "ArcIam Lost Legacy");
			o.setDisplayName(ChatColor.BLUE + "ArcIam LL");
			
			o.setDisplaySlot(DisplaySlot.SIDEBAR);
			Score s0 = o.getScore("節：");
			s0.setScore(User.getUser(p).getSection());
			Score s2 = o.getScore("章：");
			s2.setScore(User.getUser(p).getChapter());
			
			p.setScoreboard(sb);
			
			/*
			Score s1 = p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore("節:");
			s1.setScore(User.getUser(p).getSection());
			*/
			
						
	}

}
