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

@JsonTypeInfo(use=JsonTypeInfo.Id.MINIMAL_CLASS, property="classType")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public abstract class ListElement {
	
	protected String name;
	//1-1などの数字列を想定（ファイル名に対応）
	
	public Optional<ListElement> importFrom(File jsonFile) {
		String jsonText = JsonReader.importJsonText(jsonFile);
		return importFrom(jsonText);
	}
	
	public Optional<ListElement> importFrom(String jsonText) {
		ObjectMapper mapper = new ObjectMapper();
		Optional<ListElement> element = Optional.empty();
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
