package com.projects.persons;

@SuppressWarnings("serial")
public class Tenant extends Person
{
	private int mApartmentNumber;
	private int m_monthlyPaymentAmount;
	
	public Tenant(String id, String firstName, String lastName, int apartmentNumber, int monthlyPaymentAmount) 
	{
		super(id, firstName, lastName);
		
		mApartmentNumber = apartmentNumber;
		m_monthlyPaymentAmount = monthlyPaymentAmount;
	}
	
	public int apartmentNumber()
	{
		return mApartmentNumber;
	}
	
	public int monthlyPaymentAmount()
	{
		return m_monthlyPaymentAmount;
	}
}
