package com.projects.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.projects.database.SqlDataBase;

public class Server
{
	private boolean mCloseRequest = false;
	private String mWelcomeMessage = "----- Facility Tenants Manager Server ------";
	
	public Server()
	{
		System.out.println(
				String.format(
						"%s \n Server Time: %s",
						mWelcomeMessage,
						getDate(eDateFormat.Full)
						));
	}

	public void listen(int port) throws IOException, SQLException 
    {
        SqlDataBase database = new SqlDataBase();
        ServerSocket serverSocket = new ServerSocket(port);
        
        System.out.println(String.format("Server is listening on port: %d %n waiting for new connections...", port));
        
        while (!mCloseRequest) 
        {
            try 
            {
            	Socket incomingSocket = serverSocket.accept();
                SocketHandler socketHandler = new SocketHandler(incomingSocket, database);
                
                socketHandler.start();
            } 
            catch (IOException e) 
            {
                System.out.println(e);
                continue;
            }
        }
        
        serverSocket.close();
    }
    
    public void stop()
    {
    	mCloseRequest = true;
    }
    
    protected static String getDate(eDateFormat mode)
    {
    	String datePattern = null;
    	
    	switch(mode)
    	{
	    	case Full:
	    		datePattern = "HH:mm:ss dd/MM/yyyy";
	    		break;
	    	case Time:
	    		datePattern = "HH:mm:ss";
	    		break;
	    	case Date:
	    		datePattern = "dd/MM/yyyy";
	    		break;
    	}
    		
    	return LocalDateTime.now().format(DateTimeFormatter.ofPattern(datePattern));
    }
}