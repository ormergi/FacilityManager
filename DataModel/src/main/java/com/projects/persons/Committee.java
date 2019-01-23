package com.projects.persons;

@SuppressWarnings("serial")
public class Committee extends Person 
{
	private int mSeniority;
	
	public Committee(String id, String firstName, String lastName, int seniority) 
	{
		super(id, firstName, lastName);
		mSeniority = seniority;
	}
	
	public int seniority()
	{
		return mSeniority;
	}
}
