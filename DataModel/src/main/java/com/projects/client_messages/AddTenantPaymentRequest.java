package com.projects.client_messages;

@SuppressWarnings("serial")
public class AddTenantPaymentRequest implements	IClientRequest
{
	private String mTenantId = null;
	private int mMonth = 0;
	private int mAmount = 0;
	
	public AddTenantPaymentRequest(String tenantId, int month, int amount)
	{
		mTenantId = tenantId;
		mMonth = month;
		mAmount = amount;
	}
	
	public String tenantId()
	{
		return mTenantId;
	}
	
	public int month()
	{
		return mMonth;
	}
	
	public int amount()
	{
		return mAmount;
	}
}
