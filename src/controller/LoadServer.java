package controller;


public class LoadServer 
{
	/** 
	 * Cette classe permet de definir le main qui va créer le server du chat room
	 */
	
	public static void main(String[] args) 
	{
		SocketServer server = new SocketServer(); 
		Thread thread = new Thread(server);
		thread.start();
		
		try 
		{
			thread.join();
		} 
		catch (InterruptedException exception) 
		{
			exception.printStackTrace();
		}
	}
}
