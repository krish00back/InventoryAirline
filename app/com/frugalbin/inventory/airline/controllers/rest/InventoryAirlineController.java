package com.frugalbin.inventory.airline.controllers.rest;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import play.mvc.BodyParser;
import play.mvc.Result;

import com.frugalbin.common.dto.request.integration.SaveUserRequestBean;
import com.frugalbin.common.dto.request.integration.UserDetailsBean;
import com.frugalbin.common.dto.request.inventory.airline.FlightBookingRequest;
import com.frugalbin.common.dto.request.inventory.airline.FlightSearchRequest;
import com.frugalbin.common.dto.response.authentication.ErrorResponse;
import com.frugalbin.common.dto.response.integration.FlightSearchResponseBean;
import com.frugalbin.common.dto.response.integration.SaveUserResponseBean;
import com.frugalbin.common.dto.response.inventory.airline.FlightBookingResponse;
import com.frugalbin.common.dto.response.inventory.airline.PriceCheckRequestBean;
import com.frugalbin.common.dto.response.inventory.airline.PriceCheckResponseBean;
import com.frugalbin.common.dto.response.inventory.airline.SaveBookingResponseBean;
import com.frugalbin.inventory.airline.caches.CacheManager;
import com.frugalbin.inventory.airline.controllers.base.BaseController;
import com.frugalbin.inventory.airline.controllers.dto.response.CityBean;
import com.frugalbin.inventory.airline.exceptions.BusinessException;
import com.frugalbin.inventory.airline.integration.InventoryAirlineInterface;
import com.frugalbin.inventory.airline.models.UserRequests;
import com.frugalbin.inventory.airline.udchalo.dto.response.UdchaloFlightBookingResponse;

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

	// @BodyParser.Of(BodyParser.Json.class)
	// public Result getFlight() throws
	// com.frugalbin.common.exceptions.BusinessException
	// {
	// FlightSlotBean flightSlots;
	// try
	// {
	// FlightListSearchRequest request =
	// convertRequestBodyToObject(request().body(),
	// FlightListSearchRequest.class);
	// flightSlots = inventoryAirlineInterface.getFlightSlots(request);
	// }
	// catch (BusinessException e)
	// {
	// LOGGER.error("Could not create Communication", e);
	// return convertObjectToJsonResponse("Comm creation error: " +
	// e.getErrorMessage());
	// }
	//
	// return convertObjectToJsonResponse("Flight Slot Details: " +
	// flightSlots);
	// }
	//
	// @BodyParser.Of(BodyParser.Json.class)
	// public Result getFlightSlotDetails() throws
	// com.frugalbin.common.exceptions.BusinessException
	// {
	// FlightSlotBean flightSlots;
	// try
	// {
	// FlightListSearchRequest request =
	// convertRequestBodyToObject(request().body(),
	// FlightListSearchRequest.class);
	// flightSlots = inventoryAirlineInterface.getFlightSlots(request);
	// }
	// catch (BusinessException e)
	// {
	// LOGGER.error("Could not create Communication", e);
	// return convertObjectToJsonResponse("Comm creation error: " +
	// e.getErrorMessage());
	// }
	//
	// return convertObjectToJsonResponse("Flight Slot Details: " +
	// flightSlots);
	// }

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
	public Result createUserRequest()
	{
		LOGGER.info("Starting Creation of User Request.");
		SaveUserRequestBean request;
		SaveUserResponseBean res = new SaveUserResponseBean();

		try
		{
			request = convertRequestBodyToObject(request().body(), SaveUserRequestBean.class);
			UserRequests userReq = inventoryAirlineInterface.createUserRequest(request);

			res.setRequestStatus(true);
			res.setRequestId(userReq.getRequestId());
		}
		catch (BusinessException e)
		{
			LOGGER.error("Unknown Error in creating user request", e);
			res.setRequestStatus(false);
			res.setFailureReason(e.getErrorMessage());
		}
		catch (com.frugalbin.common.exceptions.BusinessException e)
		{
			LOGGER.error("Unknown Error in creating user request", e);
			res.setRequestStatus(false);
			res.setFailureReason(e.getErrorMessage());
		}
		catch (Exception e)
		{
			LOGGER.error("Unknown Error in creating user request", e);
			ErrorResponse errorResponse = new ErrorResponse(9999 + "",
					"Unknown Error occured, please contact administrator.");
			return errorObjectToJsonResponse(errorResponse);
		}

		LOGGER.info("Completed Creation of User Request.");
		return convertObjectToJsonResponse(res);
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result updateUserRequest()
	{
		LOGGER.info("Starting Creation of User Request.");
		SaveUserRequestBean request;
		SaveUserResponseBean res = new SaveUserResponseBean();

		try
		{
			request = convertRequestBodyToObject(request().body(), SaveUserRequestBean.class);
			UserRequests userReq = inventoryAirlineInterface.createUserRequest(request);

			res.setRequestStatus(true);
			res.setRequestId(userReq.getRequestId());
		}
		catch (BusinessException e)
		{
			LOGGER.error("Unknown Error in creating user request", e);
			res.setRequestStatus(false);
			res.setFailureReason(e.getErrorMessage());
		}
		catch (com.frugalbin.common.exceptions.BusinessException e)
		{
			LOGGER.error("Unknown Error in creating user request", e);
			res.setRequestStatus(false);
			res.setFailureReason(e.getErrorMessage());
		}
		catch (Exception e)
		{
			LOGGER.error("Unknown Error in creating user request", e);
			ErrorResponse errorResponse = new ErrorResponse(9999 + "",
					"Unknown Error occured, please contact administrator.");
			return errorObjectToJsonResponse(errorResponse);
		}

		LOGGER.info("Completed Creation of User Request.");
		return convertObjectToJsonResponse(res);
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result getUserDetails(String requestId)
	{
		LOGGER.info("Starting Getting User Details.");
		UserDetailsBean userDetailsBean = null;

		try
		{
			userDetailsBean = inventoryAirlineInterface.getUserDetails(requestId);
		}
		catch (com.frugalbin.common.exceptions.BusinessException e)
		{
			LOGGER.error("Unknown Error in getting user details", e);
			ErrorResponse errorResponse = new ErrorResponse(1001 + "",
					"Unknown Error occured, please contact administrator.");
			return errorObjectToJsonResponse(errorResponse);
		}
		catch (Exception e)
		{
			LOGGER.error("Unknown Error in getting user details", e);
			ErrorResponse errorResponse = new ErrorResponse(9999 + "",
					"Unknown Error occured, please contact administrator.");
			return errorObjectToJsonResponse(errorResponse);
		}

		LOGGER.info("Completed User Details Retrieval.");
		return convertObjectToJsonResponse(userDetailsBean);
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result getRequestedFlightDetails()
	{
		FlightSearchRequest request;
		FlightSearchResponseBean flightSearchRes;

		try
		{
			request = convertRequestBodyToObject(request().body(), FlightSearchRequest.class);
			LOGGER.error("Get Requested Flight Details Request: " + request);
			flightSearchRes = inventoryAirlineInterface.getRequestedFlightDetails(request);
			LOGGER.error("Flight Search Response: " + flightSearchRes);
		}
		catch (BusinessException e)
		{
			LOGGER.error("Error in getting requested Price", e);
			ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode() + "", e.getErrorMessage());
			return errorObjectToJsonResponse(errorResponse);
		}
		catch (com.frugalbin.common.exceptions.BusinessException e)
		{
			LOGGER.error("Error in getting requested Price", e);
			ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode() + "", e.getErrorMessage());
			return errorObjectToJsonResponse(errorResponse);
		}
		catch (Exception e)
		{
			LOGGER.error("Unknown Error in getting requested Price", e);
			ErrorResponse errorResponse = new ErrorResponse(9999 + "",
					"Unknown Error occured, please contact administrator.");
			return errorObjectToJsonResponse(errorResponse);
		}

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
		catch (Exception e)
		{
			LOGGER.error("Unknown Error in checking Price", e);
			ErrorResponse errorResponse = new ErrorResponse(9999 + "",
					"Unknown Error occured, please contact administrator.");
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
			LOGGER.error("Error in saving Booking", ex);
			response = new SaveBookingResponseBean();
			response.setSuccess(false);
			response.setFailureMsg(ex.getErrorCode() + ": " + ex.getErrorMessage());
		}
		catch (com.frugalbin.common.exceptions.BusinessException ex)
		{
			LOGGER.error("Error in saving Booking", ex);
			response = new SaveBookingResponseBean();
			response.setSuccess(false);
			response.setFailureMsg(ex.getErrorCode() + ": " + ex.getErrorMessage());
		}
		catch (Exception e)
		{
			LOGGER.error("Unknown Error in saving Booking", e);
			response = new SaveBookingResponseBean();
			response.setSuccess(false);
			response.setFailureMsg(9999 + ": " + "Unknown Error occured, please contact administrator.");
		}

		return convertObjectToJsonResponse(response);
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result bookFlight()
	{
		FlightBookingResponse response;

		try
		{
			FlightBookingRequest flightBookingRequest;
			flightBookingRequest = convertRequestBodyToObject(request().body(), FlightBookingRequest.class);
			response = inventoryAirlineInterface.bookFlight(flightBookingRequest);
		}
		catch (com.frugalbin.common.exceptions.BusinessException ex)
		{
			LOGGER.error("Error in saving Booking", ex);
			response = new FlightBookingResponse();
			response.setIsSuccess(false);
			response.setFailureMessage(ex.getErrorCode() + ": " + ex.getErrorMessage());
		}
		catch (BusinessException ex)
		{
			LOGGER.error("Error in saving Booking", ex);
			response = new FlightBookingResponse();
			response.setIsSuccess(false);
			response.setFailureMessage(ex.getErrorCode() + ": " + ex.getErrorMessage());
		}
		catch (Exception e)
		{
			LOGGER.error("Unknown Error in saving Booking", e);
			response = new FlightBookingResponse();
			response.setIsSuccess(false);
			response.setFailureMessage(9999 + ": " + "Unknown Error occured, please contact administrator.");
		}

		return convertObjectToJsonResponse(response);
	}
}
