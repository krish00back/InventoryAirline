package com.frugalbin.inventory.airline.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frugalbin.inventory.airline.models.FlightDetails;
import com.frugalbin.inventory.airline.models.FlightSeatDetails;

public interface FlightSeatDetailsRepository extends JpaRepository<FlightSeatDetails, Long>
{
	List<FlightSeatDetails> findByFlightInAndDepartureTimeBetween(List<FlightDetails> flightList, Date startingTime,
			Date endTime);
}