package com.github.miniyosshi.arciamstorypluginV2;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.bukkit.Location;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonIgnoreProperties(ignoreUnknown=true)
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserInfo {
	private Optional<Location> savedLocation = Optional.empty();
	//プリミティブ型以外は初期化しておかないとエラーになる
	@JsonProperty
	private int[] chapterSection = new int[2];
	@JsonProperty
	private Birthday birthday = new Birthday();
	@JsonProperty
	private boolean isInStoryEvent;
	@JsonProperty
	private Assets assets = new Assets();
	@JsonProperty
	private Ability ability = new Ability();
	@JsonIgnore
	private Optional<DesignatedArea> pastDesignatedArea = Optional.empty();
	
	public UserInfo() {
		this.chapterSection[0] = 1;
		this.chapterSection[1] = 1;
		this.birthday = new Birthday();
		this.assets = new Assets();
		this.ability = new Ability();
	}
	
	@JsonCreator
	public UserInfo(@JsonProperty("serializedSavedLocation") Optional<Map<String, Object>> serializedSavedLocation, @JsonProperty("chapterSection")int[] chapterSection, 
					@JsonProperty("birthday")Birthday birthday, @JsonProperty("isInStoryEvent")boolean isInStoryEvent, @JsonProperty("assets")Assets assets,
					@JsonProperty("ability")Ability ability) {
		serializedSavedLocation.ifPresent(v ->{
			this.savedLocation = Optional.of(SerializableLocation.deserialize(v).getLocation());
		});
		this.chapterSection = chapterSection;
		this.birthday = birthday;
		this.isInStoryEvent = isInStoryEvent;
		this.assets = assets;
		this.ability = ability;
	}
	
	@JsonIgnore
	public Optional<Location> getSavedLocation() {
		return savedLocation;
	}
	
	@JsonProperty
	public Optional<Map<String, Object>> getSerializedSavedLocation() {
		Optional<Map<String, Object>> result = savedLocation.map(v -> {
			return v.serialize();
		});
		return result;
	}
	
	@JsonIgnore
	public void setSavedLocation(Location location) {
		savedLocation =Optional.of(location);
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
	
	public void addChapterSection() {
		chapterSection = StorySections.getInstance().nextChapterSectionNumber(chapterSection[0],chapterSection[1]);
	}
	
}
