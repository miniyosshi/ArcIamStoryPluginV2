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
		
		if(cmd.getName().equalsIgnoreCase("charactercode")){
			
			sender.sendMessage("The character code used here is "+ System.getProperty("file.encoding"));
			return true;
			
		}
		
		if(cmd.getName().equalsIgnoreCase("deletearea")){
			
			if (args.length>=1) {
				
				AreaData a = AreaData.getAreaData(args[0]);
				
				if(a!=null) {
					String s = a.getName();
					
					CSVReader.areadata.remove(a);
					
					CSVExporter.exportCSV("AreaData.csv");
					sender.sendMessage(s + " is removed.");
					return true;
				}
				else {
					return false;
				}					
			}		
			else {
				return false;
			}
		}
		

		
		if(cmd.getName().equalsIgnoreCase("reloadcsv")){
			
			CSVReader.reload();
			sender.sendMessage("delete completed.");
			return true;
		}
		
		//エリア登録
		if (cmd.getName().equalsIgnoreCase("setarea")){
			if(sender instanceof Player) {
												
				if(args.length >= 2) {
					
					//boolean exist = false;
					Location loc = ((Player) sender).getLocation();
					
					
					AreaData a = AreaData.getAreaData(args[0]);
					if(a!=null) {
						a.setLocation(Integer.parseInt(args[1]),loc);					
						sender.sendMessage("上書き、もしくはもう片方の地点を新しく登録しました。");
					}
					else {
						a = new AreaData(args[0], loc, loc);
						CSVReader.areadata.add(a);
						sender.sendMessage("新しい名前の場所, "+a.getName()+"を登録しました。");
					}
					
					/*
					for (AreaData a : CSVReader.areadata) {
						if (a.getName().equalsIgnoreCase(args[0])) {
							
							a.setLocation(Integer.parseInt(args[1]),loc);					
							exist = true;
							sender.sendMessage("登録しました。");
						}
					}
					*/
					//新しい名前の場所
					/*
					if(exist == false) {
						a = new AreaData(args[0], loc, loc);
						//AreaData a = new AreaData(args[0], loc, loc);
						CSVReader.areadata.add(a);
						sender.sendMessage("新しい名前の場所, "+a.getName()+"を登録しました。");
					}
					*/
					
					//ここでcsvに書き込み
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
			
		}
		
		if(cmd.getName().equalsIgnoreCase("showarea")){
			for(AreaData a : CSVReader.areadata) {
				sender.sendMessage(a.getName() + " : " +a.getcornerA().toString() + " to "+a.getcornerB().toString());
			}
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("userlist")){
			for(User u : CSVReader.userdata) {
				sender.sendMessage(u.getName()+" : Chapter"+u.getChapter()+", Section"+u.getSection());
			}
			return true;
		}
		
		
		else
			sender.sendMessage("This command does not exist.");
			return false;
	}
		
}
