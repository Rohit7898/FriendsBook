/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendsbook;

import static friendsbook.User.uid;
import java.sql.Connection;
import java.sql.Date;
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
 * @author PrajapatiR1738
 */
public class Profile 
{
    String u_id="";
    int nextnum=0;
    public void Update()
    {
        Scanner input=new Scanner(System.in);
        Timestamp t=new Timestamp(Calendar.getInstance().getTime().getTime());
        ArrayList <String> user=new ArrayList<String>();
        final String DB_URL="jdbc:mysql://mis-sql.uhcl.edu/prajapatir1738";
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        Statement st1 = null;
        ResultSet rs1 = null;
        try
        {
            conn = DriverManager.getConnection(DB_URL,"prajapatir1738","1629042");
            st = conn.createStatement();
            rs=st.executeQuery("Select * from user Where Id='"+uid+"'");
            st1 = conn.createStatement();
            rs1=st1.executeQuery("Select u_num from nextnum");
            showProfile();
            Scanner input1 = new Scanner(System.in);
            String Selection="";
            while(rs.next())
            {
                user.add(rs.getString(2));
                user.add(rs.getString(3));
                user.add(rs.getString(4));
                user.add(rs.getString(5));
                user.add(rs.getString(6));
            }
            {
                while(!Selection.equals("x"))
                {
                    System.out.println("Select the option that you want to update");
                    System.out.println("1.Name");
                    System.out.println("2:Password");
                    System.out.println("3:Gender");
                    System.out.println("4:Birthday");
                    System.out.println("5:Education");
                    System.out.println("x:Go Back");
                    Selection = input1.nextLine();
                    System.out.println();
                    if(Selection.equals("1"))
                    {
                        System.out.println("Enter new name:");
                        String name=input.next();
                        if(user.get(0).contains(name))
                        {
                            System.out.println("Its same as current name");
                        }
                        else
                        {
                            uppost();
                            int i=st1.executeUpdate("insert into post values ('"+u_id+"','"+uid+"','Updated name','U','"+t+"')");
                            int r=st.executeUpdate("Update user Set UName='"+name+"' where Id='"+uid+"'");
                            System.out.println("****Profile Updated****");
                            showProfile();
                        }
                    }
                    if(Selection.equals("2"))
                    {
                        System.out.println("Enter new Password:");
                        String name=input.next();
                        if(user.get(1).contains(name))
                        {
                            System.out.println("Its same as current Password");
                        }
                        else
                        {
                            uppost();
                            int i=st1.executeUpdate("insert into post values ('"+u_id+"','"+uid+"','Updated password','U','"+t+"')");
                            int r=st.executeUpdate("Update user Set Password='"+name+"' where Id='"+uid+"'");
                            System.out.println("****Profile Updated****");
                            showProfile();
                        } 
                    }
                    if(Selection.equals("3"))
                    {
                        System.out.println("Enter new Gender:");
                        String name=input.next();
                        if(user.get(2).contains(name))
                        {
                            System.out.println("Its same as current Gender");
                        }
                        else
                        {
                            uppost();
                            int i=st1.executeUpdate("insert into post values ('"+u_id+"','"+uid+"','Updated Gender','U','"+t+"')");
                            int r=st.executeUpdate("Update user Set Gender='"+name+"' where Id='"+uid+"'");
                            System.out.println("****Profile Updated****");
                            showProfile();
                        }
                    }
                    if(Selection.equals("4"))
                    {
                        System.out.println("Enter new Birthday:");
                        String name=input.next();
                        if(user.get(4).contains(name))
                        {
                            System.out.println("Its same as current birthday value");
                        }
                        else
                        {
                            uppost();
                            int i=st1.executeUpdate("insert into post values ('"+u_id+"','"+uid+"','Updated birthday date','U','"+t+"')");
                            int r=st.executeUpdate("Update user Set Birthday='"+name+"' where Id='"+uid+"'");
                            System.out.println("****Profile Updated****");
                            showProfile();
                        }
                    }
                    if(Selection.equals("5"))
                    {
                        System.out.println("Enter new Education:");
                        String name=input.next();
                        if(user.get(3).contains(name))
                        {
                            System.out.println("Its same as current name of education");
                        }
                        else
                        {
                            uppost();
                            int i=st1.executeUpdate("insert into post values ('"+u_id+"','"+uid+"','Updated education','U','"+t+"')");
                            int r=st.executeUpdate("Update user Set Education='"+name+"' where Id='"+uid+"'");
                            System.out.println("****Profile Updated****");
                            showProfile();
                        }
                    }
                    if(Selection.equals("x"))
                    {
                        ;
                    }
                } 
            } 
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                conn.close();
                st.close();
                //rs.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    public  void showProfile()
    {
        Scanner input=new Scanner(System.in);
        final String DB_URL="jdbc:mysql://mis-sql.uhcl.edu/prajapatir1738";
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try
        {
            conn = DriverManager.getConnection(DB_URL,"prajapatir1738","1629042");
            st = conn.createStatement();
            rs=st.executeQuery("Select * from user Where Id='"+uid+"'");
            if(rs.next())
            {
                System.out.println("Name:"+rs.getString(2));
                System.out.println("Password:"+rs.getString(3));
                System.out.println("Gender:"+rs.getString(4));
                System.out.println("Birthdat:"+rs.getString(6));
                System.out.println("Education:"+rs.getString(5));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                conn.close();
                st.close();
                rs.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    public void uppost(){
        final String DB_URL="jdbc:mysql://mis-sql.uhcl.edu/prajapatir1738";
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try
        {
            conn = DriverManager.getConnection(DB_URL,"prajapatir1738","1629042");
            st = conn.createStatement();
            rs=st.executeQuery("Select u_num from nextnum");
            if(rs.next())
                {
                    u_id= "" + rs.getInt(1);
                    nextnum = rs.getInt(1) + 1;
                }
                int u = st.executeUpdate("Update nextnum set u_num = '" + nextnum + "'");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                conn.close();
                st.close();
                rs.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
    }
}
