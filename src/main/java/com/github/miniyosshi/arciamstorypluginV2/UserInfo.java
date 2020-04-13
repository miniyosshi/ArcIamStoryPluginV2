package com.github.miniyosshi.arciamstorypluginV2;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import org.bukkit.Location;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown=true)
public class UserInfo {
	//入出力あり
	private Location savedLocation;
	private ChapterSection chapterSection;
	private Birthday birthday;
	private boolean isInStoryEvent = false;
	//入出力なし
	@JsonIgnore
	private Optional<DesignatedArea> pastDesignatedArea;
	
	public UserInfo(Location savedLocation, ChapterSection chapterSection, Birthday birthday, boolean isInStoryEvent, Optional<DesignatedArea> pastDesignatedArea) {
		this.savedLocation = savedLocation;
		this.chapterSection = chapterSection;
		this.birthday = birthday;
		this.isInStoryEvent = isInStoryEvent;
		this.pastDesignatedArea = pastDesignatedArea;
	}
	
	@JsonCreator
	public UserInfo(@JsonProperty("serializedSavedLocation")Map<String, Object> serializedSavedLocation, @JsonProperty("chapterSection")ChapterSection chapterSection, 
					@JsonProperty("birthday")Birthday birthday, @JsonProperty("isInStoryEvent")boolean isInStoryEvent, @JsonProperty("pastDesignatedArea")Optional<DesignatedArea> pastDesignatedArea) {
		this.savedLocation = Location.deserialize(serializedSavedLocation);
		this.chapterSection = chapterSection;
		this.birthday = birthday;
		this.isInStoryEvent = isInStoryEvent;
		this.pastDesignatedArea = pastDesignatedArea;
	}
	
	@JsonIgnore
	public Location getSavedLocation() {
		return savedLocation;
	}
	@JsonProperty
	public Map<String, Object> getSerializedSavedLocation() {
		return savedLocation.serialize();
	}
	@JsonIgnore
	public void setSavedLocation(Location location) {
		savedLocation = location;
	}
	@JsonIgnore
	public boolean isInStoryEvent() {
		return isInStoryEvent;
	}
	@JsonIgnore
	public void setPastDesignatedArea(Optional<DesignatedArea> da) {
		pastDesignatedArea = da;
	}
	
	@JsonIgnore
	public boolean pastDesignatedAreaEquals(Optional<DesignatedArea> presentArea) {
		return pastDesignatedArea.equals(presentArea);
	}
	
	/*
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

	public String exportTo(File f) {
		
	}
	*/
}
