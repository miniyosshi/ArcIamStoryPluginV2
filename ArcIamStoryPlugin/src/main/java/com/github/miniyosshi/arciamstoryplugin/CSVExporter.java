package com.github.miniyosshi.arciamstoryplugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class CSVExporter {
	
	//プレーヤーのセーブ地点、章節をCSVファイルに出力
	public static void exportCSV(String filename) {
		try {
			   File f = new File(filename);
			   BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			      
			   switch(filename) {
			   case "AreaData.csv" :
				   for(int i = 0; i < CSVReader.areadata.size(); i++) {
				    	  
				    	  AreaData elem = CSVReader.areadata.get(i);
				    	  bw.write(elem.getcornerA().getWorld().getName()+ "," + elem.getcornerA().getWorld() + "," + elem.getcornerA().getX() + "," + elem.getcornerA().getY() + "," + elem.getcornerA().getZ() + "," + elem.getcornerB().getX() + "," + elem.getcornerB().getY() + "," + elem.getcornerB().getZ() );
					      bw.newLine();
				   }
				      
				   				   
			   case "UserData.csv" :
				   for(int i = 0; i < CSVReader.userdata.size(); i++) {
				    	  
				    	  User elem = CSVReader.userdata.get(i);
				    	  bw.write(elem.getName() + "," + elem.savedlocation.getWorld().getName() + "," + elem.savedlocation.getX() + "," + elem.savedlocation.getY() + "," + elem.savedlocation.getZ() + "," + elem.chapter + "," + elem.section);
					      bw.newLine();
				   }
			   
			   }
			      bw.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

}
