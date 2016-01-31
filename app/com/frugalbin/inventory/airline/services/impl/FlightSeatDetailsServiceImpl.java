package com.frugalbin.inventory.airline.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.apache.commons.lang3.time.DateUtils;

import com.frugalbin.inventory.airline.models.FlightDetails;
import com.frugalbin.inventory.airline.models.FlightSeatDetails;
import com.frugalbin.inventory.airline.repositories.FlightSeatDetailsRepository;
import com.frugalbin.inventory.airline.services.FlightSeatDetailsServiceI;

@Named
@Singleton
public class FlightSeatDetailsServiceImpl implements FlightSeatDetailsServiceI
{
	@Inject
	private FlightSeatDetailsRepository repository;

	@Override
	public List<FlightSeatDetails> findFlightSeatsByFlightAndAvailSeatsAndDepTime(List<FlightDetails> flightList,
			Date preferredTime)
	{
		Date startingTime = DateUtils.truncate(preferredTime, Calendar.DATE);
		Date endTime = DateUtils.addMilliseconds(DateUtils.ceiling(preferredTime, Calendar.DATE), -1);
		return repository.findByFlightInAndDepartureDateBetween(flightList, startingTime, endTime);
	}
}