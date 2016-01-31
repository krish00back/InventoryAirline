package com.frugalbin.inventory.airline.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frugalbin.inventory.airline.models.AirportDetails;
import com.frugalbin.inventory.airline.models.City;

public interface AirportDetailsRepository extends JpaRepository<AirportDetails, Long>
{
	List<AirportDetails> findByAirportCity(City airportCity);
}
