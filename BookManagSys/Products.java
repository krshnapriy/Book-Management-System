package DBM;

import javax.swing.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Vector;

import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
public class Products {
	
	public Object panel2;


	Products()
	{
		
		JFrame frame = new JFrame();
        frame.getContentPane().setLayout(null);
  
        JPanel panel1 = new JPanel();
        JPanel panel2= new JPanel(setLayout(null));

        //Creation of object to draw border
        Border br=BorderFactory.createLineBorder(Color.white);
        Border brd=BorderFactory.createLineBorder(Color.black);
              
        panel1.setBounds(00, 0, 800, 70);
        panel1.setBorder(br);
        panel1.setBackground(new Color(25, 102, 80));
        frame.getContentPane().add(panel1,BorderLayout.NORTH);
        
        JLabel label= new JLabel("Shopprix");
        label.setForeground(Color.WHITE);
        label.setPreferredSize(new Dimension(780,50));
        label.setFont(new Font("Times New Roman", Font.ITALIC, 55));
        panel1.add(label);

        panel2.setBounds(0, 70, 850, 700);
        panel2.setBackground(new Color(25, 102, 80));
        frame.add(panel2);
        panel2.setVisible(true);
        
        JButton b= new JButton();
        b.setBounds(10,30,80,80);
        ImageIcon img=new ImageIcon(new ImageIcon("/Users/vishal/Documents/BOOKMANSYS/src/DBM/home1.png").getImage().getScaledInstance(60,60,Image.SCALE_DEFAULT));
        b.setIcon(img);
        panel2.add(b);
        
        b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
					HOME h= new HOME();
					h.setVisible(true);
			}
        	
        });
		
		JTable jt=new JTable();
		JScrollPane scroll= new JScrollPane(jt);
		DefaultTableModel m= new DefaultTableModel();
		
		
		panel2.setVisible(true);
				
						
		String[] col= {"NAME","PRICE","GENRE","ACTION"};
								
		m.setColumnIdentifiers(col);

						
		jt.setModel(m);
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		jt.setFont(new Font("Times New Roman",Font.PLAIN,15));

						

						
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(100,0,800,800);
		panel2.add(scroll);

						

			try
			{
			String s1="";
			String s2="";
		    String s3="";

		    Connection connection=(Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/BookManagSys","postgres","vishaal1");
			PreparedStatement st= connection.prepareStatement("select productname,unitcost,genre from  PRODUCTS where unitcost>=0");

					
			ResultSet rs= st.executeQuery();
			while(rs.next())
				{
				 s1=rs.getString("productname");
				 s2=rs.getString("unitcost");
				 s3=rs.getString("genre");
						       
				 m.addRow(new Object[]{s1,s2,s3,"Add to cart"});
				} 
			}
			catch(SQLException sqlException)
			 {
				 sqlException.printStackTrace();
			 }
						
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(true);
        frame.setSize(780,500);
        frame.setVisible(true) ;
        
        
        jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        jt.getSelectionModel().addListSelectionListener(new ListSelectionListener()
        {
        	@Override
			public void valueChanged(ListSelectionEvent e) {
        		{

                 String s1=jt.getValueAt(jt.getSelectedRow(), 0).toString();
                 System.out.print(s1);
                
               
				try {
					Connection connection = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/BookManagSys","postgres","vishaal1");
					
					PreparedStatement sta= connection.prepareStatement("select productid from  PRODUCTS where productname=?");
					sta.setString(1,s1);
					ResultSet rs= sta.executeQuery();
					
					int cid = 0;
					int sid=0;
					int q=1;
					
					PreparedStatement st2= connection.prepareStatement("select userid from LOGIN where userid BETWEEN 100 AND 200 ");		
					ResultSet rs2= st2.executeQuery();
					
					PreparedStatement st3= connection.prepareStatement("select userid from LOGIN where userid BETWEEN 200 AND 300 ");		
					ResultSet rs3= st3.executeQuery();
					
					
					while(rs2.next() && rs3.next())
					{
						cid=rs2.getInt("userid");
						sid=rs3.getInt("userid");
					}
					
					
					 while(rs.next())
					    {
						 	int s2=rs.getInt("productid");
						 	
						 	
						 	PreparedStatement st1= connection.prepareStatement("insert into CART (cartid,productid,quantity,custid,sellerid) values (0,'"+s2+"','"+q+"','"+cid+"','"+sid+"')");						 	
						 	int rs1= st1.executeUpdate();
						 		 							 	
						 	frame.dispose();
						 	HOME h= new HOME();
						 	h.setVisible(true);
						 	
						 		
					    } 
					
				}
				catch (SQLException e1) 
				{
					
					e1.printStackTrace();
				}

            }

        	}

			private void dispose() {
				// TODO Auto-generated method stub
				
			}
        	
        	
            
       });
     
       }
	
    private LayoutManager setLayout(Object object) {
		// TODO Auto-generated method stub
		return null;
	}



	public static void main(String[] args) throws IOException, SQLException
    { 
    	new Products();
    }
	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}

 }

