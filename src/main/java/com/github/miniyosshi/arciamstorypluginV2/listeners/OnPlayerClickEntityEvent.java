package com.github.miniyosshi.arciamstorypluginV2.listeners;

import java.io.File;
import java.util.Optional;
import java.util.Random;
import java.util.Timer;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.Plugin;

import com.github.miniyosshi.arciamstorypluginV2.*;

public class OnPlayerClickEntityEvent implements Listener {
	
	public OnPlayerClickEntityEvent(Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPlayerClickEvent(PlayerInteractEntityEvent e) {
		Player player = e.getPlayer();
		Optional<User> user = Users.getInstance().getElementBy(player);
		
		String customName = e.getRightClicked().getCustomName();
		String plainName = e.getRightClicked().getName();
		String toStringName = e.getRightClicked().toString();
		System.out.println("customName: "+customName+", plainName: "+plainName+"toStringName: "+toStringName);
		
		Optional<NPC> npc = NPCs.getInstance().getElementBy(customName);		
		
		user.ifPresent(u -> {
			//ストーリー進行中でなければ
			if(!u.isInStoryEvent()) {
				//ストーリー進行
				//StoryProcessor.eventCheck(u,"click", e.getRightClicked().getName());
				
				//ストーリがなければ, mob会話・転入転出・ボタンクリック等(switchではnullは使えない。Unknownを設定する)
				npc.ifPresentOrElse(v ->{
					//NPCごとに決められたセリフ
					v.talk(player,"決められたセリフ");
					if(v instanceof SaverNPC && u instanceof NormalModeUser) {
						((NormalModeUser) u).saveCurrentLocation();
						v.talk(player,"セーブ完了！");
					}
					//if(v instanceof TeleporterNPC) 
					
					//商人
					
					
					
				}, () ->{
					if(e.getRightClicked().getType().equals(EntityType.VILLAGER)) {
						player.sendMessage("やあ。");
					}
				});
			}
		});

				/*
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
