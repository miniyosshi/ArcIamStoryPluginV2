package com.github.miniyosshi.economy;

//import com.github.miniyosshi.arciamstoryplugin.User;

public class Account {
	private String name;
	private double balance;
	
	public Account(String name, double balance){
		this.name = name;
		this.balance = balance;
	}
	
	public Account(String name){
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
