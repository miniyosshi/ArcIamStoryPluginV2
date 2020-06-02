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
		
		// Clicked name test
		String customName = e.getRightClicked().getCustomName();
		//String plainName = e.getRightClicked().getName();
		//String toStringName = e.getRightClicked().toString();
		//player.sendMessage("customName: "+customName+", plainName: "+plainName+"toStringName: "+toStringName);
		
		Optional<NPC> npc = NPCs.getInstance().getElementBy(customName);
		
		user.ifPresent(u -> {
			//ストーリー進行中でなければ
			if(!u.isInStoryEvent()) {
				
				Optional<String> axis = u.checkEventandReturnAxis("click", customName);
				player.sendMessage("Click:"+ axis);
				
				axis.ifPresentOrElse(v-> u.processLine(v),()->{
					//ストーリがなければ, mob会話・転入転出・ボタンクリック等(switchではnullは使えない。Unknownを設定する)
					npc.ifPresentOrElse(v ->{
						//NPCごとに決められた業務
						v.talk(u,"決められたセリフ");
						if(v instanceof SaverNPC && u instanceof NormalModeUser) {
							((SaverNPC) v).saveCurrentLocation(u);
						}
						
						if(v instanceof SellerNPC) {
							((NormalModeUser) u).saveCurrentLocation();
							v.talk(u,"いらしゃいませ。");
							((SellerNPC)v).openInventoryTo(player);
						}
						
						//if(v instanceof TeleporterNPC) 
						
					}, () ->{
						if(e.getRightClicked().getType().equals(EntityType.VILLAGER)) {
							u.sendMessage("村人", "やあ。");
						}
					});
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
