package com.github.miniyosshi.arciamstorypluginV2;

public class NPCs extends MapDataRepository<NPC> {
	
	private static final NPCs INSTANCE = new NPCs();
	
	private NPCs() {}
	
	public static NPCs getInstance() {
		return INSTANCE;
	}
	
	
}
