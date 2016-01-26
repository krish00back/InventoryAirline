package com.frugalbin.inventory.airline.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.frugalbin.inventory.airline.models.AirportDetails;
import com.frugalbin.inventory.airline.models.FlightSeatDetails;

public interface AirportDetailsRepository extends JpaRepository<AirportDetails, Long>
{
	@Query("select entity from #{#entityName} entity where entity. = ?1")
	List<AirportDetails> findAirportList(Long cityId);
}
