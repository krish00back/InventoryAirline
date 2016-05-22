package com.frugalbin.inventory.airline.udchalo.dto.response;

public class FaresBean
{
	private int __v;
	private String _id;
	private long id;
	private PassengerFares[] passengerFares;
	private double baseTotalFare;
	private double totalTax;
	private double totalFare;
	
	public int get__v()
	{
		return __v;
	}

	public void set__v(int __v)
	{
		this.__v = __v;
	}

	public String get_id()
	{
		return _id;
	}

	public void set_id(String _id)
	{
		this._id = _id;
	}

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
