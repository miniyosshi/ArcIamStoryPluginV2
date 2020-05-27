package com.github.miniyosshi.arciamstorypluginV2;

import java.util.Optional;

public class ScenarioBook extends ListDataRepository<ScenarioSentences> {
	
	private static final ScenarioBook INSTANCE = new ScenarioBook();
	
	private ScenarioBook() {}
	
	public static ScenarioBook getInstance() {
		return INSTANCE;
	}
	
	public Optional<ScenarioSentences> getElementBy(int chapter, int section) {
		String name = chapter + "-" + section;
		return getElementBy(name);
	}
	
	public Optional<ScenarioSentences> nextScenarioSentences(ScenarioSentences ss) {
		if(ss.hasNext()) {
			return getElementBy(ScenarioBook.getInstance().indexOf(ss)+1);
		}
		return Optional.empty();
	}
	
	
}
