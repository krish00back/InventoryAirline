package com.frugalbin.inventory.airline.controllers.dto.response;

import com.frugalbin.inventory.airline.models.City;

public class CityBean
{
	private Long cityId;
	private String cityName;

	public CityBean(City city)
	{
		this.cityId = city.getCityId();
		this.cityName = city.getCityName();
	}

	public Long getCityId()
	{
		return cityId;
	}

	public void setCityId(Long cityId)
	{
		this.cityId = cityId;
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
