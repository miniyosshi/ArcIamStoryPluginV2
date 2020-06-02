package com.github.miniyosshi.arciamstorypluginV2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

public abstract class ListDataRepository<T extends Element>{
	
	protected List<T> list = new ArrayList<T>();
	
	private String parentFolderString = "./plugins/ArcIamExternalFiles/JsonFiles/";
	
	public void add(T element) {
		list.add(element);
	}
	
	public void delete(T element) {
		list.remove(element);
	}
	
	public int indexOf(Element e) {
		return list.indexOf(e);
	}
	
	public int size() {
		return list.size();
	}
	
	public Optional<T> getElementBy(int index) {
		if(0<=index && index<list.size()) {
			return Optional.of(list.get(index));
		}
		System.out.println("ListDataRepository: input index is out of range.");
		return Optional.empty();
	}
	
	public Optional<T> getElementBy(String name) {
		for(T element : list) {
			if(name.equals(element.getName())){
				return Optional.of(element);
			}
		}
		return Optional.empty();
	}
	
	public void importAllFrom(File folder, Class<T> elementClass) {
		List<File> fileList = Arrays.asList(Optional.ofNullable(folder.listFiles()).orElse(new File[0]));
		for(File f : fileList) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new Jdk8Module());
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
