package com.github.miniyosshi.arciamstorypluginV2;

import java.util.Optional;

public class SubScenarioBook extends ScenarioBook {
	
	private static final SubScenarioBook INSTANCE = new SubScenarioBook();
	
	private SubScenarioBook() {}
	
	public static SubScenarioBook getInstance() {
		return INSTANCE;
	}
	
}
