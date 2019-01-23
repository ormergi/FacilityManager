package com.projects.server_messages;

import com.projects.persons.Person;

@SuppressWarnings("serial")
public class ConnectedPersonDetailsResponse implements IServerResponse 
{
	private Person mPerson;
	
	public ConnectedPersonDetailsResponse(Person person) 
	{
		mPerson = person;
	}
	
	public Person provider()
	{
		return mPerson;
	}
}
