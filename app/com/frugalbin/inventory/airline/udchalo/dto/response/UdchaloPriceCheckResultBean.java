package com.frugalbin.inventory.airline.udchalo.dto.response;

import com.frugalbin.inventory.airline.udchalo.dto.request.UdchaloFlightSearchBean;

public class UdchaloPriceCheckResultBean
{
	private UdchaloFlightSearchBean search;
	private Boolean isSuccess;
	private Boolean isFareChanged;
	private String message;
	private FaresBean fare;

	public UdchaloFlightSearchBean getSearch()
	{
		return search;
	}

	public void setSearch(UdchaloFlightSearchBean search)
	{
		this.search = search;
	}

	public Boolean getIsSuccess()
	{
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess)
	{
		this.isSuccess = isSuccess;
	}

	public Boolean getIsFareChanged()
	{
		return isFareChanged;
	}

	public void setIsFareChanged(Boolean isFareChanged)
	{
		this.isFareChanged = isFareChanged;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public FaresBean getFare()
	{
		return fare;
	}

	public void setFare(FaresBean fare)
	{
		this.fare = fare;
	}
}
