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
    
	/*
	 * private void deleteCity(HttpServletRequest request, HttpServletResponse
	 * response) throws IOException { int id =
	 * Integer.parseInt(request.getParameter("id")); String url_str =
	 * "http://localhost:8080/ville?codeCommune="+id; URL url = new URL(url_str);
	 * HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	 * conn.setRequestMethod("DELETE"); conn.setRequestProperty("Accept",
	 * "application/json"); InputStream res = conn.getInputStream();
	 * if(conn.getResponseCode()!=200) { request.setAttribute("delete_success",
	 * false); throw new
	 * RuntimeException("Failed: HTTP eror code: "+conn.getResponseCode()); } else {
	 * request.setAttribute("delete_success", true); } }
	 */
    
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
		
		/*
		 * String action = request.getServletPath(); System.out.print("action: ");
		 * System.out.println(action); try { switch(action) { case "/delete":
		 * deleteCity(request, response); break; default: break; } } catch(IOException
		 * e) {
		 * 
		 * }
		 */

        this.getServletContext().getRequestDispatcher("/WEB-INF/panel.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	this.getServletContext().getRequestDispatcher("/WEB-INF/panel.jsp").forward(request, response);
    }
}
