package com.github.miniyosshi.arciamstorypluginV2;

import java.util.Optional;

public abstract class StorySections extends ListDataRepository<StorySection>{
	
	protected String axis = "";
	
	public String getAxis() {
		return axis;
	}
	
	public Optional<StorySection> getElementBy(int chapter, int section){
		for(StorySection element : list) {
			int[] x = element.getChapterSectionNumber();
			if(x[0]==chapter && x[1] == section){
				return Optional.of(element);
			}
		}
		return Optional.empty();
	}
	
	public Optional<StorySection> nextStorySection(StorySection ss) {
		if(ss.hasNext()) {
			return getElementBy(indexOf(ss)+1);
		}
		return Optional.empty();
	}
	
}
