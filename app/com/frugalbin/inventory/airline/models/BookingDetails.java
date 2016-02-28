package com.frugalbin.inventory.airline.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.frugalbin.inventory.airline.utils.Constants;

@Entity
@Table(name = Constants.BOOKING_DETAILS_TABLE, schema = Constants.INVENTORY_AIRLINE_SCHEMA)
public class BookingDetails
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = Constants.ID_COLUMN)
	private Long bookingId;

	@Column(name = Constants.BD_AIRLINE_TRANSACTION_ID_COLUMN)
	private String airlineTransactionId;

	@ManyToOne
	private FlightSeatDetails flightSeat;

	@OneToMany(mappedBy = "compositeKey.booking", fetch = FetchType.EAGER)
	private Set<BookingPassengerMapper> compositeKeyList;

	public Long getBookingId()
	{
		return bookingId;
	}

	public void setBookingId(Long bookingId)
	{
		this.bookingId = bookingId;
	}

	public String getAirlineTransactionId()
	{
		return airlineTransactionId;
	}

	public void setAirlineTransactionId(String airlineTransactionId)
	{
		this.airlineTransactionId = airlineTransactionId;
	}

	public FlightSeatDetails getFlightSeat()
	{
		return flightSeat;
	}

	public void setFlightSeat(FlightSeatDetails flightSeat)
	{
		this.flightSeat = flightSeat;
	}
}