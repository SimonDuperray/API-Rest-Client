package com.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Distance extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Distance() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("name", "Simon");
        String url_param = "http://localhost:8080/ville?codePostal=08440";
        String url_simple = "http://localhost:8080/ville";
        URL url = new URL(url_param);
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
			    request.setAttribute("result", responseBody);
		    }
			
		}

        this.getServletContext().getRequestDispatcher("/WEB-INF/distance.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
