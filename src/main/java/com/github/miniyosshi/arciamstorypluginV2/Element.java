package com.github.miniyosshi.arciamstorypluginV2;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

@JsonTypeInfo(use=JsonTypeInfo.Id.MINIMAL_CLASS, property="classType")
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonIgnoreProperties(ignoreUnknown=true)
public abstract class Element {
	
	//@JsonIgnore
	//public final String parentFolderString = "./plugins/ArcIamExternalFiles/JsonFiles/";
	
	protected String name;
	
	public String getName() {
		return name;
	}
	
	public boolean equals(String name) {
		return this.name.equals(name);
	}
	
	public Optional<Element> importFrom(File jsonFile) {
		String jsonText = JsonReader.importJsonText(jsonFile);
		return importFrom(jsonText);
	}
	
	
	public Optional<Element> importFrom(String jsonText) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Jdk8Module());
		Optional<Element> element = Optional.empty();
		try {
			element = Optional.ofNullable(mapper.readValue(jsonText, this.getClass()));
		} catch (IOException e) {
	    	e.printStackTrace();
	    }
		return element;
	}
	
	public void exportTo(File folder) {
		//serialize
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Jdk8Module());
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		//mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		try {
			File f = new File(folder.toString() + "/" + name +".json");//若しくはFile.separator
			if(f.getParentFile().mkdirs()) {
				System.out.println("Create new directory : " + f.getParent());
			}
			if(f.createNewFile()) {
				System.out.println("Create new file : " + f.toString());
			}
			System.out.println("Export to : " + f.toString());
			mapper.writeValue(f, this);
		} catch (IOException e) {
	    	e.printStackTrace();
	    }   
	}
	
}
