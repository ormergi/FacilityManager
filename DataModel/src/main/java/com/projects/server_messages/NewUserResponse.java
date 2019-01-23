package com.projects.server_messages;

@SuppressWarnings("serial")
public class NewUserResponse implements IServerResponse
{
	private boolean mSuccess = false;
	
	public NewUserResponse(boolean success) 
	{
		mSuccess = success;
	}

	public boolean isSucceeded()
	{
		return mSuccess;
	}
}
