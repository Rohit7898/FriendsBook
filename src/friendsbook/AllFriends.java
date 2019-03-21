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

/**
 *
 * @author Rohit-PC
 */
public class AllFriends {
    public void myFriends(){
        
        User u=new User();
        final String DB_URL="jdbc:mysql://mis-sql.uhcl.edu/prajapatir1738";
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ResultSet resultSet1 = null;
        Statement statement1 = null;
        try
        {
            conn = DriverManager.getConnection(DB_URL,"prajapatir1738","1629042");
            statement = conn.createStatement();
            statement1 = conn.createStatement();
            resultSet = statement.executeQuery("Select * from friends Where id1 ='"+u.uid+"'or id2 = '"+u.uid+"'");
            int seq=0;
            while(resultSet.next())
            {
                if(resultSet.getString(1).equals(u.uid))
                {
                    resultSet1 = statement1.executeQuery("Select UName from User Where ID ='"+resultSet.getString(2)+"'");
                    seq++;
                }
                else if(resultSet.getString(2).equals(u.uid))
                {
                    resultSet1 = statement1.executeQuery("Select UName from User Where ID ='"+resultSet.getString(1)+"'");
                    seq++;
                }
                while(resultSet1.next())
                {
                    System.out.println(seq+". "+resultSet1.getString(1));
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
                conn.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
