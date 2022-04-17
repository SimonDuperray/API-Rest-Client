package com.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Ville;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;



public class Panel extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Panel() {
        super();
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
			    
			    request.setAttribute("villes", villes);
		    }
			
		}
		 
        this.getServletContext().getRequestDispatcher("/WEB-INF/panel.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String codeCommune = request.getParameter("codeCommune");
    	String nomCommune = request.getParameter("nomCommune");
    	String codePostal = request.getParameter("codePostal");
    	String libelleAcheminement = request.getParameter("libelleAcheminement");
    	String latitude = request.getParameter("latitude");
    	String longitude = request.getParameter("longitude");
    	String ligne = request.getParameter("ligne");
    	
    	System.out.println(codeCommune);
    	System.out.println(nomCommune);
    	System.out.println(codePostal);
    	System.out.println(libelleAcheminement);
    	System.out.println(ligne);
    	System.out.println(latitude);
    	System.out.println(longitude);
    	
    	Ville ville = new Ville(codeCommune, nomCommune, codePostal, libelleAcheminement, ligne, latitude, longitude);
    	
    	String url_str = "http://localhost:8080/ville?codeCommune="+codeCommune+"&nomCommune="+nomCommune+"&codePostal="+codePostal+"&libelleAcheminement="+libelleAcheminement+"&ligne="+ligne+"&latitude="+latitude+"&longitude="+longitude;
    	URL url = new URL(url_str);
    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    	conn.setRequestMethod("POST");
    	conn.setRequestProperty("Accept", "application/json");
    	conn.connect();
    	InputStream res = conn.getInputStream();
    	if(conn.getResponseCode()!=200) {
    		throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
    	} else {
    		System.out.println("City added");
    		}
    	}
    	
    }