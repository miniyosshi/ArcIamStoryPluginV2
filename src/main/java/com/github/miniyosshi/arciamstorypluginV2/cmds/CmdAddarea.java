package com.github.miniyosshi.arciamstorypluginV2.cmds;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.miniyosshi.arciamstorypluginV2.DesignatedAreas;
import com.github.miniyosshi.arciamstorypluginV2.DesignatedRoundArea;
import com.github.miniyosshi.arciamstorypluginV2.DesignatedSquareArea;

public class CmdAddarea extends ASPCmd{
	
	// /ll addarea NameofArea Square
	// /ll addarea NameofArea Round radius
	
	public CmdAddarea(){
		name = "addarea";
		permission = "admin";
		minParams = 2;
		maxParams = 3;

		addCmdExample(nameEmphasized() + " <NameofArea> <SquareOrRound> [RadiusForRound] ");
		helpText = "This is a command to add new Designated Area."+
					"This should be send by a player and the location of player is used "+
					"as a center for a Round area or as a one corner for a Square area.";
	}
	
	
	@Override
	public void execute(CommandSender sender, List<String> params) {
		if(!(sender instanceof Player)) {
			sendErrorAndHelp(sender, "This command should be send by a player.");
			return;
		}
		
		Location location = ((Player) sender).getLocation();
		DesignatedAreas das = DesignatedAreas.getInstance();
		das.getElementBy(params.get(0)).ifPresentOrElse(v ->{
			sender.sendMessage("The name is already used.");
		}, () ->{
			if(params.get(1).equalsIgnoreCase("square")) {
				new DesignatedSquareArea(params.get(0), location, location);
				sender.sendMessage("New square area is made.");
			}else if(params.get(1).equalsIgnoreCase("round")) {
				try{double radius = Double.parseDouble(params.get(2));
					new DesignatedRoundArea(params.get(0),location, radius);
					sender.sendMessage("New round area is made.");
				} catch (NumberFormatException e) {	
					sendErrorAndHelp(sender, "Radius is not correct.");
				}
			}else {
				sendErrorAndHelp(sender, "Please decide square or round.");
			}
		});
		das.exportAllToDefaultFolder();
	}

}
