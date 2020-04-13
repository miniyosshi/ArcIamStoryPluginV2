package com.github.miniyosshi.arciamstorypluginV2.listeners;


import java.util.Optional;

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

import com.github.miniyosshi.arciamstorypluginV2.*;

public class OnPlayerJoin implements Listener {
	
	public OnPlayerJoin(Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerJoin (PlayerJoinEvent e) {
		Player player = e.getPlayer();
		//対応するUserInstanceにplayerを対応付ける。
		//あらかじめログアウト時にロビーに来ている。
		Optional<User> user = Users.getInstance().getElementBy(player.getName());
		user.ifPresentOrElse(v -> v.setPlayer(player), () -> {
			//First login
			System.out.println(player.getName() + " log in the server for the first time!");
			//初期値
			//UserInfo info = new UserInfo(null, null, null, false, null);
			new NormalModeUser(player.getName(), player, null).teleportToLobby();
		});
		
		
		//e.getPlayer().setWalkSpeed(0.2f);
		
		
		
		//ログイン10秒後に場所・章節の表示
		//
		//createScoreboard(e.getPlayer());
		
	}
	
	
	
	public void createScoreboard(Player p) {
			
			Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
			Objective o = sb.registerNewObjective("ArcIamAAA", "dummy", ChatColor.BLUE + "ArcIam Lost Legacy");
			o.setDisplayName(ChatColor.BLUE + "ArcIam LL");
			o.setDisplaySlot(DisplaySlot.SIDEBAR);
			
			/*
			Score s0 = o.getScore("節：");
			s0.setScore(User.getUser(p).getSection());
			Score s2 = o.getScore("章：");
			s2.setScore(User.getUser(p).getChapter());
			*/
			p.setScoreboard(sb);
			
			/*
			Score s1 = p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore("節:");
			s1.setScore(User.getUser(p).getSection());
			*/
							
	}

}
