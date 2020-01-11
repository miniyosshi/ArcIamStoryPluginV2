package com.github.miniyosshi.economy;

//import com.github.miniyosshi.arciamstoryplugin.User;

public class Wallet {
	private String name;
	private double balance;
	
	public Wallet(String name, double balance){
		this.name = name;
		this.balance = balance;
	}
	
	public Wallet(String name){
		this.name = name;
		//this.balance = 
	}
	
	
	public String getName(){
		return name;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void withdraw(long x) {
		balance -= x;
	}
	
	public void deposit(long x) {
		balance += x;
	}
	

}
