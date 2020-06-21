package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import controller.MySocket;

public class Server implements Runnable 
{
	boolean status;
	MySocket socket;
	UsersInfos info;
	String name;
	BufferedReader in;
	PrintWriter writer;
	String content;
	ArrayList<String> list;
	ArrayList<MySocket> userList;
	
	public Server(MySocket socket, UsersInfos info) 
	{
		super();
		this.socket = socket;
		this.info = info;
		status = false;
	}
	
	@Override
	public void run() 
	{
		try
		{
			try 
			{
				in = new BufferedReader(new InputStreamReader(socket.getSocket().getInputStream()));
				writer = new PrintWriter(socket.getSocket().getOutputStream());
			} 
			catch (IOException e) 
			{
				System.out.println("Can't get io stream: " + e);
				socket.getSocket().close();
				return;
			}
			
			try
			{
				while(!status)
				{ 
					name = in.readLine();
					System.out.println(name);
					if(!info.addUser(name, (MySocket)socket)){
						writer.println("existing user name");
						writer.flush();
					}
					else
					{
						writer.println("login success");
						writer.flush();
						info.change_listener(name, Integer.parseInt(in.readLine()));
						status = true;
					}
				}
				
				while(!(content = in.readLine()).equals("LEAVE"))
				{
					if(content.equals("LIST"))
					{
						list = info.checkUser();
						String number_of_users = list.size() + " users online";
						writer.println(number_of_users); 
						for(Iterator it = list.iterator();it.hasNext();)
						{
				             String member = (String) it.next();
				             writer.println(member);
				             writer.println(info.check_SuperSock(member).getListening_port());
				             writer.println(info.check_SuperSock(member).getSocket().getInetAddress());
				        }
						writer.flush();
					}
					else
					{ 
						userList = info.broadCast(this.name);

						for(int i = 0; i < userList.size(); i++)
						{
							 System.out.println(i);
							 PrintWriter temp = new PrintWriter(userList.get(i).getSocket().getOutputStream());
				             temp.println(name + " : \n\t" + content);
				             temp.flush();
				        }
					}
				}
				
				System.out.println("out!");
				info.deleteUser(this.name);
				try 
				{
					writer.close();
					in.close();
					socket.getSocket().close();
					return;
				} 
				catch (IOException e1) 
				{
				}
			} 
			catch(IOException e)
			{
				System.out.println("Runtime IO Error" + e);
				socket.getSocket().close();
			}
		}
		catch(Exception e)
		{
			System.out.println("oneServer thread Error: "+ e);
			info.deleteUser(name);
		}
	}
	
}
