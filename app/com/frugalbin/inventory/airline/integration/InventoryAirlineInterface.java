package com.frugalbin.inventory.airline.integration;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.frugalbin.common.dto.request.integration.SaveUserRequestBean;
import com.frugalbin.common.dto.request.inventory.airline.FlightSearchRequest;
import com.frugalbin.common.dto.response.integration.FlightSearchResponseBean;
import com.frugalbin.common.dto.response.integration.FlightSearchStatus;
import com.frugalbin.common.dto.response.inventory.airline.PriceCheckRequestBean;
import com.frugalbin.common.dto.response.inventory.airline.PriceCheckResponseBean;
import com.frugalbin.common.dto.response.inventory.airline.SaveBookingResponseBean;
import com.frugalbin.common.exceptions.BusinessException;
import com.frugalbin.inventory.airline.caches.CityCache;
import com.frugalbin.inventory.airline.controllers.dto.request.FlightListSearchRequest;
import com.frugalbin.inventory.airline.controllers.dto.response.CityBean;
import com.frugalbin.inventory.airline.controllers.dto.response.FlightSlotBean;
import com.frugalbin.inventory.airline.enums.TripType;
import com.frugalbin.inventory.airline.models.City;
import com.frugalbin.inventory.airline.models.UserRequests;
import com.frugalbin.inventory.airline.services.impl.ServiceFactory;
import com.frugalbin.inventory.airline.udchalo.dto.request.UdchaloFlightSearchRequest;
import com.frugalbin.inventory.airline.udchalo.dto.response.LegBean;
import com.frugalbin.inventory.airline.udchalo.dto.response.LegCombinationsBean;
import com.frugalbin.inventory.airline.udchalo.dto.response.UdchaloFlightSearchResultBean;
import com.frugalbin.inventory.airline.udchalo.dto.response.UdchaloPriceCheckResultBean;

@Named
@Singleton
public class InventoryAirlineInterface
{
	@Inject
	private ServiceFactory serviceFactory;

	private SecureRandom random = new SecureRandom();

	public Map<String, CityBean> getCityMap()
	{
		List<City> cityList = CityCache.getInstance().getCityList();
		return BeanConverter.convertCityListObject(cityList);
	}

	public FlightSlotBean getFlightSlots(FlightListSearchRequest request) throws BusinessException
	{
		// TODO: check from and to city ids cannot be same
		if (request.getFromCityId().equals(request.getToCityId()))
		{
			throw new BusinessException(1001, "From and to city ids cannot be same");
		}

		UdchaloFlightSearchResultBean udchaloFlightsDetail = getUdchaloFlights(null);

		sortFlightLegs(udchaloFlightsDetail);

		// get leg's prices
		Map<Long, Double> legPrices = null;//BeanConverter.getLegPrices(udchaloFlightsDetail);

		return BeanConverter.convertFlightSeatDetails(udchaloFlightsDetail, legPrices);
	}

	private UdchaloFlightSearchResultBean getUdchaloFlights(UdchaloFlightSearchRequest udchaloFlightSearchRequest) throws BusinessException
	{
//		Promise<Response> promiseResponse = RestClient.sendRequest(InventoryAirlineRestProtocol.UDCHALO_SEARCH_REQUEST,
//				udchaloFlightSearchRequest);
//		UdchaloFlightSearchResultBean response = Utils.convertJsonNodeToObject(promiseResponse.get(Constants.REST_TIMEOUT)
//				.asJson(), UdchaloFlightSearchResultBean.class);
		
		UdchaloFlightSearchResultBean response = null;
		try
		{
			response = DummyData.getDummyUdchaloFlightSearchResponse();
		}
		catch (ParseException e)
		{
			
		}
		
		return response;
	}

	private void sortFlightLegs(UdchaloFlightSearchResultBean udchaloFlightsDetail)
	{
		Map<Long, LegBean> onwardLegsMap = udchaloFlightsDetail.getOnwardLegs();
		LegCombinationsBean[] legCombinations = udchaloFlightsDetail.getLegCombinations();

		LegComparator comparator = new LegComparator();
		TreeSet<LegBean> onwardLegs = new TreeSet<LegBean>(comparator);

		onwardLegs.addAll(onwardLegsMap.values());

		Map<Long, LegBean> finalOnwardLegsMap = new LinkedHashMap<Long, LegBean>();
		for (LegBean legBean : onwardLegs)
		{
			finalOnwardLegsMap.put(legBean.getId(), legBean);
		}

		udchaloFlightsDetail.setOnwardLegs(finalOnwardLegsMap);
	}

	public UserRequests createUserRequest(SaveUserRequestBean userRequestBean)
	{
		UserRequests userRequest = new UserRequests();

		String requestId;

		while (true)
		{
			requestId = generateRequestId();
			List<UserRequests> userRequestList = serviceFactory.getUserRequestsService().getUserRequests(requestId);
			
			if(userRequestList == null || userRequestList.size() == 0)
			{
				break;
			}
		}

		userRequest.setRequestId(requestId);
		userRequest.setFromCity(CityCache.getInstance().getCity(userRequestBean.getFrom()));
		userRequest.setToCity(CityCache.getInstance().getCity(userRequestBean.getTo()));
		userRequest.setAdultCount(userRequestBean.getPassengers().getAdults());
		userRequest.setInfantCount(userRequestBean.getPassengers().getInfants());
		userRequest.setDepartureDate(userRequestBean.getDepartureDate());
		userRequest.setUserId(userRequestBean.getUserDetails().getUserId());
		userRequest.setPnr(userRequestBean.getPnr());

		serviceFactory.getUserRequestsService().addUserRequests(userRequest);
		return userRequest;
	}

	public String generateRequestId()
	{
		return new BigInteger(130, random).toString(32);
	}
	// TODO: get flight list from db
	// private List<FlightSeatDetails>
	// getAvailableFlighSeatList(FlightListRequest request)
	// {
	// // get from, to City ids
	// City fromCity =
	// serviceFactory.getCityService().findCity(request.getFromCityId());
	// City toCity =
	// serviceFactory.getCityService().findCity(request.getToCityId());
	//
	// // get From, to Airport ids
	// List<AirportDetails> fromAirportList =
	// serviceFactory.getAirportService().findAirportByAirportCity(fromCity);
	// List<AirportDetails> toAirportList =
	// serviceFactory.getAirportService().findAirportByAirportCity(toCity);
	//
	// // get Flight details for from and to airport combinations
	// List<FlightDetails> flightList =
	// serviceFactory.getFlighService().findFlightByFromAirportInAndToAirportIn(
	// fromAirportList, toAirportList);
	// Map<Long, FlightDetails> flightDetailMap =
	// getFlightDetailsIdMap(flightList);
	//
	// // get Flight Seat details using flight details id and date criteria
	// List<FlightSeatDetails> flightSeatList =
	// serviceFactory.getFlightSeatService()
	// .findFlightSeatsByFlightAndAvailSeatsAndDepTime(flightList,
	// request.getPreferredTime());
	// List<FlightSeatDetails> availableFlightSeatList = new
	// ArrayList<FlightSeatDetails>();
	// Integer numOfTravellers = request.getNumberOfTravellers();
	//
	// for (FlightSeatDetails flightSeat : flightSeatList)
	// {
	// if (flightSeat.getAvailableSeats() < numOfTravellers)
	// {
	// continue;
	// }
	//
	// availableFlightSeatList.add(flightSeat);
	// }
	// return availableFlightSeatList;
	// }
	//
	// private Map<Long, FlightDetails>
	// getFlightDetailsIdMap(List<FlightDetails> flightList)
	// {
	// Map<Long, FlightDetails> map = new HashMap<Long, FlightDetails>();
	// for (FlightDetails flightDetails : flightList)
	// {
	// map.put(flightDetails.getFlightId(), flightDetails);
	// }
	//
	// return map;
	// }

	public FlightSearchResponseBean getRequestedFlightDetails(FlightSearchRequest request) throws BusinessException
	{
		List<UserRequests> userRequestList = serviceFactory.getUserRequestsService().getUserRequests(request.getRequestId());
		
		if(userRequestList == null || userRequestList.size() <= 0)
		{
			throw new BusinessException(1001, "User Request is invalid");
		}
		
		UserRequests userRequest = userRequestList.get(0);
		UdchaloFlightSearchRequest udchaloFlightSearchRequest = new UdchaloFlightSearchRequest();
		udchaloFlightSearchRequest.setOrgin(userRequest.getFromCity().getCityCode());
		udchaloFlightSearchRequest.setDestination(userRequest.getToCity().getCityCode());
		udchaloFlightSearchRequest.setDepart(userRequest.getDepartureDate());
		udchaloFlightSearchRequest.setAdults(userRequest.getAdultCount());
		udchaloFlightSearchRequest.setInfants(userRequest.getInfantCount());
		udchaloFlightSearchRequest.setTripType(TripType.ONE_WAY);
		
		UdchaloFlightSearchResultBean udchaloFlightSearchResult = getUdchaloFlights(udchaloFlightSearchRequest);
		
		if(!udchaloFlightSearchResult.isSuccess())
		{
			throw new BusinessException(1001, "Flight not available with the request or got sold out");
		}
		
		FlightSearchResponseBean flightSearchResponse = BeanConverter.convertFlightSearchResponse(udchaloFlightSearchResult);
		
		FlightSearchStatus status = new FlightSearchStatus();
		status.setIsValid(true);
		flightSearchResponse.setStatus(status);
		
		flightSearchResponse.setSearchRequest(BeanConverter.getSaveUserSearchRequest(userRequest));
		
		return flightSearchResponse;
	}

	public PriceCheckResponseBean checkFlightPrice(PriceCheckRequestBean priceCheckReq) throws BusinessException
	{
		UdchaloPriceCheckResultBean priceCheckResultBean = checkUdchaloFlightPrice(priceCheckReq);
		
		PriceCheckResponseBean priceCheckResponse = new PriceCheckResponseBean();
		priceCheckResponse.setIsFareChanged(priceCheckResultBean.getIsFareChanged());
		priceCheckResponse.setIsSuccess(priceCheckResultBean.getIsSuccess());
		priceCheckResponse.setMessage(priceCheckResultBean.getMessage());
		
		priceCheckReq.getBooking().setFare(BeanConverter.convertFare(priceCheckResultBean.getFare()));
		priceCheckResponse.setPriceCheckRequest(priceCheckReq);
		priceCheckResponse.setPricePerPerson(priceCheckResultBean.getFare().getTotalFare());
		
		return priceCheckResponse;
	}
	
	private UdchaloPriceCheckResultBean checkUdchaloFlightPrice(PriceCheckRequestBean priceCheckReq) throws BusinessException
	{
//		Promise<Response> promiseResponse = RestClient.sendRequest(InventoryAirlineRestProtocol.UDCHALO_PRICE_CHECK_REQUEST,
//				priceCheckReq);
//		UdchaloPriceCheckResultBean response = Utils.convertJsonNodeToObject(promiseResponse.get(Constants.REST_TIMEOUT)
//				.asJson(), UdchaloPriceCheckResultBean.class);
		
		UdchaloPriceCheckResultBean response = null;
		response = DummyData.getDummyUdchaloPriceCheckResponse();
		
		return response;
	}

	public SaveBookingResponseBean saveBooking(PriceCheckRequestBean priceCheckReq) throws BusinessException
	{
		return saveUdchaloBooking(priceCheckReq);
	}
	
	private SaveBookingResponseBean saveUdchaloBooking(PriceCheckRequestBean priceCheckReq) throws BusinessException
	{
//		Promise<Response> promiseResponse = RestClient.sendRequest(InventoryAirlineRestProtocol.UDCHALO_SAVE_BOOKING_REQUEST,
//				priceCheckReq);
//		SaveBookingResponse response = Utils.convertJsonNodeToObject(promiseResponse.get(Constants.REST_TIMEOUT)
//				.asJson(), SaveBookingResponse.class);
		
		SaveBookingResponseBean response = null;
		response = DummyData.getDummySaveBookingResponse();
		
		return response;
	}
}
