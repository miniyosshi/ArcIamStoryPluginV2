package com.github.miniyosshi.arciamstorypluginV2;

public class LLItems extends MapDataRepository<LLItem> {
	
	private static final LLItems INSTANCE = new LLItems();
	
	private LLItems() {}
	
	public static LLItems getInstance() {
		return INSTANCE;
	}
	
	
}
