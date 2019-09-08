package com.github.miniyosshi.arciamstoryplugin;

public enum Mob {
	村人A,
	村人B,
	村人C,
	転入係,
	転出係,
	商人,
	Unknown;
	
	public static boolean checkExistence(String name){
		for(Mob m : Mob.values()) {
			if(m.toString().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
}
