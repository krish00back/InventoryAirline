package com.frugalbin.inventory.airline.services;

import java.util.List;

import com.frugalbin.inventory.airline.models.AirportDetails;
import com.frugalbin.inventory.airline.models.City;

public interface AirportDetailsServiceI
{

	List<AirportDetails> findAirportByAirportCity(City fromCity);
}
