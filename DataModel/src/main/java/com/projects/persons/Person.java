package com.projects.persons;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class Person implements Serializable
{
	private String mId;
	private String mFirstName;
	private String mLastName;
	
	protected Person(String id, String firstName, String lastName)
	{
		mId = id;
		mFirstName = firstName;
		mLastName = lastName;
	}
	
	public String id()
	{
		return mId;
	}
	
	public String firstName()
	{
		return mFirstName;
	}
	
	public String lastName()
	{
		return mLastName;
	}
}
