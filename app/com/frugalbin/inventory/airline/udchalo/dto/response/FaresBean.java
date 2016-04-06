package com.frugalbin.inventory.airline.udchalo.dto.response;

public class FaresBean
{
	private long id;
	private PassengerFares[] passengerFares;
	private double baseTotalFare;
	private double totalTax;
	private double totalFare;

	public long getId()
	{
		return id;
	}

	public PassengerFares[] getPassengerFares()
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

	public void setId(long id)
	{
		this.id = id;
	}

	public void setPassengerFares(PassengerFares[] passengerFares)
	{
		this.passengerFares = passengerFares;
	}

	public void setBaseTotalFare(double baseTotalFare)
	{
		this.baseTotalFare = baseTotalFare;
	}

	public void setTotalTax(double totalTax)
	{
		this.totalTax = totalTax;
	}

	public void setTotalFare(double totalFare)
	{
		this.totalFare = totalFare;
	}
}
