package com.frugalbin.inventory.airline.integration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.frugalbin.inventory.airline.controllers.dto.response.CityBean;
import com.frugalbin.inventory.airline.controllers.dto.response.FlightDetailsBean;
import com.frugalbin.inventory.airline.controllers.dto.response.FlightSlotBean;
import com.frugalbin.inventory.airline.controllers.dto.response.Slots;
import com.frugalbin.inventory.airline.models.City;
import com.frugalbin.inventory.airline.models.FlightSeatDetails;

public class BeanConverter
{
	public static List<CityBean> convertCityListObject(List<City> cityList)
	{
		List<CityBean> cityBeanList = new ArrayList<CityBean>();

		for (City city : cityList)
		{
			CityBean cityBean = new CityBean(city);
			cityBeanList.add(cityBean);
		}
		return cityBeanList;
	}

	public static Map<Slots, FlightSlotBean> convertFlightSeatDetails(List<FlightSeatDetails> availableFlightSeatList)
	{
		Map<Slots, FlightSlotBean> flightSlots = new HashMap<Slots, FlightSlotBean>();
		for (FlightSeatDetails flightSeatDetails : availableFlightSeatList)
		{
			FlightDetailsBean flightDetails = new FlightDetailsBean(flightSeatDetails.getFlight().getFlightNumber(),
					flightSeatDetails.getDepartureTime(), flightSeatDetails.getArrivalTime());
			
			Slots slot = Slots.getSlot(flightSeatDetails.getDepartureTime());
			
			FlightSlotBean flightSlot = flightSlots.get(slot);
			if(flightSlot == null)
			{
				flightSlot = new FlightSlotBean();
				flightSlots.put(slot, flightSlot);
			}

			flightSlot.addFlightDetails(flightSeatDetails.getFlight().getAirlineConnection().getAirlineName(), flightDetails);
		}
		return flightSlots;
	}
}
