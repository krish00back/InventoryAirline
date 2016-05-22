package com.frugalbin.inventory.airline.udchalo.dto.request;

import java.util.List;

import com.frugalbin.common.dto.request.inventory.airline.PassengerDetailsBean;
import com.frugalbin.common.dto.response.udchalo.FaresBean;
import com.frugalbin.common.dto.response.udchalo.LegBean;

public class SaveBookingBean
{
	private List<LegBean> legs;
	private FaresBean fare;
	private List<PassengerDetailsBean> passengers;
	private SaveBookingSearchBean search;

	public List<LegBean> getLegs()
	{
		return legs;
	}

	public void setLegs(List<LegBean> legs)
	{
		this.legs = legs;
	}

	public FaresBean getFare()
	{
		return fare;
	}

	public void setFare(FaresBean fare)
	{
		this.fare = fare;
	}

	public List<PassengerDetailsBean> getPassengers()
	{
		return passengers;
	}

	public void setPassengers(List<PassengerDetailsBean> passengers)
	{
		this.passengers = passengers;
	}

	public SaveBookingSearchBean getSearch()
	{
		return search;
	}

	public void setSearch(SaveBookingSearchBean search)
	{
		this.search = search;
	}
}
