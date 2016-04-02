package com.frugalbin.inventory.airline.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.frugalbin.inventory.airline.models.helpers.Gender;
import com.frugalbin.inventory.airline.utils.Constants;

@Entity
@Table(name = Constants.PASSENGER_DETAILS_TABLE, schema = Constants.INVENTORY_AIRLINE_SCHEMA)
public class PassengerDetails
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = Constants.ID_COLUMN)
	private Long passengerId;

	@Column(name = Constants.PD_FIRST_NAME_COLUMN)
	private String firstName;

	@Column(name = Constants.PD_LAST_NAME_COLUMN)
	private String lastName;

	@Column(name = Constants.PD_AGE_COLUMN)
	private Integer age;

	@Enumerated(EnumType.STRING)
	@Column(name = Constants.PD_GENDER_COLUMN)
	private Gender gender;

	public Long getPassengerId()
	{
		return passengerId;
	}

	public void setPassengerId(Long passengerId)
	{
		this.passengerId = passengerId;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public Integer getAge()
	{
		return age;
	}

	public void setAge(Integer age)
	{
		this.age = age;
	}

	public Gender getGender()
	{
		return gender;
	}

	public void setGender(Gender gender)
	{
		this.gender = gender;
	}
}