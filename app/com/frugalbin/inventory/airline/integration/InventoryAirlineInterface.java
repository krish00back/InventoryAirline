package com.frugalbin.inventory.airline.integration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.frugalbin.inventory.airline.caches.CityCache;
import com.frugalbin.inventory.airline.controllers.dto.request.FlightListRequest;
import com.frugalbin.inventory.airline.controllers.dto.response.CityBean;
import com.frugalbin.inventory.airline.models.City;
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
		return convertListObject(cityList);
	}

	protected List<CityBean> convertListObject(List<City> cityList)
	{
		List<CityBean> cityBeanList = new ArrayList<CityBean>();

		for (City city : cityList)
		{
			CityBean cityBean = new CityBean(city);
			cityBeanList.add(cityBean);
		}
		return cityBeanList;
	}

	public void getFlightSlots(FlightListRequest request)
	{
		
	}
}
