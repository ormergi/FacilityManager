package com.projects.cli;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyScanner 
{
	private static Scanner mScanner = new Scanner(System.in);
	
	public static String readString()
	{
		return mScanner.next();
	}
	
	public static int readInt()
	{
		Integer input = 0;
		Boolean validInput = false;
		
		while (!validInput)
		{
			try
			{
				input = Integer.parseInt(mScanner.next());
				validInput = true;
			}
			catch (NumberFormatException | InputMismatchException e)
			{
				System.out.print("Enter number, try again: ");
			}
		}
		
		return input;
	}
	
	public static int readInt(int maxInt) 
	{
		Boolean validInput = false;
		Integer input = 0;
		
		while (!validInput)
		{
			try
			{
				input = MyScanner.readInt();
				if (input >= 1 && input <= maxInt)
				{
					validInput = true;
				}
				else
				{
					System.out.print(String.format("Please enter number between 1 to %d, Your selection: ",maxInt));
				}
			}
			catch (NumberFormatException | InputMismatchException e)
			{
				System.out.print("Enter number, try again: ");
			}
		}
		
		return input;
	}
	
	public static void flush()
	{
		if (mScanner.hasNextLine())
		{
			mScanner.nextLine();
		}
	}
}
