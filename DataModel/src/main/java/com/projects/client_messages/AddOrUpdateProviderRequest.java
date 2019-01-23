package com.projects.client_messages;

import com.projects.persons.Provider;

@SuppressWarnings("serial")
public class AddOrUpdateProviderRequest implements IClientRequest
{
	private Provider mProvider;
	
	public AddOrUpdateProviderRequest(Provider provider)
	{
		mProvider = provider;
	}
	
	public Provider provider() 
	{
		return mProvider;
	}
}
