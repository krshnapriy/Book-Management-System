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


public class Orderdetails { 
	
	Orderdetails()
	{
		JFrame f = new JFrame();
		f.setSize(780,500);

		JPanel p1 = new JPanel();
		p1.setBounds(0, 50, 800, 70);
		p1.setBackground(new Color(25, 102, 80));
		f.getContentPane().add(p1,BorderLayout.NORTH);

		JPanel p = new JPanel();
		p.setLayout(null);
		f.getContentPane().add(p);
		JLabel l = new JLabel("ORDER DETAILS");
		l.setForeground(Color.WHITE);
		l.setBounds(275,10,300,40);
		l.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		p1.add(l);
		
        JButton b= new JButton("BACK");
       // b.setBounds(10,300,80,40);
        b.setBounds(10,300,100,35);
        b.setBackground(new Color(25, 102, 80));
        b.setForeground(Color.BLACK);
        p.add(b);
        
        b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
					HOME h= new HOME();
					h.setVisible(true);
			}
        	
        });


		String[] col= {"PRODUCT","QUANTITY","TOTAL PRICE","ORDER DATE"};
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
		int s2=1;
		int s3=0;
		String s4="";


		try
		{
		    Connection connection=(Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/BookManagSys","postgres","vishaal1");
		    PreparedStatement st= connection.prepareStatement("select p.productname,o.quantity,o.transacdate from products p,  login l, orderdetails o,customer c where o.custid=l.userid and p.productid=o.productid"
		    		+ "");  		    
		    ResultSet rs= st.executeQuery();
		    while(rs.next()) 
		    {
		        s1=rs.getString("productname");
		        s2=rs.getInt("quantity");
		        //s3=rs.getInt("totalprice");
		        s4=rs.getString("transacdate");
		        m.addRow(new Object[]{s1,s2,s4});
		    }
		}

		catch(SQLException sqlException)
		{
		    sqlException.printStackTrace();
		}
		
		 f.setResizable(true );
		 f.setVisible(true);
		 f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

public static void main(String[] args)
{
new Orderdetails();
}

public void setVisible(boolean b) {
	// TODO Auto-generated method stub
	
}
}