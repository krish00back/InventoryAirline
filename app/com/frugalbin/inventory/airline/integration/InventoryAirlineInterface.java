package com.frugalbin.inventory.airline.integration;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import play.libs.F.Promise;
import play.libs.WS.Response;

import com.fasterxml.jackson.databind.JsonNode;
import com.frugalbin.common.dto.request.integration.SaveUserRequestBean;
import com.frugalbin.common.dto.request.integration.UserDetailsBean;
import com.frugalbin.common.dto.request.inventory.airline.FlightBookingRequest;
import com.frugalbin.common.dto.request.inventory.airline.FlightSearchRequest;
import com.frugalbin.common.dto.request.udchalo.UdchaloFlightBookRequest;
import com.frugalbin.common.dto.response.integration.FlightSearchResponseBean;
import com.frugalbin.common.dto.response.integration.FlightSearchStatus;
import com.frugalbin.common.dto.response.inventory.airline.FlightBookingResponse;
import com.frugalbin.common.dto.response.inventory.airline.PriceCheckRequestBean;
import com.frugalbin.common.dto.response.inventory.airline.PriceCheckResponseBean;
import com.frugalbin.common.dto.response.inventory.airline.SaveBookingResponseBean;
import com.frugalbin.common.enums.UserRequestStatus;
import com.frugalbin.common.exceptions.BusinessException;
import com.frugalbin.common.rest.client.RestClient;
import com.frugalbin.common.utils.Constants;
import com.frugalbin.common.utils.Utils;
import com.frugalbin.inventory.airline.caches.CityCache;
import com.frugalbin.inventory.airline.controllers.dto.response.CityBean;
import com.frugalbin.inventory.airline.enums.TripType;
import com.frugalbin.inventory.airline.models.City;
import com.frugalbin.inventory.airline.models.FlightBookDetails;
import com.frugalbin.inventory.airline.models.UserRequests;
import com.frugalbin.inventory.airline.protocol.rest.InventoryAirlineRestProtocol;
import com.frugalbin.inventory.airline.services.impl.ServiceFactory;
import com.frugalbin.inventory.airline.udchalo.UdchaloAuthenticator;
import com.frugalbin.inventory.airline.udchalo.dto.request.SaveBookingBean;
import com.frugalbin.inventory.airline.udchalo.dto.request.SaveBookingSearchBean;
import com.frugalbin.inventory.airline.udchalo.dto.request.UdchaloFindResultRequest;
import com.frugalbin.inventory.airline.udchalo.dto.request.UdchaloFlightSearchBean;
import com.frugalbin.inventory.airline.udchalo.dto.request.UdchaloFlightSearchBean1;
import com.frugalbin.inventory.airline.udchalo.dto.request.UdchaloFlightSearchRequest;
import com.frugalbin.inventory.airline.udchalo.dto.request.UdchaloSaveBookingRequest;
import com.frugalbin.inventory.airline.udchalo.dto.response.UdchaloFlightBookingResponse;
import com.frugalbin.inventory.airline.udchalo.dto.response.UdchaloFlightGetResultsResponse;
import com.frugalbin.inventory.airline.udchalo.dto.response.UdchaloFlightSearchResponseBean;
import com.frugalbin.inventory.airline.udchalo.dto.response.UdchaloPriceCheckResultBean;

@Named
@Singleton
public class InventoryAirlineInterface
{
	Logger LOGGER = LoggerFactory.getLogger(InventoryAirlineInterface.class);

	@Inject
	private ServiceFactory serviceFactory;

	@Inject
	private UdchaloAuthenticator udchaloAuthenticator;

	private SecureRandom random = new SecureRandom();

	public Map<String, CityBean> getCityMap()
	{
		List<City> cityList = CityCache.getInstance().getCityList();
		return BeanConverter.convertCityListObject(cityList);
	}

	// public FlightSlotBean getFlightSlots(FlightListSearchRequest request)
	// throws BusinessException
	// {
	// // TODO: check from and to city ids cannot be same
	// if (request.getFromCityId().equals(request.getToCityId()))
	// {
	// throw new BusinessException(1001, "From and to city ids cannot be same");
	// }
	//
	// UdchaloFlightGetResultsResponse udchaloFlightsDetail =
	// getUdchaloFlights(null);
	//
	// sortFlightLegs(udchaloFlightsDetail);
	//
	// // get leg's prices
	// Map<Long, Double> legPrices =
	// null;//BeanConverter.getLegPrices(udchaloFlightsDetail);
	//
	// return BeanConverter.convertFlightSeatDetails(udchaloFlightsDetail,
	// legPrices);
	// }

	private UdchaloFlightGetResultsResponse getUdchaloFlights(UdchaloFlightSearchRequest udchaloFlightSearchRequest)
			throws BusinessException
	{
		udchaloFlightSearchRequest.setToken(udchaloAuthenticator.getToken());

		LOGGER.error("Udchalo Flight Search Request : " + udchaloFlightSearchRequest.getSearch().getDepart()
				+ ", parsed: " + udchaloFlightSearchRequest.getSearch().getParsedDepart());

		Promise<Response> promiseResponse = RestClient.sendRequest(
				InventoryAirlineRestProtocol.UDCHALO_SAVE_SEARCH_REQUEST, udchaloFlightSearchRequest);
		UdchaloFlightSearchResponseBean flightSearchResponseBean = Utils.convertJsonNodeToObject(
				promiseResponse.get(Constants.REST_TIMEOUT).asJson(), UdchaloFlightSearchResponseBean.class);

		if (!flightSearchResponseBean.isSuccess())
		{
			throw new BusinessException(1001, flightSearchResponseBean.getValidationerrors().toString());
		}

		UdchaloFindResultRequest findResultRequest = new UdchaloFindResultRequest();
		findResultRequest.setSearchId(flightSearchResponseBean.getSearchId());
		findResultRequest.setToken(udchaloAuthenticator.getToken());

		promiseResponse = RestClient.sendRequest(InventoryAirlineRestProtocol.UDCHALO_FIND_RESULT_REQUEST,
				findResultRequest);
		UdchaloFlightGetResultsResponse findGetResultsResponse = Utils.convertJsonNodeToObject(
				promiseResponse.get(Constants.REST_TIMEOUT).asJson(), UdchaloFlightGetResultsResponse.class);

		// UdchaloFlightSearchResultBean response = null;
		// try
		// {
		// response = DummyData.getDummyUdchaloFlightSearchResponse();
		// }
		// catch (ParseException e)
		// {
		//
		// }

		return findGetResultsResponse;
	}

	// private void sortFlightLegs(UdchaloFlightSearchResponseBean
	// udchaloFlightsDetail)
	// {
	// Map<Long, LegBean> onwardLegsMap = udchaloFlightsDetail.getOnwardLegs();
	// LegCombinationsBean[] legCombinations =
	// udchaloFlightsDetail.getLegCombinations();
	//
	// LegComparator comparator = new LegComparator();
	// TreeSet<LegBean> onwardLegs = new TreeSet<LegBean>(comparator);
	//
	// onwardLegs.addAll(onwardLegsMap.values());
	//
	// Map<Long, LegBean> finalOnwardLegsMap = new LinkedHashMap<Long,
	// LegBean>();
	// for (LegBean legBean : onwardLegs)
	// {
	// finalOnwardLegsMap.put(legBean.getId(), legBean);
	// }
	//
	// udchaloFlightsDetail.setOnwardLegs(finalOnwardLegsMap);
	// }

	public UserRequests createUserRequest(SaveUserRequestBean userRequestBean) throws BusinessException
	{
		if (userRequestBean.getPassengers().getAdults() >= 10)
		{
			throw new BusinessException(1001, "Number of adults cannot be greater than 9");
		}

		if (userRequestBean.getPassengers().getInfants() >= userRequestBean.getPassengers().getAdults())
		{
			throw new BusinessException(1001, "Number of Infants cannot be equal to or greater than Number of Adults");
		}

		UserRequests userRequest = new UserRequests();

		String requestId;

		while (true)
		{
			requestId = generateRequestId();
			List<UserRequests> userRequestList = serviceFactory.getUserRequestsService().getUserRequests(requestId);

			if (userRequestList == null || userRequestList.size() == 0)
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
		userRequest.setReturnDate(userRequestBean.getReturnDate());
		userRequest.setUserId(userRequestBean.getUserDetails().getUserId());
		userRequest.setPnr(userRequestBean.getPnr());
		userRequest.setReqCreationTime(new Date());
		userRequest.setStatus(UserRequestStatus.CREATED);

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
		List<UserRequests> userRequestList = serviceFactory.getUserRequestsService().getUserRequests(
				request.getRequestId());

		if (userRequestList == null || userRequestList.size() <= 0)
		{
			throw new BusinessException(1001, "User Request is invalid");
		}

		UserRequests userRequest = userRequestList.get(0);

		if (new Date().getTime() - userRequest.getReqCreationTime().getTime() > 24 * 60 * 60 * 1000)
		{
			throw new BusinessException(1001, "User Request Expired, please create another request");
		}

		UdchaloFlightSearchRequest udchaloFlightSearchRequest = new UdchaloFlightSearchRequest();

		UdchaloFlightSearchBean1 udchaloFlightSearchBean = new UdchaloFlightSearchBean1();
		udchaloFlightSearchBean.setOrigin(userRequest.getFromCity().getCityCode());
		udchaloFlightSearchBean.setDestination(userRequest.getToCity().getCityCode());
		udchaloFlightSearchBean.setDepart(userRequest.getDepartureDate());
		udchaloFlightSearchBean.setReturndate(userRequest.getReturnDate());
		udchaloFlightSearchBean.setAdults(userRequest.getAdultCount());
		udchaloFlightSearchBean.setInfants(userRequest.getInfantCount());
		udchaloFlightSearchBean.setTriptype(TripType.oneway);

		udchaloFlightSearchRequest.setSearch(udchaloFlightSearchBean);

		udchaloFlightSearchRequest.setToken(udchaloAuthenticator.getToken());

		UdchaloFlightGetResultsResponse udchaloFlightSearchResult = getUdchaloFlights(udchaloFlightSearchRequest);

		if (!udchaloFlightSearchResult.getIsSuccess())
		{
			throw new BusinessException(1001, "Flight not available with the request or got sold out");
		}

		FlightSearchResponseBean flightSearchResponse = BeanConverter
				.convertFlightSearchResponse(udchaloFlightSearchResult);

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

		String message = priceCheckResultBean.getIsSuccess() ? priceCheckResultBean.getMessage()
				: ("Seat has already been filled" + priceCheckResultBean.getMessage() == null ? ""
						: priceCheckResultBean.getMessage());
		priceCheckResponse.setMessage(message);

		if (priceCheckResultBean.getIsSuccess())
		{
			priceCheckReq.getBooking().setFare(BeanConverter.convertFare(priceCheckResultBean.getFare()));
			priceCheckResponse.setPriceCheckRequest(priceCheckReq);
			priceCheckResponse.setPricePerPerson(priceCheckResultBean.getFare().getTotalFare());
			
			priceCheckResponse.getPriceCheckRequest().setToken(null);
		}
		return priceCheckResponse;
	}

	private UdchaloPriceCheckResultBean checkUdchaloFlightPrice(PriceCheckRequestBean priceCheckReq)
			throws BusinessException
	{
		priceCheckReq.setToken(udchaloAuthenticator.getToken());
		Promise<Response> promiseResponse = RestClient.sendRequest(
				InventoryAirlineRestProtocol.UDCHALO_PRICE_CHECK_REQUEST, priceCheckReq);
		UdchaloPriceCheckResultBean response = Utils.convertJsonNodeToObject(promiseResponse
				.get(Constants.REST_TIMEOUT).asJson(), UdchaloPriceCheckResultBean.class);

		// UdchaloPriceCheckResultBean response = null;
		// response = DummyData.getDummyUdchaloPriceCheckResponse();

		return response;
	}

	public SaveBookingResponseBean saveBooking(PriceCheckRequestBean priceCheckReq) throws BusinessException
	{
		UdchaloSaveBookingRequest saveBookingRequest = new UdchaloSaveBookingRequest();

		SaveBookingBean booking = new SaveBookingBean();
		booking.setFare(priceCheckReq.getBooking().getFare());
		booking.setLegs(priceCheckReq.getBooking().getLegs());
		booking.setPassengers(priceCheckReq.getBooking().getPassengers());

		SaveBookingSearchBean udchaloFlightSearchBean = new SaveBookingSearchBean();
		udchaloFlightSearchBean.set_id(priceCheckReq.getSearchId());
		booking.setSearch(udchaloFlightSearchBean);

		saveBookingRequest.setBooking(booking);
		return saveUdchaloBooking(saveBookingRequest);
	}

	private SaveBookingResponseBean saveUdchaloBooking(UdchaloSaveBookingRequest saveBookingRequest)
			throws BusinessException
	{
		saveBookingRequest.setToken(udchaloAuthenticator.getToken());
		Promise<Response> promiseResponse = RestClient.sendRequest(
				InventoryAirlineRestProtocol.UDCHALO_SAVE_BOOKING_REQUEST, saveBookingRequest);
		SaveBookingResponseBean response = Utils.convertJsonNodeToObject(promiseResponse.get(Constants.REST_TIMEOUT)
				.asJson(), SaveBookingResponseBean.class);

		// SaveBookingResponseBean response = null;
		// response = DummyData.getDummySaveBookingResponse();

		return response;
	}

	public FlightBookingResponse bookFlight(FlightBookingRequest flightBookingRequest) throws BusinessException
	{
		UdchaloFlightBookRequest udchaloFlightBookRequest = BeanConverter
				.convertFlightBookRequest(flightBookingRequest);

		FlightBookDetails flightBookDetails = new FlightBookDetails();
		UdchaloFlightBookingResponse udchaloBookingResponse = bookUdchaloFlight(udchaloFlightBookRequest,
				flightBookDetails);

		if (udchaloBookingResponse.getSuccess())
		{
			serviceFactory.getFlightBookDetailsService().addFlightBookDetails(flightBookDetails);
		}

		FlightBookingResponse bookingResponse = BeanConverter.convertBookingResponse(udchaloBookingResponse);
		return bookingResponse;
	}

	private UdchaloFlightBookingResponse bookUdchaloFlight(UdchaloFlightBookRequest flightBookingRequest,
			FlightBookDetails flightBookDetails) throws BusinessException
	{
		flightBookingRequest.setToken(udchaloAuthenticator.getToken());
		Promise<Response> promiseResponse = RestClient.sendRequest(InventoryAirlineRestProtocol.UDCHALO_BOOK_REQUEST,
				flightBookingRequest);

		Response response = promiseResponse.get(Constants.REST_TIMEOUT);

		JsonNode jsonResponse = response.asJson();
		flightBookDetails.setUdchaloFlightResponse(jsonResponse.textValue());

		UdchaloFlightBookingResponse flightBookingResponse = Utils.convertJsonNodeToObject(jsonResponse,
				UdchaloFlightBookingResponse.class);
		return flightBookingResponse;
	}

	public UserDetailsBean getUserDetails(String requestId) throws BusinessException
	{
		List<UserRequests> userRequestList = serviceFactory.getUserRequestsService().getUserRequests(requestId);

		if (userRequestList == null || userRequestList.size() <= 0)
		{
			throw new BusinessException(1001, "User Request is invalid");
		}

		UserRequests userRequest = userRequestList.get(0);

		UserDetailsBean userDetailsBean = new UserDetailsBean();
		userDetailsBean.setUserId(userRequest.getUserId());

		return userDetailsBean;
	}
}
