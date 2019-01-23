package com.projects.server_messages;

@SuppressWarnings("serial")
public class TenantMonthsPaidResponse implements IServerResponse 
{
	private String m_tenantPayments = null;
	
	public TenantMonthsPaidResponse(String tenantPayments) 
	{
		m_tenantPayments = tenantPayments;
	}
	
	public String tenantPayments()
	{
		return m_tenantPayments;
	}
}
