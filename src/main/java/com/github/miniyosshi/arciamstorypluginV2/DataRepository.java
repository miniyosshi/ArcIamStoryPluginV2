package com.github.miniyosshi.arciamstorypluginV2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class DataRepository<T extends MapElement> {
	
	protected Map<String, T> map = new HashMap<String, T>();
	
	//private final String parentFolderString = "./ExternalFiles/Jsons/";
	private final String parentFolderString = "D:/Desktop/JsonFiles";
	
	public void add(T element) {
		map.put(element.getName(), element);
	}
	
	public void delete(T element) {
		map.remove(element.getName());
	}
	
	public Optional<T> getElementBy(String name) {
		return Optional.ofNullable(map.get(name));
	}
	
	public void importAllFrom(File folder, Class<T> elementClass) {
		List<File> list = Arrays.asList(Optional.ofNullable(folder.listFiles()).orElse(new File[0]));
		for(File f : list) {
			ObjectMapper mapper = new ObjectMapper();
			Optional<T> element = Optional.empty();
			try {						
				element = Optional.ofNullable(mapper.readValue(f, elementClass));
				element.ifPresent(v -> add(v));
			} catch (IOException e) {
		    	e.printStackTrace();
		    }
		}
		System.out.println("Loaded json : "+map.toString());
	}
	
	public void importAllFromDefaultFolder(Class<T> elementClass) {
		String folderName = this.getClass().getSimpleName();
		File folder = new File(parentFolderString, folderName);
		System.out.println(folder.toString());
		importAllFrom(folder, elementClass);
	}
	
	public void exportAllTo(File folder) {
		for(T element : map.values()) {
			element.exportTo(folder);
		}
	}
	
	public void exportAllToDefaultFolder() {
		String folderName = this.getClass().getSimpleName();
		File folder = new File(parentFolderString, folderName);
		System.out.println(folder.toString());
		exportAllTo(folder);
	}
}
