package com.projects.client_messages;

@SuppressWarnings("serial")
public class TenantMonthsPaidRequest implements IClientRequest 
{
	private String mTenantId = null;
	
	public TenantMonthsPaidRequest(String tenantId)
	{
		mTenantId = tenantId;
	}
	
	public String tenantId()
	{
		return mTenantId;
	}
}
