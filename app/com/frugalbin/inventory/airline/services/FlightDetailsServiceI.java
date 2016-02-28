package com.frugalbin.inventory.airline.services;

import java.util.List;

import com.frugalbin.inventory.airline.models.AirportDetails;
import com.frugalbin.inventory.airline.models.FlightDetails;

public interface FlightDetailsServiceI
{
	List<FlightDetails> findFlightByFromAirportInAndToAirportIn(List<AirportDetails> fromAirportList,
			List<AirportDetails> toAirportList);
}
