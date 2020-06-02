package com.github.miniyosshi.arciamstorypluginV2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Score;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

// read only
public class ScenarioSentences extends Element {
	
	@JsonProperty
	private String axis = "";
	@JsonProperty
	private int[] chapterSection = new int[2];
	@JsonProperty
	private List<ScenarioSentence> sentences = new ArrayList<ScenarioSentence>();
	
	@JsonCreator
	public ScenarioSentences(@JsonProperty("name")String name, @JsonProperty("axis")String axis, @JsonProperty("chapterSection")int[] chapterSection, 
							 @JsonProperty("sentences")List<ScenarioSentence> sentences) {
		this.axis = axis;
		this.name = name;
		this.chapterSection = chapterSection;
		this.sentences = sentences;
	}
	
	public ScenarioSentences() {
		this("", "", new int[2], new ArrayList<>());
	}
	
	
	public String getAxis() {
		return axis;
	}
	
	public int[] getChapterSectionNumber() {
		return chapterSection;
	}
	
	public boolean hasNext() {
		int currentIndex = MainScenarioBook.getInstance().indexOf(this);
		if(currentIndex == -1) {
			System.out.println("There is no such scenario sentences.");
			return false;
		}
		if(currentIndex >= MainScenarioBook.getInstance().size()-1) {
			System.out.println("There is no more scenario sentences.");
			return false;
		}
		return true;
	}
	
	// return milliseconds
	public long runSentences(User user, String axis) {
		user.setIsInStoryEvent(true);
		
		user.setWalkspeedZero();
		
		Iterator<ScenarioSentence> iterator = sentences.iterator();
		
		BukkitRunnable task3 = new BukkitRunnable() {
			@Override
			public void run() {
				if(user.isOnline()) {
					if(iterator.hasNext()) {
						//player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20*5, 0));
						iterator.next().runSentence(user);
					}else {
						//set default
						user.setIsInStoryEvent(false);
						user.setAbilityWalkspeed();
						
						user.setNextStorySection(axis);
						this.cancel();
						user.checkEventandReturnAxis("auto", "auto")
						.ifPresent(v-> {
							user.processLine(v);
						});
					}
				} else {
					user.setAbilityWalkspeed();
					this.cancel();
				}
				
			}
		};
		task3.runTaskTimer(Bukkit.getPluginManager().getPlugin("ArcIamStoryPluginV2"), 0, 20*5);
		/*
		Timer timer = new Timer();	
		TimerTask task = new TimerTask(){
			public void run() {	
				if(user.isOnline()) {
					if(iterator.hasNext()) {
						//player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20*5, 0));
						iterator.next().runSentence(user);
					}else {
						//set default
						user.setIsInStoryEvent(false);
						user.setAbilityWalkspeed();
						
						user.setNextStorySection(axis);
						timer.cancel();
						user.checkEventandReturnAxis("auto", "auto")
						.ifPresent(v-> {
							Timer timer2 = new Timer();
							TimerTask task2 = new TimerTask() {
								public void run() {
									user.processLine(v);
								}
							};
							timer2.schedule(task2, 1000);
						});
						
					}
				} else {
					user.setAbilityWalkspeed();
					timer.cancel();
				}
			};
		};
		
		timer.schedule(task, 0, 5000);
		*/
		return sentences.size()*5000;
	}
	
}
