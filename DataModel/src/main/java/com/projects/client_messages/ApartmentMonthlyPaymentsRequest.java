package com.projects.client_messages;

@SuppressWarnings("serial")
public class ApartmentMonthlyPaymentsRequest implements IClientRequest 
{
	private int mApartmentNumber;
	
	public ApartmentMonthlyPaymentsRequest(int apartmentNumber)
	{
		mApartmentNumber = apartmentNumber;
	}
	
	public int apartmentNumber() 
	{
		return mApartmentNumber;
	}
}
