package com.projects.server_messages;

@SuppressWarnings("serial")
public class AddOrUpdateProviderResponse implements IServerResponse 
{
	private boolean mSuccess;
		
	public AddOrUpdateProviderResponse(boolean success) 
	{
		mSuccess = success;
	}
	
	public boolean isSucceeded()
	{
		return mSuccess;
	}
}
