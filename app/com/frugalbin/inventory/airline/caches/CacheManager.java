package com.frugalbin.inventory.airline.caches;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frugalbin.inventory.airline.controllers.rest.InventoryAirlineController;
import com.frugalbin.inventory.airline.services.impl.ServiceFactory;

@Named
@Singleton
public class CacheManager
{
	private static final Logger LOGGER = LoggerFactory.getLogger(InventoryAirlineController.class);

	private List<Cache> cacheList = new ArrayList<Cache>();

	@Inject
	private ServiceFactory serviceFactory;

	public CacheManager()
	{
		cacheList.add(CityCache.getInstance());
		cacheList.add(AirlineConnectionCache.getInstance());
	}

	@PostConstruct
	public void init()
	{
		for (Cache cache : cacheList)
		{
			cache.initializeCache(serviceFactory);
		}

		refreshCaches();
	}

	public void refreshCaches()
	{
		LOGGER.info("Refreshing Caches called");
		for (Cache cache : cacheList)
		{
			cache.refreshCache();
		}
		LOGGER.info("Refreshing Caches done");
	}
}