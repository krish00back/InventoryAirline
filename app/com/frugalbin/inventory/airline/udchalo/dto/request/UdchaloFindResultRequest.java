package com.frugalbin.inventory.airline.udchalo.dto.request;

public class UdchaloFindResultRequest
{
	private String searchId;
	private String token;

	public String getSearchId()
	{
		return searchId;
	}

	public void setSearchId(String searchId)
	{
		this.searchId = searchId;
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
