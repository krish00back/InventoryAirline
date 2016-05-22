package com.frugalbin.inventory.airline.udchalo.dto.request;


public class UdchaloSaveBookingRequest
{
	private SaveBookingBean booking;
	private String token;

	public SaveBookingBean getBooking()
	{
		return booking;
	}

	public void setBooking(SaveBookingBean booking)
	{
		this.booking = booking;
	}

	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}
}
