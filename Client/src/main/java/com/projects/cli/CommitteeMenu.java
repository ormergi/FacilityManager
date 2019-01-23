package com.projects.cli;

import com.projects.enums.eProvidersCategories;
import com.projects.persons.Provider;
import com.projects.logics.Logics;

public class CommitteeMenu extends AbstractMainMenu
{
	CommitteeMenu(Logics logics, String userName)
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
			showApartmentsPayments();
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
			showOptimalProviderByCategory();
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
		
		System.out.println(String.format("Paid months: %s" , tenantPayments));
	}

	private void showApartmentsPayments()
	{
		String[] apartmentsPayments = mLogics.getApartmentsPayments();
		StringBuilder apartmentPayments = new StringBuilder();

        for (String apartmentsPayment : apartmentsPayments)
        {
            String apartmentsNumber = apartmentsPayment.substring(0, 1);
            String paidMonths = apartmentsPayment.substring(2);
            apartmentPayments.append(
                    String.format(
                            "Apartment number: %s paid months: %s %n",
                            apartmentsNumber,
                            paidMonths));
        }

		System.out.println(apartmentPayments.toString());
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

		// print months payments
		String[] splitedString = apartmentMonthlyPayments.split(" ");
		StringBuilder apartmentPayments = new StringBuilder();
        apartmentPayments.append("\n");
		int monthNumber = 1;

		for (int i = 0; i < splitedString.length; i += 2)
		{
			monthNumber = Integer.parseInt(splitedString[i]);
			apartmentPayments.append(String.format("%-15s: %s %n", getMonthName(monthNumber), splitedString[i +1]));
		}

		System.out.println(apartmentPayments.toString());
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
		StringBuilder providersDetails = new StringBuilder();
		providersDetails.append("\n");
		for (int i = 0; i < providers.length; i++) 
		{
			providersDetails.append(
			        String.format(
			                "Provider number %d:%n%s %n%n",
                            (i + 1),
                            providers[i].toString()));
		}

		System.out.println(providersDetails.toString());
	}

	private void showOptimalProviderByCategory()
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
		System.out.println(String.format("Optimal provider for %s category is:%n%s", category, providers.toString()));
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
