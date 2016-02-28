package com.frugalbin.inventory.airline.models;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class BookingPassengerCompositeKey implements Serializable
{
	private static final long serialVersionUID = 6459475033478279087L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PASSENGER_ID")
	private PassengerDetails passenger;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BOOKING_ID")
	private BookingDetails booking;

	public PassengerDetails getPassenger()
	{
		return passenger;
	}

	public void setPassenger(PassengerDetails passenger)
	{
		this.passenger = passenger;
	}

	public BookingDetails getBooking()
	{
		return booking;
	}

	public void setBooking(BookingDetails booking)
	{
		this.booking = booking;
	}
}
