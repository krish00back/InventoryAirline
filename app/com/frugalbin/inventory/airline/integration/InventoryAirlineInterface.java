package com.frugalbin.inventory.airline.integration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.frugalbin.inventory.airline.caches.CityCache;
import com.frugalbin.inventory.airline.controllers.dto.request.FlightListRequest;
import com.frugalbin.inventory.airline.controllers.dto.response.CityBean;
import com.frugalbin.inventory.airline.controllers.dto.response.FlightSlotBean;
import com.frugalbin.inventory.airline.controllers.dto.response.Slots;
import com.frugalbin.inventory.airline.models.AirportDetails;
import com.frugalbin.inventory.airline.models.City;
import com.frugalbin.inventory.airline.models.FlightDetails;
import com.frugalbin.inventory.airline.models.FlightSeatDetails;
import com.frugalbin.inventory.airline.services.impl.ServiceFactory;

@Named
@Singleton
public class InventoryAirlineInterface
{
	@Inject
	private ServiceFactory serviceFactory;

	public List<CityBean> getCityList()
	{
		List<City> cityList = CityCache.getInstance().getCityList();
		return BeanConverter.convertCityListObject(cityList);
	}

	public Map<Slots, FlightSlotBean> getFlightSlots(FlightListRequest request)
	{
		// TODO: check from and to city ids cannot be same

		List<FlightSeatDetails> availableFlightSeatList = getAvailableFlighSeatList(request);

		return BeanConverter.convertFlightSeatDetails(availableFlightSeatList);
	}

	private List<FlightSeatDetails> getAvailableFlighSeatList(FlightListRequest request)
	{
		// get from, to City ids
		City fromCity = serviceFactory.getCityService().findCity(request.getFromCityId());
		City toCity = serviceFactory.getCityService().findCity(request.getToCityId());

		// get From, to Airport ids
		List<AirportDetails> fromAirportList = serviceFactory.getAirportService().findAirportByAirportCity(fromCity);
		List<AirportDetails> toAirportList = serviceFactory.getAirportService().findAirportByAirportCity(toCity);

		// get Flight details for from and to airport combinations
		List<FlightDetails> flightList = serviceFactory.getFlighService().findFlightByFromAirportInAndToAirportIn(
				fromAirportList, toAirportList);
		Map<Long, FlightDetails> flightDetailMap = getFlightDetailsIdMap(flightList);

		// get Flight Seat details using flight details id and date criteria
		List<FlightSeatDetails> flightSeatList = serviceFactory.getFlightSeatService()
				.findFlightSeatsByFlightAndAvailSeatsAndDepTime(flightList, request.getPreferredTime());
		List<FlightSeatDetails> availableFlightSeatList = new ArrayList<FlightSeatDetails>();
		Integer numOfTravellers = request.getNumberOfTravellers();

		for (FlightSeatDetails flightSeat : flightSeatList)
		{
			if (flightSeat.getAvailableSeats() < numOfTravellers)
			{
				continue;
			}

			availableFlightSeatList.add(flightSeat);
		}
		return availableFlightSeatList;
	}

	private Map<Long, FlightDetails> getFlightDetailsIdMap(List<FlightDetails> flightList)
	{
		Map<Long, FlightDetails> map = new HashMap<Long, FlightDetails>();
		for (FlightDetails flightDetails : flightList)
		{
			map.put(flightDetails.getFlightId(), flightDetails);
		}

		return map;
	}
}
