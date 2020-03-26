package com.github.miniyosshi.arciamstorypluginV2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.bukkit.entity.Player;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Users {
	//singleton
	private static Users instance = new Users();
	private static Map<String, User> users = new HashMap<String, User>();
	
	private Users() {	
	}
	
	public static Users getInstance() {
		return instance;
	}
	
	
	public static void addUser(User user) {
		String name = user.getName();
		users.put(name, user);
	}
	
	public static Optional<User> getUser(String name) {
		return Optional.ofNullable(users.get(name));
	}
	
	public static Optional<User> getUser(Player player) {
		String name = player.getName();
		return Optional.ofNullable(users.get(name));
	}
	
	
	public int getTotalNumber() {
		return users.size();
	}
	
	public static void importFrom(File f) {
		//read json file
		StringBuffer jsonText = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line;
			while((line = br.readLine()) != null) {
				jsonText.append(line + System.getProperty("line.separator"));
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Import Error.");
		}
		System.out.println("jsonText : "+jsonText);
		
		//json to Class/Collection
		ObjectMapper mapper = new ObjectMapper();
		try {
			users = mapper.readValue(jsonText.toString(), new TypeReference<Map<String ,User>>() {});
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
		System.out.println("Loaded json(users) : "+users);
	}
}
