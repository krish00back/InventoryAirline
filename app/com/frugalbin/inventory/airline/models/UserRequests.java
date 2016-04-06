package com.frugalbin.inventory.airline.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.frugalbin.inventory.airline.utils.Constants;

@Entity
@Table(name = Constants.USER_REQUEST_TABLE)
public class UserRequests
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = Constants.ID_COLUMN)
	private Long id;
	
	@Column(name = Constants.UR_REQUEST_ID_COLUMN)
	private String requestId;
	
	@ManyToOne
	private City fromCity;
	
	@ManyToOne
	private City toCity;
	
	@Column(name = Constants.UR_DEPARTURE_DATE_COLUMN)
	private Date departureDate;
	
	@Column(name = Constants.UR_ADULT_PASSENGER_COUNT_COLUMN)
	private Integer adultCount;
	
	@Column(name = Constants.UR_INFANTS_PASSENGER_COUNT_COLUMN)
	private Integer infantCount;
	
	@Column(name = Constants.UR_USER_ID_COLUMN)
	private Long userId;
	
	@Column(name = Constants.UR_PNR_COLUMN)
	private String pnr;

	public String getRequestId()
	{
		return requestId;
	}

	public void setRequestId(String requestId)
	{
		this.requestId = requestId;
	}

	public City getFromCity()
	{
		return fromCity;
	}

	public void setFromCity(City fromCity)
	{
		this.fromCity = fromCity;
	}

	public City getToCity()
	{
		return toCity;
	}

	public void setToCity(City toCity)
	{
		this.toCity = toCity;
	}

	public Date getDepartureDate()
	{
		return departureDate;
	}

	public void setDepartureDate(Date departureDate)
	{
		this.departureDate = departureDate;
	}

	public Integer getAdultCount()
	{
		return adultCount;
	}

	public void setAdultCount(Integer adultCount)
	{
		this.adultCount = adultCount;
	}

	public Integer getInfantCount()
	{
		return infantCount;
	}

	public void setInfantCount(Integer infantCount)
	{
		this.infantCount = infantCount;
	}

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public String getPnr()
	{
		return pnr;
	}

	public void setPnr(String pnr)
	{
		this.pnr = pnr;
	}
}
