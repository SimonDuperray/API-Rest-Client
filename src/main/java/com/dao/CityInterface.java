package com.dao;

import java.util.ArrayList;

import com.beans.Ville;

public interface CityInterface {
	
	// attributes
	ArrayList<Ville> getAllCities();
	
	// functions
	void addCity(String name, String codeCommune, String codePostal, String ligne, String libelle, String longitude, String latitude);
	void updateCity(String name, String codeCommune, String codePostal, String ligne, String libelle, String longitude, String latitude);
	void deleteCity(String code);
	Ville getCity(String code);
	
}
