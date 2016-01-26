package com.frugalbin.inventory.airline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frugalbin.inventory.airline.models.AirlineConnectionDetails;

public interface AirlineConnectionDetailsRepository extends JpaRepository<AirlineConnectionDetails, Long>
{
}
