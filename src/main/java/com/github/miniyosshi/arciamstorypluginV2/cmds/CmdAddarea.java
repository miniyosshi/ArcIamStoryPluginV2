package com.github.miniyosshi.arciamstorypluginV2.cmds;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.miniyosshi.arciamstorypluginV2.DesignatedAreas;
import com.github.miniyosshi.arciamstorypluginV2.DesignatedRoundArea;
import com.github.miniyosshi.arciamstorypluginV2.DesignatedSquareArea;

public class CmdAddarea extends ASPCmd{
	
	// /ll addarea NameofArea SquareOrRound 
	
	public CmdAddarea(){
		name = "addarea";
		permission = "admin";
		minParams = maxParams = 2;

		addCmdExample(nameEmphasized() + " <NameofArea> <SquareOrRound> ");
		helpText = "This is a command to add new Designated Area."+
					"This should be send by player and the location of player is used "+
					"as a center for a Round area or as a one corner for a Square area.";
	}
	
	
	@Override
	public void execute(CommandSender sender, List<String> params) {
		if(sender instanceof Player) {
			
		}
	}

}
