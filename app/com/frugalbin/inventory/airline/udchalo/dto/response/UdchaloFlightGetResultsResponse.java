package com.frugalbin.inventory.airline.udchalo.dto.response;

import java.util.Map;

import com.frugalbin.inventory.airline.udchalo.dto.request.UdchaloFlightSearchBean;

public class UdchaloFlightGetResultsResponse
{
	private UdchaloFlightSearchBean search;
	private boolean isSuccess;
	private String message;
	private Map<String, String> airlines;
	private Map<String, CityBean> airports;
	private Map<Long, FaresBean> fares;
	private LegCombinationsBean[] legCombinations;
	private Map<Long, LegBean> onwardLegs;
	private Map<Long, LegBean> returnLegs;

	public UdchaloFlightSearchBean getSearch()
	{
		return search;
	}

	public void setSearch(UdchaloFlightSearchBean search)
	{
		this.search = search;
	}

	public void setIsSuccess(boolean isSuccess)
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

	public void setFares(Map<Long, FaresBean> fares)
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

	public boolean getIsSuccess()
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

	public Map<Long, FaresBean> getFares()
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
