package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import model.ServerListener;
import view.IdentityFrame;
import view.MainFrame;

public class Login implements Runnable 
{

	BufferedReader read1;
	BufferedReader read2;
	PrintWriter writer;
	Socket socket;
	String name;
	MainFrame main;
	IdentityFrame identity;

	public Login(BufferedReader read1, PrintWriter writer, BufferedReader read2, Socket socket) 
	{
		super();
		this.read1 = read1;
		this.read2 = read2;
		this.writer = writer;
		this.socket = socket;
		identity = new IdentityFrame();
		main = new MainFrame("");
	}

	@Override
	public void run() 
	{
		/** 
		 * On definit ici l'action du boutton enter de la classe IdentityFrame. Lorsque l'on met un nom pas déjà utilisé la connection est reussi et on accède 
		 * au chat room sinon les informations sont effacés et on recommence.
		 */
		
		identity.enter.addActionListener(new ActionListener() 
		{
            @Override
            public void actionPerformed(ActionEvent e)
            {
            	name = identity.getName();
            	if(name.length()==0)
            	{
            		return;
            	}
            	
            	writer.println(name);
            	writer.flush();
            	
            	try {
					if(read2.readLine().equals("login success"))
					{
						identity.resetLabel();
						identity.setVisible(false);
						main.setVisible(true);
						main.setName(name);
						
						Information receiver = new Information(read2, main, name);
						Thread thread = new Thread(receiver);
						thread.start();
						
						ServerListener listen = new ServerListener(socket.getLocalPort(),name);
						writer.println(socket.getLocalPort()-listen.gettry_number());
						Thread l = new Thread(listen);
						l.start();
						
					}
					else
					{
						identity.resetLabel();
					}
				} 
            	catch (IOException exception1) 
            	{
					// TODO Auto-generated catch block
					exception1.printStackTrace();
					writer.close();
					try 
					{
						read2.close();
					} 
					catch (IOException exception2) 
					{
						// TODO Auto-generated catch block
						exception2.printStackTrace();
					}
					try 
					{
						socket.close();
					} catch (IOException exception3) 
					{
						// TODO Auto-generated catch block
						exception3.printStackTrace();
					}
					System.exit(0);
				}
            	
            	identity.resetLabel();
            }
         });
		
		identity.quit.addActionListener(new ActionListener() 
		{
            @Override
            public void actionPerformed(ActionEvent e)
            {
				writer.close();				
				try 
				{
					read2.close();
				} catch (IOException exception1) 
				{
					// TODO Auto-generated catch block
					exception1.printStackTrace();
				}	
				try 
				{
					socket.close();
				} catch (IOException exception2) 
				{
					// TODO Auto-generated catch block
					exception2.printStackTrace();
				}
				
            	identity.dispose();
            }
         });
		
		main.exit.addActionListener(new ActionListener() 
		{
            @Override
            public void actionPerformed(ActionEvent e)
            {
				writer.close();				
				try 
				{
					read2.close();
				} catch (IOException exception1) 
				{
					// TODO Auto-generated catch block
					exception1.printStackTrace();
				}	
				try {
					socket.close();
				} 
				catch (IOException exception2) 
				{
					// TODO Auto-generated catch block
					exception2.printStackTrace();
				}
				
            	main.dispose();
            	identity.dispose();
            	System.exit(0);
            }
         });
		
		main.send.addActionListener(new ActionListener() 
		{
            @Override
            public void actionPerformed(ActionEvent e)
            {
            	String content = main.getInput();
				writer.println(content);
				writer.flush();
				main.addMessage(name+" :"+ "\n\t" + content+"\n" );
            }
         });
		
		main.fresh_button.addActionListener(new ActionListener() 
		{
            @Override
            public void actionPerformed(ActionEvent e){
				writer.println("LIST");
				writer.flush();
            }
         });
		
		identity.setVisible(true);
	}
}
