package com.frugalbin.inventory.airline.integration;

import java.util.ArrayList;
import java.util.List;

import com.frugalbin.inventory.airline.controllers.dto.response.CityBean;
import com.frugalbin.inventory.airline.controllers.dto.response.FlightSlotBean;
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

	public static List<FlightSlotBean> convertFlightSeatDetails(List<FlightSeatDetails> availableFlightSeatList)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
