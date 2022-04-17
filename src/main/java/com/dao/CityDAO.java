package com.dao;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.net.HttpURLConnection;
import java.net.URI;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONException;

import com.beans.Ville;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.requester.Requester;

public class CityDAO implements CityInterface {
	
	@Override
	public ArrayList<Ville> getAllCities() {
		Requester requester = new Requester();
		JSONArray json;
		ArrayList<Ville> cities=new ArrayList<Ville>();
		try {
			String url = "http://localhost:8080/ville";
			json = requester.readJsonFromURL(url);
			ObjectMapper om = new ObjectMapper();
			cities = om.readValue(json.toString(), new TypeReference<ArrayList<Ville>>() {});
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
		for(Ville city: cities) {
			System.out.println(city.getNomCommune()+"\n");
		}
		return cities;
	}

	@Override
	public void addCity(String name, String codeCommune, String codePostal, String ligne, String libelle,
			String longitude, String latitude) {
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			 String requestBody = "{\n"
				   		+ "    \"nomCommune\": \""+name+"\",\n"
				   		+ "	\"codeCommuneInsee\": \""+codeCommune+"\",\n"
				   		+ "	\"codePostal\": \""+codePostal+"\",\n"
				   		+ "	\"ligne5\": \""+ligne+"\",\n"
				   		+ "	\"libelleAcheminement\": \""+libelle+"\",\n"
				   		+ "	\"longitude\": \""+longitude+"\",\n"
				   		+ "	\"latitude\": \""+latitude+"\"\n"
				   		+ "	}";
			StringEntity stringEntity = new StringEntity(requestBody);
			HttpPost post = new HttpPost();
			post.setURI(new URI("http://localhost:8080/createVille"));
			post.addHeader("Content-type", "application/json");
			post.setEntity(stringEntity);
		    CloseableHttpResponse httpResponse = httpClient.execute(post);
		    System.out.println("Status Code - " + httpResponse.getStatusLine().toString());
	    } catch (URISyntaxException e) {
	    	e.printStackTrace();
	    } catch (UnsupportedEncodingException e) {
	    	e.printStackTrace();
	    } catch (ClientProtocolException e) {
	    	e.printStackTrace();
	    } catch (IOException e) {
	    	e.printStackTrace();
	   }
	}

	@Override
	public void updateCity(String name, String codeCommune, String codePostal, String ligne, String libelle,
			String longitude, String latitude) {
		try {
		   CloseableHttpClient httpClient = HttpClients.createDefault();
		   
		   String requestBody = "{\n"
		   		+ "    \"nomCommune\": \""+name+"\",\n"
		   		+ "	\"codeCommuneInsee\": \""+codeCommune+"\",\n"
		   		+ "	\"codePostal\": \""+codePostal+"\",\n"
		   		+ "	\"ligne5\": \""+ligne+"\",\n"
		   		+ "	\"libelleAcheminement\": \""+libelle+"\",\n"
		   		+ "	\"longitude\": \""+longitude+"\",\n"
		   		+ "	\"latitude\": \""+latitude+"\"\n"
		   		+ "	}";
		   StringEntity stringEntity = new StringEntity(requestBody);
		   HttpPut put = new HttpPut();
		   put.setURI(new URI("http://localhost:8080/replaceVille"));
		   put.addHeader("Content-type", "application/json");
		   put.setEntity(stringEntity);
		   CloseableHttpResponse httpResponse = httpClient.execute(put);
		   System.out.println("Status Code - " + httpResponse.getStatusLine().toString());
		  } catch (URISyntaxException e) {
			   e.printStackTrace();
		  } catch (UnsupportedEncodingException e) {
			   e.printStackTrace();
		  } catch (ClientProtocolException e) {
			   e.printStackTrace();
		  } catch (IOException e) {
			   e.printStackTrace();
		  }
	}

	@Override
	public void deleteCity(String code) {
		try {
			URL url = new URL("http://localhost:8080/deleteVille?id="+code);
			HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
			httpUrlConnection.setRequestMethod("DELETE");
			httpUrlConnection.setRequestProperty("Accept", "application/json");

			if (httpUrlConnection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + httpUrlConnection.getResponseCode());
			}

			System.out.println("Output from Serveur ... /n");
			httpUrlConnection.disconnect();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Ville getCity(String code) {
		Requester requester= new Requester();
		JSONArray json;
		ArrayList<Ville> cities = new ArrayList<Ville>();
		try {
			json = requester.readJsonFromURL("http://localhost:8080/getVille?codeCommune="+code);
			ObjectMapper mapper = new ObjectMapper();
			cities = mapper.readValue(json.toString(), new TypeReference<ArrayList<Ville>>() {});
		} catch(JSONException | IOException e) {
			e.printStackTrace();
		}
		return cities.get(0);
		
	}
}
