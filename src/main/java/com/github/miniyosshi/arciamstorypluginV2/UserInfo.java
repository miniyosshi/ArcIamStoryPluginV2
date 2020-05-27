package com.github.miniyosshi.arciamstorypluginV2;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.bukkit.Location;
import org.bukkit.entity.Player;

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
	@JsonIgnore
	private Optional<StorySection> currentStorySection = StorySections.getInstance().getElementBy(1, 1);
	//private int[] nextChapterSection = new int[2];
	@JsonProperty
	private Birthday birthday = new Birthday();
	@JsonIgnore
	private boolean isInStoryEvent;
	@JsonProperty
	private boolean logoutInStoryEvent;
	@JsonProperty
	private Assets assets = new Assets();
	@JsonProperty
	private Ability ability = new Ability();
	@JsonIgnore
	private Optional<DesignatedArea> pastDesignatedArea = Optional.empty();
	
	public UserInfo() {}
	
	@JsonCreator
	public UserInfo(@JsonProperty("serializedSavedLocation") Optional<Map<String, Object>> serializedSavedLocation, @JsonProperty("chapterSection")Optional<int[]> chapterSection, 
					@JsonProperty("birthday")Optional<Birthday> birthday, @JsonProperty("logoutInStoryEvent")boolean logoutInStoryEvent, @JsonProperty("assets")Optional<Assets> assets,
					@JsonProperty("ability")Optional<Ability> ability) {
		serializedSavedLocation.ifPresent(v ->{
			this.savedLocation = Optional.of(SerializableLocation.deserialize(v).getLocation());
		});
		chapterSection.ifPresent(v-> this.currentStorySection = StorySections.getInstance().getElementBy(v[0], v[1]));
		birthday.ifPresent(v-> this.birthday = v);
		this.logoutInStoryEvent = logoutInStoryEvent;
		assets.ifPresent(v-> this.assets = v);
		ability.ifPresent(v-> this.ability = v);
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
	
	@JsonProperty("chapterSection")
	public Optional<int[]> getChapterSectionNumber(){
		return this.currentStorySection.map(v->v.getChapterSectionNumber());
	}
	
	@JsonIgnore
	public void setSavedLocation(Location location) {
		savedLocation =Optional.of(location);
	}
	
	/*
	 * Methods relating to Story Event
	 */
	
	public boolean isInStoryEvent() {
		return isInStoryEvent;
	}
	
	public void setIsInStoryEvent(boolean b) {
		this.isInStoryEvent = b;
	}
	
	public boolean  logoutInStoryEvent() {
		return logoutInStoryEvent;
	}
	
	public void setLogoutInStoryEvent(boolean b) {
		this.logoutInStoryEvent = b;
	}
	
	
	/*
	 * Methods relating to DesignatedAreas
	 */
	
	@JsonIgnore
	public void setPastDesignatedArea(Optional<DesignatedArea> da) {
		pastDesignatedArea = da;
	}
	
	@JsonIgnore
	public boolean pastDesignatedAreaEquals(Optional<DesignatedArea> presentArea) {
		return Objects.equals(pastDesignatedArea, presentArea);
	}
	
	/*
	 * Methods relating to StorySection and StoryEvents
	 */
	
	public void setNextStorySection() {
		currentStorySection = currentStorySection.map(v->StorySections.getInstance().nextStorySection(v))
								.orElse(Optional.empty());
	}
	
	public boolean checkEvent(String triggerAction, String triggerObject) {
		return currentStorySection.map(v -> v.ifUserMakeFlagForStoryEvent(triggerAction, triggerObject)).orElse(false);
	}
	
	public int runStorySentences(User user) {
		return currentStorySection.map(v->v.runSentences(user)).orElse(0);
	}

	
	/*
	 * Methods relating to Assets.
	 */
	
	public String showCash() {
		return assets.showCash();
	}
	public String showDeposit() {
		return assets.showDeposit();
	}
	public boolean receive(double money) {
		return assets.receive(money);
	}
	public boolean pay(double money) {
		return assets.pay(money);
	}
	public boolean withdraw(double money) {
		return assets.withdraw(money);
	}
	public boolean deposit(double money) {
		return assets.deposit(money);
	}
	
	/*
	 * Methods relating to Ability
	 */
	
	public void reflectWalkspeed(Player player) {
		ability.reflectWalkspeed(player);
	}
	
}
