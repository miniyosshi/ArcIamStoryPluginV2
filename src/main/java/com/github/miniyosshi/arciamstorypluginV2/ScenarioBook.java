package com.github.miniyosshi.arciamstorypluginV2;

import java.util.Optional;

public abstract class ScenarioBook extends ListDataRepository<ScenarioSentences> {
	
	protected String axis = "";
	
	public String getAxis() {
		return axis;
	}
	
	public Optional<ScenarioSentences> getElementBy(int chapter, int section) {
		for(ScenarioSentences element : list) {
			int[] x = element.getChapterSectionNumber();
			if(x[0]==chapter && x[1] == section){
				return Optional.of(element);
			}
		}
		return Optional.empty();
	}
	
	public Optional<ScenarioSentences> nextScenarioSentences(ScenarioSentences ss) {
		if(ss.hasNext()) {
			return getElementBy(indexOf(ss)+1);
		}
		return Optional.empty();
	}
	
	
}
