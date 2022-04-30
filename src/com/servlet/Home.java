package com.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.beans.City;
import com.service.MainService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Home() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<City> cities = MainService.getCities();
		HttpSession session = request.getSession();
		session.setAttribute("cities", cities);
		request.setAttribute("cities", cities);
		this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<City> cities = (List<City>) session.getAttribute("cities");
		if (cities == null) {
			cities = MainService.getCities();
			session.setAttribute("cities", cities);
		}
		City City1 = MainService.getCity(request.getParameter("ville1"));
		City City2 = MainService.getCity(request.getParameter("ville2"));
		int distance = MainService.getDistance(City1, City2);
		HashMap<String, String> weatherCity1 = MainService.getWeather(City1);
		HashMap<String, String> weatherCity2 = MainService.getWeather(City2);
		
		request.setAttribute("cities", cities);
		request.setAttribute("displayDistance", true);
		request.setAttribute("ville1", City1);
		request.setAttribute("ville2", City2);
		request.setAttribute("distance", distance);
		request.setAttribute("weatherCity1", weatherCity1);
		request.setAttribute("weatherCity2", weatherCity2);
		this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
	}
}