package com.projects.server_messages;

import com.projects.persons.Provider;

@SuppressWarnings("serial")
public class ProviderByCategoryResponse implements IServerResponse 
{
	private Provider[] m_providers;
	
	public ProviderByCategoryResponse(Provider[] providers) 
	{
		m_providers = providers;
	}
	
	public Provider[] providers()
	{
		return m_providers;
	}
}
