package com.github.miniyosshi.arciamstoryplugin;

public class ScenarioData {
	private String utterer;
	private String line;
	
	ScenarioData(String utterer, String line){
		this.utterer = utterer;
		this.line = line;
	}
	
	String getUtterer() {
		return utterer;
	}
	
	String getLine() {
		return line;
	}

}
