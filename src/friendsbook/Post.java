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
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author Rohit-PC
 */
public class Post {
    public void new_post()
    {

        User u=new User();
        String n_Id="";
        Scanner in=new Scanner(System.in);
        Timestamp t=new Timestamp(Calendar.getInstance().getTime().getTime());
        final String DB_URL="jdbc:mysql://mis-sql.uhcl.edu/prajapatir1738";
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;
        try
        {
            conn = DriverManager.getConnection(DB_URL,"prajapatir1738","1629042");
            st=conn.createStatement();
            rs = st.executeQuery("Select n_num from nextnum");
            int nextNum = 0;
            String cont="";
            boolean empty=true;
            if(rs.next())
            {
                n_Id= "" + rs.getInt(1);
                nextNum = rs.getInt(1) + 1;
            }
            int v = st.executeUpdate("Update nextnum set u_num = '" + nextNum + "'");
            System.out.println("Enter your content:");
            cont=in.next();
            int r = st.executeUpdate("insert into post values ('"+n_Id+"','" +u.uid+ "', '"+cont+ "','P','"+t+"')");
            System.out.println("***New Post Created***");
            empty=false;
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
                conn.close();
                ;
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }     
    }
}
