package com.frugalbin.inventory.airline.udchalo.dto.response;

import java.util.Date;

import com.frugalbin.common.dto.response.inventory.airline.CityBean;
import com.frugalbin.inventory.airline.enums.Cabins;

public class SegmentBean
{
	// CityBean
	private String origin;

	// CityBean
	private CityBean destination;
	private Date depart;
	private Date arrive;
	private int duration;
	private Cabins cabin;
	private String serviceClass;
	private String aircraft;
	private int mileage;
	private int stops;
	private String flightNumber;
	
	// airline code
	private String airline;
	
	private int layover;

	public String getOrigin()
	{
		return origin;
	}

	public CityBean getDestination()
	{
		return destination;
	}

	public Date getDepart()
	{
		return depart;
	}

	public Date getArrive()
	{
		return arrive;
	}

	public int getDuration()
	{
		return duration;
	}

	public Cabins getCabin()
	{
		return cabin;
	}

	public String getServiceClass()
	{
		return serviceClass;
	}

	public String getAircraft()
	{
		return aircraft;
	}

	public int getMileage()
	{
		return mileage;
	}

	public int getStops()
	{
		return stops;
	}

	public String getFlightNumber()
	{
		return flightNumber;
	}

	public String getAirline()
	{
		return airline;
	}

	public int getLayover()
	{
		return layover;
	}
}
