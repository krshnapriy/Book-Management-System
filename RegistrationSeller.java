package DBM;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class RegistrationSeller extends User1 {
	static int a=209;
	static int selid=a;

	
   
	RegistrationSeller() 
	{
		
		JFrame frame = new JFrame("Registration");
        frame.setSize(800,500);
   //     frame.setLayout(null);
 
        
        JPanel p = new JPanel();
        p.setBounds(0, 80, 400, 450);
        p.setLayout(null);
        frame.add(p); 
        
        JPanel p1 = new JPanel();
        p1.setBounds(0, 0, 800, 70);
        p1.setBackground(new Color(25, 102, 80));
        frame.getContentPane().add(p1,BorderLayout.NORTH);
        
        JLabel l = new JLabel("REGISTRATION");
        l.setForeground(Color.WHITE);
        l.setBounds(220,10,400,40);
        l.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        p1.add(l);
          
          JLabel name= new JLabel("Name :");
          name.setBounds(250,30, 180, 25);
          name.setFont(new Font("Times New Roman", Font.PLAIN, 20));
          p.add(name);
          
          JTextField n = new JTextField("");
          n.setBounds(350, 30, 190, 25);
          p.add(n);
          
          JLabel pin= new JLabel("Pincode :");
          pin.setBounds(250,70, 180, 25);
          pin.setFont(new Font("Times New Roman", Font.PLAIN, 20));
          p.add(pin);
          
          JTextField pcode = new JTextField("");
          pcode.setBounds(350, 70, 190, 25);
          p.add(pcode);
          
          JLabel city= new JLabel("City :");
          city.setBounds(250,110, 180, 25);
          city.setFont(new Font("Times New Roman", Font.PLAIN, 20));
          p.add(city);
          
          JTextField ct = new JTextField("");
          ct.setBounds(350, 110, 190, 25);
          p.add(ct);
          
          JLabel district= new JLabel("District :");
          district.setBounds(250,150, 180, 25);
          district.setFont(new Font("Times New Roman", Font.PLAIN, 20));
          p.add(district);
          
          JTextField dt = new JTextField("");
          dt.setBounds(350, 150, 190, 25);
          p.add(dt);
          
          JLabel state= new JLabel("State :");
          state.setBounds(250,190, 180, 25);
          state.setFont(new Font("Times New Roman", Font.PLAIN, 20));
          p.add(state);
          
          JTextField st = new JTextField("");
          st.setBounds(350, 190, 190, 25);
          p.add(st);
          
          JLabel nationality= new JLabel("Nationality :");
          nationality.setBounds(250,230, 180, 25);
          nationality.setFont(new Font("Times New Roman", Font.PLAIN, 20));
          p.add(nationality);
          
          JTextField nation = new JTextField("");
          nation.setBounds(350, 230, 190, 25);
          p.add(nation);
          
          JLabel password= new JLabel("Password :");
          password.setBounds(250,270,180,25);
          password.setFont(new Font("Times New Roman", Font.PLAIN, 20));
          p.add(password);
          
          final JPasswordField pass = new JPasswordField();
          pass.setBounds(350, 270, 190, 25);
          p.add(pass);
          
     
          JButton reg = new JButton("REGISTER");
   		  reg.setBounds(350,340,100,40);
  	      reg.setBackground(new Color(25, 102, 80));
  		  reg.setForeground(Color.BLACK);
  		  p.add(reg);
  		  
  		 JButton reg2 = new JButton("Login if already registered");
  		  reg2.setBounds(610,0, 190, 25);
 	      reg2.setBackground(new Color(25, 102, 80));
 		  reg2.setForeground(Color.BLACK);
          p.add(reg2);
          
          reg2.addActionListener(new ActionListener()
          {

              @Override
              public void actionPerformed(ActionEvent e)
              {
              	frame.dispose();
                LoginSeller l= new LoginSeller();
                l.userlogin();
                
                  
              }

			private void dispose() {
				// TODO Auto-generated method stub
				
			}
          });
          
          
 		 frame.setResizable(false);
 		 frame.setVisible(true);
 		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          
 		
 		a=a+1;
 		selid=a;
    	
    	
 		reg.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
            	
                String name=n.getText();
                int pin = Integer.parseInt(pcode.getText());
                String district=dt.getText();
                String state=st.getText();
                String nationality=nation.getText();
                String PS= pass.getText();
                int i=100;
                
                try
                {

                    Connection connection=(Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/BookManagSys","postgres","vishaal1");
                    
                    PreparedStatement st2= connection.prepareStatement("select max(sellerid) from seller");
                    ResultSet rs2= st2.executeQuery();

                    while(rs2.next())
                    {
                    	
                    	i= rs2.getInt("max");
                    	i++;
                    }

                    PreparedStatement st= connection.prepareStatement("insert into seller (sellerid,sellername,pincode,district,state,nationality,password) values('"+i+"',?,?,?,?,?,?)");
                    PreparedStatement st1= connection.prepareStatement("select sellerid from seller where sellerid = '"+i+"'");
                   
                    
                    st.setString(1,name);
                    st.setInt(2,pin);
                    st.setString(3,district);
                    st.setString(4,state);
                    st.setString(5,nationality);
                    st.setString(6, PS);
                     
                    ResultSet rs= st1.executeQuery();
                    
                    if(rs.next())
                    {
                        JOptionPane.showMessageDialog(reg,"Already Exists");
            
                             
                    } 
                    else
                    {                     
                         int rs1= st.executeUpdate();
                         LoginSeller l= new LoginSeller();
                         l.userlogin();
                         frame.dispose();
                    }
    
                }
                catch(SQLException sqlException)
                {
                    sqlException.printStackTrace();
                }
            }

            private void dispose() {
                // TODO Auto-generated method stub
                
            }
            
        });
         
}
					private void dispose() {
                        // TODO Auto-generated method stub
                        
                    }


					public void setVisible(boolean b) {
						// TODO Auto-generated method stub
						
					}
					 public static void main(String[] args) {
					        
					       new RegistrationSeller();
					          
					    }

						
                    
}