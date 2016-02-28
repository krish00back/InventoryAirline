package com.frugalbin.inventory.airline.scheduler;

import java.util.TimerTask;

import com.frugalbin.inventory.airline.caches.AirlineConnectionCache;
import com.frugalbin.inventory.airline.models.AirlineConnectionDetails;
import com.frugalbin.inventory.airline.models.helpers.ConnectionServiceType;
import com.frugalbin.inventory.airline.models.helpers.ConnectionType;

import play.libs.F.Promise;
import play.libs.WS;
import play.libs.WS.Response;

public class InventoryDetailsUpdater extends TimerTask
{
	@Override
	public void run()
	{
		for (AirlineConnectionDetails airlineConnectionDetails : AirlineConnectionCache.getInstance()
				.getAirlineConnectionDetailsList())
		{
			Promise<Response> res;
			if (ConnectionServiceType.REST.equals(airlineConnectionDetails.getConnectionServiceType()))
			{
				String requestJSON = airlineConnectionDetails.getConnectionSearchRequest();
				
				res = WS.url(airlineConnectionDetails.getConnectionUrl()).post(
						/*request().body().asJson()*/"");
			}
			else
			{
				// SOAP
				
			}
		}

		// res.get().getBody();

//		return convertObjectToJsonResponse(res.get(5000).asJson());
	}
}
