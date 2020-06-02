package com.github.miniyosshi.arciamstorypluginV2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.entity.Player;

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
	//@JsonIgnore
	//private Optional<StorySection> currentStorySection = MainStorySections.getInstance().getElementBy(1, 1);
	//private int[] nextChapterSection = new int[2];
	@JsonIgnore
	private Map<String, StorySection> currentVariousStorySection = VariousStorySections.getInstance().getInitialMap();
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
	private Set<DesignatedArea> pastDesignatedArea = new HashSet<>();
	
	public UserInfo() {}
	
	@JsonCreator
	public UserInfo(@JsonProperty("serializedSavedLocation") Optional<Map<String, Object>> serializedSavedLocation, 
					@JsonProperty("variousChapterSection")Optional<Map<String, int[]>> variousChapterSection, 
					@JsonProperty("birthday")Optional<Birthday> birthday, @JsonProperty("logoutInStoryEvent")boolean logoutInStoryEvent, @JsonProperty("assets")Optional<Assets> assets,
					@JsonProperty("ability")Optional<Ability> ability) {
		serializedSavedLocation.ifPresent(v ->{
			this.savedLocation = Optional.of(SerializableLocation.deserialize(v).getLocation());
		});
		//chapterSection.ifPresent(v-> this.currentStorySection = MainStorySections.getInstance().getElementBy(v[0], v[1]));
		variousChapterSection.ifPresent(map ->{
			map.entrySet().stream().forEach(entry->{
				VariousStorySections.getInstance().getInstanceBy(entry.getKey()).ifPresent(instance -> {
					instance.getElementBy(entry.getValue()[0], entry.getValue()[0]).ifPresent(v->this.currentVariousStorySection.put(v.getAxis(), v));
				});
			});
		});
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
	
	/*
	@JsonProperty("chapterSection")
	public Optional<int[]> getChapterSectionNumber(){
		return this.currentStorySection.map(v->v.getChapterSectionNumber());
	}
	*/
	
	@JsonProperty
	public Map<String, int[]> getVariousChapterSection(){
		Map<String, int[]> map = new HashMap<>();
		VariousStorySections.getInstance().getInitialMap()
		.entrySet().stream().forEach(v-> {
			Entry<String, int[]> entry = v.getValue().getSerializedStorySection();
			map.put(entry.getKey(), entry.getValue());
		});
		this.currentVariousStorySection.entrySet().stream().forEach(e->{
				Entry<String, int[]> entry =e.getValue().getSerializedStorySection();
				map.put(entry.getKey(), entry.getValue());
			});
		return map;
	}
	
	@JsonIgnore
	public void setSavedLocation(Location location) {
		savedLocation = Optional.of(location);
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
	public void setPastDesignatedArea(Set<DesignatedArea> da) {
		pastDesignatedArea = da;
	}
	
	@JsonIgnore
	public boolean pastDesignatedAreaEquals(Set<DesignatedArea> presentArea) {
		return Objects.equals(pastDesignatedArea, presentArea);
	}
	
	@JsonIgnore
	public Set<DesignatedArea> pastDesignatedAreaRetain(Set<DesignatedArea> presentArea) {
		Set<DesignatedArea> result = new HashSet<>(pastDesignatedArea);
		result.retainAll(presentArea);
		return result;
	}
	
	@JsonIgnore
	public Set<DesignatedArea> getUserNewlyEnteringDesignatedAreas(Set<DesignatedArea> presentArea) {
		Set<DesignatedArea> result = new HashSet<>(presentArea);
		result.removeAll(pastDesignatedArea);
		return result;
	}
	
	
	/*
	 * Methods relating to StorySection and StoryEvents
	 */
	
	public Optional<StorySection> getStorySection(String axis) {
		return Optional.ofNullable(currentVariousStorySection.get(axis));
	}
	
	public void setNextStorySection(String axis) {
		Optional<StorySection> nextss = this.getStorySection(axis).flatMap(ss->VariousStorySections.getInstance().getInstanceBy(axis)
										.map(sss -> sss.nextStorySection(ss))).orElse(Optional.empty());
		nextss.ifPresent(v-> currentVariousStorySection.put(axis, v));
	}
	
	public boolean checkEvent(String axis, String triggerAction, String triggerObject) {
		//return currentStorySection.map(v -> v.ifUserMakeFlagForStoryEvent(triggerAction, triggerObject)).orElse(false);
		return this.getStorySection(axis).map(v -> v.ifUserMakeFlagForStoryEvent(triggerAction, triggerObject)).orElse(false);
	}
	
	public Optional<String> checkEventAndReturnAxis(String triggerAction, String triggerObject) {
		return currentVariousStorySection.entrySet().stream().filter(entry-> entry.getValue().ifUserMakeFlagForStoryEvent(triggerAction, triggerObject))
				.findFirst().map(entry-> entry.getValue().getAxis());
	}
	
	public long runStorySentences(User user, String axis) {
		return this.getStorySection(axis).map(v->v.runSentences(user)).orElse(0L);
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
