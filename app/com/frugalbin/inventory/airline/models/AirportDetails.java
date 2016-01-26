package com.frugalbin.inventory.airline.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.frugalbin.inventory.airline.utils.Constants;

@Entity
@Table(name = Constants.AIRPORT_DETAILS_TABLE, schema = Constants.INVENTORY_AIRLINE_SCHEMA)
public class AirportDetails
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = Constants.ID_COLUMN)
	private Long airportId;

	@Column(name = Constants.NAME_COLUMN)
	private String airportName;

	@ManyToOne
//	@Column(name = Constants.AIRPORT_CITY_ID_COLUMN)
	private City airportCity;

	@Column(name = Constants.AIRPORT_ADDRESS_ID_COLUMN)
	private Long addressId;

	public Long getAirportId()
	{
		return airportId;
	}

	public void setAirportId(Long airportId)
	{
		this.airportId = airportId;
	}

	public String getAirportName()
	{
		return airportName;
	}

	public void setAirportName(String airportName)
	{
		this.airportName = airportName;
	}

	public City getAirportCity()
	{
		return airportCity;
	}

	public void setAirportCity(City airportCity)
	{
		this.airportCity = airportCity;
	}

	public Long getAddressId()
	{
		return addressId;
	}

	public void setAddressId(Long addressId)
	{
		this.addressId = addressId;
	}
}
