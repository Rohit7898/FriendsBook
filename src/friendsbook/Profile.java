/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendsbook;

import static friendsbook.User.uid;
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
public class Profile 
{
    public static void Update()
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
            showProfile();
            Scanner input1 = new Scanner(System.in);
            String Selection="";
            while(rs.next())
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
                        if(rs.getString(2).equals(name))
                        {
                            System.out.println("Its same as current name");
                        }
                        else
                        {
                            int r=st.executeUpdate("Update user Set UName='"+name+"' where Id='"+uid+"'");
                            System.out.println("****Profile Updated****");
                            showProfile();
                        }
                    }
                    if(Selection.equals("2"))
                    {
                        System.out.println("Enter new Password:");
                        String name=input.next();
                        if(rs.getString(3).equals(name))
                        {
                            System.out.println("Its same as current Password");
                        }
                        else
                        {
                            int r=st.executeUpdate("Update user Set Password='"+name+"' where Id='"+uid+"'");
                            System.out.println("****Profile Updated****");
                            showProfile();
                        } 
                    }
                    if(Selection.equals("3"))
                    {
                        System.out.println("Enter new Gender:");
                        String name=input.next();
                        if(rs.getString(4).equals(name))
                        {
                            System.out.println("Its same as current Gender");
                        }
                        else
                        {
                            int r=st.executeUpdate("Update user Set Gender='"+name+"' where Id='"+uid+"'");
                            System.out.println("****Profile Updated****");
                            showProfile();
                        }
                    }
                    if(Selection.equals("4"))
                    {
                        System.out.println("Enter new Birthday:");
                        String name=input.next();
                        if(rs.getString(6).equals(name))
                        {
                            System.out.println("Its same as current birthday value");
                        }
                        else
                        {
                        int r=st.executeUpdate("Update user Set Birthday='"+name+"' where Id='"+uid+"'");
                        System.out.println("****Profile Updated****");
                        showProfile();
                        }
                    }
                    if(Selection.equals("5"))
                    {
                        System.out.println("Enter new Education:");
                        String name=input.next();
                        if(rs.getString(5).equals(name))
                        {
                            System.out.println("Its same as current name of education");
                        }
                        else
                        {
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
                rs.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    public static void showProfile()
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
}
