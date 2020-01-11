package com.github.miniyosshi.arciamstoryplugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;

public class OnPlayerDeath implements Listener {
	
	public OnPlayerDeath(Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	
	@EventHandler
	public void onPlayerDeath (PlayerDeathEvent e) {
		Player p = e.getEntity();
		User u = User.getUser(p);
		
		//ハードの人は病院へ、ノーマルの人はセーブ地点へ
		if (u.getHardmode()) {
			
			//一応インベントリ抽出
			//
			
			p.getInventory().clear();
			u.setBirthday(180, 180, 180);
			//べっどいるみたいp.setBedSpawnLocation(arg0);
			p.teleport(List.spawnpoints.get(1).getLocation());
			
		}
		else {
			p.teleport(u.getSavedLocation());
		}
		
		//ゾンビ出現（プレーヤーと同じステータス）
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
		
		//ステータス
		s.setHealth(3);
		// walk speed
	}
		

}
