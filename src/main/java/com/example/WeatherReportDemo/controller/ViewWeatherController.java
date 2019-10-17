package com.example.WeatherReportDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.WeatherReportDemo.service.WeatherService;

@Controller
public class ViewWeatherController {

	@Autowired
	WeatherService weatherService;
	
	
	@RequestMapping(value="/weatherInfo",method = { RequestMethod.GET, RequestMethod.POST })
    public String greeting(@RequestParam(name="cityId", required=true, defaultValue="2643743") String cityId, Model model) {
        model.addAttribute("weatherinfo", weatherService.getWeather(cityId).toString());
        return "weatherInfo";
    }
}
