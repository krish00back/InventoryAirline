package com.frugalbin.inventory.airline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frugalbin.inventory.airline.models.BookingPassengerMapper;

public interface PassengerBookingMapperRepository extends JpaRepository<BookingPassengerMapper, Long>
{
}
