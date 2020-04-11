package com.github.miniyosshi.arciamstorypluginV2;

import java.util.Optional;

import org.bukkit.entity.Player;

public class Users extends DataRepository<User> {
	// Singleton
	private static final Users INSTANCE = new Users();
	
	private Users() {}
	
	public static Users getInstance() {
		return INSTANCE;
	}
	
	public Optional<User> getElementBy(Player player) {
		String name = player.getName();
		User user = (User) map.get(name);
		return Optional.ofNullable(user);
	}	
}
