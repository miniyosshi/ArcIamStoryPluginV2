package com.github.miniyosshi.arciamstorypluginV2.listeners;

import java.io.File;
import java.util.Optional;
import java.util.Random;
import java.util.Timer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.Plugin;

import com.github.miniyosshi.arciamstorypluginV2.NormalModeUser;
import com.github.miniyosshi.arciamstorypluginV2.UserInfo;
import com.github.miniyosshi.arciamstorypluginV2.Users;

public class OnPlayerClickEntityEvent implements Listener {
	
	public OnPlayerClickEntityEvent(Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPlayerClickEvent(PlayerInteractEntityEvent e) {
		//Optional<User> u = Users.getInstance().getElementBy(e.getPlayer());
		
		if (e.getHand() == EquipmentSlot.HAND) {
			//System.out.println(e.getRightClicked().getName()+"getName");
			System.out.println(e.getRightClicked().toString()+"toString");
			UserInfo userInfo = new UserInfo(e.getPlayer().getLocation(), null, null, false, null);
			NormalModeUser user = new NormalModeUser(e.getPlayer().getName(), e.getPlayer(), userInfo);
			System.out.println("UserName: " + user.getName());
			Users.getInstance().exportAllToDefaultFolder();
			
			
			//ストーリー進行中でなければ
			//if(!u.isInStoryEvent()) {
				//ストーリー進行
				//StoryProcessor.eventCheck(u,"click", e.getRightClicked().getName());
				
				/*
				//ストーリがなければ, mob会話・転入転出・ボタンクリック等
				//switchではnullは使えないようだ
				//以下の文すらNullPointerExceptionになる
				//System.out.println(Mob.valueOf(e.getRightClicked().getName())+"名前");
				
				MobName m = MobName.Unknown;
				boolean tf = MobName.checkExistence(e.getRightClicked().getName());
				if(tf == true) {
					m = MobName.valueOf(e.getRightClicked().getName());
				}
					
				switch (m) {
				case 村人A :
					e.getPlayer().sendMessage("["+m+"] "+"こんにちは"+ u.getChapter() + "章のお方");
					break;
				case 村人B:
					e.getPlayer().sendMessage("["+m+"] "+"やあ、気分はどうかね"+ u.getChapter() + "章のお方。");
					break;
				case 村人C:
					break;
				case 転入係:
					e.getPlayer().sendMessage("["+m+"] "+"５秒後に"+"転送します。");
					
					TeleportEffect tc =new TeleportEffect(User.getUser(e.getPlayer()));
					Timer timer = new Timer();
					timer.schedule(tc, 5000);
					
					Random rnd = new Random();
					int sumtime = 0;
					while(sumtime<4500) {
						int randomtick = rnd.nextInt(20) + 1;
						Timer timer2 = new Timer();
						TimerTaskRandomEffect ttre = new TimerTaskRandomEffect(User.getUser(e.getPlayer()), randomtick);
						sumtime += randomtick*20;
						//System.out.println(sumtime);
						timer2.schedule(ttre, sumtime);
					}
					break;
				case 転出係:
					e.getPlayer().sendMessage("["+m+"] "+"５秒後に転送します");
					
					//エフェクト変える
					TeleportEffect tc2 =new TeleportEffect(User.getUser(e.getPlayer()));
					Timer timer2 = new Timer();
					timer2.schedule(tc2, 5000);
					break;
				case 商人 :
					e.getPlayer().sendMessage("["+m+"] "+"やあ、いいもの揃っているよ");
				
				case セーブクラーク:
					User.getUser(e.getPlayer()).saveCurrentLocation();
					e.getPlayer().sendMessage("["+m+"] "+"どうぞごゆっくり。");
					break;
				
				case Unknown:
					e.getPlayer().sendMessage("僕 「この人誰だろう」");
					break;
				
				
				default:
					//e.getPlayer().sendMessage("僕「この人誰だろう」");
					break;
				
				}
				*/
				
				
			//}	
		}
		
		
		
		
	}
}
