package com.frugalbin.inventory.airline.services.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.frugalbin.inventory.airline.models.City;
import com.frugalbin.inventory.airline.repositories.CityRepository;
import com.frugalbin.inventory.airline.services.CityServiceI;

@Named
@Singleton
public class CityServiceImpl implements CityServiceI
{
	@Inject
	private CityRepository cityRepository;

	@Override
	public List<City> getAllCities()
	{
		return cityRepository.findAll();
	}

	@Override
	public void insertCity(City city)
	{
		cityRepository.saveAndFlush(city);
	}

	@Override
	public City findCity(Long cityId)
	{
		return cityRepository.findOne(cityId);
	}
}
