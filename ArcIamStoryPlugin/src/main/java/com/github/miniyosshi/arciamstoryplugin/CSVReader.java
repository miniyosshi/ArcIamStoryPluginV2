package com.github.miniyosshi.arciamstoryplugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;


public class CSVReader {
	
	public static ArrayList<AreaData> areadata = new ArrayList<AreaData>();
	public static ArrayList<ChapterData> chapterdata = new ArrayList<ChapterData>();
	public static ArrayList<ScenarioData> scenariodata = new ArrayList<ScenarioData>();
	public static ArrayList<User> userdata = new ArrayList<User>();
	
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
														
						World w = Bukkit.getServer().getWorld(data[1]);
						Location cornerA = new Location(w,(int)Float.parseFloat(data[2]),(int)Float.parseFloat(data[3]),(int)Float.parseFloat(data[4]));
						Location cornerB = new Location(w,(int)Float.parseFloat(data[5]),(int)Float.parseFloat(data[6]),(int)Float.parseFloat(data[7]));
						
						AreaData element = new AreaData(data[0],cornerA, cornerB);
						
						areadata.add(element);
					}
					break;
					
					
				case "ChapterData.csv":
					
					while ((line = br.readLine()) != null) {
						String[] data = line.split(",");
						
						ChapterData element = new ChapterData(Integer.parseInt(data[0]),Integer.parseInt(data[1]),data[2], data[3], Integer.parseInt(data[4]),data[5],data[6]);
						
						chapterdata.add(element);
					}
					break;
				
				case "ScenarioData.csv":
					
					while ((line = br.readLine()) != null) {
						String[] data = line.split(",");
						
						ScenarioData element = new ScenarioData(data[0], data[1]);
						
						scenariodata.add(element);
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
						
						userdata.add(element);
						
					}
					//System.out.println(userdata.get(0).getName()+"PlayerData.csvのやつ");
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
		
		System.out.println("Reload is started.");
		areadata.clear();
		chapterdata.clear();
		scenariodata.clear();
		userdata.clear();
		
		read("AreaData.csv");
		read("ChapterData.csv");
		read("ScenarioData.csv");
		read("UserData.csv");
		
	}

}


