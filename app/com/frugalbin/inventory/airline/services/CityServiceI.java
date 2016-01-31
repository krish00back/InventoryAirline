package com.frugalbin.inventory.airline.services;

import java.util.List;

import com.frugalbin.inventory.airline.models.City;

public interface CityServiceI
{
	List<City> getAllCities();

	void insertCity(City city);
	
	City findCity(Long cityId);
}
