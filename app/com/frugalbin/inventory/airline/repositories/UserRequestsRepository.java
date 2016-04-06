package com.frugalbin.inventory.airline.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frugalbin.inventory.airline.models.UserRequests;

public interface UserRequestsRepository extends JpaRepository<UserRequests, Long>
{
	public List<UserRequests> findByRequestId(String requestId);
}
