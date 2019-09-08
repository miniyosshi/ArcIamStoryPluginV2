package com.github.miniyosshi.arciamstoryplugin;

import java.util.Random;
import java.util.Timer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.Plugin;

public class OnPlayerClickEvent implements Listener {
	
	public OnPlayerClickEvent(Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	

	
	@EventHandler
	public void clickEvent(PlayerInteractEntityEvent e) {
		User u = User.getUser(e.getPlayer());
		
		if (e.getHand() == EquipmentSlot.HAND) {

			System.out.println(e.getRightClicked().getName()+"getName");
			System.out.println(e.getRightClicked().toString()+"toString");
			
			
			
			
			
			//ストーリー進行中でなければ
			if(u.getInStoryEvent()==false) {
				//ストーリー進行
				StoryProcessor.eventCheck(u,"click", e.getRightClicked().getName());
				
				//mob会話・転入転出・ボタンクリック等
				
				//switchではnullは使えないようだ
				//以下の文すらNullPointerExceptionになる
				//System.out.println(Mob.valueOf(e.getRightClicked().getName())+"名前");
				Mob m = Mob.Unknown;
				boolean tf = Mob.checkExistence(e.getRightClicked().getName());
				if(tf == true) {
					m = Mob.valueOf(e.getRightClicked().getName());
				}
					
				switch (m) {
				case 村人A :
					e.getPlayer().sendMessage("こんにちは"+ u.getChapter() + "章のお方");
					break;
				case 村人B:
					e.getPlayer().sendMessage("やあ、気分はどうかね"+ u.getChapter() + "章のお方。");
					break;
				case 村人C:
					break;
				case 転入係:
					e.getPlayer().sendMessage("５秒後に"+"転送します。");
					
					FieldLoginEffect tc =new FieldLoginEffect(User.getUser(e.getPlayer()));
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
					e.getPlayer().sendMessage("５秒後に転送します");
					
					//エフェクト変える
					FieldLoginEffect tc2 =new FieldLoginEffect(User.getUser(e.getPlayer()));
					Timer timer2 = new Timer();
					timer2.schedule(tc2, 5000);
					break;
				case 商人 :
					e.getPlayer().sendMessage("やあ、いいもの揃っているよ");
				case Unknown:
					e.getPlayer().sendMessage("僕「この人誰だろう」");
					break;
				
				//default:
					//e.getPlayer().sendMessage("僕「この人誰だろう」");
					//break;
				
				}
				
				
			}	
		}
		
		
		
		
	}
}
