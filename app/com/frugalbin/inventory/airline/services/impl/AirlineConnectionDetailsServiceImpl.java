package com.frugalbin.inventory.airline.services.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.frugalbin.inventory.airline.models.AirlineConnectionDetails;
import com.frugalbin.inventory.airline.repositories.AirlineConnectionDetailsRepository;
import com.frugalbin.inventory.airline.services.AirlineConnectionDetailsServiceI;

@Named
@Singleton
public class AirlineConnectionDetailsServiceImpl implements AirlineConnectionDetailsServiceI
{
	@Inject
	private AirlineConnectionDetailsRepository repository;

	@Override
	public List<AirlineConnectionDetails> getAllConnections()
	{
		return repository.findAll();
	}
}
