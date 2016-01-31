package com.frugalbin.inventory.airline.services;

import java.util.Date;
import java.util.List;

import com.frugalbin.inventory.airline.models.FlightDetails;
import com.frugalbin.inventory.airline.models.FlightSeatDetails;

public interface FlightSeatDetailsServiceI
{

	List<FlightSeatDetails> findFlightSeatsByFlightAndAvailSeatsAndDepTime(List<FlightDetails> flightList,
			Date preferredTime);
}