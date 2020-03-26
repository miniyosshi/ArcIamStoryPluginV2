package com.github.miniyosshi.arciamstoryplugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JsonReader {
		
	public static String getJsonText(File f) {
		StringBuffer jsonText = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line;
			while((line = br.readLine()) != null) {
				jsonText.append(line);
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Import Error.");
		}
		return jsonText.toString();
	}
}