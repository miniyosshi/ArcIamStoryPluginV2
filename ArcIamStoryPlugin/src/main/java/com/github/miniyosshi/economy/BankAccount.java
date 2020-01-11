package com.github.miniyosshi.economy;

//import com.github.miniyosshi.arciamstoryplugin.User;

public class BankAccount {
	private String name;
	private double balance;
	
	public BankAccount(String name, double balance){
		this.name = name;
		this.balance = balance;
	}
	
	public BankAccount(String name){
		this.name = name;
		//this.balance = 
	}
	
	public String getName(){
		return name;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void withdraw(double x) {
		balance -= x;
	}
	
	public void deposit(double x) {
		balance += x;
	}
	

}
