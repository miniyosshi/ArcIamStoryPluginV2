package com.github.miniyosshi.arciamstorypluginV2;

public class StorySections extends ListDataRepository<StorySection>{
	
	private static final StorySections INSTANCE = new StorySections();
	
	private StorySections() {}
	
	public static StorySections getInstance() {
		return INSTANCE;
	}	

}
