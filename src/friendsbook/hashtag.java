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
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author PrajapatiR1738
 */
public class hashtag {
    public void show_tag(){
        
        User u=new User();
        final String DB_URL="jdbc:mysql://mis-sql.uhcl.edu/prajapatir1738";
        Connection conn = null;
        ArrayList <String> friends =new ArrayList<String>();
        Scanner in=new Scanner(System.in);
        Statement statement = null;
        ResultSet resultSet = null;
        ResultSet resultSet1 = null;
        Statement statement1 = null;
        ResultSet resultSet2 = null;
        Statement statement2 = null;
        try
        {
            conn = DriverManager.getConnection(DB_URL,"prajapatir1738","1629042");
            statement = conn.createStatement();
            statement1 = conn.createStatement();
            statement2 = conn.createStatement();
            resultSet = statement.executeQuery("Select * from hashtag order by count desc limit 3");
            resultSet1 = statement1.executeQuery("Select * from post where type='P'");
            int seq=0,se=0;
            boolean empty=true;
            while(resultSet.next())
            {
                seq++;
                System.out.println(seq+". "+resultSet.getString(1));
                empty=false;
            }
            if(empty)
            {
                System.out.println("No hashtag in trend");
            }
            System.out.println("Enter the tag you want to explore or press 'x' to go back");
            String tag=in.next();
            if(tag.equals("x"))
            {
                User.Logedin();
            }
            else
            {
                resultSet=statement.executeQuery("Select * from friends Where id1='"+uid+"' or id2='"+uid+"'");
                while(resultSet.next()){
                    if(resultSet.getString(1).equals(uid)){
                        friends.add(resultSet.getString(2));
                    }
                    else if(resultSet.getString(2).equals(uid)){
                        friends.add(resultSet.getString(1));
                    }
                }
                while(resultSet1.next())
                {
                    if(resultSet1.getString(3).contains(tag)&&friends.contains(resultSet1.getString(2)))
                    {
                        resultSet2 = statement2.executeQuery("Select uid, content from post Where pid ='"+resultSet1.getString(1)+"'");
                    }
                    if(resultSet2!=null)
                    {
                       while(resultSet2.next())
                        {
                            se++;
                            System.out.println(se+". "+resultSet2.getString(1)+": "+resultSet2.getString(2));
                        }  
                    } 
                }
            }
            
            
        }
        catch(SQLException e)
        {
            System.out.println("Problem.");
            System.out.println("Try Again!!!!.");
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
