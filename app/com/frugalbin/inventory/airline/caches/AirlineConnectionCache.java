package com.frugalbin.inventory.airline.caches;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.frugalbin.inventory.airline.models.AirlineConnectionDetails;
import com.frugalbin.inventory.airline.models.helpers.ConnectionServiceType;
import com.frugalbin.inventory.airline.models.helpers.ConnectionType;
import com.frugalbin.inventory.airline.services.impl.ServiceFactory;

public class AirlineConnectionCache extends AbstractCache
{
	private static volatile AirlineConnectionCache instance;

	private final Map<Long, AirlineConnectionDetails> connectionMap = new HashMap<Long, AirlineConnectionDetails>();

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
		
		AirlineConnectionDetails airlineConnectionDetails = new AirlineConnectionDetails();
		airlineConnectionDetails.setAirlineName("Indigo");
		airlineConnectionDetails.setConnectionServiceType(ConnectionServiceType.REST);
		airlineConnectionDetails.setConnectionType(ConnectionType.EXTERNAL);
		airlineConnectionDetails.setConnectionUrl("https://www.googleapis.com/qpxExpress/v1/trips/search");
	}

	@Override
	public void refreshCache()
	{
		List<AirlineConnectionDetails> connectionInfoList = serviceFactory.getAirlineConnectionDetailsService()
				.getAllConnections();

//		connectionMap.putAll(connectionInfo.stream().collect(
//				Collectors.toMap(AirlineConnectionDetails::getAirlineConnectionId, (c) -> c)));
		
		for (AirlineConnectionDetails connectionInfo : connectionInfoList)
		{
			connectionMap.put(connectionInfo.getAirlineConnectionId(), connectionInfo);
		}
	}

	public AirlineConnectionDetails getAirlineConnectionDetails(Long airlineConnectionId)
	{
		return connectionMap.get(airlineConnectionId);
	}
	
	public List<AirlineConnectionDetails> getAirlineConnectionDetailsList()
	{
		return new ArrayList<AirlineConnectionDetails>(connectionMap.values());
	}
}
