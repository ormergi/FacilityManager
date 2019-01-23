package com.projects.server_messages;

@SuppressWarnings("serial")
public class TenantPaymentsResponse implements IServerResponse 
{
	private int[] mPayments; // should be array of 12 months
	
	public TenantPaymentsResponse(int[] payments) 
	{
		mPayments = payments;
	}
	
	public int[] payments()
	{
		return mPayments;
	}
}
