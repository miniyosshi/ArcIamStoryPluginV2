package com.github.miniyosshi.arciamstoryplugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.github.miniyosshi.economy.*;


public class CSVReader {
	
	public static void read(String fileheadname){
						
		try {
			String filename = fileheadname + ".csv";
			
			String path = new File(".").getAbsolutePath();
			System.out.println("Absolute path: "+path);
			
			File f = new File("./CSVFiles/"+filename);
			
			if(f.exists()) {
				
				BufferedReader br = new BufferedReader(new FileReader(f));
				
				String line;				
				
				while((line = br.readLine()) != null) {
					String[] data = line.split(",");
					
					if(data.length == CSVFiles.valueOf(fileheadname).getNumberofData()) {
						//本体
						readLinetoArrayList(CSVFiles.valueOf(fileheadname), data);
					}
					else {
						Bukkit.getLogger().info(filename +" is incorrect.");
						
						br.close();
						
						Plugin p = Bukkit.getServer().getPluginManager().getPlugin("ArcIamStoryPlugin");
						System.out.println(p.getName());
						System.out.println("So, this plugin is now automatically disabled.");
						Bukkit.getPluginManager().disablePlugin(p);
					}			
				}
				
				Bukkit.getLogger().info(filename+" has been readed.");
				br.close();
				
			}
			else {
				//f.createNewFile();
				Bukkit.getLogger().info(filename+" does not exit. "+"A new file "+ filename +" is created.");
			}
			
		}catch(IOException error) {
			System.out.println(error);
		}		
	}
			
	
	
		public static void readLinetoArrayList(CSVFiles cf, String[] data) {
			switch (cf) {
			case MoneyAccount : {
				Account element = new Account(data[0], Double.parseDouble(data[1]));
				List.moneyaccount.add(element);
				
				break;
			}
						
			
			
			case AreaData : {
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
				
				break;
			}
					
					
					
			case ChapterData : {
				
				ChapterData element = new ChapterData(Integer.parseInt(data[0]),Integer.parseInt(data[1]),data[2], data[3], Integer.parseInt(data[4]),data[5],data[6]);
				
				List.chapterdata.add(element);
				
				break;
			}
				
					
					
				
			case ScenarioData : {
				ScenarioData element = new ScenarioData(data[0], data[1]);
				
				List.scenariodata.add(element);
				break;	
			}
			
			
			
			case SpawnPoints : {
				
				World w = Bukkit.getServer().getWorld(data[1]);
				Location loc = new Location(w,(int)Float.parseFloat(data[2]),(int)Float.parseFloat(data[3]),(int)Float.parseFloat(data[4]));
				
				SpawnPoints element = new SpawnPoints(data[0], loc);
				
				List.spawnpoints.add(element);
				break;	
			}
			
			
			case UserData : {
				//System.out.println(Bukkit.getServer().getWorlds().get(0).getName());
				//System.out.println(Bukkit.getServer().getWorlds().get(0).toString());						
					
				World w = Bukkit.getServer().getWorld(data[1]);
					
				//System.out.println(w);
				
				Location savedlocation = new Location(w,(int)Float.parseFloat(data[2]),(int)Float.parseFloat(data[3]),(int)Float.parseFloat(data[4]));
				
				int[] skill = {Integer.parseInt(data[7]), Integer.parseInt(data[8]), Integer.parseInt(data[9])};
				
				User element = new User(data[0],savedlocation,(int)Float.parseFloat(data[5]),(int)Float.parseFloat(data[6]), skill, Boolean.valueOf(data[10]));
				
				List.userdata.add(element);
					
				break;
			}
				
				
			
			case ViewPoint : {
				
				World w = Bukkit.getServer().getWorld(data[2]);
				Location point = new Location(w,(int)Float.parseFloat(data[3]),(int)Float.parseFloat(data[4]),(int)Float.parseFloat(data[5]),(int)Float.parseFloat(data[6]),(int)Float.parseFloat(data[7]));
				
				List.setChapterViewPoint(Integer.parseInt(data[0]), Integer.parseInt(data[1]), point);
				
				break;
			}								
		}
	}		
	
	
	
	
	public static void reload() {
		
		System.out.println("Reload started.");
		
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			p.kickPlayer("Reload of Server Files has started. So, please join a few seconds later.");
		}		
				
		List.areadata.clear();
		List.chapterdata.clear();
		List.moneyaccount.clear();
		List.scenariodata.clear();
		List.userdata.clear();
		
		System.out.println("Cleared old cache.");
		
		for (CSVFiles f : CSVFiles.values()) {
			read(f.toString());
		}
		//OnPlayer Joinとおなじ操作が必要？
		
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


