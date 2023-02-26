package DBM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
public class HOME {
	public Object panel2;
	public Object search;
	public Object panel4;
	public Object panel3;
	static  int  s3=2000;
	HOME()
	{
		JFrame frame = new JFrame();
        frame.getContentPane().setLayout(null);
  
        JPanel panel1 = new JPanel();
        JPanel panel2= new JPanel();
        JPanel panel3= new JPanel(flowlayout());
        JPanel panel4= new JPanel(setLayout(null));

        //Creation of object to draw border
        Border br=BorderFactory.createLineBorder(Color.white);
        Border brd=BorderFactory.createLineBorder(Color.black);
              
        panel1.setBounds(00, 0, 800, 70);
        panel1.setBorder(br);
        panel1.setBackground(new Color(25, 102, 80));
        frame.getContentPane().add(panel1,BorderLayout.NORTH);
        
        panel2.setBounds(0,70,145,700);
        panel2.setBorder(br);
        frame.getContentPane().add(panel2,BorderLayout.WEST);
        panel2.setBackground(new Color(0, 102, 76));
       
        panel3.setBounds(140, 70, 850, 700);
        panel3.setBackground(Color.WHITE);
        frame.add(panel3);
        
        panel4.setBounds(140, 70, 850, 700);
        panel4.setBackground(Color.WHITE);
        frame.add(panel4);
 
        JLabel label= new JLabel("Shopprix");
        label.setForeground(Color.WHITE);
        label.setPreferredSize(new Dimension(780,50));
        label.setFont(new Font("Times New Roman", Font.ITALIC, 55));
        panel1.add(label);

         JTextField tf = new JTextField("Search");
         tf.setBorder(brd);
         tf.setBounds(10,10,575,40);
         panel3.add(tf);
         
         //Add the first products avail 
         JLabel prod1=new JLabel();
         prod1.setBounds(10,60,150,150);
         ImageIcon im6= new ImageIcon(new ImageIcon("/Users/vishal/Documents/BOOKMANSYS/src/DBM/Harry.png").getImage().getScaledInstance(100, 200, Image.SCALE_DEFAULT));
         prod1.setIcon(im6);
         panel3.add(prod1);
         
         JLabel prod2=new JLabel("HARRY POTTER");
         prod2.setFont(new Font("Times New Roman",Font.BOLD,20));
         prod2.setBounds(120,20,200,100);
         panel3.add(prod2);
         
         JLabel prod3=new JLabel("Author: JK Rowling");
         prod3.setFont(new Font("Times New Roman",Font.PLAIN,15));
         prod3.setBounds(120,50,200,100);
         panel3.add(prod3);
         
         JTextArea txt = new JTextArea(
        		 "Harry Potter and the Half-Blood Prince is a fantasy novel written by British author J.K. Rowling and the sixth and penultimate novel in the Harry Potter series."
        		 + '\n'+"Set during Harry Potter's sixth year at Hogwarts, the novel explores the past of the boy wizard's nemesis, Lord Voldemort, and Harry's preparations for the final battle against Voldemort alongside his headmaster and mentor Albus Dumbledore."
        	         		+ "\n"
        	         		+ "The book was published in the United Kingdom by Bloomsbury and in the United States by Scholastic on 16 July 2005, as well as in several other countries. It sold nine million copies in the first 24 hours after its release, a record that was eventually broken by its sequel, Harry Potter and the Deathly Hallows."
        	         		+ "Reviewers noted that the book took on a darker tone than its predecessors, though it did contain some humour. Some considered the main themes to be love, death, trust, and redemption. "
        	         		+ "\n"
        	         		+ "The considerable character development of Harry and many other teenage characters also drew attention."	
        		);
         txt.setEditable(false);
         JScrollPane js=new JScrollPane(txt);
         js.setBounds(120,110,510,100);
         panel3.add(js);
         
         //Add the second products avail 
         JLabel prod4=new JLabel();
         prod4.setBounds(10,230,200,150);
         ImageIcon im7= new ImageIcon(new ImageIcon("/Users/vishal/Documents/BOOKMANSYS/src/DBM/cosmos.jpeg").getImage().getScaledInstance(100, 200, Image.SCALE_DEFAULT));
         prod4.setIcon(im7);
         panel3.add(prod4);
         
         JLabel prod5=new JLabel("COSMOS: CARL SAGAN");
         prod5.setFont(new Font("Times New Roman",Font.BOLD,20));
         prod5.setBounds(120,190,300,100);
         panel3.add(prod5);
         
         JLabel prod6=new JLabel("Author:Carl Sagan ");
         prod6.setFont(new Font("Times New Roman",Font.PLAIN,15));
         prod6.setBounds(120,120,200,300);
         panel3.add(prod6);
         
         JTextArea txt1 = new JTextArea("Cosmos is a 1980 popular science book by astronomer and Pulitzer Prize-winning author Carl Sagan."+"\n"+"Its 13 illustrated chapters, corresponding to the 13 episodes of the Cosmos TV series, which the book was co-developed with and intended to complement,"+"\n"+"Explore the mutual development of science and civilization. One of Sagan's main purposes for the book and television series was to explain complex scientific ideas to anyone interested in learning."+"\n"+"Sagan also believed the television was one of the greatest teaching tools ever invented, so he wished to capitalize on his chance to educate the world.");
         txt1.setEditable(false);
         JScrollPane js1=new JScrollPane(txt1);
         js1.setBounds(120,280,510,100);
         panel3.add(js1);         
         
        JButton cart = new JButton("Cart");
        ImageIcon imgIcon1 = new ImageIcon(new ImageIcon("/Users/vishal/Documents/BOOKMANSYS/src/DBM/cart.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        cart.setIcon(imgIcon1);
        cart.setPreferredSize(new Dimension(140, 40));
        cart.setBackground(Color.WHITE);
        panel2.add(cart);
        
        cart.addActionListener(new ActionListener() 
        {
        	@Override
			public void actionPerformed(ActionEvent e) 
			{
        		int i=0;
				try 
				{	
				
				Connection connection = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/BookManagSys","postgres","vishaal1");
				
				 PreparedStatement st2= connection.prepareStatement("select l.userid from login l where userid BETWEEN 100 AND 200");
                 ResultSet rs2= st2.executeQuery();
                 
                 
                 while(rs2.next())
                 {
                 	i=rs2.getInt("userid");
                 	i=i*2;
                 }
				
			 	PreparedStatement st1= connection.prepareStatement("update CART set cartid='"+i+"' where productid>0");						 	
			 	int rs1= st1.executeUpdate();
			 	
				} 
				catch (SQLException e1) 
				{
					
					e1.printStackTrace();
				}
				frame.dispose();
				Cart c= new Cart();
				c.setVisible(true);
				
			}
        });
        
       
        
        JButton order = new JButton("Order Details");
        ImageIcon im3=new ImageIcon(new ImageIcon("/Users/vishal/Documents/BOOKMANSYS/src/DBM/serv.png").getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT));
        order.setIcon(im3);
        order.setPreferredSize(new Dimension(140, 40));
        order.setBackground(Color.WHITE);
        panel2.add(order);
        
        order.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
				Orderdetails ord= new Orderdetails();
				ord.setVisible(true);
			}

			private void dispose() {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        
        JButton prod = new JButton("All-Products");
        ImageIcon im4=new ImageIcon(new ImageIcon("/Users/vishal/Documents/BOOKMANSYS/src/DBM/all.png").getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT));
        prod.setIcon(im4);
        prod.setPreferredSize(new Dimension(140, 40));
        prod.setBackground(Color.WHITE);
        panel2.add(prod);
        
        prod.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
				Products p= new Products();
				p.setVisible(true);
			}

			private void dispose() {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        
        JButton acc = new JButton("Your Account");
        ImageIcon im5=new ImageIcon(new ImageIcon("//Users/vishal/Documents/BOOKMANSYS/src/DBM/acc.png").getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT));
        acc.setIcon(im5);
        acc.setPreferredSize(new Dimension(140, 40));
        acc.setBackground(Color.WHITE);
        panel2.add(acc);
        
        JButton logout = new JButton("Logout");
        ImageIcon im2= new ImageIcon(new ImageIcon("/Users/vishal/Documents/BOOKMANSYS/src/DBM/login.png").getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT));
        logout.setIcon(im2);
        logout.setPreferredSize(new Dimension(140, 40));
        logout.setBackground(Color.WHITE);
        panel2.add(logout);
        
        logout.addActionListener(new ActionListener()
        {
    
        	@Override
        	public void actionPerformed(ActionEvent e)
        	{	
        		frame.dispose();
        		StartingUI s= new StartingUI();
        		s.setVisible(true);
     
				try {
					Connection connection = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/BookManagSys","postgres","vishaal1");
					PreparedStatement st2= connection.prepareStatement("delete from Login where userid BETWEEN 100 AND 200");	    
					int rs1= st2.executeUpdate();
					} 
					catch (SQLException e2) 
					{
						
						e2.printStackTrace();
					}
        	}
    
        });           
        
        JButton search= new JButton();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("/Users/vishal/Documents/BOOKMANSYS/src/DBM/search.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        search.setBorder(brd);
        search.setIcon(imageIcon);
		search.setBounds(580, 10, 40, 40);
        search.setBackground(Color.WHITE);
		panel3.add(search);
		
		
		panel4.setVisible(false);
		
		JTable jt=new JTable();
		JScrollPane scroll= new JScrollPane(jt);
		DefaultTableModel m= new DefaultTableModel();
		
		//To add the item to cart 
		JButton addtocart=new JButton();
		addtocart.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
						
			}
			
			
		});
		search.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				
				String s2=tf.getText();
				try{
					
		            int number = Integer.parseInt(s2);
		            Search s= new Search();
					s.show(number);
					frame.dispose();
					s.setVisible(true); 
		        	}
		        catch (NumberFormatException ex)
				{
		        		
		        	  	Search s= new Search();
						s.show(s2);
						frame.dispose();
						s.setVisible(true);
		           
		        }	
			}

			private String numberToWord(int s2) {
				// TODO Auto-generated method stub
				return null;
			}

			private void dispose() {
				// TODO Auto-generated method stub
				
			}
		});		
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(true);
        frame.setSize(800,500);
        frame.setVisible(true) ;
    }

	private Icon ImageIcon(URL resource) {
		// TODO Auto-generated method stub
		return null;
	}

	private static LayoutManager flowlayout() {
		// TODO Auto-generated method stub
		return null;
	}

	private static String getResource(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	private static LayoutManager setLayout(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Dimension setLocation(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Dimension setSize(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}
	
    public static void main(String[] args) throws IOException, SQLException
    { 
    	new HOME();
    }

	void setVisible(boolean b) 
	{
		
	}
 }