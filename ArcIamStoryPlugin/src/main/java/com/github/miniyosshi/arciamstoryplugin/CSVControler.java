package com.github.miniyosshi.arciamstoryplugin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;

public class CSVControler {
	//CSV to String List
	public static ArrayList<?> read(File path) {
		String filename = path.getName();
		//test
		System.out.println("filename without directory: "+ filename);
		
		ArrayList<?> al = new ArrayList<>();
		
		//test
		System.out.println("about arraylist"+al.toString());
		
		if(path.exists()) {
			/**
			try {
				BufferedReader br = new BufferedReader(new FileReader(path));
				String line;
				while((line = br.readLine()) != null) {
					String[] data = line.split(",");
					//cast   string to int / user
					// al =new ArrayList<kata>()
					al.add(data);
				}
				br.close();
			
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			**/
		}
		else {
			Bukkit.getLogger().info(filename+" does not exit.");
		}
		return al;
	}
	
	//String list TO CSV
	public static void write(ArrayList<String[]> al, File f) {
		String filename = f.getName();
		
		if(f.exists()) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(f));
				for(String[] strings : al) {
					String elem = String.join(",",strings);
					bw.write(elem);
					bw.newLine();
				}
				bw.close();	
			} catch (IOException e) {
				System.out.println(e.getMessage());
	        }
		}
		else {
			Bukkit.getLogger().info(filename+" does not exit.");
		}
		
		
	}

}
