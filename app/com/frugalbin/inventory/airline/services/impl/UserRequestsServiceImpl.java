package com.frugalbin.inventory.airline.services.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.frugalbin.inventory.airline.models.UserRequests;
import com.frugalbin.inventory.airline.repositories.UserRequestsRepository;
import com.frugalbin.inventory.airline.services.UserRequestsServiceI;

@Named
@Singleton
public class UserRequestsServiceImpl implements UserRequestsServiceI
{
	@Inject
	private UserRequestsRepository repository;
	
	@Override
	public UserRequests getUserRequests(Long requestId)
	{
		return repository.findOne(requestId);
	}

	@Override
	public void addUserRequests(UserRequests userRequest)
	{
		repository.saveAndFlush(userRequest);
	}

	@Override
	public List<UserRequests> getUserRequests(String requestId)
	{
		return repository.findByRequestId(requestId);
	}
}
