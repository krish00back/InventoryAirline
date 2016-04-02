package com.frugalbin.inventory.airline.udchalo.dto.response;

import java.util.Map;

import com.frugalbin.inventory.airline.controllers.dto.request.FlightListSearchRequest;
import com.frugalbin.inventory.airline.controllers.dto.response.CityBean;

public class AirlineSearchResultBean
{
	private UdchaloSearchRequest search;
	private boolean isSuccess;
	private String message;
	private Map<String, String> airlines;
	private Map<String, CityBean> airports;
	private Map<Integer, FaresBean> fares;
	private LegCombinationsBean[] legCombinations;
	private Map<Long, LegBean> onwardLegs;
	private Map<Long, LegBean> returnLegs;

	public UdchaloSearchRequest getSearch()
	{
		return search;
	}

	public void setSearch(UdchaloSearchRequest search)
	{
		this.search = search;
	}

	public void setSuccess(boolean isSuccess)
	{
		this.isSuccess = isSuccess;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public void setAirlines(Map<String, String> airlines)
	{
		this.airlines = airlines;
	}

	public void setAirports(Map<String, CityBean> airports)
	{
		this.airports = airports;
	}

	public void setFares(Map<Integer, FaresBean> fares)
	{
		this.fares = fares;
	}

	public void setLegCombinations(LegCombinationsBean[] legCombinations)
	{
		this.legCombinations = legCombinations;
	}

	public void setReturnLegs(Map<Long, LegBean> returnLegs)
	{
		this.returnLegs = returnLegs;
	}

	public boolean isSuccess()
	{
		return isSuccess;
	}

	public String getMessage()
	{
		return message;
	}

	public Map<String, String> getAirlines()
	{
		return airlines;
	}

	public Map<String, CityBean> getAirports()
	{
		return airports;
	}

	public Map<Integer, FaresBean> getFares()
	{
		return fares;
	}

	public LegCombinationsBean[] getLegCombinations()
	{
		return legCombinations;
	}

	public Map<Long, LegBean> getOnwardLegs()
	{
		return onwardLegs;
	}

	public Map<Long, LegBean> getReturnLegs()
	{
		return returnLegs;
	}

	public void setOnwardLegs(Map<Long, LegBean> onwardLegs)
	{
		this.onwardLegs = onwardLegs;
	}
}
