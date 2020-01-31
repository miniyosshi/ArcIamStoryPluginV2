package com.github.miniyosshi.arciamstoryplugin;

import java.util.ArrayList;

import org.bukkit.Location;

import com.github.miniyosshi.economy.Wallet;

import com.github.miniyosshi.economy.BankAccount;


public class List {
	public static ArrayList<AreaData> areadata = new ArrayList<AreaData>();
	public static ArrayList<ChapterData> chapterdata = new ArrayList<ChapterData>();
	public static ArrayList<ScenarioData> scenariodata = new ArrayList<ScenarioData>();
	public static ArrayList<SpawnPoints> spawnpoints = new ArrayList<SpawnPoints>();
	public static ArrayList<User> userdata = new ArrayList<User>();
	public static ArrayList<Wallet> moneyaccount = new ArrayList<Wallet>();
	public static ArrayList<BankAccount> bankaccount = new ArrayList<BankAccount>();

	
	static AreaData getAreaData(String name) {
		for (AreaData a : areadata) {
			if (a.getName().equals(name)) {
				return a;
			}
		}
		//NULL
		return null;
	}
	
	static void setChapterViewPoint(int chapter, int section, Location loc) {
		for (ChapterData cd : chapterdata) {
			if (cd.getChapter()==chapter&&cd.getSection()==section) {
				cd.setViewPoint(loc);
				break;
			}
		}
	}
	
	
	
	
}
