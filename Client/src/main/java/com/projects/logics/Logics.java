package com.projects.logics;

import com.projects.client_messages.*;
import com.projects.enums.*;
import com.projects.persons.*;
import com.projects.server_messages.*;

public class Logics
{
	private String mServerAddress = "localhost";
	private Integer mServerPort = 10001;
	private ServerStreamer mServerStreamer = new ServerStreamer(mServerAddress, mServerPort);
	
	public void start()
	{		
		try 
		{
			mServerStreamer.start();
			mServerStreamer.join();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	public boolean createNewUser(Person person, String password)
	{
		boolean result = false;
		
		if (mServerStreamer.isConnected())
		{
			mServerStreamer.send(new NewUserRequest(person, password));
			IServerResponse response = mServerStreamer.read();
			
			if (response instanceof NewUserResponse)
			{
				result = ((NewUserResponse)response).isSucceeded(); 
			}
		}
		
		return result; 
	}
	
	public boolean login(eClientKind kind, String id, String password)
	{
		boolean result = false;
		
		if (mServerStreamer.isConnected())
		{
			mServerStreamer.send(new LoginRequest(kind, id, password));
			IServerResponse response = mServerStreamer.read();
			
			if (response instanceof LoginResponse)
			{
				result = ((LoginResponse)response).isAuthenticated(); 
			}
		}
		
		return result; 
	}
	
	public String getTenantMonthsPaid(String tenantId)
	{
		String result = null;
		
		if (mServerStreamer.isConnected())
		{
			mServerStreamer.send(new TenantMonthsPaidRequest(tenantId));
			IServerResponse response = mServerStreamer.read();
			
			if (response instanceof TenantMonthsPaidResponse)
			{
				result = ((TenantMonthsPaidResponse)response).tenantPayments(); 
			}
		}
		
		return result;
	}
	
	public String[] getApartmentsPayments()	
	{
		String[] result = null;
		
		if (mServerStreamer.isConnected())
		{
			mServerStreamer.send(new ApartmentsPaymentsRequest());
			IServerResponse response = mServerStreamer.read();
			
			if (response instanceof ApartmentsPaymentsResponse)
			{
				result = ((ApartmentsPaymentsResponse)response).apartmentsPayments(); 
			}
		}
		
		return result;
	}
	
	public boolean addTenantPayment(String id, int month, int amount)	
	{
		boolean result = false;
		
		if (mServerStreamer.isConnected())
		{
			mServerStreamer.send(new AddTenantPaymentRequest(id, month, amount));
			IServerResponse response = mServerStreamer.read();
			
			if (response instanceof AddTenantPaymentResponse)
			{
				result = ((AddTenantPaymentResponse)response).isSuccessed(); 
			}
		}
		
		return result;
	}
	
	public String getApartmentMonthlyPayments(int apartmentNumber)	
	{
		String result = null;
		
		if (mServerStreamer.isConnected())
		{
			mServerStreamer.send(new ApartmentMonthlyPaymentsRequest(apartmentNumber));
			IServerResponse response = mServerStreamer.read();
			
			if (response instanceof ApartmentMonthlyPaymentsResponse)
			{
				result = ((ApartmentMonthlyPaymentsResponse)response).monthlyPayments(); 
			}
		}
		
		return result;
	}
	
	public Provider[] getProvidersByCategory(eProvidersCategories category)	
	{
		Provider[] result = null;
		
		if (mServerStreamer.isConnected())
		{
			mServerStreamer.send(new ProviderByCategoryRequest(category));
			IServerResponse response = mServerStreamer.read();
			
			if (response instanceof ProviderByCategoryResponse)
			{
				result = ((ProviderByCategoryResponse)response).providers(); 
			}
		}
		
		return result;
	}
	
	public Provider getOptimalProvider(eProvidersCategories category)	
	{
		Provider result = null;
		
		if (mServerStreamer.isConnected())
		{
			mServerStreamer.send(new OptimalProviderRequest(category));
			IServerResponse response = mServerStreamer.read();
			
			if (response instanceof OptimalProviderResponse)
			{
				result = ((OptimalProviderResponse)response).provider(); 
			}
		}
		
		return result;
	}
	
	public boolean addOrUpdateProvider(Provider provider)	
	{
		boolean result = false;
		
		if (mServerStreamer.isConnected())
		{
			mServerStreamer.send(new AddOrUpdateProviderRequest(provider));
			IServerResponse response = mServerStreamer.read();
			
			if (response instanceof AddOrUpdateProviderResponse)
			{
				result = ((AddOrUpdateProviderResponse)response).isSucceeded(); 
			}
		}
		
		return result;
	}
	
	public int[] getTenantPayments() // this method should be called from tenant client only
	{
		int[] result = null;
		
		if (mServerStreamer.isConnected())
		{
			mServerStreamer.send(new TenantPaymentsRequest());
			IServerResponse response = mServerStreamer.read();
			
			if (response instanceof TenantPaymentsResponse)
			{
				result = ((TenantPaymentsResponse)response).payments(); 
			}
		}
		
		return result;
	}
	
	public Person getConnectedPersonDetails()
	{
		Person result = null;
		
		if (mServerStreamer.isConnected())
		{
			mServerStreamer.send(new ConnectedPersonDetailsRequest());
			IServerResponse response = mServerStreamer.read();
			
			if (response instanceof ConnectedPersonDetailsResponse)
			{
				result = ((ConnectedPersonDetailsResponse)response).provider(); 
			}
		}
		
		return result;
	}
}
