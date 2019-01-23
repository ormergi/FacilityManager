package com.projects.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.projects.enums.*;
import com.projects.persons.*;

public class SqlDataBase 
{
	private String mDBAddress = "localhost";
	private Integer mDBPort = 3306;
	private String mInstanceName = "facility_manager";
	private String mDBUser = "root";
	private String mPassword = "password";
	private Connection mConnection = null;

	public SqlDataBase() throws SQLException 
	{
		String connectionURL = String.format(
				"jdbc:mysql://%s:%d/%s?useSSL=true&useTimezone=true&serverTimezone=GMT%%2B8",
				mDBAddress,
				mDBPort,
				mInstanceName
				);
		mConnection = DriverManager.getConnection(connectionURL, mDBUser, mPassword);
	}
	
	public Person authenticate(eClientKind clientKind, String id, String password) 
	{
		Person person = null;
		
		switch(clientKind)
		{
		case Committee:
			person = authenticateCommittee(id, password);
			break;
		case Provider:
			person = authenticateProvider(id, password);
			break;
		case Tenant:
			person = authenticateTenant(id, password);
			break;		
		}
		
		return person;
	}

	private Person authenticateCommittee(String id, String password) 
	{
		Person person = null;
		
		try 
		{
	        String selectStatement = "SELECT first_name, last_name, seniority";
	        String fromStatement = " FROM committees";
	        String whereStatement = " WHERE id=? AND password=?";
	              
	        PreparedStatement statement = mConnection.prepareStatement(selectStatement + fromStatement + whereStatement);
        
	        statement.setString(1, id);    
	        statement.setString(2, password);
	        
	        ResultSet result = statement.executeQuery();
	        
	        if (result.next()) 
	        {
	        	String firstName = result.getString("first_name");
	        	String lastName = result.getString("last_name");
	        	int seniority = result.getInt("seniority");
	        	
	        	person = new Committee(id, firstName, lastName, seniority);
	        } 
		}
        catch (SQLException e) 
        {
	        System.out.print("Failed to authenticate committee!");
	        e.printStackTrace();
	    }
		
		return person;
	}

	private Person authenticateProvider(String id, String password) 
	{
		Person person = null;
		
		try 
		{
	        String selectStatement = "SELECT first_name, last_name, category, availability, quality, price, phone";
	        String fromStatement = " FROM providers";
	        String whereStatement = " WHERE id=? AND password=?";
	              
	        PreparedStatement statement = mConnection.prepareStatement(selectStatement + fromStatement + whereStatement);
        
	        statement.setString(1, id);    
	        statement.setString(2, password);
	        
	        ResultSet result = statement.executeQuery();
	        
	        if (result.next()) 
	        {
	        	String firstName = result.getString("first_name");
	        	String lastName = result.getString("last_name");
	        	eProvidersCategories category = eProvidersCategories.valueOf(result.getString("category"));
	        	int availability = result.getInt("availability");
	        	int quality = result.getInt("quality");
	        	int price = result.getInt("price");
	        	String phone = result.getString("phone");
	        	
	        	person = new Provider(id, firstName, lastName, category, availability, quality, price, phone);
	        } 
		}
        catch (SQLException e) 
        {
	        System.out.print("Failed to authenticate provider!");
	        e.printStackTrace();
	    }
		
		return person;
	}

	private Person authenticateTenant(String id, String password) 
	{
		Person person = null;
		
		try 
		{
	        String selectStatement = "SELECT first_name, last_name, apartment_number, monthly_payment_amount";
	        String fromStatement = " FROM tenants";
	        String whereStatement = " WHERE id=? AND password=?";
	              
	        PreparedStatement statement = mConnection.prepareStatement(selectStatement + fromStatement + whereStatement);
        
	        statement.setString(1, id);    
	        statement.setString(2, password);
	        
	        ResultSet result = statement.executeQuery();
	        
	        if (result.next()) 
	        {
	        	String firstName = result.getString("first_name");
	        	String lastName = result.getString("last_name");
	        	int apartmentNumber = result.getInt("apartment_number");
	        	int monthlyPaymentAmount = result.getInt("monthly_payment_amount");
	        	
	        	person = new Tenant(id, firstName, lastName, apartmentNumber, monthlyPaymentAmount);
	        } 
		}
        catch (SQLException e) 
        {
	        System.out.print("Failed to authenticate tenant!");
	        e.printStackTrace();
	    }
		
		return person;
	}

	public boolean addNewUser(Person person, String password) 
	{
		boolean success = false;
		
		if (person instanceof Committee)
		{
			success = addNewCommittee((Committee)person, password);
		}
		else if (person instanceof Provider)
		{
			success = addNewProvider((Provider)person, password);
		}
		else if (person instanceof Tenant)
		{
			success = addNewTenant((Tenant)person, password);			
		}
		
		return success;
	}
	
	private boolean addNewCommittee(Committee committee, String password) 
	{
		boolean success = false;
		
		if (!isExists(eClientKind.Committee, committee.id()))
		{
			try 
			{
				PreparedStatement statement = mConnection.prepareStatement("INSERT INTO committees VALUES (?,?,?,?,?)");
		  
				statement.setString(1, committee.id());
				statement.setString(2, committee.firstName());
				statement.setString(3, committee.lastName());
				statement.setInt(4, committee.seniority());
				statement.setString(5, password);
				  
				statement.execute();
				  
				success = true;
			}	 
			catch (SQLException e) 
			{
				System.out.println("Error add new committee");			  
			}
		}
		
		return success;
	}

	private boolean addNewProvider(Provider provider, String password) 
	{
		boolean success = false;
		
		if (!isExists(eClientKind.Provider, provider.id()))
		{
			try 
			{
				PreparedStatement statement = mConnection.prepareStatement("INSERT INTO providers VALUES (?,?,?,?,?,?,?,?,?)");
		  
				statement.setString(1, provider.id());
				statement.setString(2, provider.firstName());
				statement.setString(3, provider.lastName());
				statement.setString(4, provider.category().toString());
				statement.setInt(5, provider.availablity());
				statement.setInt(6, provider.quailty());
				statement.setInt(7, provider.price());
				statement.setString(8, provider.phone());
				statement.setString(9, password);
				  
				statement.execute();
				  
				success = true;
			}	 
			catch (SQLException e) 
			{
				System.out.println("Error add new provider");			  
			}
		}
		
		return success;
	}

	private boolean addNewTenant(Tenant tenant, String password) 
	{
		boolean success = false;
		
		if (!isExists(eClientKind.Tenant, tenant.id()))
		{
			try 
			{
				PreparedStatement statement = mConnection.prepareStatement("INSERT INTO tenants VALUES (?,?,?,?,?,?)");
		  
				statement.setString(1, tenant.id());
				statement.setString(2, tenant.firstName());
				statement.setString(3, tenant.lastName());
				statement.setInt(4, tenant.apartmentNumber());
				statement.setInt(5, tenant.monthlyPaymentAmount());
				statement.setString(6, password);
				  
				statement.execute();
				  
				success = true;
			}	 
			catch (SQLException e) 
			{
				System.out.println("Error add new tenant");			  
			}
		}
		
		return success;
	}

	public boolean isExists(eClientKind kind, String id) 
	{
		boolean isExists = false;

        String selectStatement = "SELECT first_name";
        String fromStatement = null;
        String whereStatement = " WHERE id=?";
        
        switch (kind)
        {
		case Committee:
			fromStatement = " FROM committees";
			break;
		case Provider:
			fromStatement = " FROM providers";
			break;
		case Tenant:
			fromStatement = " FROM tenants";
			break;
        }
        
		try 
		{
			PreparedStatement statement = mConnection.prepareStatement(selectStatement + fromStatement + whereStatement);
			  
			statement.setString(1, id);					
			isExists = statement.executeQuery().next();			
		} 
		catch (SQLException e) 
		{
			System.out.println("Error search for existing person");
		}
		
		return isExists;
	}
	
	public String getTenantMonthPaid(String tenantId) 
	{
		String monthPaid = "";
		
		try 
		{
	        String statementString = "SELECT month FROM tenants_payments WHERE tenant_id=?";
 
	        PreparedStatement statement = mConnection.prepareStatement(statementString);
        
	        statement.setString(1, tenantId);
	        
	        ResultSet result = statement.executeQuery();
	                
	        while (result.next()) 
	        {
	        	monthPaid += result.getInt("month") + " "; 
	        } 
		}
        catch (SQLException e) 
        {
        	System.out.println("Error get tenant payments");
	    }
		
		return monthPaid;
	}
	
	public String[] getApartmentsPayments() 
	{
		String[] apartmentsPayments = null;
		
		try 
		{
			// getting existing apartments id
	        String statementString = "SELECT apartment_id, count(*) AS c FROM apartments_payments GROUP BY apartment_id";
 
	        PreparedStatement statement = mConnection.prepareStatement(statementString);
 
	        ResultSet result = statement.executeQuery();
	        
	        ArrayList<Integer> apartmentsIds = new ArrayList<Integer>();
	        	        
	        while (result.next()) 
	        {
	        	apartmentsIds.add(new Integer(result.getInt("apartment_id")));
	        }
	        
	        // getting apartments paid months for each apartment id	        
	        ArrayList<String> apartmentsPaymentsList = new ArrayList<String>();
	        
	        for (int i = 0; i < apartmentsIds.size(); i++)
	        {		        
		        apartmentsPaymentsList.add(getApartmentPayments(apartmentsIds.get(i)));
	        }
	        
	        apartmentsPayments = apartmentsPaymentsList.toArray(new String[apartmentsPaymentsList.size()]);	        
		}
        catch (SQLException e) 
        {
	        System.out.print("Error counting apartments!");
	    }
		
		return apartmentsPayments;
	}
	
	private String getApartmentPayments(int apartmentId) 
	{
		String apartmensPayments = apartmentId + " ";
		
		try 
		{
	        String statementString = "SELECT month FROM apartments_payments WHERE apartment_id=?";
 
	        PreparedStatement statement = mConnection.prepareStatement(statementString);
        
	        statement.setInt(1, apartmentId);
	        
	        ResultSet result = statement.executeQuery();
	                
	        while (result.next()) 
	        {
	        	apartmensPayments += result.getInt("month") + " "; 
	        } 
		}
        catch (SQLException e) 
        {
        	System.out.println("Error get apartment payments");
	    }
		
		return apartmensPayments;
	}

	public boolean addTenantPayment(String tenantId, int month, int amount) 
	{
		boolean success = addToTenantsPaymentsTable(tenantId, month, amount);
		
		updateApartmentsPaymentsTable(tenantId, month, amount);

		return success;
	}

	@SuppressWarnings("resource")
	private boolean addToTenantsPaymentsTable(String tenantId, int month, int amount) 
	{
		boolean success = false;

		try 
		{
			if (isExists(eClientKind.Tenant, tenantId))
			{
				// getting tenants count
				//String statementString = "SELECT count(num) AS total FROM tenants_payments";
				
				//PreparedStatement statement = m_connection.prepareStatement(statementString);
				//ResultSet result = statement.executeQuery();
				
				//result.next();
				//int tenantsCount = result.getInt("total");
				
				// check if month already exists
				boolean monthAlreadyExists = false;
				String statementString;
				PreparedStatement statement;
				ResultSet result;
				statementString = "SELECT month, amount FROM tenants_payments WHERE tenant_id=? and month=?";
				statement = mConnection.prepareStatement(statementString);
				statement.setString(1, tenantId);	
				statement.setInt(2, month);
				statement.execute();
				
				result = statement.executeQuery();
				monthAlreadyExists = result.next();
				
				if (monthAlreadyExists)
				{
					// update existing month
					int currentAmount = result.getInt("amount");
					
					statementString = "UPDATE tenants_payments SET amount=? WHERE tenant_id=? and month=?";
					statement = mConnection.prepareStatement(statementString);
					statement.setInt(1, currentAmount + amount);
					statement.setString(2, tenantId);
					statement.setInt(3, month);				  
					statement.execute();					
				}
				else
				{
					// add new payment				
					statementString = "INSERT INTO tenants_payments (tenant_id,month,amount) VALUES (?,?,?)";
					statement = mConnection.prepareStatement(statementString);
					//statement.setInt(1, tenantsCount + 1);
					statement.setString(1, tenantId);
					statement.setInt(2, month);
					statement.setInt(3, amount);				  
					statement.execute();
				}
				  
				success = true;
			}
		}	 
		catch (SQLException e) 
		{
			System.out.println("Error add tenant payment");			  
		}
		
		return success;
	}

	private void updateApartmentsPaymentsTable(String tenantId, int month, int amount) 
	{
		try 
		{
			int apartmentNumber = -1;
			int apartmentMonthAmount = -1;
			
			// get tenant's apartment number			
			PreparedStatement statement = mConnection.prepareStatement("SELECT apartment_number FROM tenants WHERE id=?");
	  
			statement.setString(1, tenantId);
			  
			ResultSet result = statement.executeQuery();
			
			if (result.next())
			{
				apartmentNumber = result.getInt("apartment_number");
			}
			
			if (apartmentNumber > -1) // if apartment found
			{
				// check if the apartment already have month payment	
				statement = mConnection.prepareStatement("SELECT month, amount FROM apartments_payments WHERE apartment_id=?");				  
				statement.setInt(1, apartmentNumber);
					  
				result = statement.executeQuery();
					
				while (result.next())
				{
					if (result.getInt("month") == month)
					{
						apartmentMonthAmount = result.getInt("amount");
						break;
					}
				}	
				
				if (apartmentMonthAmount > -1) // update the row
				{
					statement = mConnection.prepareStatement("UPDATE apartments_payments SET amount=? WHERE apartment_id=? AND month=?");				  
					statement.setInt(1, apartmentMonthAmount + amount);
					statement.setInt(2, apartmentNumber);
					statement.setInt(3, month);
					statement.execute();		
				}
			}
						
			if (apartmentNumber > -1 || apartmentMonthAmount < -1) // insert new apartment and month
			{
				statement = mConnection.prepareStatement("INSERT INTO apartments_payments (apartment_id,month,amount) VALUES(?,?,?)");				  
				statement.setInt(1, apartmentNumber);
				statement.setInt(2, month);
				statement.setInt(3, amount);
				statement.execute();
			}			
		}	 
		catch (SQLException e) 
		{
			System.out.println("Error update apartments payments table");			  
		}		
	}

	public String getApartmentMonthlyPayment(int apartmentId) 
	{
		String apartmentMonthlyPayment = "";
		
		try 
		{
			PreparedStatement statement = mConnection.prepareStatement("SELECT month, amount FROM apartments_payments WHERE apartment_id=?");
		  
			statement.setInt(1, apartmentId);
			  
			ResultSet result = statement.executeQuery();
			
			while (result.next())
			{
				apartmentMonthlyPayment += result.getInt("month") + " " + result.getInt("amount") + " ";
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("Error get apartments monthly payment");
		}
		
		return apartmentMonthlyPayment;
	}

	public Provider[] getProvidersByCategory(eProvidersCategories category) 
	{
		ArrayList<Provider> providers = new ArrayList<Provider>();
		
		try 
		{
	        String selectStatement = "SELECT id, first_name, last_name, category, availability, quality, price, phone";
	        String fromStatement = " FROM providers";
	        String whereStatement = "";
	        
	        if (category != eProvidersCategories.All)
	        {
	        	whereStatement = " WHERE category='" + category.toString() + "'";
	        }
	              
	        PreparedStatement statement = mConnection.prepareStatement(selectStatement + fromStatement + whereStatement);	        
	        ResultSet result = statement.executeQuery();

	        while (result.next()) 
	        {
	        	String id = result.getString("id");
	        	String firstName = result.getString("first_name");
	        	String lastName = result.getString("last_name");
	        	eProvidersCategories resultCategory = eProvidersCategories.valueOf(result.getString("category"));
	        	int availability = result.getInt("availability");
	        	int quality = result.getInt("quality");
	        	int price = result.getInt("price");
	        	String phone = result.getString("phone");
	        	
	        	providers.add(new Provider(id, firstName, lastName, resultCategory, availability, quality, price, phone));
	        } 
		}
        catch (SQLException e) 
        {
	        System.out.println("Failed to get providers list");
	    }
		
		return providers.toArray(new Provider[providers.size()]);
	}

	public Provider getOptimalProviderByCategory(eProvidersCategories category) 
	{
		Provider[] providers = getProvidersByCategory(category);
		
		// calculate average price		
		int pricesSum = 0;
		
		for (int i = 0; i < providers.length; i++) 
		{
			pricesSum += providers[i].price();
		}	

		int averagePrice = pricesSum / providers.length;
		
		// find the optimal provider
		Provider optimalProvider = providers[0];
		double qualityGrade = optimalProvider.quailty() * 0.6;
		double availabiltyGrade = optimalProvider.availablity() * 0.3;
		double priceGrade = (averagePrice - optimalProvider.price()) * 0.1;	
		double optimalProviderGrade = qualityGrade + availabiltyGrade + priceGrade;
		
		for (int i = 1; i < providers.length; i++)
		{
			Provider candidateProvider = providers[i];
			
			qualityGrade = candidateProvider.quailty() * 0.6;
			availabiltyGrade = candidateProvider.availablity() * 0.3;
			priceGrade = (averagePrice - candidateProvider.price()) * 0.1;	
			
			double candidateProviderGrade = qualityGrade + availabiltyGrade + priceGrade;
			
			if (candidateProviderGrade > optimalProviderGrade)
			{
				optimalProvider = candidateProvider;
				optimalProviderGrade = candidateProviderGrade;
			}
		}
		
		return optimalProvider;
	}

	public boolean updateProvider(Provider provider) 
	{
		boolean success = false;
		
		String updateString = "UPDATE providers ";
		String setParams = "SET "
		 		+ "first_name=?, "
		 		+ "last_name=?, "
		 		+ "category=?, "
		 		+ "availability=?, "
		 		+ "quality=?, "
		 		+ "price=?, "
		 		+ "phone=? ";
		String whereString = "WHERE id=?";
				 			  
        try 
        {
            PreparedStatement statement = mConnection.prepareStatement(updateString + setParams + whereString);
            
            statement.setString(1, provider.firstName());
            statement.setString(2, provider.lastName());
            statement.setString(3, provider.category().toString());
            statement.setInt(4, provider.availablity());
            statement.setInt(5, provider.quailty());
            statement.setInt(6, provider.price());
            statement.setString(7, provider.phone());
            statement.setString(8, provider.id());
                       
            int recordsUpdated = statement.executeUpdate();
            
            success = recordsUpdated > 0;
	         
	    } 
        catch (SQLException e) 
        {
            System.out.println("Error updating provider");
	    }
	        
		return success;
	}

	public boolean addNewProvider(Provider provider) 
	{
		return addNewProvider(provider, "1234");
	}

	public int[] getTenantPayments(String tenantId) 
	{
		int[] tenantPayments = new int[12];

		try 
		{
			PreparedStatement statement = mConnection.prepareStatement("SELECT month, amount FROM tenants_payments WHERE tenant_id=?");		
			  
			statement.setString(1, tenantId);
			  
			ResultSet result = statement.executeQuery();
			
			while (result.next())
			{
				int month = result.getInt("month");
				int amount = result.getInt("amount");
				
				tenantPayments[month-1] += amount;		
			}	
		} 
		catch (SQLException e) 
		{
			System.out.println("Error getting tenant payments");
		}	
	
		return tenantPayments;
	}
}
