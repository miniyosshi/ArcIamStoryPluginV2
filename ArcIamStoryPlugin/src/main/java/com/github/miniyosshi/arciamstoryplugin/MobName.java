package com.github.miniyosshi.arciamstoryplugin;

import org.bukkit.util.Vector;

public enum MobName {
	村人A,
	村人B,
	村人C,
	転入係,
	転出係,
	商人,
	セーブクラーク,
	
	敵A,
	
	Unknown,
	
	;
	public static final MobName[] EnemyMobName = {
		敵A,
	};
	
	
	public static boolean checkExistence(String name){
		for(MobName m : MobName.values()) {
			if(m.toString().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public void walkaround(Vector v) {
		
	}
	
	
}
