package com.github.miniyosshi.arciamstorypluginV2;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.bukkit.Location;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserInfo {
	//入出力あり
	private Location savedLocation;
	private ChapterSection chapterSection;
	private Birthday birthday;
	//入出力なし
	@JsonIgnore
	private Optional<DesignatedArea> pastDesignatedArea;
	
	public Location getSavedLocation() {
		return savedLocation;
	}
	
	public void setSavedLocation(Location location) {
		savedLocation = location;
	}
	
	public void setPastDesignatedArea(Optional<DesignatedArea> da) {
		pastDesignatedArea = da;
	}
	
	public boolean pastDesignatedAreaEquals(Optional<DesignatedArea> presentArea) {
		return pastDesignatedArea.equals(presentArea);
	}
	
	
	
	public Optional<UserInfo> importFrom(File jsonFile) {
		//read json file
		String jsonText = JsonReader.importJsonText(jsonFile);
		return importFrom(jsonText);
	}
	
	public Optional<UserInfo> importFrom(String jsonText) {
		//json to UserInfoClass
		Optional<UserInfo> userInfo = Optional.empty();
		//UserInfo()
		ObjectMapper mapper = new ObjectMapper();
		try {
			userInfo = Optional.ofNullable(mapper.readValue(jsonText, UserInfo.class));
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
		System.out.println("Loaded json(userInfo) : "+userInfo);
		return userInfo;
	}
	
	/*
	public String exportTo(File f) {
		
	}
	*/
}
