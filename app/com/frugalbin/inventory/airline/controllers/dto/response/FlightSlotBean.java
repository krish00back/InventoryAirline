package com.frugalbin.inventory.airline.controllers.dto.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightSlotBean
{
	private String searchId;
	private Map<String, CityBean> cities;
	private String fromCity;
	private String toCity;
	private Map<String, CarrierBean> carriers;
	private Date departDate;
	private Date returnDate;
	private List<SlotBean> slotList;
	
	public String getSearchId()
	{
		return searchId;
	}
	public void setSearchId(String searchId)
	{
		this.searchId = searchId;
	}
	public Map<String, CityBean> getCities()
	{
		return cities;
	}
	public void setCities(Map<String, CityBean> cities)
	{
		this.cities = cities;
	}
	public String getFromCity()
	{
		return fromCity;
	}
	public void setFromCity(String fromCity)
	{
		this.fromCity = fromCity;
	}
	public String getToCity()
	{
		return toCity;
	}
	public void setToCity(String toCity)
	{
		this.toCity = toCity;
	}
	public Map<String, CarrierBean> getCarriers()
	{
		return carriers;
	}
	public void setCarriers(Map<String, CarrierBean> carriers)
	{
		this.carriers = carriers;
	}
	public Date getDepartDate()
	{
		return departDate;
	}
	public void setDepartDate(Date departDate)
	{
		this.departDate = departDate;
	}
	public Date getReturnDate()
	{
		return returnDate;
	}
	public void setReturnDate(Date returnDate)
	{
		this.returnDate = returnDate;
	}
	public List<SlotBean> getSlotList()
	{
		return slotList;
	}
	public void setSlotList(List<SlotBean> slotList)
	{
		this.slotList = slotList;
	}
}
