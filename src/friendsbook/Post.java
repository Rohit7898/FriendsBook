/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendsbook;

import static friendsbook.PostComment.cmnt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
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
            rs = st.executeQuery("Select u_num from nextnum");
            int nextNum = 0;
            if(rs.next())
            {
                n_Id= "" + rs.getInt(1);
                nextNum = rs.getInt(1) + 1;
            }
            int v = st.executeUpdate("Update nextnum set u_num = '" + nextNum + "'");
            newcmnt();
            int r = st.executeUpdate("insert into post values ('"+n_Id+"','" +u.uid+ "', '"+cmnt+ "','P','"+t+"')");
            System.out.println("***New Post Created***");
        }
        catch(SQLException e)
        {
            System.out.println("!!Try Again!!");
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
    public static void newcmnt()
    {
        Scanner input=new Scanner(System.in);
        boolean found= false;
        System.out.print("Enter your Content:");
        cmnt=input.nextLine();
        if(cmnt.contains("#"))
        {
            int f=(cmnt.indexOf("#"));
            int l=0;
            for (int i = f; i < cmnt.length(); i++) 
            {
                if (Character.isWhitespace(cmnt.charAt(i))) 
                {
                    l=i;
                    found=true;
                    break;
                }
                else
                {
                    continue;
                }
            }
            if(found)
            {
                new_tag(cmnt.substring(f, l));
            }
            else
            {
                new_tag(cmnt.substring(f));
            } 
        }
        
    }
        
    public static void new_tag(String t)
    {
        Scanner in=new Scanner(System.in);
        final String DB_URL="jdbc:mysql://mis-sql.uhcl.edu/prajapatir1738";
        ArrayList <String> tags =new ArrayList<String>();
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;
        ResultSet rs1 = null;
        Statement st1 = null;
        try
        {
            conn = DriverManager.getConnection(DB_URL,"prajapatir1738","1629042");
            st=conn.createStatement();
            rs = st.executeQuery("Select * from hashtag");
            st1=conn.createStatement();
            rs1 = st1.executeQuery("Select * from hashtag");
            while(rs.next())
            {
                tags.add(rs.getString(1));
            }
            if(tags.contains(t))
            {
                while(rs1.next())
                {
                    int u = st.executeUpdate("Update hashtag set count = '" + (rs1.getInt(2)+1)+ "' where tag='"+t+"'");
                }
            }
            else
            {
                int r = st.executeUpdate("insert into hashtag values ('"+t+"','" +0+ "')");
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
