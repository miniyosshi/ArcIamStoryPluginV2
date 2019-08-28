package com.github.miniyosshi.arciamstoryplugin;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OnCommand implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {	
		
		//main classへの登録をお忘れなく！
		
		
		switch(cmd.getName()) {
		case "charactercode" :
			sender.sendMessage("The character code used here is "+ System.getProperty("file.encoding"));
			return true;
		
		
		case "deletearea" :
			if (args.length>=1) {
				
				AreaData a = List.getAreaData(args[0]);
				
				if(a!=null) {
					String s = a.getName();
					
					List.areadata.remove(a);
					
					System.out.println(List.areadata.toString());
					
					CSVExporter.exportCSV("AreaData.csv");
					sender.sendMessage(s + " is removed.");
					return true;
				}
				else {
					System.out.println("removeできてないよ");
					return false;
				}					
			}		
			else {
				return false;
				}
				
		
		case "reloadcsv" :
			CSVReader.reload();
			sender.sendMessage("Reload completed.");
			return true;
			
			
		case "setarea" :
			if(sender instanceof Player) {
				
				if(args.length >= 2) {
					
					//boolean exist = false;
					Location loc = ((Player) sender).getLocation();
					
					
					AreaData a = List.getAreaData(args[0]);
					if(a!=null) {
						a.setLocation(Integer.parseInt(args[1]),loc);					
						sender.sendMessage("上書き、もしくはもう片方の地点を新しく登録しました。");
					}
					else {
						a = new AreaData(args[0], loc, loc);
						List.areadata.add(a);
						sender.sendMessage("新しい名前の場所, "+a.getName()+"を登録しました。");
					}
										
					//ここでcsvに書き込み
						
					System.out.println("書き込み前");
					for (AreaData area : List.areadata) {
						System.out.println(area.getName());
					}
					
					
					CSVExporter.exportCSV("AreaData.csv");
										
					return true;
				}
				else {
					return false;
				}
			}
			else {
				sender.sendMessage("This command should be sent by a player.");
				return false;
				}
			
				
		case "showarea" :
			for(AreaData a : List.areadata) {
				sender.sendMessage(a.getName() + " : " +a.getcornerA().toString() + " to "+a.getcornerB().toString());
			}
			return true;
				
		case "userlist" :
			for(User u : List.userdata) {
				sender.sendMessage(u.getName()+" : Chapter"+u.getChapter()+", Section"+u.getSection());
			}
			return true;

				
		default :
			sender.sendMessage("This command does not exist.");
			return false;
		}
	}
		
}
