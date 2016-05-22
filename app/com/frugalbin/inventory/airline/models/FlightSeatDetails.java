package com.frugalbin.inventory.airline.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.frugalbin.inventory.airline.utils.Constants;

@Entity
@Table(name = Constants.FLIGHT_SEAT_DETAILS_TABLE, schema = Constants.INVENTORY_AIRLINE_SCHEMA)
public class FlightSeatDetails
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = Constants.ID_COLUMN)
	private Long flightSeatId;

	@ManyToOne
	// @Column(name = Constants.FSD_FLIGHT_ID_COLUMN)
	private FlightDetails flight;

	@Column(name = Constants.FSD_DEPARTURE_TIME_COLUMN)
	@Type(type = Constants.TIMESTAMP_TYPE)
	private Date departureTime;

	@Column(name = Constants.FSD_ARRIVAL_TIME_COLUMN)
	@Type(type = Constants.TIMESTAMP_TYPE)
	private Date arrivalTime;

	@Column(name = Constants.FSD_AVAILABLE_SEATS_COLUMN)
	private Integer availableSeats;

	@Column(name = Constants.FSD_ALLOTTED_SEATS_COLUMN)
	private Integer allottedSeats;

	public Long getFlightSeatId()
	{
		return flightSeatId;
	}

	public void setFlightSeatId(Long flightSeatId)
	{
		this.flightSeatId = flightSeatId;
	}

	public FlightDetails getFlight()
	{
		return flight;
	}

	public void setFlight(FlightDetails flight)
	{
		this.flight = flight;
	}

	public Date getDepartureTime()
	{
		return departureTime;
	}

	public void setDepartureTime(Date departureTime)
	{
		this.departureTime = departureTime;
	}

	public Date getArrivalTime()
	{
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime)
	{
		this.arrivalTime = arrivalTime;
	}

	public Integer getAvailableSeats()
	{
		return availableSeats;
	}

	public void setAvailableSeats(Integer availableSeats)
	{
		this.availableSeats = availableSeats;
	}

	public Integer getAllottedSeats()
	{
		return allottedSeats;
	}

	public void setAllottedSeats(Integer allottedSeats)
	{
		this.allottedSeats = allottedSeats;
	}
}