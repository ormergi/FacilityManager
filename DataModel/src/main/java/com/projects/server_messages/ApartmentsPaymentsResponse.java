package com.projects.server_messages;

@SuppressWarnings("serial")
public class ApartmentsPaymentsResponse implements IServerResponse 
{
	private String[] mApartmentsPayments = null;
	
	public ApartmentsPaymentsResponse(String[] apartmentsPayments) 
	{
		mApartmentsPayments = apartmentsPayments;
	}
	
	public String[] apartmentsPayments()
	{
		return mApartmentsPayments;
	}
}
