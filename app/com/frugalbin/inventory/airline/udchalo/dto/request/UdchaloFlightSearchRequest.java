package com.frugalbin.inventory.airline.udchalo.dto.request;

public class UdchaloFlightSearchRequest
{
	private UdchaloFlightSearchBean1 search;
	private String token;

	public UdchaloFlightSearchBean1 getSearch()
	{
		return search;
	}

	public void setSearch(UdchaloFlightSearchBean1 search)
	{
		this.search = search;
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
