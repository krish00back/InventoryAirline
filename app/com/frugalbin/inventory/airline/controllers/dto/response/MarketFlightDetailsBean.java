package com.frugalbin.inventory.airline.controllers.dto.response;

import java.util.Date;

public class MarketFlightDetailsBean
{
	private String carrierId;
	private Date departTime;
	private Date arrivalTime;
	private int discount;
	private double pricePerPerson;
	private StopBean stop;

	public String getCarrierId()
	{
		return carrierId;
	}

	public void setCarrierId(String carrierId)
	{
		this.carrierId = carrierId;
	}

	public Date getDepartTime()
	{
		return departTime;
	}

	public void setDepartTime(Date departTime)
	{
		this.departTime = departTime;
	}

	public Date getArrivalTime()
	{
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime)
	{
		this.arrivalTime = arrivalTime;
	}

	public int getDiscount()
	{
		return discount;
	}

	public void setDiscount(int discount)
	{
		this.discount = discount;
	}

	public double getPricePerPerson()
	{
		return pricePerPerson;
	}

	public void setPricePerPerson(double pricePerPerson)
	{
		this.pricePerPerson = pricePerPerson;
	}

	public StopBean getStop()
	{
		return stop;
	}

	public void setStop(StopBean stop)
	{
		this.stop = stop;
	}
}
