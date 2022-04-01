package com.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
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
import com.google.gson.reflect.TypeToken;


public class Distance extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Distance() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url_param = "http://localhost:8080/ville?codePostal=08440";
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
    		String city1 = request.getParameter("city1");
    		String city2 = request.getParameter("city2");
    		
    		if(city1.equals(city2)) {
    			System.out.println("Cities cannot be equals");
    		} else {
    			request.setAttribute("city1", city1);
    			request.setAttribute("city2", city2);
    			// get cities coordinates
    			request.setAttribute("distance", 10);
    			this.getServletContext().getRequestDispatcher("/WEB-INF/distance_result.jsp").forward(request, response);
    		}
    	}

		
		
    }
}
