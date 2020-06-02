package com.github.miniyosshi.arciamstorypluginV2;

import java.util.Optional;

public class MainScenarioBook extends ScenarioBook {
	
	private static final MainScenarioBook INSTANCE = new MainScenarioBook();
	
	private MainScenarioBook() {
		this.axis = "Main";
		System.out.println("Main Axis = "+ axis);
	}
	
	public static MainScenarioBook getInstance() {
		return INSTANCE;
	}
	
}
