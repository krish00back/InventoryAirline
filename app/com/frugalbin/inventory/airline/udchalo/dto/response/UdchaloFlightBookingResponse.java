package com.frugalbin.inventory.airline.udchalo.dto.response;

import java.util.Map;

public class UdchaloFlightBookingResponse
{
	private Map<String, String> airlines;
	private Map<String, CityBean> airports;
	private FlightBookingBean booking;
	private Boolean isFareChanged;
	private Boolean success;
	private String message;

	public Map<String, String> getAirlines()
	{
		return airlines;
	}

	public void setAirlines(Map<String, String> airlines)
	{
		this.airlines = airlines;
	}

	public Map<String, CityBean> getAirports()
	{
		return airports;
	}

	public void setAirports(Map<String, CityBean> airports)
	{
		this.airports = airports;
	}

	public FlightBookingBean getBooking()
	{
		return booking;
	}

	public void setBooking(FlightBookingBean booking)
	{
		this.booking = booking;
	}

	public Boolean getIsFareChanged()
	{
		return isFareChanged;
	}

	public void setIsFareChanged(Boolean isFareChanged)
	{
		this.isFareChanged = isFareChanged;
	}

	public Boolean getSuccess()
	{
		return success;
	}

	public void setSuccess(Boolean success)
	{
		this.success = success;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}
}
