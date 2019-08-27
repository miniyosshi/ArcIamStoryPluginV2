package com.github.miniyosshi.arciamstoryplugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;


public class CSVReader {
	
	
	
	public static void read(String filename){
						
		try {
			File f = new File(filename);
					
			if(f.exists()) {
				BufferedReader br = new BufferedReader(new FileReader(f));
				
				String line;				
								
				switch (filename) {
				case "AreaData.csv" :
					
					while ((line = br.readLine()) != null) {
						String[] data = line.split(",");
						
						//csvの型の判定
						/*
						if(isIntChangable(data[2])) {
							
						}
						*/
						
						World w = Bukkit.getServer().getWorld(data[1]);
						Location cornerA = new Location(w,(int)Float.parseFloat(data[2]),(int)Float.parseFloat(data[3]),(int)Float.parseFloat(data[4]));
						Location cornerB = new Location(w,(int)Float.parseFloat(data[5]),(int)Float.parseFloat(data[6]),(int)Float.parseFloat(data[7]));
						
						AreaData element = new AreaData(data[0],cornerA, cornerB);
						
						List.areadata.add(element);
						
					}
					break;
					
					
				case "ChapterData.csv":
					
					while ((line = br.readLine()) != null) {
						String[] data = line.split(",");
						
						ChapterData element = new ChapterData(Integer.parseInt(data[0]),Integer.parseInt(data[1]),data[2], data[3], Integer.parseInt(data[4]),data[5],data[6]);
						
						List.chapterdata.add(element);
					}
					break;
				
				case "ScenarioData.csv":
					
					while ((line = br.readLine()) != null) {
						String[] data = line.split(",");
						
						ScenarioData element = new ScenarioData(data[0], data[1]);
						
						List.scenariodata.add(element);
					}
					break;	
					
				
					
				case "UserData.csv":
					
					//System.out.println(Bukkit.getServer().getWorlds().get(0).getName());
					//System.out.println(Bukkit.getServer().getWorlds().get(0).toString());
					
					while ((line = br.readLine()) != null) {
						String[] data = line.split(",");
						
						
						World w = Bukkit.getServer().getWorld(data[1]);
						
						//System.out.println(w);
						
						Location savedlocation = new Location(w,(int)Float.parseFloat(data[2]),(int)Float.parseFloat(data[3]),(int)Float.parseFloat(data[4]));
						
						User element = new User(data[0],savedlocation,(int)Float.parseFloat(data[5]),(int)Float.parseFloat(data[6]));
						
						List.userdata.add(element);
						
					}
					break;
					
				}
				
				System.out.println(f.getName()+" has been readed.");
							
				br.close();
			}
			else {
				System.out.println(filename+" does not exit.");
			}
			
		}catch(IOException error) {
				System.out.println(error);
		}
			
			
	}
	
	public static void reload() {
		
		System.out.println("Reload started.");
		
		
		
		List.areadata.clear();
		List.chapterdata.clear();
		List.scenariodata.clear();
		List.userdata.clear();
		
		System.out.println("Cleared old cache.");
		
		read("AreaData.csv");
		read("ChapterData.csv");
		read("ScenarioData.csv");
		read("UserData.csv");
		System.out.println("Reload completed.");
	}
	
	
	
	boolean isIntChangable(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
	
	boolean isFloatChangable(String s) {
		try {
			Float.parseFloat(s);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
	
	
	

}


