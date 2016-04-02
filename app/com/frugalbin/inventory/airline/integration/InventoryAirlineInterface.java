package com.frugalbin.inventory.airline.integration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import play.libs.F.Promise;
import play.libs.WS.Response;

import com.frugalbin.common.exceptions.BusinessException;
import com.frugalbin.common.rest.client.RestClient;
import com.frugalbin.common.utils.Constants;
import com.frugalbin.common.utils.Utils;
import com.frugalbin.inventory.airline.caches.CityCache;
import com.frugalbin.inventory.airline.controllers.dto.request.FlightListSearchRequest;
import com.frugalbin.inventory.airline.controllers.dto.response.CityBean;
import com.frugalbin.inventory.airline.controllers.dto.response.FlightSlotBean;
import com.frugalbin.inventory.airline.controllers.dto.response.Slots;
import com.frugalbin.inventory.airline.models.AirportDetails;
import com.frugalbin.inventory.airline.models.City;
import com.frugalbin.inventory.airline.models.FlightDetails;
import com.frugalbin.inventory.airline.models.FlightSeatDetails;
import com.frugalbin.inventory.airline.protocol.rest.InventoryAirlineRestProtocol;
import com.frugalbin.inventory.airline.services.impl.ServiceFactory;
import com.frugalbin.inventory.airline.udchalo.dto.response.AirlineSearchResultBean;
import com.frugalbin.inventory.airline.udchalo.dto.response.FaresBean;
import com.frugalbin.inventory.airline.udchalo.dto.response.LegBean;
import com.frugalbin.inventory.airline.udchalo.dto.response.LegCombinationsBean;

@Named
@Singleton
public class InventoryAirlineInterface
{
	@Inject
	private ServiceFactory serviceFactory;

	public Map<String, CityBean> getCityMap()
	{
		List<City> cityList = CityCache.getInstance().getCityList();
		return BeanConverter.convertCityListObject(cityList);
	}

	public FlightSlotBean getFlightSlots(FlightListSearchRequest request) throws BusinessException
	{
		// TODO: check from and to city ids cannot be same
		if(request.getFromCityId().equals(request.getToCityId()))
		{
			throw new BusinessException(1001, "From and to city ids cannot be same");
		}

		AirlineSearchResultBean udchaloFlightsDetail = getUdchaloFlights(request);

		sortFlightLegs(udchaloFlightsDetail);
		
		// get leg's prices
		Map<Long, Double> legPrices = getLegPrices(udchaloFlightsDetail);
		
		return BeanConverter.convertFlightSeatDetails(udchaloFlightsDetail, legPrices);
	}

	private AirlineSearchResultBean getUdchaloFlights(FlightListSearchRequest request) throws BusinessException
	{
		Promise<Response> promiseResponse = RestClient.sendRequest(InventoryAirlineRestProtocol.UDCHALO_SEARCH_REQUEST, request);
		AirlineSearchResultBean response = Utils.convertJsonNodeToObject(promiseResponse.get(Constants.REST_TIMEOUT).asJson(), AirlineSearchResultBean.class);
		return response;
	}

	private void sortFlightLegs(AirlineSearchResultBean udchaloFlightsDetail)
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

	private Map<Long, Double> getLegPrices(AirlineSearchResultBean udchaloFlightsDetail)
	{
		Map<Long, Double> legPrices = new HashMap<Long, Double>();
		
		for (LegCombinationsBean legCombinationsBean : udchaloFlightsDetail.getLegCombinations())
		{
			FaresBean fare = udchaloFlightsDetail.getFares().get(legCombinationsBean.getFareId());
			legPrices.put(legCombinationsBean.getOnwardLegId(), fare.getTotalFare());
		}
		return legPrices;
	}

	// TODO: get flight list from db
//	private List<FlightSeatDetails> getAvailableFlighSeatList(FlightListRequest request)
//	{
//		// get from, to City ids
//		City fromCity = serviceFactory.getCityService().findCity(request.getFromCityId());
//		City toCity = serviceFactory.getCityService().findCity(request.getToCityId());
//
//		// get From, to Airport ids
//		List<AirportDetails> fromAirportList = serviceFactory.getAirportService().findAirportByAirportCity(fromCity);
//		List<AirportDetails> toAirportList = serviceFactory.getAirportService().findAirportByAirportCity(toCity);
//
//		// get Flight details for from and to airport combinations
//		List<FlightDetails> flightList = serviceFactory.getFlighService().findFlightByFromAirportInAndToAirportIn(
//				fromAirportList, toAirportList);
//		Map<Long, FlightDetails> flightDetailMap = getFlightDetailsIdMap(flightList);
//
//		// get Flight Seat details using flight details id and date criteria
//		List<FlightSeatDetails> flightSeatList = serviceFactory.getFlightSeatService()
//				.findFlightSeatsByFlightAndAvailSeatsAndDepTime(flightList, request.getPreferredTime());
//		List<FlightSeatDetails> availableFlightSeatList = new ArrayList<FlightSeatDetails>();
//		Integer numOfTravellers = request.getNumberOfTravellers();
//
//		for (FlightSeatDetails flightSeat : flightSeatList)
//		{
//			if (flightSeat.getAvailableSeats() < numOfTravellers)
//			{
//				continue;
//			}
//
//			availableFlightSeatList.add(flightSeat);
//		}
//		return availableFlightSeatList;
//	}
//
//	private Map<Long, FlightDetails> getFlightDetailsIdMap(List<FlightDetails> flightList)
//	{
//		Map<Long, FlightDetails> map = new HashMap<Long, FlightDetails>();
//		for (FlightDetails flightDetails : flightList)
//		{
//			map.put(flightDetails.getFlightId(), flightDetails);
//		}
//
//		return map;
//	}
}
