/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendsbook;

import java.util.Scanner;

/**
 *
 * @author PrajapatiR1738
 */
public class FriendsBook {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         Scanner input = new Scanner(System.in);
        String selection = "";
        
        //while loop to display menu
        while(!selection.equals("x"))
        {
            //show the menu
            System.out.println();
            System.out.println("Please make your selection");
            System.out.println("1: Register");
            System.out.println("2: Login");
            System.out.println("x: Finish the simulation");
            
            //get the input
            selection = input.nextLine();
            System.out.println();
            
            if(selection.equals("1"))
            {
                User.Register();
                
            }
            else if(selection.equals("2"))
            {
                new User().Login();
                
                
            }
            else if(selection.equals("x"))
            {
                //go out
                ;
            }
        }
    }
    
}
