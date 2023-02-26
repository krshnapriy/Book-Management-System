package DBM;
	import java.awt.Font;
	import java.awt.BorderLayout;
	import java.awt.Color;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.sql.Connection;
	import java.sql.Date;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.text.DateFormat;
	import java.text.SimpleDateFormat;

	import javax.swing.*;
	import javax.swing.border.EmptyBorder;
	import javax.swing.table.DefaultTableModel;


	public class Payment{
		Payment()
		{
		JFrame f = new JFrame();
		f.setSize(700,500);
		
		JPanel p1 = new JPanel();
		p1.setBounds(0, 50, 800, 70);
		p1.setBackground(new Color(25, 102, 80));
		f.getContentPane().add(p1,BorderLayout.NORTH);

		JPanel p = new JPanel();
		p.setLayout(null);
		f.add(p);
		
		JLabel l = new JLabel("PAYMENT");
		l.setForeground(Color.WHITE);
		l.setBounds(275,10,300,40);
		l.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		p1.add(l);
		
		   
	    JLabel l1= new JLabel("CARD TYPE :");
	    l1.setBounds(160,40,250,40);
	    l1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
	    p.add(l1);
	    
	    
		JTextField t1= new JTextField();
		t1.setBounds(290,40,290,30);
		p.add(t1);
		
		JLabel l2= new JLabel("CARD NUMBER :");
		l2.setBounds(160,80,250,30);
		l2.setFont(new Font("Times New Roman",Font.PLAIN,15));
		p.add(l2);
		
	    
		JTextField t2= new JTextField();
		t2.setBounds(290,80,290,30);
		p.add(t2);
		
		JLabel l3= new JLabel("NAME ON CARD :");
		l3.setBounds(160,120,250,30);
		l3.setFont(new Font("Times New Roman",Font.PLAIN,15));
		p.add(l3);
		
		JTextField t3= new JTextField();
		t3.setBounds(290,120,290,30);
		p.add(t3);
	  
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");  
		Date date = new Date(0);  

		JLabel l5= new JLabel("CVV NUMBER :");
		l5.setBounds(160,160,250,30);
		l5.setFont(new Font("Times New Roman",Font.PLAIN,15));
		p.add(l5);
		
		JTextField t5= new JTextField();
		t5.setBounds(290,160,60,30);
		p.add(t5);
		
		JButton b1= new JButton("CONFIRM PAYMENT");
		b1.setBounds(225,250,250,40);
		b1.setBackground(new Color(25, 102, 80));
		b1.setForeground(Color.BLACK);
		b1.setOpaque(true);
		p.add(b1);
		
			
		JButton b2= new JButton("BACK");
		b2.setBounds(10,340,70,40);
		b2.setBackground(new Color(25, 102, 80));
		b2.setForeground(Color.BLACK);
		b2.setOpaque(true);
		p.add(b2);

		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Cart c= new Cart();
				c.setVisible(true);
			}
		});
			
		b1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{

	                String cardtype = t1.getText();
	                String cardno  = t2.getText();
	                String name  = t3.getText();
	                Date tdate = date;
	                String cvv = t5.getText();
	                int sellerid=0;
      			 	int custid=0;
      			 	int prodid=0;
	               	                
	                try {
	                	Connection connection=(Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/BookManagSys","postgres","vishaal1");
	                	PreparedStatement st1= connection.prepareStatement("select cartid from CART where quantity>0");					
						ResultSet rs1= st1.executeQuery();
						
						int c=0;
          	
						 while(rs1.next())
						    {
							 	int cartid=rs1.getInt("cartid");
							
							 	PreparedStatement st4= connection.prepareStatement("SELECT c.custid,c.sellerid,c.productid from  cart c where c.custid BETWEEN 100 AND 200 or c.sellerid BETWEEN 200 AND 300 ");	                	              			 							 	
		          			 	ResultSet rs4= st4.executeQuery();
		          			 	while(rs4.next())
		          				{
		              			 	sellerid=rs4.getInt("sellerid");
		              			 	custid=rs4.getInt("custid");
		              			 	prodid=rs4.getInt("productid");
		              			 	
		              			 	PreparedStatement st2= connection.prepareStatement("insert into ORDERDETAILS (orderid,cardno,cardtype,transacdate,sellerid,custid,productid,quantity) values ('"+cartid+"','"+cardno+"','"+cardtype+"','"+tdate+"','"+sellerid+"','"+custid+"','"+prodid+"',1)");	                	              			 							 	
								 	int rs2= st2.executeUpdate();
								 	c++;
								 	JOptionPane.showMessageDialog(b2,c);
		              			 	
		          				}
		          			 	
		          			 	
		          			 	
	              			 
							 	
							 	PreparedStatement st3= connection.prepareStatement("DELETE FROM CART;");					
								int rs3= st3.executeUpdate();
							 	
								f.dispose();
								
							 	HOME h= new HOME();
							 	h.setVisible(true);
						    }  
	                	}
	                catch(SQLException e1)
	                {
	                	e1.printStackTrace();
	                }
			}
			});
		
		f.setResizable(false);
	    f.setVisible(true);
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		}
		
		public static void main(String[] args)
		{
			new Payment();
		}

		public void setVisible(boolean b) {
			// TODO Auto-generated method stub
			
		}
	}
