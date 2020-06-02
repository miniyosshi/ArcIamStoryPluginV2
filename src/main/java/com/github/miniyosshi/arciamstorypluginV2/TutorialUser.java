package com.github.miniyosshi.arciamstorypluginV2;

import org.bukkit.entity.Player;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TutorialUser extends User {

	public TutorialUser(String name, Player player, UserInfo userInfo) {
		super(name, player, userInfo);
	}
	
	@JsonCreator
	public TutorialUser(@JsonProperty("name")String name, @JsonProperty("userInfo")UserInfo userInfo) {
		super(name, userInfo);
	}
	
	public void changeToNormalModeUser() {
		player.ifPresent(v-> new NormalModeUser(name, v, userInfo));
		this.sendMessage("ノーマルモードになりました。");
	}
	
	public void changeToHardModeUser() {
		player.ifPresent(v-> new HardModeUser(name, v, userInfo));
		this.sendMessage("ハードモードになりました。");
	}

}
