package com.projects.cli;

import com.projects.persons.Provider;
import com.projects.logics.Logics;

public class ProviderMenu extends AbstractMainMenu
{
	public ProviderMenu(Logics logics, String userName) 
	{
		super(logics, userName);
	}

	@Override
	protected void printMenuAndHandleSelection() 
	{
		int selection = AbstractMainMenu.execMenu(mMenuTitle, "Show my details");
	
		System.out.println();
		handleSelection(selection);
	}

	private void handleSelection(int selection)
	{
		switch(selection)
		{
		case 1:
			showProviderDetails();
			break;
		}
	}

	private void showProviderDetails() 
	{
		Provider provider = (Provider)mLogics.getConnectedPersonDetails();
		
		System.out.println(provider.toString());
	}
}
