package com.github.miniyosshi.arciamstorypluginV2.listeners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.github.miniyosshi.arciamstorypluginV2.Commands;
import com.github.miniyosshi.arciamstorypluginV2.cmds.*;

public class ASPCommandExecutor implements CommandExecutor{
	
	private Map<String, ASPCmd> subCommands = new HashMap<String, ASPCmd>();
	
	// Add commands in cmd package;
	public ASPCommandExecutor(){	
		addCmd(new CmdAddarea());
		addCmd(new CmdCommands());
		addCmd(new CmdSetcorners());
		
		//add
		
	}
	
	private void addCmd(ASPCmd cmd){
		subCommands.put(cmd.name, cmd);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {	
		
		List<String> params = new ArrayList<String>(Arrays.asList(args));
		
		// If cmd is just "/LL", then show available commands.
		if(params.isEmpty()) {
			params.add(0, "commands");
		}
		String cmdName = params.get(0).toLowerCase();
		params.remove(0);
		
		if (!subCommands.containsKey(cmdName)){
			cmdName = "commands";
			//params.add(0, Integer.toString(page));
		}

		ASPCmd subCommand = subCommands.get(cmdName);
		
		// check permission
		//		if (!Config.HasPermission(player, subCommand.permission))
		//		return true;
		
		// make sure valid number of parameters has been provided
		if (params.size() < subCommand.minParams || params.size() > subCommand.maxParams){
			if (subCommand.maxParams == 0)
				sender.sendMessage(ASPCmd.C_ERR + "This command does not accept any parameters.");
			else
				sender.sendMessage(ASPCmd.C_ERR + "You have not provided a valid number of parameters.");
			subCommand.sendCmdHelp(sender);
			return true;
		}
		
		// execute command
		subCommand.execute(sender, params);

		return true;
		
		
		/*
		Commands c = Commands.valueOf(Commands.class, cmd.getName().toLowerCase());
		return c.execute(sender, cmd, commandLabel, args);
		*/
	}
		
		
}
