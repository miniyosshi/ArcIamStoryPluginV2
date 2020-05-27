package com.github.miniyosshi.arciamstorypluginV2.cmds;

import java.util.List;

import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class CmdCommands extends ASPCmd{
	
	// /ll commands [page:Optional] 
	
	private static int pageSize = 8;  // examples to list per page; 10 lines available, 1 for header, 1 for footer

	public CmdCommands(){
		name = "commands";
		permission = "help";
	}

	@Override
	public void execute(CommandSender sender, List<String> params){
		// determine which page we're viewing
		// page 0 represents all commands list
		int page = (sender instanceof Player) ? 1 : 0;
		if (!params.isEmpty()){
			try{
				page = Integer.parseInt(params.get(0));
			}
			catch(NumberFormatException ignored) {}
		}

		// see whether we're showing examples to player or to console, and determine number of pages available
		List<String> examples = (sender instanceof Player) ? cmdExamplesPlayer : cmdExamplesConsole;
		int pageCount = (int) Math.ceil(examples.size() / (double) pageSize);

		// if specified page number is negative or higher than we have available, default back to first page
		if (page < 0 || page > pageCount)
			page = (sender instanceof Player) ? 1 : 0;

		// send command example header
		sender.sendMessage( C_HEAD + "LostLegacy" + "  -  key: " +
							commandEmphasized("command") + C_REQ + "<required> " + C_OPT + "[optional]" );

		if (page > 0){
			// send examples for this page
			int first = ((page - 1) * pageSize);
			int count = Math.min(pageSize, examples.size() - first);
			for(int i = first; i < first + count; i++){
				sender.sendMessage(examples.get(i));
			}

			// send page footer, if relevant; manual spacing to get right side lined up near edge is crude, but sufficient
			String footer = C_HEAD + " (Page " + page + "/" + pageCount + ")              " + cmd(sender);
			if (page < pageCount)
				sender.sendMessage(footer + Integer.toString(page + 1) + C_DESC + " - view next page of commands.");
			else if (page > 1)
				sender.sendMessage(footer + C_DESC + "- view first page of commands.");
		} else{
			// if page "0" is specified, send all examples; done by default for console but can be specified by player
			for (String example : examples)
			{
				sender.sendMessage(example);
			}
		}
	}
}
