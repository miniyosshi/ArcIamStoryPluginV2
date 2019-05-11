package com.github.miniyosshi.arciamstoryplugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.Location;

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
				    	  bw.write(elem.getName()+ "," + elem.getcornerA().getWorld().getName() + "," + elem.getcornerA().getX() + "," + elem.getcornerA().getY() + "," + elem.getcornerA().getZ() + "," + elem.getcornerB().getX() + "," + elem.getcornerB().getY() + "," + elem.getcornerB().getZ() );
					      bw.newLine();
				   }
				   break;
				
				   				   
			   	case "UserData.csv" :
				   for(int i = 0; i < CSVReader.userdata.size(); i++) {
				    	  
				    	  User elem = CSVReader.userdata.get(i);
				    	  
				    	  Location l = elem.getSavedLocation();
				    	  String s1 = l.getWorld().getName();
				    	  String s2 = String.valueOf(l.getX());
				    	  String s3 = String.valueOf(l.getY());
				    	  String s4 = String.valueOf(l.getZ());
				    	 				    	  
				    	  bw.write(elem.getName() + "," + s1 + "," + s2 + "," + s3 + "," + s4 + "," + elem.getChapter() + "," + elem.getSection());
					      bw.newLine();
				   }
				   break;
			   
			   }
			      bw.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

}
