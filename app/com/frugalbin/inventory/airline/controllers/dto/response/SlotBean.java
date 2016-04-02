package com.frugalbin.inventory.airline.controllers.dto.response;

import java.util.List;

public class SlotBean
{
	private String slotId;
	private String slotHeading;
	private SlotRangeBean slotRange;
	private double bestPrice;
	private List<PriceCheckRequestBean> bestPriceCheckRequests;
	private List<MarketFlightDetailsBean> marketFlights;

	public String getSlotId()
	{
		return slotId;
	}

	public void setSlotId(String slotId)
	{
		this.slotId = slotId;
	}

	public String getSlotHeading()
	{
		return slotHeading;
	}

	public void setSlotHeading(String slotHeading)
	{
		this.slotHeading = slotHeading;
	}

	public SlotRangeBean getSlotRange()
	{
		return slotRange;
	}

	public void setSlotRange(SlotRangeBean slotRange)
	{
		this.slotRange = slotRange;
	}

	public double getBestPrice()
	{
		return bestPrice;
	}

	public void setBestPrice(double bestPrice)
	{
		this.bestPrice = bestPrice;
	}

	public List<PriceCheckRequestBean> getBestPriceCheckRequests()
	{
		return bestPriceCheckRequests;
	}

	public void setBestPriceCheckRequests(List<PriceCheckRequestBean> bestPriceCheckRequests)
	{
		this.bestPriceCheckRequests = bestPriceCheckRequests;
	}

	public List<MarketFlightDetailsBean> getMarketFlights()
	{
		return marketFlights;
	}

	public void setMarketFlights(List<MarketFlightDetailsBean> marketFlights)
	{
		this.marketFlights = marketFlights;
	}
}
