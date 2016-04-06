package com.frugalbin.inventory.airline.udchalo.dto.request;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.frugalbin.inventory.airline.enums.TripType;

public class UdchaloFlightSearchRequest
{
	/*
	 * "search": {
	    "_id": "56f3da509bbf36cda1cdbeea",
	    "origin": "PNQ",
	    "destination": "DEL",
	    "depart": "2016-04-13T00:00:00.000Z",
	    "returndate": "2016-04-20T00:00:00.000Z",
	    "adults": 1,
	    "infants": 0,
	    "triptype": "oneway",
	    "__v": 0
	},
	 */

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ss.000'Z'");
	
	private String _id;
	private String orgin;
	private String destination;
	private String depart;
	private String returndate;
	private int adults;
	private int infants;
	private TripType tripType;
	private int __v;

	public String get_id()
	{
		return _id;
	}

	public String getOrgin()
	{
		return orgin;
	}

	public String getDestination()
	{
		return destination;
	}

	public String getDepart()
	{
		return depart;
	}
	
	public Date getDepartDate()
	{
		try
		{
			return dateFormat.parse(this.depart);
		}
		catch (ParseException e)
		{
			return null;
		}
	}

	public String getReturndate()
	{
		return returndate;
	}

	public Date getReturnDate()
	{
		try
		{
			return dateFormat.parse(this.returndate);
		}
		catch (ParseException e)
		{
			return null;
		}
	}

	public int getAdults()
	{
		return adults;
	}

	public int getInfants()
	{
		return infants;
	}

	public TripType getTripType()
	{
		return tripType;
	}

	public int get__v()
	{
		return __v;
	}

	public void set_id(String _id)
	{
		this._id = _id;
	}

	public void setOrgin(String orgin)
	{
		this.orgin = orgin;
	}

	public void setDestination(String destination)
	{
		this.destination = destination;
	}

	public void setDepart(String depart)
	{
		this.depart = depart;
	}

	public void setReturndate(String returndate)
	{
		this.returndate = returndate;
	}

	public void setAdults(int adults)
	{
		this.adults = adults;
	}

	public void setInfants(int infants)
	{
		this.infants = infants;
	}

	public void setTripType(TripType tripType)
	{
		this.tripType = tripType;
	}

	public void set__v(int __v)
	{
		this.__v = __v;
	}

	public void setDepart(Date departureDate)
	{
		depart = dateFormat.format(departureDate);
	}
}
