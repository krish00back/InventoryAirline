package com.frugalbin.inventory.airline.integration;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.frugalbin.common.dto.request.integration.PassengersCountBean;
import com.frugalbin.common.dto.request.integration.SaveUserRequestBean;
import com.frugalbin.common.dto.request.integration.UserDetailsBean;
import com.frugalbin.common.dto.response.integration.FlightSearchResponseBean;
import com.frugalbin.common.dto.response.inventory.airline.Booking;
import com.frugalbin.common.dto.response.inventory.airline.PriceCheckRequestBean;
import com.frugalbin.common.dto.response.udchalo.PassengerFares;
import com.frugalbin.common.dto.response.udchalo.SegmentBean;
import com.frugalbin.common.dto.response.udchalo.TaxesBean;
import com.frugalbin.common.enums.Cabins;
import com.frugalbin.common.enums.PassengerType;
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
import com.frugalbin.inventory.airline.models.UserRequests;
import com.frugalbin.inventory.airline.udchalo.dto.request.UdchaloFlightSearchRequest;
import com.frugalbin.inventory.airline.udchalo.dto.response.FaresBean;
import com.frugalbin.inventory.airline.udchalo.dto.response.LegBean;
import com.frugalbin.inventory.airline.udchalo.dto.response.LegCombinationsBean;
import com.frugalbin.inventory.airline.udchalo.dto.response.UdchaloFlightSearchResultBean;
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

	public static FlightSlotBean convertFlightSeatDetails(UdchaloFlightSearchResultBean udchaloFlightsDetail,
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

	public static Map<String, com.frugalbin.common.dto.response.inventory.airline.CityBean> convertCityList(
			List<City> cityList)
	{
		Map<String, com.frugalbin.common.dto.response.inventory.airline.CityBean> cityBeanMap = new LinkedHashMap<String, com.frugalbin.common.dto.response.inventory.airline.CityBean>();

		for (City city : cityList)
		{
			com.frugalbin.common.dto.response.inventory.airline.CityBean cityBean = new com.frugalbin.common.dto.response.inventory.airline.CityBean(
					city.getCityCode(), city.getCityName());
			cityBeanMap.put(cityBean.getId(), cityBean);
		}
		return cityBeanMap;
	}

	public static FlightSearchResponseBean convertFlightSearchResponse(
			UdchaloFlightSearchResultBean udchaloFlightsDetail)
	{
		FlightSearchResponseBean flightSearchResponse = new FlightSearchResponseBean();

		// Search Id
		flightSearchResponse.setSearchId(udchaloFlightsDetail.getSearch().get_id());

		// City Map
		List<City> cityList = CityCache.getInstance().getCityList();
		Map<String, com.frugalbin.common.dto.response.inventory.airline.CityBean> cityMap = convertCityList(cityList);
		Map<String, com.frugalbin.common.dto.response.inventory.airline.CityBean> userCityMap = new LinkedHashMap<String, com.frugalbin.common.dto.response.inventory.airline.CityBean>();

		String origin = udchaloFlightsDetail.getSearch().getOrgin();
		String desetination = udchaloFlightsDetail.getSearch().getDestination();
		userCityMap.put(origin, cityMap.get(origin));
		userCityMap.put(desetination, cityMap.get(desetination));

		flightSearchResponse.setCities(userCityMap);

		// From and To City
		flightSearchResponse.setFromCity(udchaloFlightsDetail.getSearch().getOrgin());
		flightSearchResponse.setToCity(udchaloFlightsDetail.getSearch().getDestination());

		// carriers
		Map<String, String> airlines = udchaloFlightsDetail.getAirlines();

		Map<String, com.frugalbin.common.dto.response.inventory.airline.CarrierBean> carriers = new LinkedHashMap<String, com.frugalbin.common.dto.response.inventory.airline.CarrierBean>();
		for (Entry<String, String> entry : airlines.entrySet())
		{
			carriers.put(
					entry.getKey(),
					new com.frugalbin.common.dto.response.inventory.airline.CarrierBean(entry.getKey(), entry
							.getValue()));
		}
		flightSearchResponse.setCarriers(carriers);

		// departDate
		flightSearchResponse.setDepartDate(udchaloFlightsDetail.getSearch().getDepartDate());
		// flightSearchResponse.setReturnDate(udchaloFlightsDetail.getSearch().getReturnDate());

		Set<Entry<Long, LegBean>> legsEntrySet = udchaloFlightsDetail.getOnwardLegs().entrySet();

		double minPrice = Integer.MAX_VALUE;
		Map<Long, FaresBean> legPrices = getLegPrices(udchaloFlightsDetail);
		List<com.frugalbin.common.dto.response.inventory.airline.MarketFlightDetailsBean> marketFlightDetailsList = new ArrayList<com.frugalbin.common.dto.response.inventory.airline.MarketFlightDetailsBean>();

		for (LegBean legEntry : udchaloFlightsDetail.getOnwardLegs().values())
		{
			FaresBean fareBean = legPrices.get(legEntry.getId());
			if (fareBean.getTotalFare() > minPrice)
			{
				continue;
			}
			else
				if (fareBean.getTotalFare() < minPrice)
				{
					minPrice = fareBean.getTotalFare();
					marketFlightDetailsList.clear();
				}

			com.frugalbin.common.dto.response.inventory.airline.MarketFlightDetailsBean marketFlightDetailsBean = new com.frugalbin.common.dto.response.inventory.airline.MarketFlightDetailsBean();
			marketFlightDetailsBean.setCarrierId(legEntry.getAirline());
			marketFlightDetailsBean.setDepartTime(legEntry.getDepart());
			marketFlightDetailsBean.setArrivalTime(legEntry.getArrive());
			marketFlightDetailsBean.setPricePerPerson(fareBean.getTotalFare());

			com.frugalbin.common.dto.response.inventory.airline.StopBean stop = new com.frugalbin.common.dto.response.inventory.airline.StopBean();
			stop.setNoOfStop(legEntry.getStops());

			List<com.frugalbin.common.dto.response.inventory.airline.StopDetailsBean> stopDetailsList = new ArrayList<com.frugalbin.common.dto.response.inventory.airline.StopDetailsBean>();
			com.frugalbin.common.dto.response.inventory.airline.StopDetailsBean stopDetails = new com.frugalbin.common.dto.response.inventory.airline.StopDetailsBean();
			stopDetails.setName(legEntry.getAirline());

			stop.setStopDetails(stopDetailsList);
			marketFlightDetailsBean.setStop(stop);

			PriceCheckRequestBean priceCheckRequestBean = new PriceCheckRequestBean();

			Booking booking = new Booking();
			List<com.frugalbin.common.dto.response.udchalo.LegBean> legs = new ArrayList<com.frugalbin.common.dto.response.udchalo.LegBean>();
			legs.add(convertLegEntry(legEntry));
			booking.setLegs(legs);

			booking.setFare(convertFare(fareBean));

			priceCheckRequestBean.setSearchId(udchaloFlightsDetail.getSearch().get_id());
			priceCheckRequestBean.setBooking(booking);

			marketFlightDetailsBean.setPriceCheckRequest(priceCheckRequestBean);

			marketFlightDetailsList.add(marketFlightDetailsBean);
		}

		flightSearchResponse.setMarketFlights(marketFlightDetailsList);
		return flightSearchResponse;
	}

	public static com.frugalbin.common.dto.response.udchalo.FaresBean convertFare(FaresBean fareBean)
	{
		com.frugalbin.common.dto.response.udchalo.FaresBean resFareBean = new com.frugalbin.common.dto.response.udchalo.FaresBean();

		resFareBean.setBaseTotalFare(fareBean.getBaseTotalFare());
		resFareBean.setId(fareBean.getId());
		resFareBean.setPassengerFares(convertPassengerFares(fareBean.getPassengerFares()));
		resFareBean.setTotalFare(fareBean.getTotalFare());
		resFareBean.setTotalTax(fareBean.getTotalTax());
		return resFareBean;
	}

	private static PassengerFares[] convertPassengerFares(
			com.frugalbin.inventory.airline.udchalo.dto.response.PassengerFares[] passengerFares)
	{
		if (passengerFares == null)
		{
			return null;
		}

		List<PassengerFares> resPassengerFares = new ArrayList<PassengerFares>();

		for (com.frugalbin.inventory.airline.udchalo.dto.response.PassengerFares passengerFare : passengerFares)
		{
			resPassengerFares.add(convertPassengerFare(passengerFare));
		}

		return resPassengerFares.toArray(new PassengerFares[0]);
	}

	private static PassengerFares convertPassengerFare(
			com.frugalbin.inventory.airline.udchalo.dto.response.PassengerFares passengerFares)
	{
		PassengerFares resPassengerFares = new PassengerFares();

		resPassengerFares.setBaseFare(passengerFares.getBaseFare());
		resPassengerFares.setCabins(convertCabins(passengerFares.getCabins()));
		resPassengerFares.setFareBasicCodes(passengerFares.getFareBasicCodes());
		resPassengerFares.setPassengerType(convertPassengerFares(passengerFares.getPassengerType()));
		resPassengerFares.setQuantity(passengerFares.getQuantity());
		resPassengerFares.setServiceClasses(passengerFares.getServiceClasses());
		resPassengerFares.setTaxes(convertTaxesBean(passengerFares.getTaxes()));
		resPassengerFares.setTotalFare(passengerFares.getTotalFare());
		resPassengerFares.setTotalTax(passengerFares.getTotalTax());

		return resPassengerFares;
	}

	private static TaxesBean[] convertTaxesBean(com.frugalbin.inventory.airline.udchalo.dto.response.TaxesBean[] taxes)
	{
		List<TaxesBean> resTaxes = new ArrayList<TaxesBean>();

		for (com.frugalbin.inventory.airline.udchalo.dto.response.TaxesBean taxBean : taxes)
		{
			resTaxes.add(convertTaxBean(taxBean));
		}

		return resTaxes.toArray(new TaxesBean[0]);
	}

	private static TaxesBean convertTaxBean(com.frugalbin.inventory.airline.udchalo.dto.response.TaxesBean taxBean)
	{
		TaxesBean resTaxBean = new TaxesBean();

		resTaxBean.setAmount(taxBean.getAmount());
		resTaxBean.setCode(taxBean.getCode());

		return resTaxBean;
	}

	private static PassengerType convertPassengerFares(com.frugalbin.inventory.airline.enums.PassengerType passengerType)
	{
		return PassengerType.valueOf(passengerType.name());
	}

	private static Cabins[] convertCabins(com.frugalbin.inventory.airline.enums.Cabins[] cabins)
	{
		if (cabins == null)
		{
			return null;
		}

		List<Cabins> resCabins = new ArrayList<Cabins>();

		for (com.frugalbin.inventory.airline.enums.Cabins cabin : cabins)
		{
			resCabins.add(convertCabin(cabin));
		}

		return resCabins.toArray(new Cabins[0]);
	}

	private static com.frugalbin.common.dto.response.udchalo.LegBean convertLegEntry(LegBean legBean)
	{
		com.frugalbin.common.dto.response.udchalo.LegBean resLegBean = new com.frugalbin.common.dto.response.udchalo.LegBean();

		resLegBean.setAirline(legBean.getAirline());
		resLegBean.setArrive(legBean.getArrive());
		resLegBean.setCabin(convertCabin(legBean.getCabin()));
		resLegBean.setDepart(legBean.getDepart());
		resLegBean.setDestination(legBean.getDestination());
		resLegBean.setDuration(legBean.getDuration());
		resLegBean.setId(legBean.getId());
		resLegBean.setOrigin(legBean.getOrigin());
		resLegBean.setSegments(convertSegments(legBean.getSegments()));
		resLegBean.setServiceClass(legBean.getServiceClass());
		resLegBean.setShowDetails(legBean.isShowDetails());
		resLegBean.setStops(legBean.getStops());
		return resLegBean;
	}

	private static SegmentBean[] convertSegments(
			com.frugalbin.inventory.airline.udchalo.dto.response.SegmentBean[] segments)
	{
		List<SegmentBean> resSegments = new ArrayList<SegmentBean>();

		for (com.frugalbin.inventory.airline.udchalo.dto.response.SegmentBean segmentBean : segments)
		{
			SegmentBean resSegment = new SegmentBean();
			resSegment.setAircraft(segmentBean.getAircraft());
			resSegment.setAirline(segmentBean.getAirline());
			resSegment.setArrive(segmentBean.getArrive());
			resSegment.setCabin(convertCabin(segmentBean.getCabin()));
			resSegment.setDepart(segmentBean.getDepart());
			resSegment.setDestination(segmentBean.getDestination());
			resSegment.setDuration(segmentBean.getDuration());
			resSegment.setFlightNumber(segmentBean.getFlightNumber());
			resSegment.setLayover(segmentBean.getLayover());
			resSegment.setMileage(segmentBean.getMileage());
			resSegment.setOrigin(segmentBean.getOrigin());
			resSegment.setServiceClass(segmentBean.getServiceClass());
			resSegment.setStops(segmentBean.getStops());

			resSegments.add(resSegment);
		}

		return resSegments.toArray(new SegmentBean[0]);
	}

	private static Cabins convertCabin(com.frugalbin.inventory.airline.enums.Cabins cabin)
	{
		return Cabins.valueOf(cabin.name());
	}

	public static Map<Long, FaresBean> getLegPrices(UdchaloFlightSearchResultBean udchaloFlightsDetail)
	{
		Map<Long, FaresBean> legPrices = new HashMap<Long, FaresBean>();

		for (LegCombinationsBean legCombinationsBean : udchaloFlightsDetail.getLegCombinations())
		{
			FaresBean fare = udchaloFlightsDetail.getFares().get((int) legCombinationsBean.getFareId());
			legPrices.put(legCombinationsBean.getOnwardLegId(), fare);
		}
		return legPrices;
	}

	public static SaveUserRequestBean getSaveUserSearchRequest(UserRequests userRequest)
	{
		SaveUserRequestBean saveUserRequestBean = new SaveUserRequestBean();

		saveUserRequestBean.setDepartureDate(userRequest.getDepartureDate());
		saveUserRequestBean.setFrom(userRequest.getFromCity().getCityCode());

		PassengersCountBean passengersCountBean = new PassengersCountBean();
		passengersCountBean.setAdults(userRequest.getAdultCount());
		passengersCountBean.setInfants(userRequest.getInfantCount());
		saveUserRequestBean.setPassengers(passengersCountBean);

		saveUserRequestBean.setPnr(userRequest.getPnr());
		saveUserRequestBean.setTo(userRequest.getToCity().getCityCode());

		UserDetailsBean userDetails = new UserDetailsBean();
		userDetails.setUserId(userRequest.getUserId());
		saveUserRequestBean.setUserDetails(userDetails);

		return saveUserRequestBean;
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
