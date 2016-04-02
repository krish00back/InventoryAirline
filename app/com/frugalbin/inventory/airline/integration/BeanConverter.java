package com.frugalbin.inventory.airline.integration;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.frugalbin.inventory.airline.caches.CityCache;
import com.frugalbin.inventory.airline.controllers.dto.response.CarrierBean;
import com.frugalbin.inventory.airline.controllers.dto.response.CityBean;
import com.frugalbin.inventory.airline.controllers.dto.response.FlightSlotBean;
import com.frugalbin.inventory.airline.controllers.dto.response.MarketFlightDetailsBean;
import com.frugalbin.inventory.airline.controllers.dto.response.SlotBean;
import com.frugalbin.inventory.airline.controllers.dto.response.SlotRangeBean;
import com.frugalbin.inventory.airline.controllers.dto.response.StopBean;
import com.frugalbin.inventory.airline.controllers.dto.response.StopDetailsBean;
import com.frugalbin.inventory.airline.models.City;
import com.frugalbin.inventory.airline.udchalo.dto.response.AirlineSearchResultBean;
import com.frugalbin.inventory.airline.udchalo.dto.response.LegBean;
import com.frugalbin.inventory.airline.utils.Constants;

public class BeanConverter
{
	public static Map<String, CityBean> convertCityListObject(List<City> cityList)
	{
		Map<String, CityBean> cityBeanMap = new LinkedHashMap<String, CityBean>();

		for (City city : cityList)
		{
			CityBean cityBean = new CityBean(city.getCityCode(), city.getCityName());
			cityBeanMap.put(cityBean.getId(), cityBean);
		}
		return cityBeanMap;
	}

	public static FlightSlotBean convertFlightSeatDetails(AirlineSearchResultBean udchaloFlightsDetail,
			Map<Long, Double> legPrices)
	{
		FlightSlotBean slotBean = new FlightSlotBean();

		// Search Id
		slotBean.setSearchId(udchaloFlightsDetail.getSearch().get_id());

		// City Map
		List<City> cityList = CityCache.getInstance().getCityList();
		Map<String, CityBean> cityMap = BeanConverter.convertCityListObject(cityList);

		Set<String> cityCodes = cityMap.keySet();
		cityCodes.remove(udchaloFlightsDetail.getSearch().getOrgin());
		cityCodes.remove(udchaloFlightsDetail.getSearch().getDestination());

		for (String cityCode : cityCodes)
		{
			cityMap.remove(cityCode);
		}

		slotBean.setCities(cityMap);

		// From and To City
		slotBean.setFromCity(udchaloFlightsDetail.getSearch().getOrgin());
		slotBean.setToCity(udchaloFlightsDetail.getSearch().getDestination());

		// carriers
		Map<String, String> airlines = udchaloFlightsDetail.getAirlines();

		Map<String, CarrierBean> carriers = new LinkedHashMap<String, CarrierBean>();
		for (Entry<String, String> entry : airlines.entrySet())
		{
			carriers.put(entry.getKey(), new CarrierBean(entry.getKey(), entry.getValue()));
		}
		slotBean.setCarriers(carriers);

		// departDate
		slotBean.setDepartDate(udchaloFlightsDetail.getSearch().getDepartDate());
		slotBean.setReturnDate(udchaloFlightsDetail.getSearch().getReturnDate());

		Set<Entry<Long, LegBean>> legsEntrySet = udchaloFlightsDetail.getOnwardLegs().entrySet();
		int totalLegs = legsEntrySet.size();
		int noOfSlots = 1;
		int legsInSlot;

		while (true)
		{
			legsInSlot = totalLegs / noOfSlots;
			if (legsInSlot < Constants.MAX_FLIGHTS_IN_SLOT && noOfSlots == 1
					&& totalLegs / (noOfSlots + 1) > Constants.MIN_FLIGHTS_IN_SLOT)
			{
				noOfSlots++;
				legsInSlot /= noOfSlots;
				break;
			}
			else
				if (legsInSlot < Constants.MAX_FLIGHTS_IN_SLOT)
				{
					break;
				}

			noOfSlots++;
		}

		int processedLegs = 0;
		int currSlotNo = 1;
		SlotBean currSlot = new SlotBean();
		boolean isStart = true;
		SlotRangeBean slotRangeBean;
		List<MarketFlightDetailsBean> marketFlightList;

		List<SlotBean> slotList = new ArrayList<SlotBean>();
		slotBean.setSlotList(slotList);

		slotList.add(currSlot);

		long slotIdTime = new Date().getTime();

		Iterator<Entry<Long, LegBean>> iterator = legsEntrySet.iterator();

		// TODO: need to add discount, minimum fare and bestPriceCheckRequests
		// and need to make a condition to end the slot based on timing
		while (iterator.hasNext())
		{
			Entry<Long, LegBean> legEntry = iterator.next();

			if (isStart)
			{
				// slot ID and Heading
				currSlot.setSlotId("" + slotIdTime + currSlotNo);
				currSlot.setSlotHeading(Constants.BIN_HEADING + currSlotNo);

				slotRangeBean = new SlotRangeBean();

				Date depTime = legEntry.getValue().getDepart();
				Calendar cal = Calendar.getInstance();
				cal.setTime(depTime);
				int hour = cal.get(Calendar.HOUR_OF_DAY);

				slotRangeBean.setFromTimeSlot((hour % 2) + " "
						+ (hour < 12 ? Constants.AM_STRING : Constants.PM_STRING));

				currSlot.setSlotRange(slotRangeBean);

				marketFlightList = new ArrayList<MarketFlightDetailsBean>();
				currSlot.setMarketFlights(marketFlightList);
			}

			MarketFlightDetailsBean marketFlightDetailsBean = new MarketFlightDetailsBean();
			marketFlightDetailsBean.setCarrierId(legEntry.getValue().getAirline());
			marketFlightDetailsBean.setDepartTime(legEntry.getValue().getDepart());
			marketFlightDetailsBean.setArrivalTime(legEntry.getValue().getArrive());
			marketFlightDetailsBean.setPricePerPerson(legPrices.get(legEntry.getKey()));

			StopBean stop = new StopBean();
			stop.setNoOfStop(legEntry.getValue().getStops());

			List<StopDetailsBean> stopDetailsList = new ArrayList<StopDetailsBean>();
			StopDetailsBean stopDetails = new StopDetailsBean();
			stopDetails.setName(legEntry.getValue().getAirline());

			stop.setStopDetails(stopDetailsList);
			marketFlightDetailsBean.setStop(stop);

		}

		return slotBean;
	}
	// public static Map<Slots, FlightSlotBean>
	// convertFlightSeatDetails(List<FlightSeatDetails> availableFlightSeatList)
	// {
	// Map<Slots, FlightSlotBean> flightSlots = new HashMap<Slots,
	// FlightSlotBean>();
	// for (FlightSeatDetails flightSeatDetails : availableFlightSeatList)
	// {
	// FlightDetailsBean flightDetails = new
	// FlightDetailsBean(flightSeatDetails.getFlight().getFlightNumber(),
	// flightSeatDetails.getDepartureTime(),
	// flightSeatDetails.getArrivalTime());
	//
	// Slots slot = Slots.getSlot(flightSeatDetails.getDepartureTime());
	//
	// FlightSlotBean flightSlot = flightSlots.get(slot);
	// if(flightSlot == null)
	// {
	// flightSlot = new FlightSlotBean();
	// flightSlots.put(slot, flightSlot);
	// }
	//
	// flightSlot.addFlightDetails(flightSeatDetails.getFlight().getAirlineConnection().getAirlineName(),
	// flightDetails);
	// }
	// return flightSlots;
	// }
}
