package com.frugalbin.inventory.airline.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import com.frugalbin.inventory.airline.models.helpers.ConnectionServiceType;
import com.frugalbin.inventory.airline.models.helpers.ConnectionType;
import com.frugalbin.inventory.airline.utils.Constants;

@Entity
@Table(name = Constants.AIRLINE_CONNECTION_DETAILS_TABLE, schema = Constants.INVENTORY_AIRLINE_SCHEMA)
//@MappedSuperclass
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
	
	@Column(name = Constants.CONNECTION_TYPE_COLUMN)
	@Enumerated(EnumType.STRING)
	private ConnectionType connectionType;
	
	@Column(name = Constants.CONNECTION_SERVICE_TYPE_COLUMN)
	@Enumerated(EnumType.STRING)
	private ConnectionServiceType connectionServiceType;
	
	@Column(name = Constants.SEARCH_JSON_REQUEST_COLUMN)
	private String connectionSearchRequest;

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

	public ConnectionType getConnectionType()
	{
		return connectionType;
	}

	public void setConnectionType(ConnectionType connectionType)
	{
		this.connectionType = connectionType;
	}

	public ConnectionServiceType getConnectionServiceType()
	{
		return connectionServiceType;
	}

	public void setConnectionServiceType(ConnectionServiceType connectionServiceType)
	{
		this.connectionServiceType = connectionServiceType;
	}

	public String getConnectionSearchRequest()
	{
		return connectionSearchRequest;
	}

	public void setConnectionSearchRequest(String connectionSearchRequest)
	{
		this.connectionSearchRequest = connectionSearchRequest;
	}
}
