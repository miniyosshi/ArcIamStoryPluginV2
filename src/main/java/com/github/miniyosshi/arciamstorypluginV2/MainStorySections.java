package com.github.miniyosshi.arciamstorypluginV2;

import java.util.Optional;

public class MainStorySections extends StorySections{
	
	private static final MainStorySections INSTANCE = new MainStorySections();
	
	private MainStorySections() {
		this.axis = "Main";
		System.out.println("Main Axis = "+ axis);
	}
	
	public static MainStorySections getInstance() {
		return INSTANCE;
	}
	
	/*
	public int[] nextChapterSectionNumber(StorySection ss) {
		Optional<StorySection> nss = nextStorySection(ss);
		//後がなければそのままの番号を返す
		return nss.map(v-> v.getChapterSectionNumber()).orElse(ss.getChapterSectionNumber());
	}
	*/
	/*
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
	*/

}
