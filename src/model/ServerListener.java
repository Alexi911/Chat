package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import view.AskPrivateChat;

public class ServerListener implements Runnable
{
	int number;
	int port;
	String name;
	ServerSocket server;
	
	public ServerListener(int port, String name)
	{
		this.port = port;
		this.name = name;
		number = 1;
		while(true)
		{
			try 
			{
				server = new ServerSocket(port-number);
				break;
			} 
			catch (NumberFormatException e) 
			{
				e.printStackTrace();
				System.out.println("Invalid port!");
				break;
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
				number++;
				System.out.println(number);
			}
		}
	}
	
	public int gettry_number()
	{
		return number;
	}
	
	@Override
	public void run() 
	{
		try 
		{
			while(true)
			{
				Socket socket = null;
				socket = server.accept();
				AskPrivateChat sender = new AskPrivateChat(socket, false, name);
				Thread t = new Thread(sender);
				t.start();
				
			}
		} 
		catch (NumberFormatException e) 
		{
			e.printStackTrace();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
