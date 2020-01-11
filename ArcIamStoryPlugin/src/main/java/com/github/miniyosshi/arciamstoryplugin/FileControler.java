package com.github.miniyosshi.arciamstoryplugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;

public abstract class FileControler {

	public abstract void read(File f, ArrayList<String[]> al);
	
	public abstract void write(ArrayList<String[]> al, File f);
	
	public static void generate(File f) {
		String filename = f.getName();
		try {
			f.createNewFile();
			Bukkit.getLogger().info("A new file "+ filename +" is created.");
		} catch (IOException e) {
			e.getMessage();
		}
	}
	
	public static void delete(File f) {
		f.delete();
	}
	

}
