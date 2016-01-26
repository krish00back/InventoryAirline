package com.frugalbin.inventory.airline.controllers.rest;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import play.mvc.BodyParser;
import play.mvc.Result;

import com.frugalbin.inventory.airline.caches.CacheManager;
import com.frugalbin.inventory.airline.controllers.base.BaseController;
import com.frugalbin.inventory.airline.controllers.dto.request.FlightListRequest;
import com.frugalbin.inventory.airline.controllers.dto.response.CityBean;
import com.frugalbin.inventory.airline.exceptions.BusinessException;
import com.frugalbin.inventory.airline.integration.InventoryAirlineInterface;

@Named
@Singleton
public class InventoryAirlineController extends BaseController
{
	private static final Logger LOGGER = LoggerFactory.getLogger(InventoryAirlineController.class);

	@Inject
	private InventoryAirlineInterface inventoryAirlineInterface;

	@Inject
	private CacheManager cacheManager;

	@BodyParser.Of(BodyParser.Json.class)
	public Result getCityList()
	{
		LOGGER.info("Get City List started");
		
		List<CityBean> cityBeanList = inventoryAirlineInterface.getCityList();

		LOGGER.info("Get City List ended: " + cityBeanList);
		return convertObjectToJsonResponse(cityBeanList);
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result getFlightSlotDetails()
	{
		try
		{
			FlightListRequest request = convertRequestBodyToObject(request().body(),
					FlightListRequest.class);
			flightSlots = inventoryAirlineInterface.getFlightSlots(request);
		}
		catch (BusinessException e)
		{
			LOGGER.error("Could not create Communication", e);
			return convertObjectToJsonResponse("Comm creation error: " + e.getErrorMessage());
		}

		return convertObjectToJsonResponse("Flight Slot Details: ");
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result bookFlightTicket()
	{
//		try
//		{
//			SendCommunicationRequestDto request = convertRequestBodyToObject(request().body(),
//					SendCommunicationRequestDto.class);
//			communicationInterface.sendCommunication(request);
//		}
//		catch (BusinessException e)
//		{
//			LOGGER.error("Could not create Communication", e);
//			return convertObjectToJsonResponse("Comm creation error: " + e.getErrorMessage());
//		}

		return convertObjectToJsonResponse("Flight Booked: ");
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result refreshDB()
	{
		/*
		 * TODO: handle any exceptions 
		 */
		LOGGER.info("Refresh Cache Service has been called");
		cacheManager.refreshCaches();
		return convertObjectToJsonResponse(Boolean.TRUE);
	}
}
