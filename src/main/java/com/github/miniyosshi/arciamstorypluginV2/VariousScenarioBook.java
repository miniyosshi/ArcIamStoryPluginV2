package com.github.miniyosshi.arciamstorypluginV2;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class VariousScenarioBook{
	//singleton
	private static final VariousScenarioBook INSTANCE = new VariousScenarioBook();
	
	private VariousScenarioBook() {
		classSet.add(MainScenarioBook.getInstance());
		classSet.add(Dangeon1ScenarioBook.getInstance());
		//add
		
	}
	
	public static VariousScenarioBook getInstance() {
		return INSTANCE;
	}
	
	private Set<ScenarioBook> classSet = new HashSet<>();
	
	public void showAxis() {
		classSet.stream().forEach(v-> System.out.println(v.getAxis()));
	}
	
	public void importAllFromDefaultFolder() {
		classSet.stream().forEach(v-> v.importAllFromDefaultFolder(ScenarioSentences.class));
	}
	
	public Optional<ScenarioBook> getInstanceBy(String axis) {
		return classSet.stream().filter(v-> v.getAxis().equalsIgnoreCase(axis)).findFirst();
	}
	
}
