package com.github.miniyosshi.arciamstoryplugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVExporter {
	
	//プレーヤーのセーブ地点、章節をCSVファイルに出力
	public static void exportCSV(String filename) {
		try {
			   File f = new File(filename);
			   BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			      
			   switch(filename) {
			   case "AreaData.csv" :
				   
				   
				   
				   
				   
				   
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
