package com.projects.logics;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.projects.client_messages.IClientRequest;
import com.projects.server_messages.IServerResponse;

public class ServerStreamer extends Thread
{
	private String mHost;
	private int mPort;
	
	private Socket mSocket;
	private ObjectOutputStream mOutputToServer;
	private ObjectInputStream mInputFromServer;
	private boolean mIsConnected;

	public ServerStreamer(String host, int port) 
	{
		mHost = host;
		mPort = port;
	}
	
	@Override
	public void run()
	{
		try 
		{
			mSocket = new Socket(mHost, mPort);
			mOutputToServer = new ObjectOutputStream(mSocket.getOutputStream());
			mInputFromServer = new ObjectInputStream(mSocket.getInputStream());
			mIsConnected = true;
		} 
		catch (IOException e) 
		{
			mIsConnected = false;
		}		
		
	}
	
	public boolean isConnected()
	{
		return mIsConnected;
	}
	
	public void send(IClientRequest request)
	{
		try 
		{
			mOutputToServer.writeObject(request);
		} 
		catch (IOException e) 
		{
			System.out.println("Error sending to server");
		}
	}
	
	public IServerResponse read()
	{
		IServerResponse response = null;
		
		try 
		{
			response = (IServerResponse)mInputFromServer.readObject();
		} 
		catch (ClassNotFoundException | IOException e) 
		{
			System.out.println("Error reading from server");
		}
		
		return response;
	}
}
