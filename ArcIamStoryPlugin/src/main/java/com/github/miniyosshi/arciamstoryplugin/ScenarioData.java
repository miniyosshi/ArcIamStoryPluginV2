package com.github.miniyosshi.arciamstoryplugin;

public class ScenarioData {
	String utterer;
	String line;
	
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
