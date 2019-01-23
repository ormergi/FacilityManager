package com.projects.server_messages;

@SuppressWarnings("serial")
public class LoginResponse implements IServerResponse
{
	private boolean mIsAuthenticated = false;
	
	public LoginResponse(boolean isAuthenticated) 
	{
		mIsAuthenticated = isAuthenticated;
	}

	public boolean isAuthenticated()
	{
		return mIsAuthenticated;
	}
}
