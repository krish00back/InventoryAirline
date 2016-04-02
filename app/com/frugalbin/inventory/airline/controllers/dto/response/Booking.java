package com.frugalbin.inventory.airline.controllers.dto.response;

import java.util.List;

import com.frugalbin.inventory.airline.udchalo.dto.response.FaresBean;
import com.frugalbin.inventory.airline.udchalo.dto.response.LegBean;

public class Booking
{
	private List<LegBean> legs;
	private FaresBean fare;

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
}
