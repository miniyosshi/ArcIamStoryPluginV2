package com.github.miniyosshi.arciamstorypluginV2.cmds;

import java.util.List;

import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.World;

public class CmdSetcorners extends ASPCmd{
	
	public CmdSetcorners(){
		name = "setcorners";
		permission = "admin";
		minParams = maxParams = 4;

		addCmdExample(nameEmphasizedW() + "<x1> <z1> <x2> <z2> - corner coords.");
		helpText = "This is an alternate way to set a border, by specifying the X and Z coordinates of two opposite " +
			"corners of the border area ((x1, z1) to (x2, z2)). [world] is optional for players and defaults to the " +
			"world the player is in.";
	}

	@Override
	public void execute(CommandSender sender,  List<String> params){

		try{
			double x1 = Double.parseDouble(params.get(0));
			double z1 = Double.parseDouble(params.get(1));
			double x2 = Double.parseDouble(params.get(2));
			double z2 = Double.parseDouble(params.get(3));
			//Config.setBorderCorners(worldName, x1, z1, x2, z2);
		}
		catch(NumberFormatException ex){
			sendErrorAndHelp(sender, "The x1, z1, x2, and z2 coordinate values must be numerical.");
			return;
		}
	}
}
