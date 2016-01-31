package com.frugalbin.inventory.airline.services.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.frugalbin.inventory.airline.models.AirportDetails;
import com.frugalbin.inventory.airline.models.FlightDetails;
import com.frugalbin.inventory.airline.repositories.FlightDetailsRepository;
import com.frugalbin.inventory.airline.services.FlightDetailsServiceI;

@Named
@Singleton
public class FlightDetailsServiceImpl implements FlightDetailsServiceI
{
	@Inject
	private FlightDetailsRepository repository;

	@Override
	public List<FlightDetails> findFlightByFromAirportInAndToAirportIn(List<AirportDetails> fromAirportList,
			List<AirportDetails> toAirportList)
	{
		return repository.findByFromAirportInAndToAirportIn(fromAirportList, toAirportList);
	}
}
