package com.github.miniyosshi.arciamstoryplugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.bukkit.entity.Player;

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
}
