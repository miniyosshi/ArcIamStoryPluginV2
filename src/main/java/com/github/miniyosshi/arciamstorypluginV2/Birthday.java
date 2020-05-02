package com.github.miniyosshi.arciamstorypluginV2;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Birthday {
	@JsonProperty
	private int year;
	@JsonProperty
	private int month;
	@JsonProperty
	private int day;
	
	public Birthday() {
		this.year = 1980;
		this.month = 1;
		this.day = 1;
	}
	
	@JsonCreator
	public Birthday(@JsonProperty("year")int year, @JsonProperty("month")int month, @JsonProperty("day")int day) {
		this.year = year;
		this.month = month;
		this.day = day;
		//examine if this is valid date
	}
	//constellation
	
	
	
}
