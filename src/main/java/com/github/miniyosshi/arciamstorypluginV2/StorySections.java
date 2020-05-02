package com.github.miniyosshi.arciamstorypluginV2;

import java.util.NoSuchElementException;
import java.util.Optional;

public class StorySections extends ListDataRepository<StorySection>{
	
	private static final StorySections INSTANCE = new StorySections();
	
	private StorySections() {}
	
	public static StorySections getInstance() {
		return INSTANCE;
	}
	
	public Optional<StorySection> getElementBy(int chapter, int section){
		String name = chapter + "-" + section;
		return getElementBy(name);
	}
	
	public Optional<StorySection> nextStorySection(StorySection ss) {
		if(ss.hasNext()) {
			return getElementBy(StorySections.getInstance().indexOf(ss)+1);
		}
		return Optional.empty();
	}
	
	public int[] nextChapterSectionNumber(StorySection ss) {
		Optional<StorySection> nss = nextStorySection(ss);
		//後がなければそのままの番号を返す
		return nss.map(v-> v.getChapterSectionNumber()).orElse(ss.getChapterSectionNumber());
	}
	
	public int[] nextChapterSectionNumber(int chapter, int section) {
		StorySections sss = StorySections.getInstance();
		Optional<StorySection> ss = sss.getElementBy(chapter, section);
		Optional<StorySection> lastElement = sss.getElementBy(sss.size()-1);
		int[] a = {0,0};
		int[] lastNumber = lastElement.map(v-> v.getChapterSectionNumber()).orElseGet(()->{
			System.out.println("No next chaptersectionnumber");
			return a;
		});
		return ss.map(v->nextChapterSectionNumber(v)).orElse(lastNumber);		
	}
	
	

}
