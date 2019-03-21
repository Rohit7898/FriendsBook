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
 * @author Rohit-PC
 */
public class Notification {
    public  void CheckNotification()
      {
        Scanner input = new Scanner(System.in);
        User u=new User();
        System.out.print(u.uid);
        String type="";
        System.out.println("New Notifications:");
        System.out.println("1:Friend Requests");
        System.out.println("2:Messages");
        type=input.next();
        System.out.println();
        if(type.equals("1"))
        {   
            final String DB_URL="jdbc:mysql://mis-sql.uhcl.edu/prajapatir1738";
            Connection conn = null;
            Statement statement = null;
            Statement statement1 = null;
            ResultSet resultSet = null;
            ResultSet resultSet1 = null;
            try
            {
                conn = DriverManager.getConnection(DB_URL,"prajapatir1738","1629042");
                statement = conn.createStatement();
                statement1 = conn.createStatement();
                resultSet = statement.executeQuery("Select * from notification where receiver='"+u.uid+ "'and status ='"+0+" 'and type ='R'");
                resultSet1 = statement1.executeQuery("Select * from notification where receiver='"+u.uid+ "'and status ='"+0+" 'and type ='R'");
                System.out.println("Friend Requests:");
                System.out.println("----------------");
                int seq=0;
                while(resultSet.next())
                {
                    seq++;
                    System.out.printf(seq+". %s  %s",resultSet.getString(2),resultSet.getString(5));
                    System.out.println();
                }
                
                if(seq==0)
                {
                    System.out.println("No New Request");
                    System.out.println();
                }
                else
                {
                    int not=0,se=0;
                    System.out.println("Enter the notification number you want to process");
                    not=input.nextInt();
                    if(resultSet1.absolute(not))
                    {
                        se++;
                        System.out.printf(se+". %s  %s",resultSet1.getString(2),resultSet1.getString(5));
                        System.out.println();
                    }
                    String Selection="";
                    System.out.println("Do you want to add or delete:");
                    System.out.println();
                    System.out.println("1:Add");
                    System.out.println("2:Delete");
                    System.out.println("x:Go Back");
                    Selection = input.next();
                    System.out.println();
                    if(Selection.equals("1"))
                    {
                        int i=statement.executeUpdate("insert into friends values ('"+u.uid+"','" +resultSet1.getString(2)+"')");
                        int j=statement.executeUpdate("UPDATE notification SET status='"+1+"' where NId='"+resultSet1.getString(1)+"'"); 
                    }
                    if(Selection.equals("2"))
                    {
                        System.out.println(resultSet1.getString(2));
                        int j=statement1.executeUpdate("UPDATE notification SET status='"+1+"' where NId='"+resultSet1.getString(1)+"'"); 

                    }
                    if(Selection.equals("x"))
                    {
                        ;
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
                    resultSet.close();
                    statement.close();
                    conn.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
             }
        }
        else if(type.equals("2"))
        {   
            final String DB_URL="jdbc:mysql://mis-sql.uhcl.edu/prajapatir1738";
            Connection conn = null;
            Statement statement = null;
            ResultSet resultSet = null;
            Statement statement1 = null;
            ResultSet resultSet1 = null;
            try
            {
                conn = DriverManager.getConnection(DB_URL,"prajapatir1738","1629042");
                statement = conn.createStatement();
                resultSet = statement.executeQuery("Select * from notification where receiver='"+u.uid+ "'and status ='"+0+" 'and type ='M'");
                statement1 = conn.createStatement();
                resultSet1 = statement1.executeQuery("Select * from notification where receiver='"+u.uid+ "'and status ='"+0+" 'and type ='M'");
                System.out.println("New Messages:");
                System.out.println("----------------");
                int seq=0;
                int msgn=0;
                while(resultSet.next())
                {
                    seq++;
                    System.out.printf(seq+". %s  %s",resultSet.getString(2),resultSet.getString(5));
                    System.out.println();
                }
                if(seq==0){
                    System.out.println("No New Messsages");
                    System.out.println();
                }
                else
                {
                    String Selection="";
                    System.out.println("Select the message number you want to reply:");
                    msgn=input.nextInt();
                    System.out.println("Do you want to reply or not:");
                    System.out.println();
                    System.out.println("1:Reply");
                    System.out.println("2:Go Back");
                    Selection = input.next();
                    System.out.println();
                    if(Selection.equals("1"))
                    {
                        if(resultSet1.absolute(msgn))
                        {
                            new Message().chat(resultSet1.getString(2),u.uid);
                            int j=statement.executeUpdate("UPDATE notification SET status='"+1+"' where Sender='"+resultSet1.getString(2)+"'"); 
                        }
                    }
                    if(Selection.equals("2"))
                    {
                        if(resultSet1.absolute(msgn))
                        {
                            int j=statement.executeUpdate("UPDATE notification SET status='"+1+"' where Sender='"+resultSet1.getString(2)+"'"); 
                        }
                        ;
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
                    resultSet.close();
                    statement.close();
                    resultSet1.close();
                    statement1.close();
                    conn.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
