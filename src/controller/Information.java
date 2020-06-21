package controller;

import java.io.BufferedReader;
import java.util.regex.Pattern;

import view.MainFrame;

public class Information implements Runnable
{
	BufferedReader read;
	MainFrame main;
	String name;
	
	public Information(BufferedReader read, MainFrame main, String name) 
	{
		super();
		this.read = read;
		this.main = main;
		this.name = name;
	}
	
	  /** 
	   * run permet de lire les personnes dans le chat de part leur nom, leur port et leur adresse IP
	   */
	
	@Override
	public void run() 
	{	
		String users = "^[1-9]\\d*(\\s)users(\\s)online$";
		Pattern pattern = Pattern.compile(users);

		while(true)
		{
			try
			{
				String content = read.readLine();
				if(pattern.matcher(content).matches())
				{
					String sub = content.substring(0, content.indexOf(" "));
					main.temp.resetTable();
					for(int i=0; i<Integer.parseInt(sub);i++)
					{
							main.temp.addRow(read.readLine(), read.readLine(), read.readLine().substring(1));
					}	
				}
				else
				{
					main.addMessage(content+"\n");
				}
				
			} catch (Exception e) 
			{
				System.out.println("Connection Expired");
				return;
			}
		}
	}
}