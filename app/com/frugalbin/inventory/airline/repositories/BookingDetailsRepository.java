package com.frugalbin.inventory.airline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frugalbin.inventory.airline.models.BookingDetails;

public interface BookingDetailsRepository extends JpaRepository<BookingDetails, Long>
{
}