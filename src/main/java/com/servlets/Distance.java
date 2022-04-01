package com.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.json.JSONParser;

import com.beans.Ville;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;


public class Distance extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Distance() {
        super();
    }
    
    
    private double distance(double lat1, double lon1, double lat2, double lon2, double el1, double el2) {    	
    	double dist =  (6378.137)*Math.acos(Math.sin(Math.toRadians(lat1))*Math.sin(Math.toRadians(lat2))+Math.cos(Math.toRadians(lat1))*Math.cos(Math.toRadians(lat2))*Math.cos(Math.toRadians(lon2-lon1)));
    	return (double) Math.round(dist*10d)/10d;
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url_simple = "http://localhost:8080/ville";
        URL url = new URL(url_simple);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		InputStream res = conn.getInputStream();

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		} else {
			try (Scanner scanner = new Scanner(res)) {
			    String responseBody = scanner.useDelimiter("\\A").next();
			    // convert string to array of Ville
			    Gson gson = new Gson();
			    List<Ville> villes = new ArrayList<Ville>();
			    villes = gson.fromJson (responseBody, new TypeToken<List<Ville>>() {}.getType());
			    
			    request.setAttribute("result", villes);
		    }
			
		}

        this.getServletContext().getRequestDispatcher("/WEB-INF/distance.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if(request.getParameter("city1")!=null && request.getParameter("city2")!=null) {
    		String codeCommune1 = request.getParameter("city1");
    		String codeCommune2 = request.getParameter("city2");
    		
    		double lat1, lon1, lat2, lon2;
    		String nomCommune1, nomCommune2;
    		
    		// GET FIRST CITY COORDINATES
    		String url_city1 = "http://localhost:8080/ville?codeCommune="+codeCommune1;
    		System.out.println("\n========\n"+url_city1);
    		URL url1 = new URL(url_city1);
    		HttpURLConnection conn1 = (HttpURLConnection) url1.openConnection();
    		conn1.setRequestMethod("GET");
    		conn1.setRequestProperty("Accept", "application/json");
    		InputStream res1 = conn1.getInputStream();
    		if(conn1.getResponseCode()!=200) {
    			throw new RuntimeException("Failed : HTTP error code : "
    					+ conn1.getResponseCode());
    		} else {
    			try (Scanner scanner = new Scanner(res1)) {
    				String response1 = scanner.useDelimiter("\\A").next();
    				System.out.println(response1);
    				Gson gson = new Gson();
    				Ville ville1result = gson.fromJson(response1, Ville.class);
    				lat1 = Double.parseDouble(ville1result.getLatitude());
    				lon1 = Double.parseDouble(ville1result.getLongitude());
    				nomCommune1 = ville1result.getNomCommune();
    				System.out.println(ville1result.getNomCommune()+": "+ville1result.getLatitude()+" - "+ville1result.getLongitude());
    			}
    		}
    		
    		// GET SECOND CITY COORDINATES
    		String url_city2 = "http://localhost:8080/ville?codeCommune="+codeCommune2;
    		System.out.println(url_city2);
    		URL url2 = new URL(url_city2);
    		HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
    		conn2.setRequestMethod("GET");
    		conn2.setRequestProperty("Accept", "application/json");
    		InputStream res2 = conn2.getInputStream();
    		if(conn1.getResponseCode()!=200) {
    			throw new RuntimeException("Failed : HTTP error code : "
    					+ conn2.getResponseCode());
    		} else {
    			try (Scanner scanner = new Scanner(res2)) {
    				String response2 = scanner.useDelimiter("\\A").next();
    				System.out.println(response2);
    				Gson gson = new Gson();
    				Ville ville2result = gson.fromJson(response2, Ville.class);
    				lat2 = Double.parseDouble(ville2result.getLatitude());
    				lon2 = Double.parseDouble(ville2result.getLongitude());
    				nomCommune2 = ville2result.getNomCommune();
    				System.out.println(ville2result.getNomCommune()+": "+ville2result.getLatitude()+" - "+ville2result.getLongitude());
    			}
    		}
    		
    		System.out.println("("+lat1+";"+lon1+") -- ("+lat2+";"+lon2+")");
    		double distance = distance(lat1, lon1, lat2, lon2, 0.0, 0.0);
    		//double distance = distance(32.9697, -96.80322, 29.46786, -98.53506, 0.0, 0.0);
    		
    		
    		
    		
    		// REDIRECT OR NOT
    		if(codeCommune1.equals(codeCommune2)) {
    			System.out.println("Cities cannot be equals");
    		} else {
    			request.setAttribute("city1", nomCommune1);
    			request.setAttribute("city2", nomCommune2);
    			request.setAttribute("distance",distance);
    			String url_simple = "http://localhost:8080/ville";
    	        URL url = new URL(url_simple);
    			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    			conn.setRequestMethod("GET");
    			conn.setRequestProperty("Accept", "application/json");
    			InputStream res = conn.getInputStream();

    			if (conn.getResponseCode() != 200) {
    				throw new RuntimeException("Failed : HTTP error code : "
    						+ conn.getResponseCode());
    			} else {
    				try (Scanner scanner = new Scanner(res)) {
    				    String responseBody = scanner.useDelimiter("\\A").next();
    				    // convert string to array of Ville
    				    Gson gson = new Gson();
    				    List<Ville> villes = new ArrayList<Ville>();
    				    villes = gson.fromJson (responseBody, new TypeToken<List<Ville>>() {}.getType());
    				    
    				    request.setAttribute("result", villes);
    			    }
    				
    			}
    			
    			this.getServletContext().getRequestDispatcher("/WEB-INF/distance.jsp").forward(request, response);
    		}
    	}

		
		
    }
}
