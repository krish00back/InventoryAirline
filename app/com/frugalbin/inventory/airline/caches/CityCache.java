package com.frugalbin.inventory.airline.caches;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.frugalbin.inventory.airline.models.City;
import com.frugalbin.inventory.airline.services.impl.ServiceFactory;

public class CityCache extends AbstractCache
{
	private static volatile CityCache instance;

	private Map<Long, City> cityMap = new HashMap<Long, City>();

	private CityCache()
	{
	}

	public static CityCache getInstance()
	{
		if (instance == null)
		{
			synchronized (CityCache.class)
			{
				if (instance == null)
				{
					instance = new CityCache();
				}
			}
		}

		return instance;
	}

	@Override
	public void initializeCache(ServiceFactory serviceFactory)
	{
		super.initializeCache(serviceFactory);

		// TODO: Remove this when using actual DB
		City city = new City();
		city.setCityName("Pune");
		city.setCityState("Maharashtra");
		this.serviceFactory.getCityService().insertCity(city);

		city = new City();
		city.setCityName("Bengaluru");
		city.setCityState("Karnataka");
		this.serviceFactory.getCityService().insertCity(city);

		city = new City();
		city.setCityName("Delhi");
		city.setCityState("Delhi");
		this.serviceFactory.getCityService().insertCity(city);

		city = new City();
		city.setCityName("Agra");
		city.setCityState("Uttar Pradesh");
		this.serviceFactory.getCityService().insertCity(city);
	}

	@Override
	public void refreshCache()
	{
		List<City> cityList = serviceFactory.getCityService().getAllCities();

		cityMap = cityList.stream().collect(Collectors.toMap(City::getCityId, (c) -> c));
	}

	public City getCity(Long cityId)
	{
		return cityMap.get(cityId);
	}

	public List<City> getCityList()
	{
		return new ArrayList<City>(cityMap.values());
	}

}
