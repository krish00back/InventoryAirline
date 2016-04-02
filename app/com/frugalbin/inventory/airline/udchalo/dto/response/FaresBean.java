package com.frugalbin.inventory.airline.udchalo.dto.response;

public class FaresBean
{
	private long id;
	private PassengerFares passengerFares;
	private double baseTotalFare;
	private double totalTax;
	private double totalFare;

	public long getId()
	{
		return id;
	}

	public PassengerFares getPassengerFares()
	{
		return passengerFares;
	}

	public double getBaseTotalFare()
	{
		return baseTotalFare;
	}

	public double getTotalTax()
	{
		return totalTax;
	}

	public double getTotalFare()
	{
		return totalFare;
	}
}
