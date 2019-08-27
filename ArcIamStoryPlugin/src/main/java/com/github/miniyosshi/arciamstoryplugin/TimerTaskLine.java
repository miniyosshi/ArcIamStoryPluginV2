package com.github.miniyosshi.arciamstoryplugin;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTaskLine extends TimerTask {
	User u;
	int lineno;
	int k;
	Timer timer;
	
	TimerTaskLine(User u, int lineno, int k, Timer timer){
		this.u = u;
		this.lineno = lineno;
		this.k = k;
		this.timer = timer;
	}
	
	
	public void run(){
		u.getPlayer().sendMessage(List.scenariodata.get(lineno).getUtterer() + " : " + List.scenariodata.get(lineno).getLine());
		
		lineno += 1;
		
		if(u.getPlayer().isOnline()==false) {
			this.cancel();
			timer.cancel();
		}
		
		if(lineno == k ) {
			this.cancel();
			timer.cancel();
			u.setInStoryEvent(false);
			u.addChapterSectionNumber();
			
		}
		
	}
	
	

}
