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
	
	public boolean receive(double money) {
		if(money>=0) {
			cash += money;
			return true;
		}
		return false;
	}
	
	public boolean pay(double money) {
		if(money>=0&&cash>money) {
			cash -= money;
			return true;
		}
		return false;
	}
	
	public boolean withdraw(double money) {
		if(money>=0&&deposit>money) {
			deposit -= money;
			cash += money;
			return true;
		}
		return false;
	}
	
	public boolean deposit(double money) {
		if(money>=0&&cash>money) {
			cash -= money;
			deposit += money;
			return true;
		}
		return false;
		
	}
	
}
