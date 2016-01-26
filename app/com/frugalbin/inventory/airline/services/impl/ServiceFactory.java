package com.frugalbin.inventory.airline.services.impl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.frugalbin.inventory.airline.services.AirlineConnectionDetailsServiceI;
import com.frugalbin.inventory.airline.services.AirportDetailsServiceI;
import com.frugalbin.inventory.airline.services.BookingDetailsServiceI;
import com.frugalbin.inventory.airline.services.CityServiceI;
import com.frugalbin.inventory.airline.services.FlightDetailsServiceI;
import com.frugalbin.inventory.airline.services.FlightSeatDetailsServiceI;
import com.frugalbin.inventory.airline.services.PassengerBookingMapperServiceI;
import com.frugalbin.inventory.airline.services.PassengerDetailsServiceI;

@Named
@Singleton
public class ServiceFactory
{
	@Inject
	private AirlineConnectionDetailsServiceI connectionDetailsService;

	@Inject
	private AirportDetailsServiceI airportService;

	@Inject
	private BookingDetailsServiceI bookingService;

	@Inject
	private CityServiceI cityService;

	@Inject
	private FlightDetailsServiceI flighService;

	@Inject
	private FlightSeatDetailsServiceI flightSeatService;

	@Inject
	private PassengerBookingMapperServiceI passengerBookingMapperService;

	@Inject
	private PassengerDetailsServiceI passengerService;

	public AirlineConnectionDetailsServiceI getAirlineConnectionDetailsService()
	{
		return connectionDetailsService;
	}

	public void setConnectionDetailsService(AirlineConnectionDetailsServiceI connectionDetailsService)
	{
		this.connectionDetailsService = connectionDetailsService;
	}

	public AirportDetailsServiceI getAirportService()
	{
		return airportService;
	}

	public void setAirportService(AirportDetailsServiceI airportService)
	{
		this.airportService = airportService;
	}

	public BookingDetailsServiceI getBookingService()
	{
		return bookingService;
	}

	public void setBookingService(BookingDetailsServiceI bookingService)
	{
		this.bookingService = bookingService;
	}

	public CityServiceI getCityService()
	{
		return cityService;
	}

	public void setCityService(CityServiceI cityService)
	{
		this.cityService = cityService;
	}

	public FlightDetailsServiceI getFlighService()
	{
		return flighService;
	}

	public void setFlighService(FlightDetailsServiceI flighService)
	{
		this.flighService = flighService;
	}

	public FlightSeatDetailsServiceI getFlightSeatService()
	{
		return flightSeatService;
	}

	public void setFlightSeatService(FlightSeatDetailsServiceI flightSeatService)
	{
		this.flightSeatService = flightSeatService;
	}

	public PassengerBookingMapperServiceI getPassengerBookingMapperService()
	{
		return passengerBookingMapperService;
	}

	public void setPassengerBookingMapperService(PassengerBookingMapperServiceI passengerBookingMapperService)
	{
		this.passengerBookingMapperService = passengerBookingMapperService;
	}

	public PassengerDetailsServiceI getPassengerService()
	{
		return passengerService;
	}

	public void setPassengerService(PassengerDetailsServiceI passengerService)
	{
		this.passengerService = passengerService;
	}
}
