package com.example.WeatherReportDemo;

import static org.mockito.Mockito.when;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.example.WeatherReportDemo.controller.ViewWeatherController;
import com.example.WeatherReportDemo.entity.AllData;
import com.example.WeatherReportDemo.entity.Main;
import com.example.WeatherReportDemo.entity.Sys;
import com.example.WeatherReportDemo.entity.Weather;
import com.example.WeatherReportDemo.service.WeatherService;
import com.example.WeatherReportDemo.utility.Utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Before;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WeatherReportDemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	// create dummy instance
	@Mock
	private Environment env;

	// create dummy instance
	@Mock
	private RestTemplate restTemplate;

	// create dummy instance
	@Mock
	private WeatherService ws_mc;

	// inject dummy instance
	@InjectMocks
	private WeatherService ws = new WeatherService();

	// inject dummy instance
	@InjectMocks
	private ViewWeatherController viewWeatherController = new ViewWeatherController();

	private AllData allData = new AllData();

	@Before
	public void init() {
		// initializes fields annotated with Mockito annotations
		MockitoAnnotations.initMocks(this);

		// set up test data
		allData = new AllData();
		allData.setId(2643743);
		allData.setName("London");
		Main main = new Main();
		main.setTemp(1.11);
		Sys sys = new Sys();
		sys.setSunrise(1111111);
		sys.setSunset(22222222);
		Weather weather = new Weather();
		weather.setDescription("it is hot");
		allData.setMain(main);
		allData.setSys(sys);
		allData.setTimezone(2800);
		List<Weather> lw = new ArrayList<Weather>();
		lw.add(weather);
		allData.setWeather(lw);

		// mock behavior for weather service
		Mockito.when(env.getProperty("weather.restful.APICALL"))
				.thenReturn("http://api.openweathermap.org/data/2.5/weather?id=%s&APPID=%s");

		Mockito.when(env.getProperty("weahter.restful.APIKEY")).thenReturn("d219e072a0c0da98d29a1e35e0121841");
		String url = String.format(env.getProperty("weather.restful.APICALL"), 2643743,
				env.getProperty("weahter.restful.APIKEY"));
		Mockito.when(restTemplate.getForEntity(url, AllData.class))
				.thenReturn(new ResponseEntity<AllData>(allData, HttpStatus.OK));

	}

	@Test
	public void TestWeatherService() {
		Assert.assertEquals(allData, ws.getWeather("2643743"));
	}

	@Test
	public void TestWeatherController() throws Exception {

		this.mockMvc.perform(get("/weatherInfo")).andExpect(status().isOk())
				// city name
				.andExpect(content().string(containsString("London")))
				// city id
				.andExpect(content().string(containsString("2643743")));
	}

	@Test
	public void testKelvinToFahrenheit() {
		// pass 200k
		assertEquals("-99.67", Utility.kelvinToFahrenheit(200));
		assertNotEquals("-99.6", Utility.kelvinToFahrenheit(200));

		// pass 400k
		assertEquals("260.33", Utility.kelvinToFahrenheit(400));
		assertNotEquals("260", Utility.kelvinToFahrenheit(400));

		// pass 0k
		assertEquals("-459.67", Utility.kelvinToFahrenheit(0));
		assertNotEquals("0", Utility.kelvinToFahrenheit(0));
	}

	@Test
	public void testKelvinToCesius() {
		// pass 200k
		assertEquals("-73.15", Utility.kelvinToCesius(200));
		assertNotEquals("-72.15", Utility.kelvinToCesius(200));

		// pass 400k
		assertEquals("126.85", Utility.kelvinToCesius(400));
		assertNotEquals("123", Utility.kelvinToCesius(400));

		// pass 0k
		assertEquals("-273.15", Utility.kelvinToCesius(0));
		assertNotEquals("0", Utility.kelvinToCesius(0));
	}

	@Test
	public void testUrlStatus() {

	}

	@Test
	public void testConvertTimeBasedOnTimeZone() throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		// prepare hongkong time
		sdf.setTimeZone(TimeZone.getTimeZone("Hongkong"));
		assertEquals(sdf.format(calendar.getTime()), Utility.convertTimeBasedOnTimeZone(28800));

		// prepare longdon time
		sdf.setTimeZone(TimeZone.getTimeZone("Europe/London"));
		assertEquals(sdf.format(calendar.getTime()), Utility.convertTimeBasedOnTimeZone(3600));
	}

	@Test
	public void testCovertTimeBasedOnTime() {
		assertEquals("05:57 AM", Utility.covertTimeBasedOnTime(1565215067, 28800));
		assertEquals("07:00 PM", Utility.covertTimeBasedOnTime(1565262027, 28800));
		assertEquals("01:46 AM", Utility.covertTimeBasedOnTime(1000000000, 0));
		assertEquals("03:10 AM", Utility.covertTimeBasedOnTime(1000000000, 5000));
	}
}
