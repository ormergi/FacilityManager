package com.projects.cli;

import com.projects.enums.*;
import com.projects.persons.*;
import com.projects.logics.Logics;

public class CliUserInterface 
{
	private Logics mLogics;
	
	public CliUserInterface(Logics logics) 
	{
		mLogics = logics;
	}

	public void start() 
	{
		System.out.println("----- Facility Tenants Manager ----");
		eClientKind kind = selectClientKind();
		System.out.println();
		Person person = login(kind);
		
		AbstractMainMenu mainMenu = createMenu(person);
		
		mainMenu.loop();	
	}

	private AbstractMainMenu createMenu(Person person) 
	{
		AbstractMainMenu mainMenu = null;
		
		if (person instanceof Committee)
		{
			mainMenu = new CommitteeMenu(mLogics, person.firstName());
		}
		else if (person instanceof Tenant)
		{
			mainMenu = new TenantMenu(mLogics, person.firstName());			
		}
		else if(person instanceof Provider)
		{
			mainMenu = new ProviderMenu(mLogics, person.firstName());				
		}
		
		return mainMenu;
	}

	private eClientKind selectClientKind() 
	{
		eClientKind kind = eClientKind.Committee;
		int selection = AbstractMainMenu.execMenu(
				"Welcome! Who are you?",
				"Comittee",
				"Tenant",
				"Provider");
		
		switch(selection)
		{
		case 1:
			kind = eClientKind.Committee;
			break;
		case 2:			
			kind = eClientKind.Tenant;
			break;
		case 3:			
			kind = eClientKind.Provider;
			break;
		}
		
		return kind;
	}
	
	private Person login(eClientKind clientKind) 
	{
		Person person = null;
		
		if (clientKind == eClientKind.Provider)
		{
			person = signInExistingUser(clientKind);
		}
		else
		{
			int selection = AbstractMainMenu.execMenu(
					"Hi " + clientKind + ", " +
							"please select:",
					"Sign in",
					"Create new user");
			
			System.out.println();
			
			switch (selection)
			{
			case 1:
				person = signInExistingUser(clientKind);
				break;
			case 2:
				person = createNewUser(clientKind);
				break;
			}
		}
		
		return person;
	}

	private Person signInExistingUser(eClientKind clientkind) 
	{
		boolean authenticated = false;
		
		while (!authenticated)
		{
			System.out.print("Enter ID: ");
			String id = MyScanner.readString();
			
			System.out.print("Enter Password: ");
			String password = MyScanner.readString();
			
			authenticated = mLogics.login(clientkind, id, password);
		
			if (!authenticated)
			{
				System.out.println("User not authenticated, try again.");
			}
		}		
		
		return mLogics.getConnectedPersonDetails();
	}

	private Person createNewUser(eClientKind clientKind) 
	{
		Person newPerson = null;
		
		System.out.print("Your ID: ");
		String id = MyScanner.readString();
		
		System.out.print("Your first name (no spaces): ");
		String firstName = MyScanner.readString();
		
		System.out.print("Your last name (no spaces): ");
		String lastName = MyScanner.readString();
		
		MyScanner.flush();
		
		switch(clientKind)
		{
		case Committee:
			newPerson = createNewCommittee(id, firstName, lastName);
			break;
		case Tenant:
			newPerson = createNewTenant(id, firstName, lastName);
			break;
		case Provider:
			System.out.println("Only committee can add new provider");
			break;
		}
		
		System.out.print("Enter password: ");
		String password = MyScanner.readString();
		
		mLogics.createNewUser(newPerson, password);
		
		return newPerson;
	}

	private Committee createNewCommittee(String id, String firstName, String lastName) 
	{
		System.out.print("Enter seniority: ");
		int seniority = MyScanner.readInt();		
				
		return new Committee(id, firstName, lastName, seniority);
	}

	private Tenant createNewTenant(String id, String firstName, String lastName) 
	{
		System.out.print("Enter apartment number: ");
		int apartmentNumber = MyScanner.readInt();

		System.out.print("Enter monthly payment amount: ");
		int montlyPaymentAmount = MyScanner.readInt();
	
		return new Tenant(id, firstName, lastName, apartmentNumber, montlyPaymentAmount);
	}
}
