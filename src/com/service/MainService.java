package com.service;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.beans.City;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class MainService {
	
	public static int createCity(City newVille) {
		try {
			URL url = new URL(Requester.cities());

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);

			Gson gson = new Gson();
			String requestBody = gson.toJson(newVille);

			OutputStream outputStream = connection.getOutputStream();
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
			outputStreamWriter.write(requestBody);
			outputStreamWriter.flush();
			outputStreamWriter.close();
			outputStream.close();
			connection.connect();

			return connection.getResponseCode();
		} catch (Exception e) {
			return 1000;
		}
	}

	public static City getCity(String codeCommune) {
		City city = null;

		try {
			URL url = new URL(Requester.city(codeCommune));

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();

			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("HttpResponseCode: " + connection.getResponseCode());
			} else {

				String inline = "";
				Scanner scanner = new Scanner(url.openStream());

				while (scanner.hasNext()) {
					inline += scanner.nextLine();
				}
				scanner.close();

				Gson gson = new Gson();
				city = gson.fromJson(inline, City.class);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return city;
	}
	
	public static List<City> getCities() {
		List<City> cities = new ArrayList<City>();

		try {
			URL url = new URL(Requester.cities());

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();

			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("HttpResponseCode: " + connection.getResponseCode());
			} else {
				String inline = "";
				Scanner scanner = new Scanner(url.openStream());

				while (scanner.hasNext()) {
					inline += scanner.nextLine();
				}
				scanner.close();

				Gson gson = new Gson();
				cities = gson.fromJson(inline, new TypeToken<List<City>>(){}.getType());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cities;
	}
	
	public static Map<String, Object> getCitiesPaginated(int page) {
		Map<String, Object> villePage = new HashMap<String, Object>();

		try {
			URL url = new URL(Requester.citiesPaginated(page));

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();

			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("HttpResponseCode: " + connection.getResponseCode());
			} else {

				String inline = "";
				Scanner scanner = new Scanner(url.openStream());

				while (scanner.hasNext()) {
					inline += scanner.nextLine();
				}
				scanner.close();

				Gson gson = new Gson();
				JsonObject json = gson.fromJson(inline, JsonObject.class);
				List<City> cities = gson.fromJson(json.getAsJsonArray("content"), new TypeToken<List<City>>(){}.getType());
				int totalPages = json.get("totalPages").getAsInt();

				villePage.put("villes", cities);
				villePage.put("nbPages", totalPages);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return villePage;
	}
	
	public static int updateCity(City updatedVille) {
		try {
			URL url = new URL(Requester.cities());

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("PUT");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);

			Gson gson = new Gson();
			String requestBody = gson.toJson(updatedVille);

			OutputStream outputStream = connection.getOutputStream();
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
			outputStreamWriter.write(requestBody);
			outputStreamWriter.flush();
			outputStreamWriter.close();
			outputStream.close();
			connection.connect();

			return connection.getResponseCode();
		} catch (Exception e) {
			return 1000;
		}
	}

	public static int deleteCity(String codeCommune) {
		try {
			URL url = new URL(Requester.city(codeCommune));

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("DELETE");
			connection.connect();

			return connection.getResponseCode();
		} catch (Exception e) {
			return 1000;
		}
	}
	
	public static HashMap<String, String> getWeather(City ville) {
		HashMap<String, String> weather = new HashMap<String, String>();
		try {
			URL url = new URL(Requester.weather(ville.getLatitude(),
													ville.getLongitude()));

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();

			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("HttpResponseCode: " + connection.getResponseCode());
			} else {

				String inline = "";
				Scanner scanner = new Scanner(url.openStream());

				while (scanner.hasNext()) {
					inline += scanner.nextLine();
				}
				scanner.close();

				Gson gson = new Gson();
				JsonObject json = gson.fromJson(inline, JsonObject.class);
				String temp = json.get("main").getAsJsonObject().get("temp").getAsString();
				weather.put("temp", temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return weather;
	}
	
	public static int getDistance(City ville1, City ville2) {	
		
		double lon1, lon2, lat1, lat2, dlon, dlat, a, c, r;
		r = 6371e3;
		
        lon1 = Double.parseDouble(ville1.getLongitude());
        lon2 = Double.parseDouble(ville2.getLongitude());
        lat1 = Double.parseDouble(ville1.getLatitude());
        lat2 = Double.parseDouble(ville2.getLatitude());
        
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        
        dlon = lon2 - lon1;
        dlat = lat2 - lat1;
        a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2),2);
        c = 2 * Math.asin(Math.sqrt(a));
        
        return (int) Math.round(c*r);
	}
}