package com.frugalbin.inventory.airline.udchalo.dto.response;

import com.frugalbin.inventory.airline.enums.Cabins;
import com.frugalbin.inventory.airline.enums.PassengerType;

public class PassengerFares
{
	private PassengerType passengerType;
	private int quantity;
	private double baseFare;
	private TaxesBean[] taxes;
	private double totalTax;
	private double totalFare;
	private String[] fareBasicCodes;
	private String[] serviceClasses;
	private Cabins[] cabins;

	public PassengerType getPassengerType()
	{
		return passengerType;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public double getBaseFare()
	{
		return baseFare;
	}

	public TaxesBean[] getTaxes()
	{
		return taxes;
	}

	public double getTotalTax()
	{
		return totalTax;
	}

	public double getTotalFare()
	{
		return totalFare;
	}

	public String[] getFareBasicCodes()
	{
		return fareBasicCodes;
	}

	public String[] getServiceClasses()
	{
		return serviceClasses;
	}

	public Cabins[] getCabins()
	{
		return cabins;
	}
}
