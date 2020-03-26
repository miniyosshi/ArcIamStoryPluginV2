package com.github.miniyosshi.arciamstoryplugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Collection {
	
	public static void importFrom(String jsonText) {
		//json to Class
		ObjectMapper mapper = new ObjectMapper();
		try {
			collection = mapper.readValue(jsonText.toString(), new TypeReference<List<Element>>>() {});
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
		System.out.println("Loaded json(designatedAreas) : "+designatedAreas);
	}
	
	public static void exportTo(File f) {
		
	}
}
