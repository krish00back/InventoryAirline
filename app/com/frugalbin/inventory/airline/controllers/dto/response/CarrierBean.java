package com.frugalbin.inventory.airline.controllers.dto.response;

public class CarrierBean
{
	private String id;
	private String name;

	public CarrierBean()
	{
	}

	public CarrierBean(String id, String name)
	{
		this.id = id;
		this.name = name;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
