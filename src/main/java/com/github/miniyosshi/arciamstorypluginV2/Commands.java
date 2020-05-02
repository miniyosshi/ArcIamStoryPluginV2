package com.github.miniyosshi.arciamstorypluginV2;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public enum Commands {	
	//designatedArea
	addarea {
		@Override
		public boolean execute(CommandSender sender, Command cmd, String commandLabel, String[] args) {
			if(sender instanceof Player) {
				if(args.length >= 2) {
					Location location = ((Player) sender).getLocation();
					DesignatedAreas das = DesignatedAreas.getInstance();
					das.getElementBy(args[0]).ifPresentOrElse(v ->{
						sender.sendMessage("The name is already used.");
					}, () ->{
						if(args[1].equalsIgnoreCase("square")) {
							new DesignatedSquareArea(args[0], location, location);
							sender.sendMessage("New area is made.");
						}else if(args[1].equalsIgnoreCase("round")) {
							try{double radius = Double.parseDouble(args[2]);
								new DesignatedRoundArea(args[0],location, radius);
								sender.sendMessage("New area is made.");
							} catch (NumberFormatException e) {	
							}
						}
					});
					das.exportAllToDefaultFolder();
					return true;
				}
			}else {
				sender.sendMessage("This command should be sent by a player.");
			}
			return false;
		}
	},
	
	modifyarea {
		@Override
		public boolean execute(CommandSender sender, Command cmd, String commandLabel, String[] args) {
			if(sender instanceof Player) {
				if(args.length == 2) {
					Location location = ((Player) sender).getLocation();
					DesignatedAreas das = DesignatedAreas.getInstance();
					das.getElementBy(args[0]).ifPresentOrElse(v ->{
						if(v instanceof DesignatedSquareArea) {
							DesignatedSquareArea da = (DesignatedSquareArea) v;
							if(args[1].equals("A")) {
								da.setCornerA(location);
								sender.sendMessage("cornerA is set.");
							} else if(args[1].equals("B")) {
								da.setCornerB(location);
								sender.sendMessage("cornerB is set.");
							} else {
								sender.sendMessage("This is a square area. So the argument should be A or B.");
							}
						}
						if(v instanceof DesignatedRoundArea) {
							try{double radius = Double.parseDouble(args[1]);
								new DesignatedRoundArea(args[0],location, radius);
								sender.sendMessage("New area is made.");
						} catch (NumberFormatException e) {	
						}
						}											
					}, ()->{
							sender.sendMessage(args[0] + "does not exist yet.");
						}
					);										
					//Export
					das.exportAllToDefaultFolder();			
					return true;
				}
			}
			else {
				sender.sendMessage("This command should be sent by a player.");
			}
			return false;
		}
	},
	deletearea {
		@Override
		public boolean execute(CommandSender sender, Command cmd, String commandLabel, String[] args) {
			// TODO Auto-generated method stub
			return false;
		}
	},
	showarea {
		@Override
		public boolean execute(CommandSender sender, Command cmd, String commandLabel, String[] args) {
			sender.sendMessage(DesignatedAreas.getInstance().showAll());
			return true;
		}
	},
	
	setspot {
		@Override
		public boolean execute(CommandSender sender, Command cmd, String commandLabel, String[] args) {
			// TODO Auto-generated method stub
			return false;
		}
	},
	
	//User
	showusers {
		@Override
		public boolean execute(CommandSender sender, Command cmd, String commandLabel, String[] args) {
			sender.sendMessage(Users.getInstance().showAll());
			return true;
		}
	},
	money {
		@Override
		public boolean execute(CommandSender sender, Command cmd, String commandLabel, String[] args) {
			// TODO Auto-generated method stub
			return false;
		}
	},
	
	//NPC
	createmerchant {
		@Override
		public boolean execute(CommandSender sender, Command cmd, String commandLabel, String[] args) {
			if(sender instanceof Player) {
				/*
				// create merchant:
				Merchant merchant = Bukkit.createMerchant("test");
				ItemStack sellingItem = new ItemStack(Material.APPLE);
				ItemStack buyItem1 = new ItemStack(Material.BREAD);

				// setup trading recipes:
				List<MerchantRecipe> merchantRecipes = new ArrayList<MerchantRecipe>();
				MerchantRecipe recipe = new MerchantRecipe(sellingItem, 3); // no max-uses limit
				recipe.setExperienceReward(false); // no experience rewards
				recipe.addIngredient(buyItem1);
				merchantRecipes.add(recipe);

				//apply recipes to merchant:
				merchant.setRecipes(merchantRecipes);

				// open trading window:
				((Player)sender).openMerchant(merchant, true);
				*/
				Inventory inventory = Bukkit.createInventory(null, 9, "SHOP");
				((Player)sender).openInventory(inventory);
				
				return true;
			} else {
				sender.sendMessage("This command should be sent by a player.");
			}
			return false;
		}
	},
	
	//game
	goal {
		@Override
		public boolean execute(CommandSender sender, Command cmd, String commandLabel, String[] args) {
			if(sender instanceof Player) {
				sender.sendMessage("Goal: ");
			}else {
				sender.sendMessage("This command should be sent by a player.");
			}
			return false;
		}
	},
	
	createmob {
		@Override
		public boolean execute(CommandSender sender, Command cmd, String commandLabel, String[] args) {
			if(sender instanceof Player) {
				if(args.length == 1) {
					NPCs.getInstance().getElementBy(args[0]).ifPresent(v-> {
						v.spawnAt(((Player)sender).getLocation());
					});
				}
			}else {
				sender.sendMessage("This command should be sent by a player.");
			}
			return false;
		}
	},
	
	createspecialsword{
		@Override
		public boolean execute(CommandSender sender, Command cmd, String commandLabel, String[] args) {
			if(sender instanceof Player) {
				LLItem item = new LLItem("すごい剣", Material.DIAMOND_SWORD, 100);
				ItemStack itemStack = item.generate(1);
				ItemMeta meta = itemStack.getItemMeta();
				meta.setDisplayName("この剣やばい");
				meta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
				itemStack.setItemMeta(meta);
				((Player) sender).getInventory().setItem(10, itemStack);
				item.setItemMeta(meta);
				LLItems.getInstance().exportAllToDefaultFolder();
			}else {
				sender.sendMessage("This command should be sent by a player.");
			}
			return false;
		}
		
	};
	
	public abstract boolean execute(CommandSender sender, Command cmd, String commandLabel, String[] args);
}
