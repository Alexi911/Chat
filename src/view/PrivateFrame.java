package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class PrivateFrame extends JFrame
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton send = new JButton("Send");
	public JButton quit = new JButton("Exit");
	private JTextArea input = new JTextArea();
	public JTextArea show = new JTextArea();
	private JLabel message = new JLabel("Private Chat");
	private JScrollPane scroll_in=new JScrollPane(input,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private JScrollPane scroll_out = new JScrollPane(show,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JLabel back_ground = new JLabel("");
	private JLabel anonymous = new JLabel("");
    
    
    public PrivateFrame()
    {
    	getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().setLayout(null);
		setSize(800,800);
		setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        show.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        show.setBorder(new LineBorder(new Color(0, 0, 0)));
        show.setEditable(false);
        
        input.setBorder(null);
        input.setEditable(true);
        
        show.setLineWrap(true);
        input.setLineWrap(true);
        
        scroll_in.setBorder(UIManager.getBorder("CheckBox.border"));
        scroll_in.setBounds(16,624,766,93);
        scroll_out.setBounds(16,50,766,562);
        
        getContentPane().add(scroll_in);
        getContentPane().add(scroll_out);
        
        quit.setFont(new Font("Noteworthy", Font.BOLD | Font.ITALIC, 13));
        quit.setBounds(672,732,90,30);
        getContentPane().add(quit);
        
        send.setFont(new Font("Noteworthy", Font.BOLD | Font.ITALIC, 13));
        send.setBounds(540,732,90,30);
        getContentPane().add(send);
        
        message.setHorizontalAlignment(SwingConstants.CENTER);
	    message.setForeground(Color.WHITE);
	    message.setFont(new Font("Times New Roman", Font.ITALIC, 26));
	    
	    message.setBounds(217, 17, 382, 20);
	    getContentPane().add(message); 
	    
        back_ground.setBounds(32, -11, 58, 71);
        getContentPane().add(back_ground);
        ImageIcon imageIcon = new ImageIcon("/Users/alexis/Desktop/Chat/src/Images/incognito-1.png"); 
        Image image = imageIcon.getImage(); 
        Image newimg = image.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH); 
        ImageIcon imagep = new ImageIcon(newimg);
        back_ground.setIcon(imagep); 
        
	    anonymous.setBounds(0, -12, 800, 790);
        getContentPane().add(anonymous);
        ImageIcon background = new ImageIcon("/Users/alexis/Desktop/Chat/src/Images/Uni.jpg"); 
        Image back = background.getImage(); 
        Image newback = back.getScaledInstance(800, 800,  java.awt.Image.SCALE_SMOOTH);  
        ImageIcon imageback = new ImageIcon(newback);
        anonymous.setIcon(imageback);
    }
    public void setName(String name)
    {
    	setTitle(name);
    }
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
    
    public static void main(String[] args)
    {
    	PrivateFrame chat = new PrivateFrame();
    	chat.setVisible(true); 
    }
}