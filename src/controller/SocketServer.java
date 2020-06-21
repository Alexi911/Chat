package controller;

import java.net.ServerSocket;
import model.Server;
import model.UsersInfos;

public class SocketServer implements Runnable 
{
	@SuppressWarnings("resource")
	@Override
	public void run() 
	{
		UsersInfos info = new UsersInfos();

		try 
		{
			ServerSocket server = null;
			try 
			{
				server = new ServerSocket(12345);
				System.out.println("listening......");
			} 
			catch (Exception exception1) 
			{
				System.out.println("listening error" + exception1);
			}
			while (true) 
			{	
				MySocket socket = new MySocket();
				try 
				{
					socket.setSocket(server.accept());
					System.out.println("new connection");
				} 
				catch (Exception exception2) 
				{
					System.out.println("Accept Error:" + exception2);
				}
				Server newServer = new Server(socket, info);
				Thread t = new Thread(newServer);
				t.start();
			}
		} 
		catch (Exception exception3) 
		{
			System.out.println("Error: " + exception3);
		}
	}
}

