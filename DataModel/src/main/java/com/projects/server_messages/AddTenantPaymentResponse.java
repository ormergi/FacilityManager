package com.projects.server_messages;

@SuppressWarnings("serial")
public class AddTenantPaymentResponse implements IServerResponse 
{
	private boolean mSuccess = false;
	
	public AddTenantPaymentResponse(boolean success) 
	{
		mSuccess = success;
	}

	public boolean isSuccessed()
	{
		return mSuccess;
	}
}
