package com.github.miniyosshi.arciamstorypluginV2;

public class ScenerioBook extends ListDataRepository<ScenerioSentences> {
	
	private static final ScenerioBook INSTANCE = new ScenerioBook();
	
	private ScenerioBook() {}
	
	public static ScenerioBook getInstance() {
		return INSTANCE;
	}
	
	
}
