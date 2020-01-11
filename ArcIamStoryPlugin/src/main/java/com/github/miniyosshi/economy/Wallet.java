package com.github.miniyosshi.economy;

import org.bukkit.entity.Player;

import com.github.miniyosshi.arciamstoryplugin.CSVExporter;
import com.github.miniyosshi.arciamstoryplugin.User;

public class Wallet {
	private Player p;
	private double money;
	
	public Wallet(double money){
		this.money = money;
	}
	
	public double getMoney() {
		return money;
	}
	
	public void subtractMoney(double x) {
		money -= x;
		CSVExporter.exportCSV(fileheadname);
	}
	
	public void addMoney(double x) {
		money += x;
		
	}
	

}
