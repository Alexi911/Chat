package model;


import java.util.ArrayList;
import java.util.HashMap;

import controller.MySocket;


public class UsersInfos 
{
	private static HashMap<String, MySocket> users =new HashMap<String, MySocket>();
	
	
	public synchronized boolean addUser(String name, MySocket writer)
	{
		if(users.containsKey(name))
		{
			return false;
		}
		else
		{
			users.put(name, writer);
			return true;
		}
	}
	
	public synchronized ArrayList<String> checkUser()
	{
		ArrayList<String> list = new ArrayList<String>();
		
		for(String name: users.keySet())
		{
			list.add(name);
		}
		return list;
	}
	
	public synchronized ArrayList<MySocket> broadCast(String sender)
	{
		ArrayList<MySocket> iolist = new ArrayList<MySocket>();
		for(String name: users.keySet())
		{
			System.out.println(name);
			if(!name.equals(sender))
				iolist.add(users.get(name));
		}
		return iolist;
	}
	
	public synchronized void deleteUser(String name)
	{
		users.remove(name);
	}
	
	public synchronized MySocket check_SuperSock(String name)
	{
		return users.get(name);
	}
	
	public synchronized void change_listener(String name,int number)
	{
		users.get(name).setListening_port(number);
	}
}