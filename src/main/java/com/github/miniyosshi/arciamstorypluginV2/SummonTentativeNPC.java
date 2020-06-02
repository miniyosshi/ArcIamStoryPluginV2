package com.github.miniyosshi.arciamstorypluginV2;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.entity.Mob;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SummonTentativeNPC extends AdditionalTask {
	@JsonProperty
	private String npcName = "";
	
	@JsonCreator
	public SummonTentativeNPC(@JsonProperty("npcName")String npcName) {
		this.npcName = npcName;
	}
	
	@Override
	public void execute(User user, long ms) {
		user.sendMessage("in async");
		NPCs.getInstance().getElementBy(npcName).ifPresent(npc ->{
			user.sendMessage("aaa");
			Optional<Mob> mob = user.summonNPC(npc);
				
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {
				public void run() {
					mob.ifPresent(v->{
						user.sendMessage("removal");
						v.remove();
						});
					}
				};
			user.sendMessage("waitfor"+ms);
			timer.schedule(task, ms);
		});
	}
	
}
