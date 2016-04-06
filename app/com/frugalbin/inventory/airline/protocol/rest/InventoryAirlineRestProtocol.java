package com.frugalbin.inventory.airline.protocol.rest;

import com.frugalbin.common.rest.client.RestClientProtocolInterface;
import com.frugalbin.common.rest.client.RequestType;

public enum InventoryAirlineRestProtocol implements RestClientProtocolInterface
{
	UDCHALO_SEARCH_REQUEST(RequestType.POST, InventoryAirlineRestProtocol.UDCHALO_CONTEXT_URL + "search"),
	UDCHALO_PRICE_CHECK_REQUEST(RequestType.POST, InventoryAirlineRestProtocol.UDCHALO_CONTEXT_URL + "priceCheck"),
	UDCHALO_SAVE_BOOKING_REQUEST(RequestType.POST, InventoryAirlineRestProtocol.UDCHALO_CONTEXT_URL + "saveBooking"),
	UDCHALO_BOOK_REQUEST(RequestType.POST, InventoryAirlineRestProtocol.UDCHALO_CONTEXT_URL + "book"),
	;

	private static final String URL_PATH_SEPARATOR = "/";

	private static final String UDCHALO_CONTEXT_URL = "http://localhost:9001" + URL_PATH_SEPARATOR;

	private final String url;

	private RequestType reqType;

	private String[] params;

	private InventoryAirlineRestProtocol(RequestType reqType, String url, String... params)
	{
		this.reqType = reqType;
		this.url = url;
		this.params = params;
	}

	public String getUrl()
	{
		return url;
	}

	public RequestType getReqType()
	{
		return reqType;
	}

	public String[] getParams()
	{
		return params;
	}

	@Override
	public String getName()
	{
		return this.name();
	}
}
