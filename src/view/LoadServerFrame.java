package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import controller.Login;

public class LoadServerFrame extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea ip=new JTextArea(20,21);
    private JTextArea port=new JTextArea(20,21);
    private JButton join = new JButton("JOIN");
    private JLabel ip_view = new JLabel("IP :");
    private JLabel port_view = new JLabel("PORT :");
    private JLabel message = new JLabel("");
	private JLabel back_ground = new JLabel("");
    
	LoadServerFrame()
    {
    	getContentPane().setLayout(null);
		setSize(600,180);
		setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        ip_view.setForeground(Color.WHITE); 
        ip_view.setFont(new Font("Times New Roman", Font.ITALIC, 13));
        ip_view.setBounds(102, 31, 50, 20);
        getContentPane().add(ip_view);
        
        port_view.setForeground(Color.WHITE);
        port_view.setFont(new Font("Times New Roman", Font.ITALIC, 13));
        port_view.setBounds(102, 71, 50, 20);
        getContentPane().add(port_view);
        
        ip.setBounds(185,32,200,20);
        getContentPane().add(ip);
        
        port.setBounds(185,72,200,20);
        getContentPane().add(port);
        
        join.setFont(new Font("Noteworthy", Font.BOLD, 13));
        join.setBounds(241,124,80,20);
        getContentPane().add(join);
       
        back_ground.setBounds(0, -12, 800, 790);
        getContentPane().add(back_ground);
        ImageIcon background = new ImageIcon("/Users/alexis/Desktop/Chat/src/Images/Uni.jpg");
        Image back = background.getImage();
        Image newback = back.getScaledInstance(800, 800, java.awt.Image.SCALE_SMOOTH); 
        ImageIcon imageback = new ImageIcon(newback);
        back_ground.setIcon(imageback);
        
    	join.addActionListener(new ActionListener() 
    	{

            @Override
            public void actionPerformed(ActionEvent e)
            {
            	try
            	{
            		String ip_address = ip.getText();
            		String port_number = port.getText();
            		Socket socket = new Socket(ip_address, Integer.parseInt(port_number));
            		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    				PrintWriter write = new PrintWriter(socket.getOutputStream());
    				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    				Login s = new Login(br, write, in, socket);
    				Thread t = new Thread(s);
    				
    				dispose();
    				t.start();
    				t.join();
            	}
            	catch(Exception e1)
            	{
            		e1.printStackTrace();
            		
            		message.setText("Address is not valid");
            		ip.setText("");
            		port.setText("");
            	}
            }
         });
    }
}