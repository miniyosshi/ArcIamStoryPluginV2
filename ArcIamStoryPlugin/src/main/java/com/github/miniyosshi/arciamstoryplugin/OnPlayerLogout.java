package com.github.miniyosshi.arciamstoryplugin;


import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;


public class OnPlayerLogout implements Listener {
	
	public OnPlayerLogout(Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	
	@EventHandler
	public void onPlayerLogout (PlayerQuitEvent e) {
		
		Player p = e.getPlayer();
		User u = User.getUser(e.getPlayer());
		
		//ストーリーイベント途中で落ちた場合
		if(u.getInStoryEvent()==true) {
			System.out.println(e.getPlayer().getName()+"has logged out half way through a story event...");
			//文章止める(不要)
		}
		
		//セーブ地点へ移動
		p.teleport(u.getSavedLocation());
		
		//名前のあるエリア外でログアウトで...
		
		if(u.isInAreaOf().getName().equals(List.areadata.get(0).getName()) ) {
			
			//ゾンビ出現
			
			ItemStack skull = new ItemStack(Material.SKELETON_SKULL);
			SkullMeta sm = (SkullMeta) skull.getItemMeta();
			sm.setOwningPlayer(p);
			skull.setItemMeta(sm);
			
			Location loc =p.getLocation();
			
			Zombie s = (Zombie) p.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
			s.setCustomNameVisible(true);
			s.setCustomName(p.getName() + "の哀れな姿");
			s.getEquipment().setHelmet(skull);
			s.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SWORD,1));
			s.setHealth(3);
			
			//ハードコア向けアイテムロスト
			
			if(u.getHardmode() == true) {
				//一応記録
				System.out.println(u.getPlayer().getInventory().getStorageContents());
								
				//p.getInventory().clear();
			}
			
			
		}
		
	}
	

}
