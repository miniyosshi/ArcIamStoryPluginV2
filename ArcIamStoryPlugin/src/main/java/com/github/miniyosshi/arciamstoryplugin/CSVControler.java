package com.github.miniyosshi.arciamstoryplugin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;

public class CSVControler extends FileControler {
	
	public static void read(File f, ArrayList<String[]> al) {
		String filename = f.getName();
		String line;
		
		if(f.exists()) {
			al.clear();
			try {
				BufferedReader br = new BufferedReader(new FileReader(f));
				while((line = br.readLine()) != null) {
					String[] data = line.split(",");
					al.add(data);
				}
				br.close();
			
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		else {
			Bukkit.getLogger().info(filename+" does not exit.");
		}
		
	}

	public static void write(ArrayList<String[]> al, File f) {
		String filename = f.getName();
		
		if(!f.exists()) {
			Bukkit.getLogger().info(filename+" does not exit.");
			generate(f);
		}
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

}
