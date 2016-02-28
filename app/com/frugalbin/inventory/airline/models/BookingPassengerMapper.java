package com.frugalbin.inventory.airline.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.frugalbin.inventory.airline.utils.Constants;

@Entity
@Table(name = Constants.BOOKING_PASSENGER_MAPPER_TABLE, schema = Constants.INVENTORY_AIRLINE_SCHEMA)
public class BookingPassengerMapper
{
	@EmbeddedId
	private BookingPassengerCompositeKey compositeKey;

	public PassengerDetails getPassenger()
	{
		return compositeKey.getPassenger();
	}

	public BookingDetails getBooking()
	{
		return compositeKey.getBooking();
	}
}
