package com.github.miniyosshi.arciamstoryplugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

import org.bukkit.Location;
import com.github.miniyosshi.economy.*;

public class CSVExporter {
	
	//プレーヤーのセーブ地点、章節をCSVファイルに出力
	public static void exportCSV(String fileheadname) {
		
		
		
		
		
		
		
		
		
		
		
		
		try {
			
			String filename = fileheadname + ".csv";
			
			File f = new File(filename);
			   
			      
			   switch(CSVFiles.valueOf(fileheadname)) {
			   case AreaData :
				   for(int i = 0; i < List.areadata.size(); i++) {
				    	  
				    	  AreaData elem = List.areadata.get(i);
				    	  bw.write(elem.getName()+ "," + elem.getcornerA().getWorld().getName() + "," + elem.getcornerA().getX() + "," + elem.getcornerA().getY() + "," + elem.getcornerA().getZ() + "," + elem.getcornerB().getX() + "," + elem.getcornerB().getY() + "," + elem.getcornerB().getZ() );
					      bw.newLine();
				   }
				   break;
				
				   
			   case BankAccount :
				   for(int i = 0; i < List.moneyaccount.size(); i++) {
				    	  
<<<<<<< HEAD
				    	  Wallet elem = List.moneyaccount.get(i);
=======
				    	  BankAccount elem = List.moneyaccount.get(i);
>>>>>>> 6772faa5305f7e043d039e22fd0a2938c90ba90e
				    	  bw.write(elem.getName() + "," + BigDecimal.valueOf(elem.getBalance()).toPlainString());
				    	  bw.newLine();
				   }
				   break;
				   				   
			   	case UserData :
				   for(int i = 0; i < List.userdata.size(); i++) {
				    	  
				    	  User elem = List.userdata.get(i);
				    	  
				    	  Location l = elem.getSavedLocation();
				    	  String s1 = l.getWorld().getName();
				    	  String s2 = String.valueOf(l.getX());
				    	  String s3 = String.valueOf(l.getY());
				    	  String s4 = String.valueOf(l.getZ());
				    	  
				    	  int year = elem.getBirthday()[0];
				    	  int month = elem.getBirthday()[1];
				    	  int day = elem.getBirthday()[2];
				    	  
				    	  String h = String.valueOf(elem.getHardmode());
				    	 				    	  
				    	  bw.write(elem.getName() + "," + s1 + "," + s2 + "," + s3 + "," + s4 + "," + elem.getChapter() + "," + elem.getSection() + "," + year + "," + month + "," + day + "," + h );
					      bw.newLine();
				   }
				   break;
			default:
				System.out.println("Exporterでエラー");
				break;
			   
			   }
			      bw.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

}
