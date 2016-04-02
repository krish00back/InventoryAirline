package com.frugalbin.inventory.airline.integration;

import java.util.Comparator;

import com.frugalbin.inventory.airline.udchalo.dto.response.LegBean;

public class LegComparator implements Comparator<LegBean>
{

	@Override
	public int compare(LegBean leg1, LegBean leg2)
	{
		return leg1.getDepart().compareTo(leg2.getDepart());
	}

}
