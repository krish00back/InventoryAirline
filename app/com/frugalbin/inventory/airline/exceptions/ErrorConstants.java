package com.frugalbin.inventory.airline.exceptions;

/**
 * Application Error Handled constants.
 * 
 * @author pkonwar
 */
public enum ErrorConstants
{

	// Error Code Series : 4XX : Client Side Error (Frontend Error)
	INVALID_REQUEST_DATA("403", "Invalid Request Data"),
	INVALID_RESPONSE_FORMAT("404", "Invalid Response data"),
	CONTACT_SYSTEM_ADMINISTRATOR("500", "System Error. Contact system administrator");
	public String errorCode;
	public String errorMessage;

	private ErrorConstants(String errorCode, String errorMessage)
	{
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
}
