package com.projects.driver;

import java.io.IOException;
import java.sql.SQLException;

import com.projects.server.Server;

public class ServerDriver 
{
    public static void main(String argv[])
    {
        try 
        {
            Server server = new Server();
            server.listen(10001);
        } 
        catch(SQLException | IOException e) 
        {
            System.out.println(String.format("Unable to start server. %n Reason: %n %s", e.getMessage()));
            System.exit(1);
        }
    }
}
