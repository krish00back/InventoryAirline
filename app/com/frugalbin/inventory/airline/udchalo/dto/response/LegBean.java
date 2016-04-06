package com.frugalbin.inventory.airline.udchalo.dto.response;

import java.util.Date;

import com.frugalbin.common.dto.response.inventory.airline.CityBean;
import com.frugalbin.inventory.airline.enums.Cabins;

public class LegBean
{
	private long Id;

	// CityBean
	private String origin;

	// CityBean
	private String destination;
	private Date depart;
	private Date arrive;
	private int duration;
	private Cabins cabin;
	private String serviceClass;
	private int stops;

	// airline code
	private String airline;

	private SegmentBean[] segments;

	private boolean showDetails = false;
	
	public long getId()
	{
		return Id;
	}

	public String getOrigin()
	{
		return origin;
	}

	public String getDestination()
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

	public int getStops()
	{
		return stops;
	}

	public String getAirline()
	{
		return airline;
	}

	public SegmentBean[] getSegments()
	{
		return segments;
	}

	public boolean isShowDetails()
	{
		return showDetails;
	}

	public void setId(long id)
	{
		Id = id;
	}

	public void setOrigin(String origin)
	{
		this.origin = origin;
	}

	public void setDestination(String destination)
	{
		this.destination = destination;
	}

	public void setDepart(Date depart)
	{
		this.depart = depart;
	}

	public void setArrive(Date arrive)
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

	public void setServiceClass(String serviceClass)
	{
		this.serviceClass = serviceClass;
	}

	public void setStops(int stops)
	{
		this.stops = stops;
	}

	public void setAirline(String airline)
	{
		this.airline = airline;
	}

	public void setSegments(SegmentBean[] segments)
	{
		this.segments = segments;
	}

	public void setShowDetails(boolean showDetails)
	{
		this.showDetails = showDetails;
	}
}
