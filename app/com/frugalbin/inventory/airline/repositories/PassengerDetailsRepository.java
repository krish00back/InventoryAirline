package com.frugalbin.inventory.airline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frugalbin.inventory.airline.models.PassengerDetails;

public interface PassengerDetailsRepository extends JpaRepository<PassengerDetails, Long>
{
}