package com.projects.client_messages;

import com.projects.enums.*;

@SuppressWarnings("serial")
public class LoginRequest implements IClientRequest
{
	private eClientKind mClientKind;
	private String mId;
	private String mPassword;
	
	public LoginRequest(eClientKind kind, String id, String password)
	{
		mClientKind = kind;
		mId = id;
		mPassword = password;
	}
	
	public eClientKind clientKind() 
	{
		return mClientKind;
	}
	
	public String id() 
	{
		return mId;
	}
	
	public String password() 
	{
		return mPassword;
	}
}