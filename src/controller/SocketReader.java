package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import view.PrivateFrame;

public class SocketReader implements Runnable
{
	PrivateFrame frame;
	Socket socket;
	String name;
	
	public SocketReader(PrivateFrame frame, Socket socket, String name)
	{
		this.frame = frame;
		this.socket = socket;
		this.name = name;
	}
	@Override
	public void run() 
	{	
			try 
			{
				final BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				while(true)
				{
					String content = in.readLine();
					if(content.equals("ciao"))
					{
						frame.dispose();
						in.close();
						socket.close();
						break;
					}
					else
					{
						frame.addMessage(name + ":\n" + "  "+ content + "\n");
					}
				}
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
				try 
				{
					socket.close();
				} catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			}
	}

}
