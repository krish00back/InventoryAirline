package com.frugalbin.inventory.airline.udchalo.dto.response;

public class UdchaloAuthenticationResponseBean
{
	private Boolean success;
	private String message;
	private String token;
	private AppBean app;

	public Boolean getSuccess()
	{
		return success;
	}

	public void setSuccess(Boolean success)
	{
		this.success = success;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	public AppBean getApp()
	{
		return app;
	}

	public void setApp(AppBean app)
	{
		this.app = app;
	}
}
