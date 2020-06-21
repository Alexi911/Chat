package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class WelcomeFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel message = new JLabel("Welcome to our Chat Room !");
	public	JButton enter = new JButton("Log In");
	private JLabel back_ground = new JLabel("");

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public WelcomeFrame() throws IOException 
	{
		getContentPane().setLayout(null);
		setSize(600,520);
		setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Welcome");
        
	    message.setBackground(Color.WHITE);
	    message.setHorizontalAlignment(SwingConstants.CENTER);
	    message.setForeground(Color.WHITE);
	    message.setFont(new Font("Times New Roman", Font.ITALIC, 30));
	    message.setBounds(6, 147, 588, 144);
	    
	    enter.addActionListener(new ActionListener() 
	    {
	    	public void actionPerformed(ActionEvent e) 
	    	{
	    		LoadServerFrame a = new LoadServerFrame();
	    		a.setVisible(true);
	            dispose();
	    	}
	    });
	    
	    enter.setFont(new Font("Noteworthy", Font.BOLD | Font.ITALIC, 18));
	    enter.setBounds(234, 355, 131, 47);
	    
	    getContentPane().add(message);
	    getContentPane().add(enter);

	    back_ground.setBounds(0, -12, 616, 510);
	    getContentPane().add(back_ground);
	    ImageIcon imageIcon = new ImageIcon("/Users/alexis/Desktop/Chat/src/Images/Uni.jpg");
	    Image image = imageIcon.getImage();
	    Image newimg = image.getScaledInstance(600, 520,  java.awt.Image.SCALE_SMOOTH);
	    ImageIcon imagep = new ImageIcon(newimg);
	    back_ground.setIcon(imagep);
	}

}
