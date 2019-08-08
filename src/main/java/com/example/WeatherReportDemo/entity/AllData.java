package com.example.WeatherReportDemo.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.WeatherReportDemo.utility.Utility;

public class AllData {

	private String name;
	private List<Weather> weather = new ArrayList<Weather>();

	// temperature info
	private Main main;

	// sun rise and set info
	private Sys sys;

	private long timezone;
	
	// city id
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getTimezone() {
		return timezone;
	}

	public void setTimezone(long timezone) {
		this.timezone = timezone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Weather> getWeather() {
		return weather;
	}

	public void setWeather(List<Weather> weather) {
		this.weather = weather;
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public Sys getSys() {
		return sys;
	}

	public void setSys(Sys sys) {
		this.sys = sys;
	}

	
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("Current Date:").append(Utility.convertTimeBasedOnTimeZone(this.getTimezone())).append(',').append("City Name:")
				.append(this.getName()).append(',')
				.append(this.getWeather().get(0).toString()).append(',')
				.append(this.getMain().toString()).append(',').append(this.getSys().toString(this.getTimezone()));
		return output.toString();
	}

}
