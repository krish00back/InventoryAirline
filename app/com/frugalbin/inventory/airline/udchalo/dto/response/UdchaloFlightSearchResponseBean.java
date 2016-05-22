package com.frugalbin.inventory.airline.udchalo.dto.response;

import java.util.List;


public class UdchaloFlightSearchResponseBean
{
	private String searchId;
	private boolean success;
	private List<String> validationerrors;

	public String getSearchId()
	{
		return searchId;
	}

	public void setSearchId(String searchId)
	{
		this.searchId = searchId;
	}

	public boolean isSuccess()
	{
		return success;
	}

	public void setSuccess(boolean success)
	{
		this.success = success;
	}

	public List<String> getValidationerrors()
	{
		return validationerrors;
	}

	public void setValidationerrors(List<String> validationerrors)
	{
		this.validationerrors = validationerrors;
	}
}
