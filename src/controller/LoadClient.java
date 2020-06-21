package controller;

import java.io.IOException;

import view.WelcomeFrame;

public class LoadClient
{
	
	/** 
	 * Cette classe permet de definir le main qui va lancer l'application afin qu'un nouvel utilisateur se connecte
	 */
	
	public static void main(String[] args) throws IOException
	{
		WelcomeFrame main = new WelcomeFrame();
		main.setVisible(true);
	}
}
