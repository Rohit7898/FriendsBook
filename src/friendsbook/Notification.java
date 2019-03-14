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
             String notice="";
            System.out.println("Check Notifications:");
           System.out.println("--------------------");
                System.out.println("1:Friend Requests");
                System.out.println("2:Messages");
                notice = input.nextLine();
                System.out.println();
             if(notice.equals("1"))
                {   
               final String DB_URL="jdbc:mysql://mis-sql.uhcl.edu/prajapatir1738";
        
            Connection conn = null;
            Statement statement = null;
            ResultSet resultSet = null;
                
                 try
                  {
                   conn = DriverManager.getConnection(DB_URL,"prajapatir1738","1629042");
                   statement = conn.createStatement();
                 resultSet = statement.executeQuery("Select * from notification where receiver='"+u.uid+ "'and status ='"+0+" 'and type ='R'");
                 System.out.println("Friend Requests:");
                System.out.println("----------------");
                 int seq=0;
                 while(resultSet.next())
                 {
                      seq++;
                      System.out.printf(seq+". %s  %s",resultSet.getString(2),resultSet.getString(5));
                      System.out.println();
                 }

                
                
                String Selection="";
                

                while(!Selection.equals("x"))
        {
            System.out.println("Do you want to add or delete:");
                System.out.println();
                System.out.println("1:Confirm");
                System.out.println("2:Delete");
                System.out.println("x:Main Menu");
                Selection = input.nextLine();
                System.out.println();
                if(Selection.equals("1"))
                {
                   int i=statement.executeUpdate("insert into friends values ('"+u.uid+"','" +resultSet.getString(2)+"')");
                }
                if(Selection.equals("2"))
                 {
                    //Check_not.Delete();
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
      
      }}
}
