package com.github.miniyosshi.arciamstoryplugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;


public class CSVReader {
	
	public static ArrayList<AreaData> areadata = new ArrayList<AreaData>();
	public static ArrayList<ChapterData> chapterdata = new ArrayList<ChapterData>();
	public static ArrayList<User> userdata = new ArrayList<User>();
	
	public void read(String filename){
		
		try {
			File f = new File(filename);
			BufferedReader br = new BufferedReader(new FileReader(f));
			
			String line;
			
			switch (filename) {
			case "AreaData.csv" :
				
				while ((line = br.readLine()) != null) {
					String[] data = line.split(",");
					
					World w = Bukkit.getServer().getWorld(data[1]);
					Location cornerA = new Location(w,Integer.parseInt(data[2]),Integer.parseInt(data[3]),Integer.parseInt(data[4]));
					Location cornerB = new Location(w,Integer.parseInt(data[5]),Integer.parseInt(data[6]),Integer.parseInt(data[7]));
					
					AreaData element = new AreaData(data[0],cornerA, cornerB);
					
					areadata.add(element);
				}
				//System.out.println(areadata.get(0).getName());
				break;
				
				
			case "ChapterData.csv":
				
				while ((line = br.readLine()) != null) {
					String[] data = line.split(",");
					
					ChapterData element = new ChapterData(Integer.parseInt(data[0]),Integer.parseInt(data[1]),data[2], Integer.parseInt(data[3]),data[4],data[5]);
					
					chapterdata.add(element);
				}
				
				break;
			
				
			case "UserData.csv":
				
				System.out.println(Bukkit.getServer().getWorlds().get(0).getName());
				System.out.println(Bukkit.getServer().getWorlds().get(0).toString());
				
				while ((line = br.readLine()) != null) {
					String[] data = line.split(",");
					
					
					World w = Bukkit.getServer().getWorld(data[1]);
					
					//System.out.println(w);
					
					Location savedlocation = new Location(w,Integer.parseInt(data[2]),Integer.parseInt(data[3]),Integer.parseInt(data[4]));
					
					User element = new User(data[0],savedlocation,Integer.parseInt(data[5]),Integer.parseInt(data[6]));
					
					userdata.add(element);
					
				}
				//System.out.println(userdata.get(0).getName()+"PlayerData.csvのやつ");
				break;
				
				
				
			}
						
			br.close();
		}catch(IOException error) {
			System.out.println(error);
		}
	}

}


