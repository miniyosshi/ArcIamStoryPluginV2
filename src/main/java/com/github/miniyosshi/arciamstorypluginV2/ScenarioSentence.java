package com.github.miniyosshi.arciamstorypluginV2;

import java.util.List;

import org.bukkit.ChatColor;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ScenarioSentence {
	@JsonProperty
	private String utterer;
	@JsonProperty
	private String sentence;
	
	@JsonCreator
	public ScenarioSentence(@JsonProperty("utterer")String utterer, @JsonProperty("sentence")String sentence) {
		this.utterer = utterer;
		this.sentence = sentence;
	}
	
	public void runSentence(User user) {
		user.sendMessage(ChatColor.AQUA + utterer + " : " + ChatColor.WHITE + sentence);
	}
	
	

}
