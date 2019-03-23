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
 * @author Rohit-PC
 */
public class PostComment 
{
    static String u_id="";
    static int nextnum=0;
    public static void post_update()
    {
        Scanner input=new Scanner(System.in);
        final String DB_URL="jdbc:mysql://mis-sql.uhcl.edu/prajapatir1738";
        ArrayList <String> friends =new ArrayList<String>();
        Connection conn = null;
        Statement st1 = null;
        ResultSet rs1 = null;
        Statement st2 = null;
        ResultSet rs2 = null;
        Statement st3 = null;
        ResultSet rs3 = null;
        int seq=0;
        try
        {
            conn = DriverManager.getConnection(DB_URL,"prajapatir1738","1629042");
            st1 = conn.createStatement();
            st2 = conn.createStatement();
            st3 = conn.createStatement();
            rs1=st1.executeQuery("Select * from post order by time desc Limit 5");
            rs2=st2.executeQuery("Select * from friends Where id1='"+uid+"' or id2='"+uid+"'");
            rs3=st3.executeQuery("Select * from comment");
            Scanner input1 = new Scanner(System.in);
            String Selection="";
            String cmnt="";
            while(rs2.next()){
                if(rs2.getString(1).equals(uid)){
                    friends.add(rs2.getString(2));
                }
                else if(rs2.getString(2).equals(uid)){
                    friends.add(rs2.getString(1));
                }
            }
            while(rs1.next())
            {
                seq++;
                if(rs1.getString(4).equals("U"))
                {
                    System.out.printf(seq+". %s  %s",rs1.getString(2),rs1.getString(3));
                    System.out.println();
                }
                else if(rs1.getString(4).equals("P"))
                {
                    System.out.printf(seq+". %s  %s",rs1.getString(2),rs1.getString(3));
                    System.out.println();
                } 
                
            }
            if(seq==0)
            {
                System.out.println("No New Posts or Updates");
                System.out.println();
            }
            else
            {
                int not=0,se=0;
                String pid="";
                System.out.println("Enter the post/update number you want to process");
                not=input.nextInt();
                if(rs1.absolute(not))
                {
                    se++;
                    System.out.printf(se+". %s  %s",rs1.getString(2),rs1.getString(3));
                    System.out.println();
                    while(rs3.next())
                    {
                        if(rs3.getString(2).equals(rs1.getString(1)))
                        {
                            System.out.println(rs3.getString(3)+": "+rs3.getString(4));
                        }
                    }
                    System.out.println();
                }
                System.out.println("Do you want to comment:");
                System.out.println();
                System.out.println("1:Yes");
                System.out.println("2:No");
                Selection = input.next();
                System.out.println();
                if(Selection.equals("1"))
                {
                    System.out.print("Enter your comment:");
                    cmnt=input.next();
                    upcmnt();
                    int i=st1.executeUpdate("insert into comment values ('"+u_id+"','" +rs1.getString(1)+"','" +uid+"','" +cmnt+"')");
                }
                if(Selection.equals("2"))
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
                conn.close();
                st1.close();
                rs1.close();
                st2.close();
                rs2.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }   
    public static void upcmnt()
    {
        final String DB_URL="jdbc:mysql://mis-sql.uhcl.edu/prajapatir1738";
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try
        {
            conn = DriverManager.getConnection(DB_URL,"prajapatir1738","1629042");
            st = conn.createStatement();
            rs=st.executeQuery("Select c_num from nextnum");
            if(rs.next())
                {
                    u_id= "" + rs.getInt(1);
                    nextnum = rs.getInt(1) + 1;
                }
                int u = st.executeUpdate("Update nextnum set c_num = '" + nextnum + "'");
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

