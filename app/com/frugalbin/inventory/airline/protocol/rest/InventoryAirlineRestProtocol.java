package com.frugalbin.inventory.airline.protocol.rest;

import com.frugalbin.common.rest.client.RestClientProtocolInterface;
import com.frugalbin.common.rest.client.RequestType;

public enum InventoryAirlineRestProtocol implements RestClientProtocolInterface
{
	UDCHALO_AUTHENTICATE_REQUEST(RequestType.POST, InventoryAirlineRestProtocol.AUTHENTICATION_URL + "authenticate"),
	UDCHALO_SAVE_SEARCH_REQUEST(RequestType.POST, InventoryAirlineRestProtocol.AIR_URL + "search"),
	UDCHALO_FIND_RESULT_REQUEST(RequestType.POST, InventoryAirlineRestProtocol.AIR_URL + "results"),
	UDCHALO_PRICE_CHECK_REQUEST(RequestType.POST, InventoryAirlineRestProtocol.AIR_URL + "price"),
	UDCHALO_SAVE_BOOKING_REQUEST(RequestType.POST, InventoryAirlineRestProtocol.AIR_URL + "savebooking"),
	UDCHALO_BOOK_REQUEST(RequestType.POST, InventoryAirlineRestProtocol.AIR_URL + "book"),
	;

	private static final String URL_PATH_SEPARATOR = "/";

	private static final String UDCHALO_CONTEXT_URL = "http://apis-9658ca71.82e35578.svc.dockerapp.io:8080" + URL_PATH_SEPARATOR;
	
	private static final String AUTHENTICATION_URL = UDCHALO_CONTEXT_URL + "app" + URL_PATH_SEPARATOR;
	
	private static final String AIR_URL = UDCHALO_CONTEXT_URL + "air" + URL_PATH_SEPARATOR;

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
