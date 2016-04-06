package com.frugalbin.inventory.airline.udchalo.dto.response;

public class LegCombinationsBean
{
	private long onwardLegId;
	private long returnLegId;
	private long fareId;

	public long getOnwardLegId()
	{
		return onwardLegId;
	}

	public long getReturnLegId()
	{
		return returnLegId;
	}

	public long getFareId()
	{
		return fareId;
	}

	public void setOnwardLegId(long onwardLegId)
	{
		this.onwardLegId = onwardLegId;
	}

	public void setReturnLegId(long returnLegId)
	{
		this.returnLegId = returnLegId;
	}

	public void setFareId(long fareId)
	{
		this.fareId = fareId;
	}
}
