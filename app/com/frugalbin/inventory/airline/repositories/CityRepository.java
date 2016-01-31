package com.frugalbin.inventory.airline.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frugalbin.inventory.airline.models.City;

public interface CityRepository extends JpaRepository<City, Long>
{
	List<City> findByCityName(String cityName);
}
