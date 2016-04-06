package com.frugalbin.inventory.airline.services;

import java.util.List;

import com.frugalbin.inventory.airline.models.UserRequests;

public interface UserRequestsServiceI
{
	public UserRequests getUserRequests(Long userRequestId);
	
	public List<UserRequests> getUserRequests(String requestId);
	
	public void addUserRequests(UserRequests userRequest);
}
