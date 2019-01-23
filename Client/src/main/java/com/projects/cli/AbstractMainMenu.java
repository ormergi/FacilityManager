package com.projects.cli;

import java.io.IOException;
import java.text.DateFormatSymbols;

import com.projects.logics.Logics;

public abstract class AbstractMainMenu 
{
	protected String mMenuTitle = null;
	protected Logics mLogics = null;
	protected boolean mQuitRequested = false;
	
	public AbstractMainMenu(Logics logics, String userName)
	{
		mLogics = logics;
		mMenuTitle = String.format("Hello %s, Please select: ",userName);
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
		StringBuilder menu = new StringBuilder();

		menu.append(title);
		menu.append("\n");
		for (int i = 0; i < options.length; i++)
		{
			menu.append(String.format("%d. %s. %n",(i + 1), options[i]));
		}
		menu.append("Your selection: ");
		System.out.print(menu.toString());
		
		return MyScanner.readInt(options.length);
	}

	protected String getMonthName(Integer month)
    {
        return DateFormatSymbols.getInstance().getMonths()[month - 1];
    }
}
