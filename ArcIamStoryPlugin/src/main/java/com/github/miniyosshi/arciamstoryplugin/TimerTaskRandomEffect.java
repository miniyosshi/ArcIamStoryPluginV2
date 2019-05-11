package com.github.miniyosshi.arciamstoryplugin;

import org.apache.commons.lang.RandomStringUtils;

import java.util.TimerTask;

public class TimerTaskRandomEffect extends TimerTask {
	User u;
	int tick;
	
	TimerTaskRandomEffect(User u, int tick){
		this.u = u;
		this.tick = tick;
	}
	
	public void run(){
		u.getPlayer().sendTitle("", "Teleport Phase : " + RandomStringUtils.randomAlphabetic(10), 0, tick*20, 1);
	}
}
