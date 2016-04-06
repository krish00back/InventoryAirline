package com.frugalbin.inventory.airline.controllers.rest;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import play.mvc.BodyParser;
import play.mvc.Result;

import com.frugalbin.common.dto.request.integration.SaveUserRequestBean;
import com.frugalbin.common.dto.request.inventory.airline.FlightSearchRequest;
import com.frugalbin.common.dto.response.authentication.ErrorResponse;
import com.frugalbin.common.dto.response.integration.FlightSearchResponseBean;
import com.frugalbin.common.dto.response.integration.SaveUserResponseBean;
import com.frugalbin.common.dto.response.inventory.airline.PriceCheckRequestBean;
import com.frugalbin.common.dto.response.inventory.airline.PriceCheckResponseBean;
import com.frugalbin.common.dto.response.inventory.airline.SaveBookingResponseBean;
import com.frugalbin.inventory.airline.caches.CacheManager;
import com.frugalbin.inventory.airline.controllers.base.BaseController;
import com.frugalbin.inventory.airline.controllers.dto.request.FlightListSearchRequest;
import com.frugalbin.inventory.airline.controllers.dto.response.CityBean;
import com.frugalbin.inventory.airline.controllers.dto.response.FlightSlotBean;
import com.frugalbin.inventory.airline.controllers.dto.response.Slots;
import com.frugalbin.inventory.airline.exceptions.BusinessException;
import com.frugalbin.inventory.airline.integration.InventoryAirlineInterface;
import com.frugalbin.inventory.airline.models.UserRequests;

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

		Map<String, CityBean> cityBeanMap = inventoryAirlineInterface.getCityMap();

		LOGGER.info("Get City List ended: " + cityBeanMap);
		return convertObjectToJsonResponse(cityBeanMap);
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result getFlight() throws com.frugalbin.common.exceptions.BusinessException
	{
		FlightSlotBean flightSlots;
		try
		{
			FlightListSearchRequest request = convertRequestBodyToObject(request().body(),
					FlightListSearchRequest.class);
			flightSlots = inventoryAirlineInterface.getFlightSlots(request);
		}
		catch (BusinessException e)
		{
			LOGGER.error("Could not create Communication", e);
			return convertObjectToJsonResponse("Comm creation error: " + e.getErrorMessage());
		}

		return convertObjectToJsonResponse("Flight Slot Details: " + flightSlots);
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result getFlightSlotDetails() throws com.frugalbin.common.exceptions.BusinessException
	{
		FlightSlotBean flightSlots;
		try
		{
			FlightListSearchRequest request = convertRequestBodyToObject(request().body(),
					FlightListSearchRequest.class);
			flightSlots = inventoryAirlineInterface.getFlightSlots(request);
		}
		catch (BusinessException e)
		{
			LOGGER.error("Could not create Communication", e);
			return convertObjectToJsonResponse("Comm creation error: " + e.getErrorMessage());
		}

		return convertObjectToJsonResponse("Flight Slot Details: " + flightSlots);
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result bookFlightTicket()
	{
		// try
		// {
		// SendCommunicationRequestDto request =
		// convertRequestBodyToObject(request().body(),
		// SendCommunicationRequestDto.class);
		// communicationInterface.sendCommunication(request);
		// }
		// catch (BusinessException e)
		// {
		// LOGGER.error("Could not create Communication", e);
		// return convertObjectToJsonResponse("Comm creation error: " +
		// e.getErrorMessage());
		// }

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

	@BodyParser.Of(BodyParser.Json.class)
	public Result createUserRequest() throws BusinessException
	{
		SaveUserRequestBean request = convertRequestBodyToObject(request().body(), SaveUserRequestBean.class);
		UserRequests userReq = inventoryAirlineInterface.createUserRequest(request);

		SaveUserResponseBean res = new SaveUserResponseBean();
		res.setRequestStatus(true);
		res.setRequestId(userReq.getRequestId());

		return convertObjectToJsonResponse(res);
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result getRequestedFlightDetails() throws BusinessException,
			com.frugalbin.common.exceptions.BusinessException
	{
		FlightSearchRequest request = convertRequestBodyToObject(request().body(), FlightSearchRequest.class);
		FlightSearchResponseBean flightSearchRes = inventoryAirlineInterface.getRequestedFlightDetails(request);
		return convertObjectToJsonResponse(flightSearchRes);
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result checkFlightPrice()
	{
		PriceCheckResponseBean response;
		try
		{
			PriceCheckRequestBean priceCheckReq = convertRequestBodyToObject(request().body(),
					PriceCheckRequestBean.class);
			response = inventoryAirlineInterface.checkFlightPrice(priceCheckReq);
		}
		catch (BusinessException ex)
		{
			LOGGER.error("Error in checking Price", ex);
			ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode() + "", ex.getErrorMessage());
			return errorObjectToJsonResponse(errorResponse);
		}
		catch (com.frugalbin.common.exceptions.BusinessException ex)
		{
			LOGGER.error("Error in checking Price", ex);
			ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode() + "", ex.getErrorMessage());
			return errorObjectToJsonResponse(errorResponse);
		}
		return convertObjectToJsonResponse(response);
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result saveBooking()
	{
		SaveBookingResponseBean response;
		try
		{
			PriceCheckRequestBean priceCheckReq = convertRequestBodyToObject(request().body(),
					PriceCheckRequestBean.class);
			response = inventoryAirlineInterface.saveBooking(priceCheckReq);
		}
		catch (BusinessException ex)
		{
			LOGGER.error("Error in checking Price", ex);
			ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode() + "", ex.getErrorMessage());
			return errorObjectToJsonResponse(errorResponse);
		}
		catch (com.frugalbin.common.exceptions.BusinessException ex)
		{
			LOGGER.error("Error in checking Price", ex);
			ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode() + "", ex.getErrorMessage());
			return errorObjectToJsonResponse(errorResponse);
		}

		return convertObjectToJsonResponse(response);
	}
}
