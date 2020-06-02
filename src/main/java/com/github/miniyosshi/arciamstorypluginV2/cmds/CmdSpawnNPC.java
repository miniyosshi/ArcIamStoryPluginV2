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
import com.github.miniyosshi.arciamstorypluginV2.NPCs;

public class CmdSpawnNPC extends ASPCmd{
	
	// /ll spawnnpc nameofNPC
	
	public CmdSpawnNPC(){
		name = "spawnnpc";
		permission = "admin";
		minParams = 1;
		maxParams = 1;

		addCmdExample(nameEmphasized() + " <NameofNPC> ");
		helpText = "This is a command to spawn NPC at player's location."+
					"This should be send by a player because the location of player is used."+
					"as the location for a NPC.";
	}
	
	
	@Override
	public void execute(CommandSender sender, List<String> params) {
		if(!(sender instanceof Player)) {
			sendErrorAndHelp(sender, "This command should be send by a player.");
			return;
		}
		
		Location location = ((Player) sender).getLocation();
		NPCs npcs = NPCs.getInstance();
		npcs.getElementBy(params.get(0)).ifPresentOrElse(v ->{
			v.spawnAt(location);
		}, () ->{
			sendErrorAndHelp(sender, "There is no such NPC.");
		});
	}

}
