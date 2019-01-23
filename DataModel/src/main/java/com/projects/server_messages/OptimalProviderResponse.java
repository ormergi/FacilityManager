package com.projects.server_messages;

import com.projects.persons.Provider;

@SuppressWarnings("serial")
public class OptimalProviderResponse implements IServerResponse 
{
	private Provider mProvider;
	
	public OptimalProviderResponse(Provider provider) 
	{
		mProvider = provider;
	}
	
	public Provider provider()
	{
		return mProvider;
	}
}
