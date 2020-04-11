package com.github.miniyosshi.arciamstorypluginV2;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@JsonTypeInfo(use=JsonTypeInfo.Id.MINIMAL_CLASS, property="classType")
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class MapElement {
	
	protected String name;
	
	public String getName() {
		return name;
	}
	
	public boolean equals(String name) {
		return this.name.equals(name);
	}
	
	public Optional<MapElement> importFrom(File jsonFile) {
		String jsonText = JsonReader.importJsonText(jsonFile);
		return importFrom(jsonText);
	}
	
	public Optional<MapElement> importFrom(String jsonText) {
		ObjectMapper mapper = new ObjectMapper();
		Optional<MapElement> element = Optional.empty();
		try {
			element = Optional.ofNullable(mapper.readValue(jsonText, MapElement.class));
		} catch (IOException e) {
	    	e.printStackTrace();
	    }
		return element;
	}
	
	public void exportTo(File folder) {
		//serialize
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		//mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		try {
			File f = new File(folder.toString() + "/" + name +".json");//若しくはFile.separator
			System.out.println(f.toString());
			mapper.writeValue(f, this);
		} catch (IOException e) {
	    	e.printStackTrace();
	    }   
	}
	
}
