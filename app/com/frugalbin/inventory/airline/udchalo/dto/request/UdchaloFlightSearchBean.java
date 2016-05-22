package com.frugalbin.inventory.airline.udchalo.dto.request;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frugalbin.inventory.airline.enums.TripType;

public class UdchaloFlightSearchBean
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
	private String origin;
	private String destination;
	private String depart;
	private String returndate;
	private int adults;
	private int infants;
	private TripType triptype;
	private int __v;
	private String token;

	public String get_id()
	{
		return _id;
	}

	public String getOrigin()
	{
		return origin;
	}

	public String getDestination()
	{
		return destination;
	}

	public String getDepart()
	{
		return depart;
	}
	
	@JsonIgnore
	public Date getParsedDepart()
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

	public String getReturnDate()
	{
		return returndate;
	}

	@JsonIgnore
	public Date getParsedReturnDate()
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

	public TripType getTriptype()
	{
		return triptype;
	}

	public int get__v()
	{
		return __v;
	}

	public void set_id(String _id)
	{
		this._id = _id;
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

	public void setTriptype(TripType triptype)
	{
		this.triptype = triptype;
	}

	public void set__v(int __v)
	{
		this.__v = __v;
	}

	@JsonIgnore
	public void setDepartDate(Date departureDate)
	{
		depart = dateFormat.format(departureDate);
	}

	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}
}
