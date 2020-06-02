package com.github.miniyosshi.arciamstorypluginV2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class VariousStorySections{
	//singleton
	private static final VariousStorySections INSTANCE = new VariousStorySections();
	
	private VariousStorySections() {
		classSet.add(MainStorySections.getInstance());
		classSet.add(Dangeon1StorySections.getInstance());
		//add
	}
	
	public static VariousStorySections getInstance() {
		return INSTANCE;
	}
	
	private Set<StorySections> classSet = new HashSet<>();
	
	public void showAxis() {
		classSet.stream().forEach(v-> System.out.println(v.getAxis()));
	}
	
	public void importAllFromDefaultFolder() {
		classSet.stream().forEach(v-> v.importAllFromDefaultFolder(StorySection.class));
	}
	
	public Optional<StorySections> getInstanceBy(String axis) {
		return classSet.stream().filter(v-> v.getAxis().equalsIgnoreCase(axis)).findFirst();
	}
	
	public Map<String, StorySection> getInitialMap(){
		Map<String, StorySection> map = new HashMap<>();
		classSet.stream().forEach(instance-> {
			instance.getElementBy(1, 1).ifPresent(v-> map.put(v.getAxis(), v));
		});
		return map;
	}
	
}
