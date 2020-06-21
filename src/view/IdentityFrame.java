package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class IdentityFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JLabel message = new JLabel("Choose a name :");
	public	JButton enter = new JButton("ENTER");
	public	JButton quit = new JButton("QUIT");
	private JTextArea name = new JTextArea();
	private JLabel back_ground = new JLabel("");

	/** 
	 * Cette classe affiche la fenetre qui demande à l'utilisateur de choisir un nom pour le chat. Si l'utilisateur est déjà pris 
	 */
	
	public IdentityFrame()
	{
		getContentPane().setLayout(null);
		setSize(600,150);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Enter Your Name");
	
	    message.setBackground(Color.WHITE);
	    message.setHorizontalAlignment(SwingConstants.CENTER);
	    message.setForeground(Color.WHITE);
	    message.setFont(new Font("Times New Roman", Font.ITALIC, 13));
	    message.setBounds(6, 10, 588, 20);
	    getContentPane().add(message);
	    
	    name.setBounds(157, 31, 280, 20);
	    getContentPane().add(name);
	    
	    enter.setFont(new Font("Noteworthy", Font.BOLD | Font.ITALIC, 13));
	    enter.setBounds(186, 75, 80, 20);
	    getContentPane().add(enter);
	    
	    quit.setFont(new Font("Noteworthy", Font.BOLD | Font.ITALIC, 13));
	    quit.setBounds(328, 75, 80, 20);
	    getContentPane().add(quit);
	    
        back_ground.setBounds(0, -12, 612, 162);
        getContentPane().add(back_ground);
        ImageIcon imageIcon = new ImageIcon("/Users/alexis/Desktop/Chat/src/Images/Uni.jpg"); 
        Image image = imageIcon.getImage(); 
        Image newimg = image.getScaledInstance(600, 150,  java.awt.Image.SCALE_SMOOTH);   
        ImageIcon imagep = new ImageIcon(newimg);
        back_ground.setIcon(imagep);
		
	}

	public void resetLabel()
	{
		message.setText("This username already exists. Please choose another one.");
		name.setText("");
	}

	public String getName()
	{
		return name.getText();
	}
}