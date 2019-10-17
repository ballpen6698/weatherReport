package com.example.WeatherReportDemo.entity;

import com.example.WeatherReportDemo.utility.*;
public class Main {

	// k by default
	private double temp;
	

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}
	
	
	public String toString() {
		StringBuilder output =  new StringBuilder();
		output.append("Temp:").append(Utility.kelvinToFahrenheit(temp)).append("F/").append(Utility.kelvinToCesius(temp)).append("C");
		return output.toString();
	}
	
}
