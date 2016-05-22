package com.frugalbin.inventory.airline.services.impl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.frugalbin.inventory.airline.models.FlightBookDetails;
import com.frugalbin.inventory.airline.repositories.FlightBookDetailsRepository;
import com.frugalbin.inventory.airline.services.FlightBookDetailsServiceI;

@Named
@Singleton
public class FlightBookDetailsServiceImpl implements FlightBookDetailsServiceI
{
	@Inject
	private FlightBookDetailsRepository repository;

	@Override
	public void addFlightBookDetails(FlightBookDetails flightBookDetails)
	{
		repository.saveAndFlush(flightBookDetails);
	}
}
