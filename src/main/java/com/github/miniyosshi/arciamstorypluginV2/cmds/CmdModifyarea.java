package com.github.miniyosshi.arciamstorypluginV2.cmds;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.miniyosshi.arciamstorypluginV2.DesignatedAreas;
import com.github.miniyosshi.arciamstorypluginV2.DesignatedRoundArea;
import com.github.miniyosshi.arciamstorypluginV2.DesignatedSquareArea;

public class CmdModifyarea extends ASPCmd{
	
	// /ll modifyarea NameofSquareArea AorB
	// /ll modifyarea NameofRoundArea <"center" or radius>
	
	public CmdModifyarea(){
		name = "modifyarea";
		permission = "admin";
		minParams = 2;
		maxParams = 2;

		addCmdExample(nameEmphasized() + " <NameofSquareArea> <AorB> ");
		addCmdExample(nameEmphasized() + " <NameofRoundArea> <\"center\" or radius> ");
		helpText = "This is a command to modify Designated Area which is already created."+
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
			if(v instanceof DesignatedSquareArea) {
				DesignatedSquareArea da = (DesignatedSquareArea) v;
				if(params.get(1).equalsIgnoreCase("A")) {
					if(da.setCornerA(location)) {
						sender.sendMessage("CornerA is set.");
					} else {
						sendErrorAndHelp(sender, "Error:Corner A and B are in different worlds from each other : DesignatedSquareArea " + name);
					}
				} else if(params.get(1).equalsIgnoreCase("B")) {
					if(da.setCornerB(location)) {
						sender.sendMessage("CornerB is set.");
					} else {
						sendErrorAndHelp(sender, "Error:Corner A and B are in different worlds from each other : DesignatedSquareArea " + name);
					}
				} else {
					sendErrorAndHelp(sender, "This is a square area. So the argument should be A or B.");
				}
			}
			if(v instanceof DesignatedRoundArea) {
				DesignatedRoundArea da = (DesignatedRoundArea) v;
				if(params.get(1).equalsIgnoreCase("center")) {
					da.setCenter(location);
					sender.sendMessage("Center location is set.");
				} else {
					try{
						double radius = Double.parseDouble(params.get(1));
						da.setRadius(radius);
						sender.sendMessage("Radius is set.");
					} catch (NumberFormatException e) {
						sendErrorAndHelp(sender, "Please enter \"center\" or correct radius.");
					}
				}
			}											
		}, ()->{
				sendErrorAndHelp(sender, params.get(0) + "does not exist yet.");
			}
		);										
		das.exportAllToDefaultFolder();
	}

}
