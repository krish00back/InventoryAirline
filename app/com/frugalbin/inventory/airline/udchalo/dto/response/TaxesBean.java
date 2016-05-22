package com.frugalbin.inventory.airline.udchalo.dto.response;

public class TaxesBean
{
	private String _id;
	private double amount;
	private String code;

	public String get_id()
	{
		return _id;
	}

	public void set_id(String _id)
	{
		this._id = _id;
	}

	public double getAmount()
	{
		return amount;
	}

	public void setAmount(double amount)
	{
		this.amount = amount;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}
}
