package com.github.miniyosshi.arciamstorypluginV2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ListDataRepository<T extends ListElement> {
	
	private List<T> list = new ArrayList<T>();
	
	private final String parentFolderString = "./ArcIamExternalFiles/JsonFiles/";
	
	public void add(T element) {
		list.add(element);
	}
	
	public void delete(T element) {
		list.remove(element);
	}
	
	public void importAllFrom(File folder, Class<T> elementClass) {
		List<File> fileList = Arrays.asList(Optional.ofNullable(folder.listFiles()).orElse(new File[0]));
		for(File f : fileList) {
			ObjectMapper mapper = new ObjectMapper();
			Optional<T> element = Optional.empty();
			try {						
				element = Optional.ofNullable(mapper.readValue(f, elementClass));
				element.ifPresent(v -> add(v));
			} catch (IOException e) {
		    	e.printStackTrace();
		    }
		}
		System.out.println("Loaded json : "+list.toString());
	}
	
	public void importAllFromDefaultFolder(Class<T> elementClass) {
		String folderName = this.getClass().getSimpleName();
		File folder = new File(parentFolderString, folderName);
		System.out.println(folder.toString());
		importAllFrom(folder, elementClass);
	}
	
	public void exportAllTo(File folder) {
		for(T element : list) {
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
