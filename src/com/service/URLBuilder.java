package com.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class URLBuilder {
	
	private static final String VILLES_API_BASE_URL = "http://localhost:8080/city";
	private static final String ROUTING_API_BASE_URL = "https://router.hereapi.com/v8/routes";
	private static final String WEATHER_API_BASE_URL = "https://api.openweathermap.org/data/2.5/weather";
	
	public static String villes(String codePostal) {
		if (codePostal != null) {
			return VILLES_API_BASE_URL + "?codePostal=" + codePostal;
		} else {
			return VILLES_API_BASE_URL;
		}
	}

	public static String villesPaginated(int page) {
		return VILLES_API_BASE_URL + "?size=50&page=" + page;
	}
	
	public static String villes() {
		return villes(null);
	}
	
	public static String ville(String codeCommune) {
		return VILLES_API_BASE_URL + "?codeCommune=" + codeCommune;
	}
	
	public static String distanceRoad(String latitude1, String longitude1, 
			String latitude2, String longitude2) {
		Properties prop = new Properties();
		try {
			prop.load(URLBuilder.class.getClassLoader().getResourceAsStream("api.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ROUTING_API_BASE_URL
				+ "?transportMode=car"
				+ "&origin=" + latitude1 + "," + longitude1
				+ "&destination=" + latitude2 + "," + longitude2
				+ "&return=summary"
				+ "&apiKey=" + prop.getProperty("here.apiKey");
	}
	
	public static String weather(String latitude, String longitude) {
		Properties prop = new Properties();
		try {
			prop.load(URLBuilder.class.getClassLoader().getResourceAsStream("api.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return WEATHER_API_BASE_URL
				+ "?lon=" + longitude
				+ "&lat=" + latitude
				+ "&units=metric"
				+ "&appid=" + prop.getProperty("openWeather.apiKey");
	}
}