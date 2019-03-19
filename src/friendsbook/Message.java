/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendsbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author PrajapatiR1738
 */
public class Message 
{
    public void send_msg()
    {

        User u=new User();
        String f_Id="";
        String n_Id="";
        Scanner in=new Scanner(System.in);
        System.out.println("Enter User Id to send Message:");
        f_Id=in.next();
        final String DB_URL="jdbc:mysql://mis-sql.uhcl.edu/prajapatir1738";
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ResultSet rs = null;
        Statement st = null;
        Statement stf = null;
        ResultSet rsf = null;
        try
        {
            conn = DriverManager.getConnection(DB_URL,"prajapatir1738","1629042");
            statement = conn.createStatement();
            st=conn.createStatement();
            stf=conn.createStatement();
            resultSet = statement.executeQuery("Select Id from user where Id='"+f_Id+"'");
            rs = st.executeQuery("Select n_num from nextnum");
            rsf = stf.executeQuery("Select * from friends");
            int nextNum = 0;
            String msg="";
            boolean empty=true;
            if(resultSet.next())
            {
                while(rsf.next())
                {
                    if((rsf.getString(1).equals(u.uid)&&rsf.getString(2).equals(f_Id))||(rsf.getString(2).equals(u.uid)&&rsf.getString(1).equals(f_Id)))
                    {
                        if(rs.next())
                        {
                            n_Id= "" + rs.getInt(1);
                            nextNum = rs.getInt(1) + 1;
                        }
                        int t = statement.executeUpdate("Update nextnum set n_num = '" + nextNum + "'");
                        System.out.println("Enter your message:");
                        msg=in.next();
                        int r = statement.executeUpdate("insert into notification values ('"+n_Id+"','" +u.uid+ "', '"+f_Id+ "','M', '"+msg+"','"+0+"')");
                        System.out.println("***Message Sent***");
                        empty=false;
                    }
                }
                if(empty)
                {
                    System.out.println("This person is not your friend");
                }
            }
            }
        catch(SQLException e)
        {
            System.out.println("Friend's Id NOT FOUND!!Try Again!!");
            e.printStackTrace();
        }
        finally
        {
            try
            {
                rs.close();
                st.close();
                rsf.close();
                stf.close();
                resultSet.close();
                statement.close();
                conn.close();
                ;
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }     
    }
    public void chat(String s,String r){
        System.out.println(s);
        System.out.println(r);
    }
}