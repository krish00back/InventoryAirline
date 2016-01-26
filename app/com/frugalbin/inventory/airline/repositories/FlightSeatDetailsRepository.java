package com.frugalbin.inventory.airline.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.frugalbin.inventory.airline.models.FlightSeatDetails;

public interface FlightSeatDetailsRepository extends JpaRepository<FlightSeatDetails, Long>
{
	@Query("select entity from #{#entityName} entity where entity.lastname = ?1")
	List<FlightSeatDetails> findFlightList();
}