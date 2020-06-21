package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PrivateQuestionFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	public JButton accept = new JButton("Accept");
	public JButton refuse = new JButton("Refuse");
	JLabel quest = new JLabel();
	private JLabel back_ground = new JLabel("");
	
	PrivateQuestionFrame(String name)
	{
		setLayout(null);
		setSize(600,150);
		setResizable(false);
		
		quest.setForeground(Color.WHITE);
		quest.setBackground(Color.WHITE);
		quest.setFont(new Font("Noteworthy", Font.BOLD | Font.ITALIC, 13));
		quest.setText( name + " ask to chat with you");
		quest.setBounds(209,38,183,20);
		getContentPane().add(quest);
		
		accept.setFont(new Font("Noteworthy", Font.BOLD | Font.ITALIC, 13));
		accept.setBounds(200,85,90,20);
		getContentPane().add(accept);
		
		refuse.setFont(new Font("Noteworthy", Font.BOLD | Font.ITALIC, 13));
		refuse.setBounds(302,85,90,20);
		getContentPane().add(refuse);
		
	    back_ground.setBounds(0, -12, 612, 162);
	    getContentPane().add(back_ground);        
	    ImageIcon imageIcon = new ImageIcon("/Users/alexis/Desktop/Chat/src/Images/Uni.jpg"); 
	    Image image = imageIcon.getImage(); 
	    Image newimg = image.getScaledInstance(600, 150,  java.awt.Image.SCALE_SMOOTH);  
	    ImageIcon imagep = new ImageIcon(newimg);
	    back_ground.setIcon(imagep);
	}
}