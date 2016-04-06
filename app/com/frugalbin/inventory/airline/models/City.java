package com.frugalbin.inventory.airline.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.frugalbin.inventory.airline.utils.Constants;

@Entity
@Table(name = Constants.CITIES_TABLE, schema = Constants.INVENTORY_AIRLINE_SCHEMA)
public class City
{
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = Constants.ID_COLUMN)
	private String cityCode;

	@Column(name = Constants.NAME_COLUMN)
	private String cityName;

	@Column(name = Constants.STATE_COLUMN)
	private String cityState;

	public String getCityCode()
	{
		return cityCode;
	}

	public void setCityCode(String cityCode)
	{
		this.cityCode = cityCode;
	}

	public String getCityName()
	{
		return cityName;
	}

	public void setCityName(String cityName)
	{
		this.cityName = cityName;
	}

	public String getCityState()
	{
		return cityState;
	}

	public void setCityState(String cityState)
	{
		this.cityState = cityState;
	}
}
