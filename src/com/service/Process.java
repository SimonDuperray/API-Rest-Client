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

import com.beans.Ville;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class Process {

	public static List<Ville> getVilles() {
		List<Ville> villes = new ArrayList<Ville>();

		try {
			URL url = new URL(URLBuilder.villes());

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();

			int responsecode = conn.getResponseCode();

			if (responsecode != 200) {
				throw new RuntimeException("HttpResponseCode: " + responsecode);
			} else {

				String inline = "";
				Scanner scanner = new Scanner(url.openStream());

				while (scanner.hasNext()) {
					inline += scanner.nextLine();
				}
				scanner.close();

				Gson gson = new Gson();
				villes = gson.fromJson(inline, new TypeToken<List<Ville>>(){}.getType());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return villes;
	}
	
	public static Map<String, Object> getVillesPaginated(int page) {
		Map<String, Object> villePage = new HashMap<String, Object>();

		try {
			URL url = new URL(URLBuilder.villesPaginated(page));

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();

			int responsecode = conn.getResponseCode();

			if (responsecode != 200) {
				throw new RuntimeException("HttpResponseCode: " + responsecode);
			} else {

				String inline = "";
				Scanner scanner = new Scanner(url.openStream());

				while (scanner.hasNext()) {
					inline += scanner.nextLine();
				}
				scanner.close();

				Gson gson = new Gson();
				JsonObject json = gson.fromJson(inline, JsonObject.class);
				List<Ville> villes = gson.fromJson(json.getAsJsonArray("content"), new TypeToken<List<Ville>>(){}.getType());
				int totalPages = json.get("totalPages").getAsInt();

				villePage.put("villes", villes);
				villePage.put("nbPages", totalPages);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return villePage;
	}
	
	public static Ville getVille(String codeCommune) {
		Ville ville = null;

		try {
			URL url = new URL(URLBuilder.ville(codeCommune));

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();

			// Getting the response code
			int responsecode = conn.getResponseCode();

			if (responsecode != 200) {
				throw new RuntimeException("HttpResponseCode: " + responsecode);
			} else {

				String inline = "";
				Scanner scanner = new Scanner(url.openStream());

				while (scanner.hasNext()) {
					inline += scanner.nextLine();
				}
				scanner.close();

				Gson gson = new Gson();
				ville = gson.fromJson(inline, Ville.class);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ville;
	}

	
	public static int getDistanceRoad(Ville ville1, Ville ville2) {
		int distance  = -1;
		try {
			URL url = new URL(URLBuilder.distanceRoad(ville1.getLatitude(),
													ville1.getLongitude(),
													ville2.getLatitude(),
													ville2.getLongitude()));

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();

			int responsecode = conn.getResponseCode();

			if (responsecode != 200) {
				throw new RuntimeException("HttpResponseCode: " + responsecode);
			} else {

				String inline = "";
				Scanner scanner = new Scanner(url.openStream());

				while (scanner.hasNext()) {
					inline += scanner.nextLine();
				}
				scanner.close();

				Gson gson = new Gson();
				JsonObject json = gson.fromJson(inline, JsonObject.class);				
				String distanceStr = json.getAsJsonArray("routes").get(0).getAsJsonObject()
								.getAsJsonArray("sections").get(0).getAsJsonObject()
								.getAsJsonObject("summary")
								.get("length").getAsString();
				distance = Integer.parseInt(distanceStr);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return distance;

	}
	
	public static int getDistanceDirect(Ville ville1, Ville ville2) {		
        double lon1 = Double.parseDouble(ville1.getLongitude());
        double lon2 = Double.parseDouble(ville2.getLongitude());
        double lat1 = Double.parseDouble(ville1.getLatitude());
        double lat2 = Double.parseDouble(ville2.getLatitude());
        
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                 + Math.cos(lat1) * Math.cos(lat2)
                 * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));
        
        double r = 6371e3;
        
        return (int) Math.round(c * r);
	}
	
	public static HashMap<String, String> getWeather(Ville ville) {
		HashMap<String, String> weather = new HashMap<String, String>();
		
		try {
			URL url = new URL(URLBuilder.weather(ville.getLatitude(),
													ville.getLongitude()));

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();

			int responsecode = conn.getResponseCode();

			if (responsecode != 200) {
				throw new RuntimeException("HttpResponseCode: " + responsecode);
			} else {

				String inline = "";
				Scanner scanner = new Scanner(url.openStream());

				while (scanner.hasNext()) {
					inline += scanner.nextLine();
				}
				scanner.close();

				Gson gson = new Gson();
				JsonObject json = gson.fromJson(inline, JsonObject.class);
				String main = json.getAsJsonArray("weather").get(0).getAsJsonObject()
								.get("main").getAsString();
				String description = json.getAsJsonArray("weather").get(0).getAsJsonObject()
								.get("description").getAsString();
				String temp = json.get("main").getAsJsonObject().get("temp").getAsString();
				String icon = "http://openweathermap.org/img/wn/";
				icon += json.getAsJsonArray("weather").get(0).getAsJsonObject()
							.get("icon").getAsString();
				icon += "@2x.png";
				
				weather.put("main", main);
				weather.put("description", description);
				weather.put("temp", temp);
				weather.put("icon", icon);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return weather;
	}

	public static int updateVille(Ville updatedVille) {
		try {
			URL url = new URL(URLBuilder.villes());

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);

			Gson gson = new Gson();
			String requestBody = gson.toJson(updatedVille);

			OutputStream outStream = conn.getOutputStream();
			OutputStreamWriter outStreamWriter = new OutputStreamWriter(outStream, "UTF-8");
			outStreamWriter.write(requestBody);
			outStreamWriter.flush();
			outStreamWriter.close();
			outStream.close();
			conn.connect();

			return conn.getResponseCode();
		} catch (Exception e) {
			return -1;
		}
	}

	public static int deleteVille(String codeCommune) {
		try {
			URL url = new URL(URLBuilder.ville(codeCommune));

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("DELETE");
			conn.connect();

			return conn.getResponseCode();
		} catch (Exception e) {
			return -1;
		}
	}

	public static int createVille(Ville newVille) {
		try {
			URL url = new URL(URLBuilder.villes());

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);

			Gson gson = new Gson();
			String requestBody = gson.toJson(newVille);

			OutputStream outStream = conn.getOutputStream();
			OutputStreamWriter outStreamWriter = new OutputStreamWriter(outStream, "UTF-8");
			outStreamWriter.write(requestBody);
			outStreamWriter.flush();
			outStreamWriter.close();
			outStream.close();
			conn.connect();

			return conn.getResponseCode();
		} catch (Exception e) {
			return -1;
		}
	}
}