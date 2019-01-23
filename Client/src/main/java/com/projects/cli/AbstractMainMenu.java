package com.projects.cli;

import java.io.IOException;

import com.projects.logics.Logics;

public abstract class AbstractMainMenu 
{
	protected String mMenuTitle = null;
	protected Logics mLogics = null;
	protected boolean mQuitRequested = false;
	
	public AbstractMainMenu(Logics logics, String userName)
	{
		mLogics = logics;
		mMenuTitle = "Hello " + userName + ", Please select: ";
	}
	
	public void loop()
	{
		while (!mQuitRequested)
		{
			System.out.println();
			printMenuAndHandleSelection();
			System.out.println();
			waitForAnyKeyEntered();
		}	
	}

	protected abstract void printMenuAndHandleSelection();
	
	private void waitForAnyKeyEntered() 
	{
		MyScanner.flush();
		
		System.out.println("Enter any key to continue...");
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println();
		System.out.println();
	}
	
	public static int execMenu(String title, String... options)
	{
		System.out.println(title);
		
		for (int i = 0; i < options.length; i++)
		{
			System.out.println((i + 1) + ". " + options[i] + ".");
		}
		
		System.out.print("Your selection: ");
		
		return MyScanner.readInt(options.length);
	}
}
