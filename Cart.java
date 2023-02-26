package DBM;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


public class Cart { 
	
	protected int s4;
	Cart()
	{
		JFrame f = new JFrame();
		f.setSize(780,500);
		//f.getContentPane().setBackground(Color.BLUE);

		JPanel p1 = new JPanel();
		p1.setBounds(0, 50, 800, 70);
		p1.setBackground(new Color(25, 102, 80));
		f.getContentPane().add(p1,BorderLayout.NORTH);

		JPanel p = new JPanel();
		p.setLayout(null);
		f.getContentPane().add(p);
		JLabel l = new JLabel("CART");
		l.setForeground(Color.WHITE);
		l.setBounds(275,10,300,40);
		l.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		p1.add(l);


		String[] col= {"PRODUCT","UNIT PRICE","QUANTITY","TOTAL PRICE"};
		DefaultTableModel m= new DefaultTableModel();
		m.setColumnIdentifiers(col);

		JTable jt=new JTable();
		jt.setModel(m);
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		jt.setFont(new Font("Times New Roman",Font.PLAIN,15));

		jt.getColumnModel().getColumn(0).setPreferredWidth(100);
		jt.getColumnModel().getColumn(1).setPreferredWidth(10);
		jt.getColumnModel().getColumn(2).setPreferredWidth(5);
		jt.getColumnModel().getColumn(3).setPreferredWidth(10);

		jt.setRowHeight(jt.getRowHeight() + 10);

		JScrollPane scroll= new JScrollPane(jt);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(50,30,600,225);
		p.add(scroll);


		String s1="";
		String s2="";
		String s3="";
		int s4=0;


		try
		{
		    Connection connection=(Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/BookManagSys","postgres","vishaal1");
		    
		    PreparedStatement st= connection.prepareStatement("select p.productname,p.unitcost,c.quantity, (p.unitcost*c.quantity) as totalprice from cart c, products p where c.productid = p.productid");  		    
		    PreparedStatement st1= connection.prepareStatement("select sum (p.unitcost*c.quantity) as totalprice from cart c, products p where c.productid = p.productid");
		    ResultSet rs= st.executeQuery();
		    ResultSet rs1= st1.executeQuery();
		    while(rs.next()) 
		    {
		        s1=rs.getString("productname");
		        s2=rs.getString("unitcost");
		        s3=rs.getString("quantity");
		        s4=rs.getInt("totalprice");
		        m.addRow(new Object[]{s1,s2,s3,s4});
		    }
		    
		    if(rs1.next())
		    {
		    	int sum=rs1.getInt(1);
		        	
		    	JLabel label = new JLabel("Amount To Pay: " + sum);
		    	label.setBounds(400,260,200,40);
		    	label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		    	p.add(label);
		     }
		}

		catch(SQLException sqlException)
		{
		    sqlException.printStackTrace();
		}

		JButton b1= new JButton("CHECKOUT");
		b1.setBounds(480,300,100,30);
		b1.setBackground(new Color(25, 102, 80));
		b1.setForeground(new Color(25, 102, 80));
		b1.setOpaque(true);
		p.add(b1);

		b1.addActionListener(new ActionListener()
				{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(b1,"Proceeding to Payment");
				
				f.dispose();
			 	Payment p= new Payment();
			 	p.setVisible(true);
			}
			
		});	
			
		    f.setResizable(true );
		    f.setVisible(true);
		    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
public static void main(String[] args)
{
	new Cart();
}
public void setVisible(boolean b) {
	// TODO Auto-generated method stub
	
}
}