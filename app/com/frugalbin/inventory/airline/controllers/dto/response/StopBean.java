package com.frugalbin.inventory.airline.controllers.dto.response;

import java.util.List;

public class StopBean
{
	private int noOfStop;
	private List<StopDetailsBean> stopDetails;

	public int getNoOfStop()
	{
		return noOfStop;
	}

	public void setNoOfStop(int noOfStop)
	{
		this.noOfStop = noOfStop;
	}

	public List<StopDetailsBean> getStopDetails()
	{
		return stopDetails;
	}

	public void setStopDetails(List<StopDetailsBean> stopDetails)
	{
		this.stopDetails = stopDetails;
	}
}
