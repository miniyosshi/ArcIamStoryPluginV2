package com.github.miniyosshi.arciamstorypluginV2;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.bukkit.Location;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfo {
	
	private Location savedLocation;
	private int[] chapterSection = new int[2];
	private Birthday birthday;
	private boolean isInStoryEvent = false;
	private double money;
	@JsonIgnore
	private Optional<DesignatedArea> pastDesignatedArea;
	
	public UserInfo(Location savedLocation, int[] chapterSection, Birthday birthday,
					boolean isInStoryEvent, double money,Optional<DesignatedArea> pastDesignatedArea) {
		this.savedLocation = savedLocation;
		this.chapterSection = chapterSection;
		this.birthday = birthday;
		this.isInStoryEvent = isInStoryEvent;
		this.money = money;
		this.pastDesignatedArea = pastDesignatedArea;
	}
	
	@JsonCreator
	public UserInfo(@JsonProperty("serializedSavedLocation")Map<String, Object> serializedSavedLocation, @JsonProperty("chapterSection")int[] chapterSection, 
					@JsonProperty("birthday")Birthday birthday, @JsonProperty("isInStoryEvent")boolean isInStoryEvent, @JsonProperty("money")double money,
					@JsonProperty("pastDesignatedArea")Optional<DesignatedArea> pastDesignatedArea) {
		this.savedLocation = SerializableLocation.deserialize(serializedSavedLocation).getLocation();
		this.chapterSection = chapterSection;
		this.birthday = birthday;
		this.isInStoryEvent = isInStoryEvent;
		this.money = money;
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
		return Objects.equals(pastDesignatedArea, presentArea);
	}
}
