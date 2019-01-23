package com.projects.persons;

import com.projects.enums.eProvidersCategories;

@SuppressWarnings("serial")
public class Provider extends Person
{
	private eProvidersCategories mCategory;
	private int mAvailability;
	private int mQuality;
	private int mPrice;
	private String m_phone;
	
	public Provider(String id, String firstName, String lastName, 
			eProvidersCategories category, int availability, 
			int quality, int price, String phone) 
	{
		super(id, firstName, lastName);
		
		mCategory = category;
		mAvailability = availability;
		mQuality = quality;
		mPrice = price;
		m_phone = phone;
	}
	
	public eProvidersCategories category() 
	{
		return mCategory;
	}
	
	public int availablity()
	{
		return mAvailability;
	}
	
	public int quailty()
	{
		return mQuality;
	}
	
	public int price()
	{
		return mPrice;
	}
	
	public String phone()
	{
		return m_phone;
	}
	
	public String toString()
	{
		return 
				
		"First Name: " + firstName() + "\n" +
		"Last Name: " + lastName() + "\n" +
		"ID: " + id() + "\n" +
		"Category: " + category().toString() + "\n" +
		"Availability: " + availablity() + "\n" +
		"Quality: " + quailty() + "\n" +
		"Price: " + price() + "\n" +
		"Phone: " + phone();
	}
}
