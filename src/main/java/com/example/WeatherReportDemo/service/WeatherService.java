package com.example.WeatherReportDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.WeatherReportDemo.entity.AllData;

@Service("weatherService")
public class WeatherService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Environment env;
	
	// Get weather by city ID
	public AllData getWeather(String cityId) {
		String url = String.format(env.getProperty("weather.restful.APICALL"), cityId, env.getProperty("weahter.restful.APIKEY"));
		ResponseEntity<AllData> resp =  restTemplate.getForEntity(url, AllData.class);
	    return resp.getStatusCode() == HttpStatus.OK ? resp.getBody() : null;

	}
}
