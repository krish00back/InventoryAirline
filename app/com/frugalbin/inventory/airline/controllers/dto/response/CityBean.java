package com.frugalbin.inventory.airline.controllers.dto.response;


public class CityBean
{
	private String id;
	private String cityName;

	public CityBean(String id, String cityName)
	{
		this.id = id;
		this.cityName = cityName;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getCityName()
	{
		return cityName;
	}

	public void setCityName(String cityName)
	{
		this.cityName = cityName;
	}
}
