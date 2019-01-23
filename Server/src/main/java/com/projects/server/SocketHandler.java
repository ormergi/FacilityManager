package com.projects.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.projects.persons.*;
import com.projects.client_messages.*;
import com.projects.enums.eClientKind;
import com.projects.server_messages.*;
import com.projects.database.SqlDataBase;

public class SocketHandler extends Thread 
{
    private SqlDataBase mDatabase = null;
    private ObjectInputStream mInputFromClient = null;
    private ObjectOutputStream mOutputToClient = null;
    private Socket mSocket = null;
    private Person mPerson = null;
    private Boolean mIsAuthenticated = false;

    public SocketHandler(Socket incomingSocket, SqlDataBase database) throws IOException 
    {
    	mSocket = incomingSocket;
    	mDatabase = database;
        mInputFromClient = new ObjectInputStream(incomingSocket.getInputStream());
        mOutputToClient = new ObjectOutputStream(incomingSocket.getOutputStream());
    }

    public void run() 
    {
        System.out.println(
        		String.format(
        				"[%s] New connection from: %s",
        				Server.getDate(eDateFormat.Full),
        				mSocket.toString()
        				));
        
        mPerson = authenticateClient();

        if (mPerson != null)
        {
        	System.out.println(
        			String.format(
					"[%s] Client: %s %s %s autheticated", 
					Server.getDate(eDateFormat.Time), 
					mPerson.id(), mPerson.firstName(), 
					mPerson.lastName()
					));
        }
        
        // serve client until he quits
        while (mIsAuthenticated) 
        {
        	try 
        	{ 
        		IClientRequest clientRequest = (IClientRequest)mInputFromClient.readObject();
				
        		if (clientRequest instanceof QuitRequest)
        		{
        			mOutputToClient.writeObject(new QuitResponse());
        			break;
        		}
        		else if (clientRequest instanceof ConnectedPersonDetailsRequest)
        		{
        			mOutputToClient.writeObject(new ConnectedPersonDetailsResponse(mPerson));
        		}
        		//else if(clientRequest instanceof LoginRequest)
        		//{
        		//	m_outputToClient.writeObject(new LoginResponse(true));
        		//}
        		else
        		{  		
					if (mPerson instanceof Committee)				
					{
						handleCommitteeSelection(clientRequest);
					}
					else if (mPerson instanceof Tenant)
					{
						handleTenantSelection(clientRequest);
					}
					else if (mPerson instanceof Provider)
					{
						handleProviderSelection(clientRequest);
					}
        		}
			} 
        	catch (ClassNotFoundException | IOException e) 
        	{
        		System.out.println(
        				String.format(
        						"[%s] Client: %s %s %s disconnected", 
        						Server.getDate(eDateFormat.Time), 
        						mPerson.id(), mPerson.firstName(), 
        						mPerson.lastName()
        						));
        		break;
			}
        }
    }

    private Person authenticateClient() 
    {
        //boolean isAuthenticated = false;
        Person loggedInPerson = null;
        
        // keep trying until the user is authenticated
        while (!mIsAuthenticated) 
        {
			try 
			{
				IClientRequest clientRequest = (IClientRequest)mInputFromClient.readObject();
				
				System.out.println("Client try to authenticate");
				
				IServerResponse serverResponse = null;
				
	            if (clientRequest instanceof LoginRequest) 
	            {
	            	LoginRequest request = (LoginRequest)clientRequest;
	            	
	            	loggedInPerson = mDatabase.authenticate(request.clientKind(), request.id(), request.password());
	            	mIsAuthenticated = loggedInPerson != null;            	
	            	serverResponse = new LoginResponse(mIsAuthenticated);
	            } 
	            else if (clientRequest instanceof NewUserRequest)
	            {
	            	NewUserRequest request = (NewUserRequest)clientRequest;
	            	
	            	mIsAuthenticated = mDatabase.addNewUser(request.person(), request.password());
	            	
	            	if (mIsAuthenticated)
	            	{
		            	loggedInPerson = request.person();	            		
	            	}
	            	
	            	serverResponse = new NewUserResponse(mIsAuthenticated);
	            }
	            else
	            {
	            	System.out.println("Client sent unsupported message");
	            }
	            
	            // write response back to client
	            mOutputToClient.writeObject(serverResponse);
			} 
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				System.out.println("Unauthenticated client disconnected");
				mIsAuthenticated = false;
				break;
			}
        }
            
        return loggedInPerson;
    }

	private void handleCommitteeSelection(IClientRequest i_request) 
	{
		IServerResponse response = null;
		
		if (i_request instanceof TenantMonthsPaidRequest)
		{
			TenantMonthsPaidRequest request = (TenantMonthsPaidRequest)i_request;
			String tenantPayments = mDatabase.getTenantMonthPaid(request.tenantId());
			
			response = new TenantMonthsPaidResponse(tenantPayments);
		}
		else if (i_request instanceof ApartmentsPaymentsRequest)
		{
			String[] apartmentsPayments = mDatabase.getApartmentsPayments();
			
			response = new ApartmentsPaymentsResponse(apartmentsPayments);
		}
		else if (i_request instanceof AddTenantPaymentRequest)
		{
			AddTenantPaymentRequest request = (AddTenantPaymentRequest)i_request;
			boolean success = mDatabase.addTenantPayment(request.tenantId(), request.month(), request.amount());
			
			response = new AddTenantPaymentResponse(success);
		}
		else if (i_request instanceof ApartmentMonthlyPaymentsRequest)
		{
			ApartmentMonthlyPaymentsRequest request = (ApartmentMonthlyPaymentsRequest)i_request;
			String monthlyPayments = mDatabase.getApartmentMonthlyPayment(request.apartmentNumber());
					
			response = new ApartmentMonthlyPaymentsResponse(monthlyPayments);
		}
		else if (i_request instanceof ProviderByCategoryRequest)
		{
			ProviderByCategoryRequest request = (ProviderByCategoryRequest)i_request;			
			Provider[] providers = mDatabase.getProvidersByCategory(request.category());
			
			response = new ProviderByCategoryResponse(providers);
		}
		else if (i_request instanceof OptimalProviderRequest)
		{
			OptimalProviderRequest request = (OptimalProviderRequest)i_request;
			Provider provider = mDatabase.getOptimalProviderByCategory(request.category());
			
			response = new OptimalProviderResponse(provider);
		}
		else if (i_request instanceof AddOrUpdateProviderRequest)
		{
			AddOrUpdateProviderRequest request = (AddOrUpdateProviderRequest)i_request;
			boolean success = false;
			
			if (mDatabase.isExists(eClientKind.Provider, request.provider().id()))
			{
				success = mDatabase.updateProvider(request.provider());
			}
			else
			{
				success = mDatabase.addNewProvider(request.provider());
			}
			
			response = new AddOrUpdateProviderResponse(success);
		}
		else
		{
			System.out.println("Client sent unsupported request");
		}
		
		if (response != null)
		{
			try 
			{
				mOutputToClient.writeObject(response);
			} 
			catch (IOException e) 
			{
				System.out.println("Error send response to client");
			}
		}			
	}

	private void handleTenantSelection(IClientRequest i_request) 
	{
		IServerResponse response = null;
		
		if (i_request instanceof TenantPaymentsRequest)
		{
			int[] payments = mDatabase.getTenantPayments(mPerson.id());
			
			response = new TenantPaymentsResponse(payments);
		}
		else
		{
			System.out.println("Client sent unsupported request");
		}
		
		if (response != null)
		{
			try 
			{
				mOutputToClient.writeObject(response);
			} 
			catch (IOException e) 
			{
				System.out.println("Error send response to client");
			}
		}
	}

    private void handleProviderSelection(IClientRequest i_request) 
	{
		IServerResponse response = null;
		
		if (i_request instanceof ConnectedPersonDetailsRequest)
		{
			response = new ConnectedPersonDetailsResponse(mPerson);
		}
		else
		{
			System.out.println("Client sent unsupported request");
		}
		
		if (response != null)
		{
			try 
			{
				mOutputToClient.writeObject(response);
			} 
			catch (IOException e) 
			{
				System.out.println("Error send response to client");
			}
		}
	}
}
