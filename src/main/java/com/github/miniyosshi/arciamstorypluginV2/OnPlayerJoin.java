package com.github.miniyosshi.arciamstorypluginV2;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import com.fasterxml.jackson.databind.ObjectMapper;

public class OnPlayerJoin implements Listener {
	
	public OnPlayerJoin(Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerJoin (PlayerJoinEvent e) {
		Player player = e.getPlayer();
		
		//UserInstanceの生成, Usersへの登録（Usersは鯖起動時は空にしておいて、ログインのたびに追加、ログアウト時に保存、削除する）
		User user = new User(player, userInfo);
		
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
		
		//ログイン10秒後に場所・章節の表示
		//
		//
		
		
		
		//初回ログインの人向け初期化
		if(a == false){
			//初期値
			Location loc = List.spawnpoints.get(0).getLocation();
			int[] defaultbirthday = {1990, 1, 1};
			User newuser = new User(e.getPlayer().getName(), loc, 1, 1, defaultbirthday, false);
			List.userdata.add(newuser);	
			

			Wallet newwallet = new Wallet(0);
			List.moneyaccount.add(newwallet);

			BankAccount newaccount = new BankAccount(e.getPlayer().getName(), 0);
			List.bankaccount.add(newaccount);

			
			CSVExporter.exportCSV(CSVFiles.UserData.toString());
			CSVExporter.exportCSV(CSVFiles.BankAccount.toString());
			
			//StringだけでなくPlayer型も登録
			newuser.setPlayer(e.getPlayer());
			newuser.setPastarea(newuser.isInAreaOf());
			
			createScoreboard(e.getPlayer());
			
			Bukkit.getLogger().info(e.getPlayer().getName()+" has newly logged in the server!");
		}
		
		
	}
	
	
	
	public void createScoreboard(Player p) {
			
			Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
			Objective o = sb.registerNewObjective("ArcIamAAA", "dummy", ChatColor.BLUE + "ArcIam Lost Legacy");
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
