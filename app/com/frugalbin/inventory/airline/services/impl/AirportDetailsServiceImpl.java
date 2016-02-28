package com.frugalbin.inventory.airline.services.impl;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import com.frugalbin.inventory.airline.models.AirportDetails;
import com.frugalbin.inventory.airline.models.City;
import com.frugalbin.inventory.airline.repositories.AirportDetailsRepository;
import com.frugalbin.inventory.airline.services.AirportDetailsServiceI;

@Named
@Singleton
public class AirportDetailsServiceImpl implements AirportDetailsServiceI
{
	private AirportDetailsRepository repository;

	@Override
	public List<AirportDetails> findAirportByAirportCity(City airportCity)
	{
		return repository.findByAirportCity(airportCity);
	}
}
