package com.github.miniyosshi.arciamstorypluginV2;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class Test {
	
	public static void main(String[] args) {
		//export
		//File f = new File("D:\\Desktop\\outputOfDesignatedAreas.json");
		DesignatedAreas das = DesignatedAreas.getInstance();
		Location loc = null;
		DesignatedSquareArea da1 = new DesignatedSquareArea("townx", loc, loc);
		DesignatedSquareArea da2 = new DesignatedSquareArea("cityA", loc, loc);
		//da.exportTo(f);
		das.exportAllTo(f);
		
		
		/* import
		DesignatedAreas das = DesignatedAreas.getInstance();
		File f = new File("D:\\Desktop\\designatedAreas.json");
		das.importAllFrom(f);
		*/
	}

}
