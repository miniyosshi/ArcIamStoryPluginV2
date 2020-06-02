package com.github.miniyosshi.arciamstorypluginV2;

import java.util.Optional;

public class Dangeon1ScenarioBook extends ScenarioBook {
	
	private static final Dangeon1ScenarioBook INSTANCE = new Dangeon1ScenarioBook();
	
	private Dangeon1ScenarioBook() {
		this.axis = "Dangeon1";
		System.out.println("Axis = "+ axis);
	}
	
	public static Dangeon1ScenarioBook getInstance() {
		return INSTANCE;
	}
	
}
