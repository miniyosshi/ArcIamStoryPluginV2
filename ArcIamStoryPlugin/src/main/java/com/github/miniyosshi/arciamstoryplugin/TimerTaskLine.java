package com.github.miniyosshi.arciamstoryplugin;

import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Score;

public class TimerTaskLine extends TimerTask {
	User u;
	ChapterData cd;
	int lineno;
	int numberoflines;
	int finishednumberoflines=0;
	Timer timer;
	
	TimerTaskLine(User u, ChapterData cd, int startlineno, int numberoflines, Timer timer){
		this.u = u;
		this.cd = cd;
		this.lineno = startlineno;
		this.numberoflines = numberoflines;
		this.timer = timer;
	}
	
	
	public void run(){
		
		u.getPlayer().sendMessage(ChatColor.AQUA + List.scenariodata.get(lineno).getUtterer() + " : " + ChatColor.WHITE + List.scenariodata.get(lineno).getLine());
		finishednumberoflines += 1;
		lineno += 1;
		
		if(u.getPlayer().isOnline()==false) {
			this.cancel();
			timer.cancel();
		}
		
		if(finishednumberoflines == numberoflines) {
			this.cancel();
			timer.cancel();
			u.setInStoryEvent(false);
			u.getPlayer().setWalkSpeed(0.2f);
			u.addChapterSectionNumber();
			
			//Scoreboard
			
			Score s = u.getPlayer().getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore("節:");
			s.setScore(u.getSection());
			
		}
		
	}
	
	

}
