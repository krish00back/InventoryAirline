package com.frugalbin.inventory.airline.udchalo;

import java.util.Date;

import javax.inject.Named;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import play.libs.F.Promise;
import play.libs.WS.Response;

import com.frugalbin.common.exceptions.BusinessException;
import com.frugalbin.common.rest.client.RestClient;
import com.frugalbin.common.utils.Utils;
import com.frugalbin.inventory.airline.protocol.rest.InventoryAirlineRestProtocol;
import com.frugalbin.inventory.airline.udchalo.dto.response.UdchaloAuthenticationRequestBean;
import com.frugalbin.inventory.airline.udchalo.dto.response.UdchaloAuthenticationResponseBean;
import com.frugalbin.inventory.airline.utils.Constants;

@Named
@Singleton
public class UdchaloAuthenticator
{
	private static final Logger LOGGER = LoggerFactory.getLogger(UdchaloAuthenticator.class);

	private UdchaloAuthenticationRequestBean authenticationRequestBean = new UdchaloAuthenticationRequestBean();
	private String token;
	private Date tokenTime;

	public String getToken()
	{
		if (token == null || new Date().getTime() - tokenTime.getTime() > Constants.UDCHALO_AUTH_EXPIRE_TIME)
		{
			getAuthToken();
		}
		return token;
	}

	private void getAuthToken()
	{
		Promise<Response> promiseResponse;
		try
		{
			promiseResponse = RestClient.sendRequest(InventoryAirlineRestProtocol.UDCHALO_AUTHENTICATE_REQUEST,
					authenticationRequestBean);
			UdchaloAuthenticationResponseBean response = Utils.convertJsonNodeToObject(
					promiseResponse.get(com.frugalbin.common.utils.Constants.REST_TIMEOUT).asJson(),
					UdchaloAuthenticationResponseBean.class);

			if (!response.getSuccess())
			{
				LOGGER.error("Couldn't get the token, ", response.getMessage());
				return;
			}

			token = response.getToken();
			tokenTime = new Date();
		}
		catch (Exception | BusinessException e)
		{
			LOGGER.error("Couldn't get the token, ", e);
			return;
		}
	}
}
