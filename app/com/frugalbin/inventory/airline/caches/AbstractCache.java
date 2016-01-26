package com.frugalbin.inventory.airline.caches;

import com.frugalbin.inventory.airline.services.impl.ServiceFactory;

public abstract class AbstractCache implements Cache
{
	private boolean isInitialized = false;

	protected ServiceFactory serviceFactory;

	public AbstractCache()
	{
	}

	@Override
	public void initializeCache(ServiceFactory serviceFactory)
	{
		this.serviceFactory = serviceFactory;

		if (!isInitialized())
		{
			refreshCache();
			isInitialized = true;
		}
	}

	protected boolean isInitialized()
	{
		return isInitialized;
	}

	@Override
	public void setServiceFactory(ServiceFactory serviceFactory)
	{
		this.serviceFactory = serviceFactory;
	}
}