package com.github.miniyosshi.arciamstorypluginV2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Assets {
	@JsonProperty
	private double cash;
	@JsonProperty
	private double deposit;
	
	public Assets() {}
	
	@JsonCreator
	public Assets(@JsonProperty("cash")double cash, @JsonProperty("deposit")double deposit) {
		this.cash = cash;
		this.deposit = deposit;
	}
	
	public String showCash() {
		return String.valueOf(cash);
	}
	
	public String showDeposit() {
		return String.valueOf(deposit);
	}
	
	public void recieve(double money) {
		cash += money;
	}
	
	public void pay(double money) {
		cash -= money;
	}
	
	public void withdraw(double money) {
		deposit -= money;
		cash += money;
	}
	
	public void deposit(double money) {
		cash -= money;
		deposit += money;
	}
	
}
