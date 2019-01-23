package com.projects.cli;

import com.projects.enums.eProvidersCategories;
import com.projects.persons.Provider;
import com.projects.logics.Logics;

public class CommitteeMenu extends AbstractMainMenu
{
	public CommitteeMenu(Logics logics, String userName) 
	{
		super(logics, userName);
	}

	@Override
	protected void printMenuAndHandleSelection() 
	{
		int selection = AbstractMainMenu.execMenu(mMenuTitle, 
				"Show which month a tenant has paid",
				"Show apartments paid months",
				"Add tenant payment",
				"Show apartment's monthly payments",
				"Show providers list",
				"Find optimal provider by category",
				"Add or update provider");
		
		System.out.println();
		handleSelection(selection);
	}
	
	private void handleSelection(int selection)
	{
		switch(selection)
		{
		case 1:
			showTenantMonthsPaid();
			break;
		case 2:
			showApartpmentsPayments();
			break;
		case 3:
			addTenantPayment();
			break;
		case 4:
			showApartmentMonthlyPayments();
			break;
		case 5:
			showProvidersList();
			break;
		case 6:
			showOptiomalProviderByCategory();
			break;
		case 7:
			addOrUpdateProvider();
			break;
		}
	}

	private void showTenantMonthsPaid() 
	{
		System.out.print("Enter tenant ID: ");
		
		String tenantId = MyScanner.readString();	
		String tenantPayments = mLogics.getTenantMonthsPaid(tenantId);
		
		System.out.println("Paid months: " + tenantPayments);
	}

	private void showApartpmentsPayments() 
	{
		String[] apartmentsPayments = mLogics.getApartmentsPayments();
		
		for (int i = 0; i < apartmentsPayments.length; i++) 
		{
			String apartmentsNumber = apartmentsPayments[i].substring(0, 1);
			String paidMonths = apartmentsPayments[i].substring(2);
			
			System.out.println("Apartment number " + apartmentsNumber + " paid months: " + paidMonths);
		}
	}

	private void addTenantPayment() 
	{
		System.out.print("Enter tenant ID: ");		
		String tenantId = MyScanner.readString();

		System.out.print("Enter month: ");		
		int month= MyScanner.readInt(12);

		System.out.print("Enter amount: ");		
		int amount = MyScanner.readInt();
		
		boolean success = mLogics.addTenantPayment(tenantId, month, amount);
		
		if (success)
		{
			System.out.println("Payment added successfully");
		}
		else
		{
			System.out.println("Payment failed, try again");
		}		
	}

	private void showApartmentMonthlyPayments() 
	{
		System.out.print("Enter apartment number: ");
		int apartmentNumber = MyScanner.readInt();
		
		String apartmentMonthlyPayments = mLogics.getApartmentMonthlyPayments(apartmentNumber);
		
		System.out.println();
		
		// print months payments
		String[] splitedString = apartmentMonthlyPayments.split(" ");
		
		for (int i = 0; i < splitedString.length; i += 2) 
		{
			System.out.println("Month number " + splitedString[i] + ": " + splitedString[i + 1]);
		}
	}

	private void showProvidersList() 
	{
		int selection = AbstractMainMenu.execMenu("Which providers category?", 
				eProvidersCategories.All.toString(),
				eProvidersCategories.Cleaning.toString(),
				eProvidersCategories.Plumbing.toString(),
				eProvidersCategories.Gardening.toString(),
				eProvidersCategories.Electrics.toString());
		
		eProvidersCategories category = eProvidersCategories.All;
		
		switch (selection)
		{
		case 1:
			category = eProvidersCategories.All;
			break;
		case 2:
			category = eProvidersCategories.Cleaning;
			break;
		case 3:
			category = eProvidersCategories.Plumbing;
			break;
		case 4:
			category = eProvidersCategories.Gardening;
			break;
		case 5:
			category = eProvidersCategories.Electrics;
			break;
		}
		
		Provider[] providers = mLogics.getProvidersByCategory(category);
		
		for (int i = 0; i < providers.length; i++) 
		{
			System.out.println();
			System.out.println("Provider number " + (i + 1) + ":");
			System.out.println(providers[i].toString());
		}	
	}

	private void showOptiomalProviderByCategory() 
	{
		int selection = AbstractMainMenu.execMenu("Which providers category?",
				eProvidersCategories.Cleaning.toString(),
				eProvidersCategories.Plumbing.toString(),
				eProvidersCategories.Gardening.toString(),
				eProvidersCategories.Electrics.toString());
		
		eProvidersCategories category = eProvidersCategories.All;
		
		switch (selection)
		{
		case 1:
			category = eProvidersCategories.Cleaning;
			break;
		case 2:
			category = eProvidersCategories.Plumbing;
			break;
		case 3:
			category = eProvidersCategories.Gardening;
			break;
		case 4:
			category = eProvidersCategories.Electrics;
			break;
		}
		
		Provider providers = mLogics.getOptimalProvider(category);

		System.out.println();
		System.out.println("Optimal provider for " + category + " category is:");
		System.out.println(providers.toString());
	}

	private void addOrUpdateProvider() 
	{
		int selection = AbstractMainMenu.execMenu("Which providers category?",
				eProvidersCategories.Cleaning.toString(),
				eProvidersCategories.Plumbing.toString(),
				eProvidersCategories.Gardening.toString(),
				eProvidersCategories.Electrics.toString());
		
		eProvidersCategories category = eProvidersCategories.All;
		
		switch (selection)
		{
		case 1:
			category = eProvidersCategories.Cleaning;
			break;
		case 2:
			category = eProvidersCategories.Plumbing;
			break;
		case 3:
			category = eProvidersCategories.Gardening;
			break;
		case 4:
			category = eProvidersCategories.Electrics;
			break;
		}
		
		System.out.print("Enter ID: ");
		String id = MyScanner.readString();
		
		System.out.print("Enter first name (no spaces): ");
		String firstName = MyScanner.readString();
		
		System.out.print("Enter last name (no spaces): ");
		String lastName = MyScanner.readString();
		
		System.out.print("Enter availability (number from 1 to 10): ");
		int availability = MyScanner.readInt(10);
		
		System.out.print("Enter quality (number from 1 to 10): ");
		int quality = MyScanner.readInt(10);
		
		System.out.print("Enter price (number): ");
		int price = MyScanner.readInt();
		
		System.out.print("Enter phone: ");
		String phone = MyScanner.readString();
		
		Provider provider = new Provider(id, firstName, lastName, category, availability, quality, price, phone);
		
		boolean success = mLogics.addOrUpdateProvider(provider);
		
		if (success)
		{
			System.out.println("Provider has added / updated successfully");
		}
		else
		{
			System.out.println("Error while adding / updating provider");
		}
	}
}
