package com.frugalbin.inventory.airline.udchalo.dto.response;

import java.text.ParseException;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frugalbin.common.dto.response.inventory.airline.CityBean;
import com.frugalbin.inventory.airline.enums.Cabins;
import com.frugalbin.inventory.airline.utils.Constants;

public class LegBean
{
	private long Id;

	// CityBean
	private String origin;

	// CityBean
	private String destination;
	private String depart;
	private String arrive;
	private int duration;
	private Cabins cabin;
	private String serviceclass;
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

	public void setServiceclass(String serviceClass)
	{
		this.serviceclass = serviceClass;
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
