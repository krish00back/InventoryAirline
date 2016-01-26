package com.frugalbin.inventory.airline.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.frugalbin.inventory.airline.utils.Constants;

@Entity
@Table(name = Constants.AIRLINE_CONNECTION_DETAILS_TABLE, schema = Constants.INVENTORY_AIRLINE_SCHEMA)
public class AirlineConnectionDetails
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = Constants.ID_COLUMN)
	private Long airlineConnectionId;

	@Column(name = Constants.NAME_COLUMN)
	private String airlineName;

	@Column(name = Constants.ACD_CONNECTION_URL_COLUMN)
	private String connectionUrl;

	@Column(name = Constants.ACD_USERNAME_COLUMN)
	private String username;

	@Column(name = Constants.ACD_PASSWORD_COLUMN)
	private String password;

	public Long getAirlineConnectionId()
	{
		return airlineConnectionId;
	}

	public void setAirlineConnectionId(Long airlineConnectionId)
	{
		this.airlineConnectionId = airlineConnectionId;
	}

	public String getAirlineName()
	{
		return airlineName;
	}

	public void setAirlineName(String airlineName)
	{
		this.airlineName = airlineName;
	}

	public String getConnectionUrl()
	{
		return connectionUrl;
	}

	public void setConnectionUrl(String connectionUrl)
	{
		this.connectionUrl = connectionUrl;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
}
