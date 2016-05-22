package com.frugalbin.inventory.airline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frugalbin.inventory.airline.models.FlightBookDetails;

public interface FlightBookDetailsRepository extends JpaRepository<FlightBookDetails, Long>
{
	
}
