package com.frugalbin.inventory.airline.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.frugalbin.inventory.airline.utils.Constants;

@Entity
@Table(name = Constants.FLIGHT_DETAILS_TABLE, schema = Constants.INVENTORY_AIRLINE_SCHEMA)
public class FlightDetails
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = Constants.ID_COLUMN)
	private Long flightId;

	@ManyToOne
//	@Column(name = Constants.FD_AIRLINE_ID_COLUMN)
	private AirlineConnectionDetails airlineConnection;

	@Column(name = Constants.FD_FLIGHT_NUMBER_COLUMN)
	private String flightNumber;

	@ManyToOne
//	@Column(name = Constants.FD_FROM_AIRPORT_COLUMN)
	private AirportDetails fromAirport;

	@ManyToOne
//	@Column(name = Constants.FD_TO_AIRPORT_COLUMN)
	private AirportDetails toAirport;

	@Column(name = Constants.FD_TOTAL_SEATS_COLUMN)
	private Integer totalSeats;

	public Long getFlightId()
	{
		return flightId;
	}

	public void setFlightId(Long flightId)
	{
		this.flightId = flightId;
	}

	public AirlineConnectionDetails getAirlineConnection()
	{
		return airlineConnection;
	}

	public void setAirlineConnection(AirlineConnectionDetails airlineConnection)
	{
		this.airlineConnection = airlineConnection;
	}

	public String getFlightNumber()
	{
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber)
	{
		this.flightNumber = flightNumber;
	}

	public AirportDetails getFromAirport()
	{
		return fromAirport;
	}

	public void setFromAirport(AirportDetails fromAirport)
	{
		this.fromAirport = fromAirport;
	}

	public AirportDetails getToAirport()
	{
		return toAirport;
	}

	public void setToAirport(AirportDetails toAirport)
	{
		this.toAirport = toAirport;
	}

	public Integer getTotalSeats()
	{
		return totalSeats;
	}

	public void setTotalSeats(Integer totalSeats)
	{
		this.totalSeats = totalSeats;
	}
}
