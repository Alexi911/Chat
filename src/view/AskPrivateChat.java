package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import controller.SocketReader;

public class AskPrivateChat implements Runnable
{	
	boolean id;
	Socket socket;
	String name;
	String to_who;
	
	public AskPrivateChat(Socket socket,boolean id, String name, String to_who)
	{
		this.socket = socket;
		this.id = id;
		this.name = name;
		this.to_who = to_who;
	}
	
	public AskPrivateChat(Socket socket,boolean id, String name)
	{
		this.socket = socket;
		this.id = id;
		this.name = name;
	}

	@SuppressWarnings("unused")
	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		
		try 
		{
			final BufferedReader read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			final PrintWriter writer = new PrintWriter(socket.getOutputStream());
			final PrivateFrame frame = new PrivateFrame();
			frame.quit.addActionListener(new ActionListener() 
			{
	            @Override
	            public void actionPerformed(ActionEvent e)
	            {
					writer.println(name + " quit the private chat.");
					frame.dispose();
					writer.close();
					try 
					{
						read.close();
						socket.close();
					} catch (IOException exception1) 
					{
						// TODO Auto-generated catch block
						exception1.printStackTrace();
					}	
	            }
	         });
			
			if(id)
			{
				writer.println(this.name);
				writer.flush();
				frame.setName(to_who);
				frame.send.setEnabled(false);
				frame.setVisible(true);
				frame.show.append("Waiting for your friend to accept\n");
				if(read.readLine().equals("accept"))
				{
					frame.send.setEnabled(true);
					frame.show.append("You can start chatting!\n");
				}
				else
				{
					frame.show.append("Your friend decline this private chat!\n");
				}
			}
			else
			{
				this.to_who=read.readLine();
				final PrivateQuestionFrame accept = new PrivateQuestionFrame(to_who);
				frame.setName(to_who);
				accept.setVisible(true);
				
				accept.accept.addActionListener(new ActionListener() 
				{
		            @Override
		            public void actionPerformed(ActionEvent e)
		            {
						writer.println("accept");
						writer.flush();
						accept.dispose();
						frame.setVisible(true);
		            }
		         });
				
				accept.refuse.addActionListener(new ActionListener() 
				{
		            @Override
		            public void actionPerformed(ActionEvent e)
		            {
						writer.println("refuse");
						writer.flush();
						frame.dispose();
						accept.dispose();
		            }
		         });
			}
			
			frame.send.addActionListener(new ActionListener() 
			{

	            @Override
	            public void actionPerformed(ActionEvent e)
	            {
	            	String content = frame.getInput();
					writer.println(content);
					writer.flush();
					frame.addMessage(name + ":\n" + "  "+ content + "\n");
	            }
	         });
			
			SocketReader reader = new SocketReader(frame,socket, to_who);
			Thread thread = new Thread(reader);
			thread.start();
		} 
		catch (IOException exception2) 
		{
			// TODO Auto-generated catch block
			exception2.printStackTrace();
			try 
			{
				socket.close();
			} catch (IOException exception3) 
			{
				// TODO Auto-generated catch block
				exception3.printStackTrace();
			}
		}
	}
}
