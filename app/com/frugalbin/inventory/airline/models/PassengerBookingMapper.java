package com.frugalbin.inventory.airline.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.frugalbin.inventory.airline.utils.Constants;

//@Entity
//@Table(name = Constants.PASSENGER_DETAILS_TABLE, schema = Constants.INVENTORY_AIRLINE_SCHEMA)
@Embeddable
public class PassengerBookingMapper
{
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = Constants.ID_COLUMN)
//	private Long mapperId;

	@OneToOne
//	@Column(name = Constants.PBM_PASSENGER_ID_COLUMN)
	private PassengerDetails passenger;

	@ManyToOne
//	@Column(name = Constants.PBM_BOOKING_ID_COLUMN)
	private BookingDetails booking;

	public Long getMapperId()
	{
		return mapperId;
	}

	public void setMapperId(Long mapperId)
	{
		this.mapperId = mapperId;
	}

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
