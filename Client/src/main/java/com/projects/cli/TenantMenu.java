package com.projects.cli;

import com.projects.logics.Logics;

public class TenantMenu extends AbstractMainMenu
{
	public TenantMenu(Logics logics, String userName) 
	{
		super(logics, userName);
	}

	@Override
	protected void printMenuAndHandleSelection() 
	{
		int selection = AbstractMainMenu.execMenu(mMenuTitle, "Show my payments");
	
		System.out.println();
		handleSelection(selection);
	}

	private void handleSelection(int selection)
	{
		switch(selection)
		{
		case 1:
			showTenantPayments();
			break;
		}
	}

	private void showTenantPayments() 
	{
		int[] paymnets = mLogics.getTenantPayments();
		StringBuilder paidMonths = new StringBuilder();

		for (int i = 0; i < paymnets.length; i++)
		{
			paidMonths.append(String.format("%-15s: %d",getMonthName(i + 1),paymnets[i]));
		}

		System.out.println(paidMonths.toString());
	}
}
