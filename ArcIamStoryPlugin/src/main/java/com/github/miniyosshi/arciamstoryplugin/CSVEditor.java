package com.github.miniyosshi.arciamstoryplugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVEditor {
	
	//プレーヤーのセーブ地点、章節をCSVファイルに出力
	public static void exportCSV() {
		try {
			   File f = new File("PlayerData.csv");
			      BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			      
			      for(int i = 0; i < CSVReader.userdata.size(); i++) {
			    	  
			    	  User elem = CSVReader.userdata.get(i);
			    	  bw.write(elem.getName() + "," + elem.savedlocation.getWorld() + "," + elem.savedlocation.getX() + "," + elem.savedlocation.getY() + "," + elem.savedlocation.getZ() + "," + elem.chapter + "," + elem.section);
				      bw.newLine();
			      }

			      bw.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

}
