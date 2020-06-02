package com.github.miniyosshi.arciamstorypluginV2.cmds;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.miniyosshi.arciamstorypluginV2.DesignatedAreas;
import com.github.miniyosshi.arciamstorypluginV2.DesignatedRoundArea;
import com.github.miniyosshi.arciamstorypluginV2.DesignatedSpot;
import com.github.miniyosshi.arciamstorypluginV2.DesignatedSpots;
import com.github.miniyosshi.arciamstorypluginV2.DesignatedSquareArea;

public class CmdAddspot extends ASPCmd{
	
	// /ll addspot NameofSpot
	
	public CmdAddspot(){
		name = "addspot";
		permission = "admin";
		minParams = 1;
		maxParams = 1;

		addCmdExample(nameEmphasized() + " <NameofSpot> ");
		helpText = "This is a command to add new Designated Spot."+
					"This should be send by a player and the location of player is used "+
					"as the location for a spot.";
	}
	
	
	@Override
	public void execute(CommandSender sender, List<String> params) {
		if(!(sender instanceof Player)) {
			sendErrorAndHelp(sender, "This command should be send by a player.");
			return;
		}
		
		Location location = ((Player) sender).getLocation();
		DesignatedSpots dss = DesignatedSpots.getInstance();
		dss.getElementBy(params.get(0)).ifPresentOrElse(v ->{
			sender.sendMessage("The name is already used.");
		}, () ->{
			new DesignatedSpot(params.get(0), location);
		});
		dss.exportAllToDefaultFolder();
	}

}
