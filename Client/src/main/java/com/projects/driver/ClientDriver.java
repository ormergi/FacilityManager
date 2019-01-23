package com.projects.driver;

import com.projects.cli.CliUserInterface;
import com.projects.logics.Logics;

public class ClientDriver 
{
	public static void main(String[] args) 
	{
		Logics logics = new Logics();
		CliUserInterface ui = new CliUserInterface(logics);
		
		logics.start();
		ui.start();
	}	
}
