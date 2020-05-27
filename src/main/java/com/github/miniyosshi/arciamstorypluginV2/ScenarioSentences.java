package com.github.miniyosshi.arciamstorypluginV2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Score;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

// read only
public class ScenarioSentences extends Element {
	
	@JsonProperty
	private List<ScenarioSentence> sentences = new ArrayList<ScenarioSentence>();
	
	@JsonCreator
	public ScenarioSentences(@JsonProperty("chapterSection")int[] chapterSection, @JsonProperty("sentences")List<ScenarioSentence> sentences) {
		this.name = String.valueOf(chapterSection[0]) + "-"+String.valueOf(chapterSection[1]);
		this.sentences = sentences;
	}
	
	public ScenarioSentences() {
		this(new int[2], new ArrayList<>());
	}
	
	public boolean hasNext() {
		int currentIndex = ScenarioBook.getInstance().indexOf(this);
		if(currentIndex == -1) {
			System.out.println("There is no such scenario sentences.");
			return false;
		}
		if(currentIndex >= ScenarioBook.getInstance().size()-1) {
			System.out.println("There is no more scenario sentences.");
			return false;
		}
		return true;
	}
	
	// return seconds
	public int runSentences(User user) {
		user.setIsInStoryEvent(true);
		
		user.setWalkspeedZero();
		
		Iterator<ScenarioSentence> iterator = sentences.iterator();
		
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
						
						user.setNextStorySection();
						timer.cancel();
						this.cancel();
					}
				} else {
					user.setAbilityWalkspeed();
					timer.cancel();
					this.cancel();
				}
			};
		};
		
		timer.schedule(task, 0, 5000);
		return sentences.size()*5;
	}
	
}
