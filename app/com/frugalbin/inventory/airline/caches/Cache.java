package com.frugalbin.inventory.airline.caches;

import com.frugalbin.inventory.airline.services.impl.ServiceFactory;

public interface Cache
{
	void refreshCache();

	void setServiceFactory(ServiceFactory serviceFactory);

	void initializeCache(ServiceFactory serviceFactory);
}
