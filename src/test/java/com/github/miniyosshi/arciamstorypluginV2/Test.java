package com.github.miniyosshi.arciamstorypluginV2;

import java.io.File;
import java.util.Optional;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Test {
	
	public static void main(String[] args) {
		//export
		/*
		DesignatedAreas das = DesignatedAreas.getInstance();
		Location loc = null;
		DesignatedSquareArea da1 = new DesignatedSquareArea("townx", loc, loc);
		DesignatedSquareArea da2 = new DesignatedSquareArea("cityA", loc, loc);
		das.exportAllToDefaultFolder();
		*/
		/*
		NormalModeUser u = new NormalModeUser("taro0001", new UserInfo());		
		Users.getInstance().exportAllToDefaultFolder();
		*/
		
		/* import
		DesignatedAreas das = DesignatedAreas.getInstance();
		File f = new File("D:\\Desktop\\designatedAreas.json");
		das.importAllFrom(f);
		*/
		
		//Users.getInstance().importAllFromDefaultFolder(User.class);
		
		/*
		Optional<String> s = null;
		System.out.println(s.isEmpty());
		//return nullは厳禁　return Optional.empty()とする！！！
		*/
		/*
		SaverNPC npc = new SaverNPC("セーブさん");
		NPCs.getInstance().exportAllToDefaultFolder();
		*/
		
	}

}
