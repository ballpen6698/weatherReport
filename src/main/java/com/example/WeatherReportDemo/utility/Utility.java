package com.example.WeatherReportDemo.utility;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Utility {

	private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
	private final static DateTimeFormatter FORMATTER_12HOUR = DateTimeFormatter.ofPattern("hh:mm a");
	
	public static String kelvinToFahrenheit(double temp_f) {
		return String.format("%.2f", temp_f * 9 /5 - 459.67);
	}
	
	public static String kelvinToCesius(double temp_f) {
		return String.format("%.2f", temp_f - 273.15);
	}
	
	// get time based on timezone
	public static String convertTimeBasedOnTimeZone(long timezone) {
		ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC).plusHours(timezone/3600);
        return now.format(FORMATTER);
	}
	
	// calculate time based on provided time and timezone
	public static String covertTimeBasedOnTime(long time, long timezone) {
		// value are provided as second and need to convert to mills
		LocalDateTime date =
	    LocalDateTime.ofInstant(Instant.ofEpochMilli((time+ timezone) * 1000), ZoneOffset.UTC);
		return date.format(FORMATTER_12HOUR);
	}
}
