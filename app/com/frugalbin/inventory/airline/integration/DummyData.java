package com.frugalbin.inventory.airline.integration;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.frugalbin.common.dto.response.inventory.airline.SaveBookingResponseBean;
import com.frugalbin.inventory.airline.enums.Cabins;
import com.frugalbin.inventory.airline.enums.PassengerType;
import com.frugalbin.inventory.airline.enums.TripType;
import com.frugalbin.inventory.airline.udchalo.dto.request.UdchaloFlightSearchBean;
import com.frugalbin.inventory.airline.udchalo.dto.response.FaresBean;
import com.frugalbin.inventory.airline.udchalo.dto.response.LegBean;
import com.frugalbin.inventory.airline.udchalo.dto.response.LegCombinationsBean;
import com.frugalbin.inventory.airline.udchalo.dto.response.PassengerFares;
import com.frugalbin.inventory.airline.udchalo.dto.response.SegmentBean;
import com.frugalbin.inventory.airline.udchalo.dto.response.TaxesBean;
import com.frugalbin.inventory.airline.udchalo.dto.response.UdchaloFlightGetResultsResponse;
import com.frugalbin.inventory.airline.udchalo.dto.response.UdchaloPriceCheckResultBean;

public class DummyData
{
	private static final Random RANDOM = new Random();
	private static final Double MIN_FARE = 2500.0;
	private static final Double MAX_FARE = 6500.0;

	public static UdchaloFlightGetResultsResponse getDummyUdchaloFlightSearchResponse() throws ParseException
	{
		UdchaloFlightGetResultsResponse response = new UdchaloFlightGetResultsResponse();

		// Airlines
		LinkedHashMap<String, String> airlines = new LinkedHashMap<String, String>();
		airlines.put("9W", "Jet Airways");
		response.setAirlines(airlines);

		// Airports
		LinkedHashMap<String, com.frugalbin.inventory.airline.udchalo.dto.response.CityBean> airports = new LinkedHashMap<String, com.frugalbin.inventory.airline.udchalo.dto.response.CityBean>();

		com.frugalbin.inventory.airline.udchalo.dto.response.CityBean city = new com.frugalbin.inventory.airline.udchalo.dto.response.CityBean();
		city.setCityname("Pune");
		city.setCode("PNQ");
		city.setName("Lohegaon");
		airports.put("PNQ", city);

		city = new com.frugalbin.inventory.airline.udchalo.dto.response.CityBean();
		city.setCityname("Pune");
		city.setCode("PNQ");
		city.setName("Lohegaon");
		airports.put("PNQ", city);

		response.setAirports(airports);

		// Fares
		Map<Long, FaresBean> fares = new LinkedHashMap<Long, FaresBean>();

		// 1 Fare
		FaresBean fare = new FaresBean();
		fare.setId(0);

		List<PassengerFares> passengerFares = new ArrayList<PassengerFares>();
		PassengerFares passengerFare = new PassengerFares();
		passengerFare.setPassengerType(PassengerType.ADT);
		passengerFare.setQuantity(1);
		passengerFare.setBaseFare(4050);

		TaxesBean[] taxes = new TaxesBean[8];
		passengerFare.setTaxes(taxes);

		TaxesBean tax = new TaxesBean();
		tax.setAmount(0);
		tax.setCode("YQ");
		taxes[0] = tax;
		tax = new TaxesBean();
		tax.setAmount(134);
		tax.setCode("YR");
		taxes[1] = tax;
		tax = new TaxesBean();
		tax.setAmount(0);
		tax.setCode("HK");
		taxes[2] = tax;
		tax = new TaxesBean();
		tax.setAmount(475);
		tax.setCode("IN");
		taxes[3] = tax;
		tax = new TaxesBean();
		tax.setAmount(0);
		tax.setCode("TS");
		taxes[4] = tax;
		tax = new TaxesBean();
		tax.setAmount(238);
		tax.setCode("WO");
		taxes[5] = tax;
		tax = new TaxesBean();
		tax.setAmount(235);
		tax.setCode("JN");
		taxes[6] = tax;
		tax = new TaxesBean();
		tax.setAmount(9);
		tax.setCode("F2");
		taxes[7] = tax;

		double totalFare = MIN_FARE + (MAX_FARE - MIN_FARE) * RANDOM.nextDouble();

		passengerFare.setTotalTax(1091);
		passengerFare.setTotalFare(totalFare);
		passengerFare.setFareBasisCodes(new String[] { "K4MILIPU" });
		passengerFare.setServiceClasses(new String[] { "K" });
		passengerFare.setCabins(new Cabins[] { Cabins.Economy });

		passengerFares.add(passengerFare);

		fare.setPassengerFares(passengerFares.toArray(new PassengerFares[0]));
		fare.setBaseTotalFare(4050);
		fare.setTotalTax(1091);
		fare.setTotalFare(totalFare);
		fares.put(0L, fare);

		// 2 Fare
		fare = new FaresBean();
		fare.setId(1);

		passengerFares.clear();

		passengerFare = new PassengerFares();
		passengerFare.setPassengerType(PassengerType.ADT);
		passengerFare.setQuantity(1);
		passengerFare.setBaseFare(3050);

		taxes = new TaxesBean[8];
		passengerFare.setTaxes(taxes);

		tax = new TaxesBean();
		tax.setAmount(0);
		tax.setCode("YQ");
		taxes[0] = tax;
		tax = new TaxesBean();
		tax.setAmount(134);
		tax.setCode("YR");
		taxes[1] = tax;
		tax = new TaxesBean();
		tax.setAmount(0);
		tax.setCode("HK");
		taxes[2] = tax;
		tax = new TaxesBean();
		tax.setAmount(475);
		tax.setCode("IN");
		taxes[3] = tax;
		tax = new TaxesBean();
		tax.setAmount(0);
		tax.setCode("TS");
		taxes[4] = tax;
		tax = new TaxesBean();
		tax.setAmount(238);
		tax.setCode("WO");
		taxes[5] = tax;
		tax = new TaxesBean();
		tax.setAmount(179);
		tax.setCode("JN");
		taxes[6] = tax;
		tax = new TaxesBean();
		tax.setAmount(7);
		tax.setCode("F2");
		taxes[7] = tax;

		totalFare = MIN_FARE + (MAX_FARE - MIN_FARE) * RANDOM.nextDouble();

		passengerFare.setTotalTax(1033);
		passengerFare.setTotalFare(totalFare);
		passengerFare.setFareBasisCodes(new String[] { "H4MILIPU" });
		passengerFare.setServiceClasses(new String[] { "H" });
		passengerFare.setCabins(new Cabins[] { Cabins.Economy });

		passengerFares.add(passengerFare);

		fare.setPassengerFares(passengerFares.toArray(new PassengerFares[0]));
		fare.setBaseTotalFare(3050);
		fare.setTotalTax(1033);
		fare.setTotalFare(totalFare);
		fares.put(1L, fare);

		// 3 Fare
		fare = new FaresBean();
		fare.setId(2);

		passengerFares.clear();

		passengerFare = new PassengerFares();
		passengerFare.setPassengerType(PassengerType.ADT);
		passengerFare.setQuantity(1);
		passengerFare.setBaseFare(1625);

		taxes = new TaxesBean[8];
		passengerFare.setTaxes(taxes);

		tax = new TaxesBean();
		tax.setAmount(0);
		tax.setCode("YQ");
		taxes[0] = tax;
		tax = new TaxesBean();
		tax.setAmount(134);
		tax.setCode("YR");
		taxes[1] = tax;
		tax = new TaxesBean();
		tax.setAmount(0);
		tax.setCode("HK");
		taxes[2] = tax;
		tax = new TaxesBean();
		tax.setAmount(475);
		tax.setCode("IN");
		taxes[3] = tax;
		tax = new TaxesBean();
		tax.setAmount(0);
		tax.setCode("TS");
		taxes[4] = tax;
		tax = new TaxesBean();
		tax.setAmount(238);
		tax.setCode("WO");
		taxes[5] = tax;
		tax = new TaxesBean();
		tax.setAmount(99);
		tax.setCode("JN");
		taxes[6] = tax;
		tax = new TaxesBean();
		tax.setAmount(4);
		tax.setCode("F2");
		taxes[7] = tax;

		totalFare = MIN_FARE + (MAX_FARE - MIN_FARE) * RANDOM.nextDouble();

		passengerFare.setTotalTax(950);
		passengerFare.setTotalFare(totalFare);
		passengerFare.setFareBasisCodes(new String[] { "O4MILIPU" });
		passengerFare.setServiceClasses(new String[] { "O" });
		passengerFare.setCabins(new Cabins[] { Cabins.Economy });

		passengerFares.add(passengerFare);

		fare.setPassengerFares(passengerFares.toArray(new PassengerFares[0]));
		fare.setBaseTotalFare(1625);
		fare.setTotalTax(950);
		fare.setTotalFare(totalFare);
		fares.put(2L, fare);

		response.setFares(fares);

		// Leg Combinations
		LegCombinationsBean[] legCombinations = new LegCombinationsBean[6];

		LegCombinationsBean legCombination = new LegCombinationsBean();
		legCombination.setOnwardLegId(0);
		legCombination.setReturnLegId(-1);
		legCombination.setFareId(0);
		legCombinations[0] = legCombination;

		legCombination = new LegCombinationsBean();
		legCombination.setOnwardLegId(1);
		legCombination.setReturnLegId(-1);
		legCombination.setFareId(2);
		legCombinations[1] = legCombination;

		legCombination = new LegCombinationsBean();
		legCombination.setOnwardLegId(2);
		legCombination.setReturnLegId(-1);
		legCombination.setFareId(1);
		legCombinations[2] = legCombination;

		legCombination = new LegCombinationsBean();
		legCombination.setOnwardLegId(3);
		legCombination.setReturnLegId(-1);
		legCombination.setFareId(1);
		legCombinations[3] = legCombination;

		legCombination = new LegCombinationsBean();
		legCombination.setOnwardLegId(4);
		legCombination.setReturnLegId(-1);
		legCombination.setFareId(1);
		legCombinations[4] = legCombination;

		legCombination = new LegCombinationsBean();
		legCombination.setOnwardLegId(5);
		legCombination.setReturnLegId(-1);
		legCombination.setFareId(2);
		legCombinations[5] = legCombination;

		response.setLegCombinations(legCombinations);

		// LEGS
		Map<Long, LegBean> onwardLegs = new LinkedHashMap<Long, LegBean>();

		// 0. Leg
		LegBean leg = new LegBean();
		leg.setId(0);
		leg.setOrigin("PNQ");
		leg.setDestination("DEL");
		leg.setDepart("2016-04-13T05:30:00");
		leg.setArrive("2016-04-13T07:40:00");
		leg.setDuration(130);
		leg.setCabin(Cabins.Economy);
		leg.setStops(0);
		leg.setAirline("9W");

		SegmentBean[] segments = new SegmentBean[1];
		SegmentBean segment = new SegmentBean();
		segment.setOrigin("PNQ");
		segment.setDestination("DEL");
		segment.setDepart("2016-04-13T05:30:00");
		segment.setArrive("2016-04-13T07:40:00");
		segment.setDuration(130);
		segment.setCabin(Cabins.Economy);
		segment.setMileage(0);
		segment.setStops(0);
		segment.setFlightNumber("364");
		segment.setAirline("9W");
		segment.setLayover(0);
		segments[0] = segment;
		leg.setSegments(segments);

		onwardLegs.put(0L, leg);

		// 1. Leg
		leg = new LegBean();
		leg.setId(1);
		leg.setOrigin("PNQ");
		leg.setDestination("DEL");
		leg.setDepart("2016-04-13T07:35:00");
		leg.setArrive("2016-04-13T09:40:00");
		leg.setDuration(125);
		leg.setCabin(Cabins.Economy);
		leg.setStops(0);
		leg.setAirline("9W");

		segments = new SegmentBean[1];
		segment = new SegmentBean();
		segment.setOrigin("PNQ");
		segment.setDestination("DEL");
		segment.setDepart("2016-04-13T07:33:00");
		segment.setArrive("2016-04-13T09:40:00");
		segment.setDuration(125);
		segment.setCabin(Cabins.Economy);
		segment.setMileage(0);
		segment.setStops(0);
		segment.setFlightNumber("374");
		segment.setAirline("9W");
		segment.setLayover(0);
		segments[0] = segment;
		leg.setSegments(segments);

		onwardLegs.put(1L, leg);

		// 2. Leg
		leg = new LegBean();
		leg.setId(2);
		leg.setOrigin("PNQ");
		leg.setDestination("DEL");
		leg.setDepart("2016-04-13T11:10:00");
		leg.setArrive("2016-04-13T13:25:00");
		leg.setDuration(135);
		leg.setCabin(Cabins.Economy);
		leg.setStops(0);
		leg.setAirline("9W");

		segments = new SegmentBean[1];
		segment = new SegmentBean();
		segment.setOrigin("PNQ");
		segment.setDestination("DEL");
		segment.setDepart("2016-04-13T11:10:00");
		segment.setArrive("2016-04-13T13:25:00");
		segment.setDuration(135);
		segment.setCabin(Cabins.Economy);
		segment.setMileage(0);
		segment.setStops(0);
		segment.setFlightNumber("366");
		segment.setAirline("9W");
		segment.setLayover(0);
		segments[0] = segment;
		leg.setSegments(segments);

		onwardLegs.put(2L, leg);

		// 3. Leg
		leg = new LegBean();
		leg.setId(3);
		leg.setOrigin("PNQ");
		leg.setDestination("DEL");
		leg.setDepart("2016-04-13T15:30:00");
		leg.setArrive("2016-04-13T17:35:00");
		leg.setDuration(125);
		leg.setCabin(Cabins.Economy);
		leg.setStops(0);
		leg.setAirline("9W");

		segments = new SegmentBean[1];
		segment = new SegmentBean();
		segment.setOrigin("PNQ");
		segment.setDestination("DEL");
		segment.setDepart("2016-04-13T15:30:00");
		segment.setArrive("2016-04-13T17:35:00");
		segment.setDuration(125);
		segment.setCabin(Cabins.Economy);
		segment.setMileage(0);
		segment.setStops(0);
		segment.setFlightNumber("786");
		segment.setAirline("9W");
		segment.setLayover(0);
		segments[0] = segment;
		leg.setSegments(segments);

		onwardLegs.put(3L, leg);

		// 4. Leg
		leg = new LegBean();
		leg.setId(4);
		leg.setOrigin("PNQ");
		leg.setDestination("DEL");
		leg.setDepart("2016-04-13T19:55:00");
		leg.setArrive("2016-04-13T22:10:00");
		leg.setDuration(135);
		leg.setCabin(Cabins.Economy);
		leg.setStops(0);
		leg.setAirline("9W");

		segments = new SegmentBean[1];
		segment = new SegmentBean();
		segment.setOrigin("PNQ");
		segment.setDestination("DEL");
		segment.setDepart("2016-04-13T19:55:00");
		segment.setArrive("2016-04-13T22:10:00");
		segment.setDuration(135);
		segment.setCabin(Cabins.Economy);
		segment.setMileage(0);
		segment.setStops(0);
		segment.setFlightNumber("372");
		segment.setAirline("9W");
		segment.setLayover(0);
		segments[0] = segment;
		leg.setSegments(segments);

		onwardLegs.put(4L, leg);

		// 5. Leg
		leg = new LegBean();
		leg.setId(5);
		leg.setOrigin("PNQ");
		leg.setDestination("DEL");
		leg.setDepart("2016-04-13T23:05:00");
		leg.setArrive("2016-04-14T01:15:00");
		leg.setDuration(130);
		leg.setCabin(Cabins.Economy);
		leg.setStops(0);
		leg.setAirline("9W");

		segments = new SegmentBean[1];
		segment = new SegmentBean();
		segment.setOrigin("PNQ");
		segment.setDestination("DEL");
		segment.setDepart("2016-04-13T23:05:00");
		segment.setArrive("2016-04-14T01:15:00");
		segment.setDuration(130);
		segment.setCabin(Cabins.Economy);
		segment.setMileage(0);
		segment.setStops(0);
		segment.setFlightNumber("7071");
		segment.setAirline("9W");
		segment.setLayover(0);
		segments[0] = segment;
		leg.setSegments(segments);

		onwardLegs.put(5L, leg);

		response.setOnwardLegs(onwardLegs);

		response.setReturnLegs(new LinkedHashMap<Long, LegBean>());

		// SEARCH
		UdchaloFlightSearchBean search = new UdchaloFlightSearchBean();
		search.set_id("56f3da509bbf36cda1cdbeea");
		search.setOrigin("PNQ");
		search.setDestination("DEL");
//		search.setDepartdate("2016-04-13T00:00:00.000Z");
		search.setAdults(1);
		search.setInfants(0);
		search.setTriptype(TripType.oneway);
		search.set__v(0);
		response.setSearch(search);

		response.setIsSuccess(true);

		return response;
	}

	public static UdchaloPriceCheckResultBean getDummyUdchaloPriceCheckResponse()
	{
		UdchaloPriceCheckResultBean response = new UdchaloPriceCheckResultBean();

		// SEARCH
		UdchaloFlightSearchBean search = new UdchaloFlightSearchBean();
		search.set_id("56f3da509bbf36cda1cdbeea");
		search.setOrigin("PNQ");
		search.setDestination("DEL");
//		search.setDepartdate("2016-04-13T00:00:00.000Z");
		search.setAdults(1);
		search.setInfants(0);
		search.setTriptype(TripType.oneway);
		search.set__v(0);
		response.setSearch(search);

		// IS SUCCESS, IS FARE CHANGED, MESSAGE
		response.setIsFareChanged(new Random().nextBoolean());
		response.setIsSuccess(true);

		FaresBean fare = new FaresBean();
		fare.setId(9);

		List<PassengerFares> passengerFares = new ArrayList<PassengerFares>();

		PassengerFares passengerFare = new PassengerFares();
		passengerFare.setPassengerType(PassengerType.ADT);
		passengerFare.setQuantity(1);
		passengerFare.setBaseFare(3250);

		TaxesBean[] taxes = new TaxesBean[6];
		passengerFare.setTaxes(taxes);

		TaxesBean tax = new TaxesBean();
		tax.setAmount(268);
		tax.setCode("YR");
		taxes[0] = tax;

		tax = new TaxesBean();
		tax.setAmount(1037);
		tax.setCode("IN");
		taxes[1] = tax;

		tax = new TaxesBean();
		tax.setAmount(387);
		tax.setCode("WO");
		taxes[2] = tax;

		tax = new TaxesBean();
		tax.setAmount(197);
		tax.setCode("JN");
		taxes[3] = tax;

		tax = new TaxesBean();
		tax.setAmount(7);
		tax.setCode("F2");
		taxes[4] = tax;

		tax = new TaxesBean();
		tax.setAmount(115);
		tax.setCode("YM");
		taxes[5] = tax;

		double totalFare = MIN_FARE + (MAX_FARE - MIN_FARE) * RANDOM.nextDouble();
		passengerFare.setTotalTax(2011);
		passengerFare.setTotalFare(totalFare);
		passengerFare.setFareBasisCodes(new String[] { "O4MILIPU" });
		passengerFare.setServiceClasses(new String[] { "O" });

		passengerFares.add(passengerFare);

		fare.setPassengerFares(passengerFares.toArray(new PassengerFares[0]));
		fare.setBaseTotalFare(1625);
		fare.setTotalTax(950);
		fare.setTotalFare(totalFare);
		
		response.setFare(fare);

		return response;
	}

	public static SaveBookingResponseBean getDummySaveBookingResponse()
	{
		SaveBookingResponseBean response = new SaveBookingResponseBean();
		response.setBookingId("dfkj343kr34094u");
		response.setSuccess(true);
		return response;
	}

}
