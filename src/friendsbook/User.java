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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author PrajapatiR1738
 */
public class User {
    public static String uid="";
    private static Pattern userName = Pattern.compile("(?=.*\\d)(?=.*[a-zA-Z])(?=.*[#?!*]).{3,10}");
    public static void Register(){
             String id="";
             String Name="";
             String password="";
            String Gender="";
            String Education="";
            String Birthday="";
            boolean validate=true;
            ArrayList<String> names = new ArrayList<String>();
    Scanner input = new Scanner (System.in);
    while(validate){
         System.out.println("Enter your id:");
    id = input.next();
    
    
       
        final String DB_URL="jdbc:mysql://mis-sql.uhcl.edu/prajapatir1738";
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
         
        try{
            
           conn = DriverManager.getConnection(DB_URL,"prajapatir1738","1629042");
            st = conn.createStatement();
            rs=st.executeQuery("Select Id from User");
            while(rs.next()){
                names.add(rs.getString(1));
            }
             Matcher mtch = userName.matcher(id);
        if (mtch.matches()) 
        {  
            if(names.contains(id))
            {
                System.out.print("Name already Exist");
            }
            else
            {
                validate=false;
                System.out.println("Enter your name:");
                Name = input.next();  
                System.out.println("Enter your password:");
                password = input.next();
                System.out.println("Enter your Gender:");
                Gender = input.next();
                System.out.println("Enter your education:");
                Education = input.next();
                System.out.println("Enter your Birthday:");
                Birthday = input.next(); 
                int r = st.executeUpdate("Insert into user value('"+id+"', '"+Name+"', '"+password+"', '"+Gender+"', '"+Education+"', '"+Birthday+"')");
                System.out.println("The new user account is created!");
                new User().Login();
            }
        
        }
        else
        {
                 System.out.println("Invalid Id");
         continue;
        }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            try{
                conn.close();
                st.close();
                rs.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    }
    
    public static void Login(){
         Scanner input=new Scanner(System.in);
        String password="";
        
        
        System.out.print("Please enter your ID");
         uid=input.next();
        
        System.out.print("Please enter your password");
        password=input.next();
        
         final String DB_URL="jdbc:mysql://mis-sql.uhcl.edu/prajapatir1738";
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        
        try{
            conn = DriverManager.getConnection(DB_URL,"prajapatir1738","1629042");
            st = conn.createStatement();
            rs=st.executeQuery("Select * from user Where Id='"+uid+"'and Password='"+password+"'");
            
            if(rs.next()){
                 Scanner input1 = new Scanner(System.in);
                String Selection="";
                
                while(!Selection.equals("x")){
                    System.out.println("1.Select an update and post");
                System.out.println("2:Check Notification");
                System.out.println("3:Create a new post");
                System.out.println("4:Friends");
                System.out.println("5:Update Profile");
                System.out.println("6:Send a message");
                System.out.println("7:Send a friend request");
                System.out.println("8:See Hashtag in trend");
                System.out.println("x:Logout");
                Selection = input1.nextLine();
                System.out.println();
                    if(Selection.equals("1"))
                {
                }
                if(Selection.equals("2"))
                {
                    new Notification().CheckNotification();
                }
                if(Selection.equals("3"))
                {
                }
                if(Selection.equals("4"))
                {
                   new AllFriends().myFriends();
                }
                if(Selection.equals("5"))
                {
                }
                if(Selection.equals("6"))
                {
                   new Friends().Msg();
                }
                if(Selection.equals("7"))
                {
                   Friend_Request.SendFriendReq();
                }
                if(Selection.equals("8"))
                {
                }
                if(Selection.equals("x"))
                {
                    ;
                }
                }
                
            }
            else{
                System.out.print("Try again!!!");
            }
           }
        catch(SQLException e){
            e.printStackTrace();
}
        finally{
            try{
                conn.close();
                st.close();
                rs.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
