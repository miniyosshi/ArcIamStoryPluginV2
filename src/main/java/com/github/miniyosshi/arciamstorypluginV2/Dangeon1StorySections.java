package com.github.miniyosshi.arciamstorypluginV2;

import java.util.Optional;

public class Dangeon1StorySections extends StorySections{
	
	private static final Dangeon1StorySections INSTANCE = new Dangeon1StorySections();
	
	private Dangeon1StorySections() {
		this.axis = "Dangeon1";
		System.out.println("Axis = "+ axis);
	}
	
	public static Dangeon1StorySections getInstance() {
		return INSTANCE;
	}
}
