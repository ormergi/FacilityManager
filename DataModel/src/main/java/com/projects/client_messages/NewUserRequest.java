package com.projects.client_messages;

import com.projects.persons.Person;

@SuppressWarnings("serial")
public class NewUserRequest implements IClientRequest
{
	private Person mPerson;
	private String mPassword;
	
	public NewUserRequest(Person person, String password)
	{
		mPerson = person;
		mPassword = password;
	}
	
	public Person person()
	{
		return mPerson;
	}
	
	public String password()
	{
		return mPassword;
	}
}
