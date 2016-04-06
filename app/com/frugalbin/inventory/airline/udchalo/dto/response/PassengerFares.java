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

	public void setPassengerType(PassengerType passengerType)
	{
		this.passengerType = passengerType;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public void setBaseFare(double baseFare)
	{
		this.baseFare = baseFare;
	}

	public void setTaxes(TaxesBean[] taxes)
	{
		this.taxes = taxes;
	}

	public void setTotalTax(double totalTax)
	{
		this.totalTax = totalTax;
	}

	public void setTotalFare(double totalFare)
	{
		this.totalFare = totalFare;
	}

	public void setFareBasicCodes(String[] fareBasicCodes)
	{
		this.fareBasicCodes = fareBasicCodes;
	}

	public void setServiceClasses(String[] serviceClasses)
	{
		this.serviceClasses = serviceClasses;
	}

	public void setCabins(Cabins[] cabins)
	{
		this.cabins = cabins;
	}
}
