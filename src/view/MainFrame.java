package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class MainFrame extends JFrame 
{
	private JTextArea input = new JTextArea(20,21);
    private JTextArea show = new JTextArea(20,21);
    private JScrollPane text_screen = new JScrollPane(input,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private JScrollPane chat_screen = new JScrollPane(show,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private JLabel message = new JLabel("Chat with your friends!");
    public JButton send = new JButton("Send");
    public JButton exit = new JButton("Quit");
    public JButton newuser = new JButton("Change User");
    private JScrollPane table = null;
	private JLabel back_ground = new JLabel("");
    public JButton fresh_button;
    public UserInChat temp;
    String name;
    
    public String getInput()
    {
    	String result = input.getText();
    	input.setText("");
    	return result;
    }
    
    public void addMessage(String str)
    {
    	show.append(str);
    	show.setCaretPosition(show.getText().length());
    }
    
    public MainFrame(String name)
	{
		this.name = name;
		
		setTitle(name);
		getContentPane().setLayout(null);
		setSize(600,520);
		setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Chat room");
        
        show.setBackground(Color.WHITE);
        show.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        show.setEditable(false);
        show.setLineWrap(true);
        
        input.setForeground(Color.BLACK);
        input.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        input.setEditable(true);
        input.setLineWrap(true);
        
        text_screen.setAutoscrolls(true);
        text_screen.setBackground(Color.WHITE);
        text_screen.setBounds(10,381,580,69);
        getContentPane().add(text_screen);
        
        chat_screen.setBackground(Color.GRAY);
        chat_screen.setBounds(10,50,458,300);
        getContentPane().add(chat_screen);
        
        exit.setFont(new Font("Noteworthy", Font.BOLD | Font.ITALIC, 13));
        exit.setBounds(500,465,90,27);
        getContentPane().add(exit);
        
        send.setFont(new Font("Noteworthy", Font.BOLD | Font.ITALIC, 13));
        send.setBounds(400,465,90,27);
        getContentPane().add(send);

        temp = new UserInChat(491,50,90,300);
        table = temp.getTable();
        getContentPane().add(table);
        
        fresh_button = temp.getButton();
        fresh_button.setBounds(491,356,90,20);
        getContentPane().add(fresh_button);	
        
        message.setHorizontalAlignment(SwingConstants.CENTER);
	    message.setForeground(Color.WHITE);
	    message.setFont(new Font("Times New Roman", Font.ITALIC, 26));
	    message.setBounds(10, 6, 580, 31);
	    getContentPane().add(message);
	    
	    newuser.addActionListener(new ActionListener() 
	    {
	    	public void actionPerformed(ActionEvent e) 
	    	{
	    		LoadServerFrame a = new LoadServerFrame();
	    		a.setVisible(true);
	            dispose();
	    	}
	    });
	    
	    newuser.setFont(new Font("Noteworthy", Font.BOLD | Font.ITALIC, 13));
	    newuser.setBounds(20, 465,105,27);
	    getContentPane().add(newuser);
	    
        back_ground.setBounds(0, -12, 616, 510);
        getContentPane().add(back_ground);
        ImageIcon imageIcon = new ImageIcon("/Users/alexis/Desktop/Chat/src/Images/Uni.jpg"); 
        Image image = imageIcon.getImage();  
        Image newimg = image.getScaledInstance(800, 800,  java.awt.Image.SCALE_SMOOTH);  
        ImageIcon imagep = new ImageIcon(newimg);
        back_ground.setIcon(imagep);
	}
	
	public void setName(String name)
	{
		setTitle(name);
        temp.resetName(name);
	}
}