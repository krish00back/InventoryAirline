package com.frugalbin.inventory.airline.caches;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.frugalbin.inventory.airline.models.AirlineConnectionDetails;
import com.frugalbin.inventory.airline.services.impl.ServiceFactory;

public class AirlineConnectionCache extends AbstractCache
{
	private static volatile AirlineConnectionCache instance;

	private Map<Long, AirlineConnectionDetails> connectionMap = new HashMap<Long, AirlineConnectionDetails>();

	private AirlineConnectionCache()
	{
	}

	public static AirlineConnectionCache getInstance()
	{
		if (instance == null)
		{
			synchronized (AirlineConnectionCache.class)
			{
				if (instance == null)
				{
					instance = new AirlineConnectionCache();
				}
			}
		}

		return instance;
	}

	@Override
	public void initializeCache(ServiceFactory serviceFactory)
	{
		super.initializeCache(serviceFactory);
	}

	@Override
	public void refreshCache()
	{
		List<AirlineConnectionDetails> connectionInfo = serviceFactory.getAirlineConnectionDetailsService()
				.getAllConnections();

		connectionMap = connectionInfo.stream().collect(
				Collectors.toMap(AirlineConnectionDetails::getAirlineConnectionId, (c) -> c));
	}

	public AirlineConnectionDetails getAirlineConnectionDetails(Long airlineConnectionId)
	{
		return connectionMap.get(airlineConnectionId);
	}
}
