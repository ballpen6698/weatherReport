package com.example.WeatherReportDemo.entity;


import com.example.WeatherReportDemo.utility.Utility;

public class Sys {

	private long sunrise;
	private long sunset;
	public long getSunrise() {
		return sunrise;
	}
	public void setSunrise(long sunrise) {
		this.sunrise = sunrise;
	}
	public long getSunset() {
		return sunset;
	}
	public void setSunset(long sunset) {
		this.sunset = sunset;
	}
	
	public String toString(long timezone) { 
		StringBuilder output =  new StringBuilder();
		output.append("Sunrise Time:").append(Utility.covertTimeBasedOnTime(sunrise, timezone)).append(',').append("Sunset Time:").append(Utility.covertTimeBasedOnTime(sunset, timezone));
		return output.toString();
	}
	
}
