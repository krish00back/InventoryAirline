package com.frugalbin.inventory.airline.udchalo.dto.response;

import java.util.List;

import com.frugalbin.common.dto.request.inventory.airline.PassengerDetailsBean;
import com.frugalbin.common.dto.response.udchalo.LegBean;
import com.frugalbin.inventory.airline.udchalo.dto.request.UdchaloFlightSearchBean;

public class FlightBookingBean
{
	private int __v;
	private String _id;
	private FaresBean fare;
	private List<LegBean> legs;
	private List<PassengerDetailsBean> passengers;
	private String pnr;
	private UdchaloFlightSearchBean search;

	public int get__v()
	{
		return __v;
	}

	public void set__v(int __v)
	{
		this.__v = __v;
	}

	public String get_id()
	{
		return _id;
	}

	public void set_id(String _id)
	{
		this._id = _id;
	}

	public FaresBean getFare()
	{
		return fare;
	}

	public void setFare(FaresBean fare)
	{
		this.fare = fare;
	}

	public List<LegBean> getLegs()
	{
		return legs;
	}

	public void setLegs(List<LegBean> legs)
	{
		this.legs = legs;
	}

	public List<PassengerDetailsBean> getPassengers()
	{
		return passengers;
	}

	public void setPassengers(List<PassengerDetailsBean> passengers)
	{
		this.passengers = passengers;
	}

	public String getPnr()
	{
		return pnr;
	}

	public void setPnr(String pnr)
	{
		this.pnr = pnr;
	}

	public UdchaloFlightSearchBean getSearch()
	{
		return search;
	}

	public void setSearch(UdchaloFlightSearchBean search)
	{
		this.search = search;
	}
}
