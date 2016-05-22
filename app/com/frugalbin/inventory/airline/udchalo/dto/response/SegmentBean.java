package com.frugalbin.inventory.airline.udchalo.dto.response;

import java.text.ParseException;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frugalbin.common.dto.response.inventory.airline.CityBean;
import com.frugalbin.inventory.airline.enums.Cabins;
import com.frugalbin.inventory.airline.utils.Constants;

public class SegmentBean
{
	// CityBean
	private String origin;

	// CityBean
	private String destination;
	private String depart;
	private String arrive;
	private int duration;
	private Cabins cabin;
	private String serviceclass;
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

	public String getDestination()
	{
		return destination;
	}

	@JsonIgnore
	public Date getParsedDepart()
	{
		try
		{
			return Constants.LEG_DATE_FORMAT.parse(depart);
		}
		catch (ParseException e)
		{
			return null;
		}
	}

	public String getDepart()
	{
		return depart;
	}

	@JsonIgnore
	public Date getParsedArrive()
	{
		try
		{
			return Constants.LEG_DATE_FORMAT.parse(arrive);
		}
		catch (ParseException e)
		{
			return null;
		}
	}
	
	public String getArrive()
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

	public String getServiceclass()
	{
		return serviceclass;
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

	public void setOrigin(String origin)
	{
		this.origin = origin;
	}

	public void setDestination(String destination)
	{
		this.destination = destination;
	}

	public void setDepart(String depart)
	{
		this.depart = depart;
	}

	public void setArrive(String arrive)
	{
		this.arrive = arrive;
	}

	public void setDuration(int duration)
	{
		this.duration = duration;
	}

	public void setCabin(Cabins cabin)
	{
		this.cabin = cabin;
	}

	public void setServiceclass(String serviceclass)
	{
		this.serviceclass = serviceclass;
	}

	public void setAircraft(String aircraft)
	{
		this.aircraft = aircraft;
	}

	public void setMileage(int mileage)
	{
		this.mileage = mileage;
	}

	public void setStops(int stops)
	{
		this.stops = stops;
	}

	public void setFlightNumber(String flightNumber)
	{
		this.flightNumber = flightNumber;
	}

	public void setAirline(String airline)
	{
		this.airline = airline;
	}

	public void setLayover(int layover)
	{
		this.layover = layover;
	}
}
