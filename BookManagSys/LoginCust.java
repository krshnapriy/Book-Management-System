package DBM;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

abstract class user2{
    abstract void userlogin1();

	protected void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
}

public class LoginCust extends user2{

    void userlogin1()
    {
        JFrame f= new JFrame();
        f.setSize(700,500);
        
        JPanel p1 = new JPanel();
        p1.setBounds(0, 50, 780, 70);
        p1.setBackground(new Color(25, 102, 80));
        f.getContentPane().add(p1,BorderLayout.NORTH);

        JPanel p = new JPanel();
        p.setLayout(null);
        f.add(p);
        
        JLabel l = new JLabel("LOGIN");
        l.setForeground(Color.WHITE);
        l.setBounds(275,10,300,40);
        l.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        p1.add(l);
       
        p.setBackground(Color.WHITE);
        
        ImageIcon img = new ImageIcon(new ImageIcon("/Users/vishal/Documents/BOOKMANSYS/src/DBM/personicon.jpeg").getImage().getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH));         
        JLabel piclabel= new JLabel(img);
        piclabel.setBounds(280,10,150,150);
        p.add(piclabel);
        
        
        JLabel l1= new JLabel("USER ID :");
        l1.setBounds(160,160,300,40);
        l1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        p.add(l1);
        
        
        final JTextField t1= new JTextField();
        t1.setBounds(280,160,250,40);
        p.add(t1);
        
        JLabel l2= new JLabel("PASSWORD :");
        l2.setBounds(120,210,250,40);
        l2.setFont(new Font("Times New Roman",Font.PLAIN,20));
        p.add(l2);
        
        final JPasswordField t2= new JPasswordField();
        t2.setBounds(280,210,250,40);
        p.add(t2);
        
        
        final JButton b1= new JButton("SUBMIT");
        b1.setBounds(300,300,100,40);
        b1.setBackground(new Color(25, 102, 80));
        b1.setForeground(Color.BLACK);
        b1.setOpaque(true);
        p.add(b1);
        
        
         f.setResizable(false);
         f.setVisible(true);
         f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
         
        
        b1.addActionListener(new ActionListener()
                {

                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        String u=t1.getText();
                        String p=t2.getText();
                        
                        try
                        {
                            Connection connection=(Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/BookManagSys","postgres","vishaal1");
                            PreparedStatement st= connection.prepareStatement("select custid,custname,password from CUSTOMER where custname=? and password=?");
                            st.setString(1,u);
                            st.setString(2,p);
                            ResultSet rs= st.executeQuery();
                            
                            PreparedStatement st2= connection.prepareStatement("delete from login where userid BETWEEN 100 AND 200");
                            int rs2=st2.executeUpdate();
                            
                            if(rs.next())
                            {
                                 String s1=rs.getString("custid");
                                 String s2=rs.getString("custname");
                                 String s3=rs.getString("password");
                    
                                 PreparedStatement st1= connection.prepareStatement("insert into LOGIN (userid,username,password) values ('"+s1+"','"+s2+"','"+s3+"')");                             
                                 int rs1= st1.executeUpdate();
                                                                       
                                 HOME h= new HOME();
                                 f.dispose();
                                 h.setVisible(true);        
                            } 
                            else
                            {
                                JOptionPane.showMessageDialog(b1,"Invaid Credentials");
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

    public void setVisible(boolean b) {
        // TODO Auto-generated method stub
        
    }

    public static void main(String[] args) 
    {
         LoginCust obj = new LoginCust();
         obj.userlogin1();
    }
}
