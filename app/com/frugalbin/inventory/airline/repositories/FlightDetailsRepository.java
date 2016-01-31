package com.frugalbin.inventory.airline.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frugalbin.inventory.airline.models.AirportDetails;
import com.frugalbin.inventory.airline.models.FlightDetails;

public interface FlightDetailsRepository extends JpaRepository<FlightDetails, Long>
{
	List<FlightDetails> findByFromAirportInAndToAirportIn(List<AirportDetails> fromAirportList,
			List<AirportDetails> toAirportList);
}
