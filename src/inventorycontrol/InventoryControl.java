/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorycontrol;
import java.util.*;
import java.io.InputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.*;

/**
 *
 * @author Devin
 */



public class InventoryControl {
    
    
    public void displayMenu(){
        
        
        System.out.println("Welcome to my simple invetory control system");
        System.out.println("\nPlease select an option 1-4 to continue: ");
        
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        
        while(choice >= 1 && choice <= 4){
            
            switch(choice){
                
                case 1: System.out.println("View all");
                      //view all method
                    break;
                case 2: System.out.println("Search");
                    //search method call
                    break;
                case 3: System.out.println("Add item");
                    //add method call
                    break;
                case 4: System.out.println("Exit");
                    System.out.println("Thanks for using my program!");
                    break;
                default:
                    System.out.println("Please enter a valid choice 1-4!");
                    
            }
        }

    }
    
    //instantiate logging class
    
   private final static Logger log = Logger.getLogger(InventoryControl.class.getName());
    
    //creation of a handler, formatter and log manager
    static{
        try{
            FileHandler fh = new FileHandler(InventoryControl.class.getName() + "logs.txt");
            fh.setFormatter(new SimpleFormatter());
            fh.setLevel(Level.ALL);
            log.addHandler(fh);
            InputStream stream = InventoryControl.class.getClassLoader()
                    .getResourceAsStream("logging.properties");
            LogManager.getLogManager().readConfiguration();
        }catch (IOException | SecurityException ex){
            ex.printStackTrace();
        }
        log.setLevel(Level.INFO);
        
        log.setLevel(Level.WARNING);
        
    }
    
    public static void main(String[] args) throws SecurityException, IOException {
        
        InventoryControl ic = new InventoryControl();
       
        //SQL connection code
        //My server is running but is not connecting to netbeans
        Connection conn = null;
        
        try{
          
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Inventory","root","Gortoonforleeway232");
            if(conn != null){
                System.out.println("Successful connection to the database");
            }
        }catch(Exception e)
        {
            System.out.println("Unsuccessful connection to the database");
            log.warning("The SQL server is running but it is not connected to netbeans.");
        }
        
       
        
    
    }
}
