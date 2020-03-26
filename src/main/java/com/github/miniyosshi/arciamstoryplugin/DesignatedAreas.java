package com.github.miniyosshi.arciamstoryplugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bukkit.Location;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DesignatedAreas extends Collection {
	
	private static List<DesignatedArea> designatedAreas = new ArrayList<DesignatedArea>();
	
	
	public static Optional<DesignatedArea> getDesigantedArea(String name) {
		for (DesignatedArea da : designatedAreas) {
			if(da.equals(name)) {
				Optional<DesignatedArea> oda = Optional.of(da);
				return oda;
			}
		}
		return null;
	}
	
	public static Optional<DesignatedArea> getDesigantedArea(Location location) {
		for (DesignatedArea da : designatedAreas) {
			if(da.contains(location)) {
				return Optional.of(da);
			}
		}
		return null;
	}
	
	public static void addDesignatedArea(DesignatedArea da) {
		designatedAreas.add(da);
	}
	
	public static void deleteDesignatedArea(DesignatedArea da) {
		designatedAreas.remove(da);
	}
	
	public static void importFrom(File f) {
		//read json file
		StringBuffer jsonText = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line;
			while((line = br.readLine()) != null) {
				jsonText.append(line);
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Import Error.");
		}
		System.out.println("jsonText : "+jsonText);
		
		//json to Class
		ObjectMapper mapper = new ObjectMapper();
		try {
			designatedAreas = mapper.readValue(jsonText.toString(), new TypeReference<List<DesignatedArea>>() {});
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
		System.out.println("Loaded json(designatedAreas) : "+designatedAreas);
	}
	
	public static void exportTo(File f) {
		
	}
	
	
}
