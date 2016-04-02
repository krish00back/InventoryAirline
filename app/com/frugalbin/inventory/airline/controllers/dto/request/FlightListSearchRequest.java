package com.frugalbin.inventory.airline.controllers.dto.request;

import java.util.Date;

import com.frugalbin.inventory.airline.enums.TripType;

public class FlightListSearchRequest
{
	private String fromCityId;
	private String toCityId;
	private Date depart;
	private Date arrival;
	private Integer numOfAdults;
	private Integer numOfInfants;
	private TripType tripType = TripType.ONE_WAY;
	private Date preferredTime;
	private Integer numberOfTravellers;
	private String slotBreakupType = "THREE_DAYS";

	public String getFromCityId()
	{
		return fromCityId;
	}

	public void setFromCityId(String fromCityId)
	{
		this.fromCityId = fromCityId;
	}

	public String getToCityId()
	{
		return toCityId;
	}

	public void setToCityId(String toCityId)
	{
		this.toCityId = toCityId;
	}

	public Date getPreferredTime()
	{
		return preferredTime;
	}

	public void setPreferredTime(Date preferredTime)
	{
		this.preferredTime = preferredTime;
	}

	public Integer getNumberOfTravellers()
	{
		return numberOfTravellers;
	}

	public void setNumberOfTravellers(Integer numberOfTravellers)
	{
		this.numberOfTravellers = numberOfTravellers;
	}

	public String getSlotBreakupType()
	{
		return slotBreakupType;
	}

	public void setSlotBreakupType(String slotBreakupType)
	{
		this.slotBreakupType = slotBreakupType;
	}

	public Date getDepart()
	{
		return depart;
	}

	public void setDepart(Date depart)
	{
		this.depart = depart;
	}

	public Date getArrival()
	{
		return arrival;
	}

	public void setArrival(Date arrival)
	{
		this.arrival = arrival;
	}

	public Integer getNumOfAdults()
	{
		return numOfAdults;
	}

	public void setNumOfAdults(Integer numOfAdults)
	{
		this.numOfAdults = numOfAdults;
	}

	public Integer getNumOfInfants()
	{
		return numOfInfants;
	}

	public void setNumOfInfants(Integer numOfInfants)
	{
		this.numOfInfants = numOfInfants;
	}

	public TripType getTripType()
	{
		return tripType;
	}

	public void setTripType(TripType tripType)
	{
		this.tripType = tripType;
	}
}
