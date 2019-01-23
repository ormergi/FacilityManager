package com.projects.server_messages;

@SuppressWarnings("serial")
public class ApartmentMonthlyPaymentsResponse implements IServerResponse 
{
	private String mMonthlyPayments;
	
	public ApartmentMonthlyPaymentsResponse(String monthlyPayments)
	{
		mMonthlyPayments = monthlyPayments;
	}
	
	public String monthlyPayments()
	{
		return mMonthlyPayments;
	}
}
