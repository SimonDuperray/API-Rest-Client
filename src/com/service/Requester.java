package com.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Requester {
	
	private static final String CITIES_API = "http://localhost:8080/city";
	private static final String WEATHER_API = "https://api.openweathermap.org/data/2.5/weather";
	
	public static String cities(String codePostal) {
		if (codePostal != null) {
			return CITIES_API + "?codePostal=" + codePostal;
		} else {
			return CITIES_API;
		}
	}
	
	public static String cities() {
		return cities(null);
	}

	public static String citiesPaginated(int page) {
		return CITIES_API + "?size=50&page=" + page;
	}
	
	public static String city(String codeCommune) {
		return CITIES_API + "?codeCommune=" + codeCommune;
	}
	
	public static String weather(String latitude, String longitude) {
		Properties prop = new Properties();
		try {
			prop.load(Requester.class.getClassLoader().getResourceAsStream("api.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return WEATHER_API
				+ "?lon=" + longitude
				+ "&lat=" + latitude
				+ "&units=metric"
				+ "&appid=" + prop.getProperty("openWeather.apiKey");
	}
}