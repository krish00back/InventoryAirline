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
@Table(name = Constants.FLIGHT_BOOK_DETAILS_TABLE, schema = Constants.INVENTORY_AIRLINE_SCHEMA)
public class FlightBookDetails
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = Constants.ID_COLUMN)
	private Long id;

	@ManyToOne
	private UserRequests userRequests;

	@Column(name = Constants.FBD_FRUGAL_TXN_ID_COLUMN)
	private String txnId;

	@Column(name = Constants.FBD_UDCHALO_RESPONSE_COLUMN, columnDefinition = "text")
	private String udchaloFlightResponse;

	public UserRequests getUserRequests()
	{
		return userRequests;
	}

	public void setUserRequests(UserRequests userRequests)
	{
		this.userRequests = userRequests;
	}

	public String getTxnId()
	{
		return txnId;
	}

	public void setTxnId(String txnId)
	{
		this.txnId = txnId;
	}

	public String getUdchaloFlightResponse()
	{
		return udchaloFlightResponse;
	}

	public void setUdchaloFlightResponse(String udchaloFlightResponse)
	{
		this.udchaloFlightResponse = udchaloFlightResponse;
	}
}
