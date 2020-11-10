/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorycontrol;
import java.util.*;
import java.sql.*;
import java.io.InputStream;
import java.io.IOException;
import java.util.logging.*;

/**
 *
 * @author Devin
 */



public class InventoryControl {
    
    
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
        
      //  log.setLevel(Level.WARNING);
        
    }
    
    //public static void main(String[] args) throws SecurityException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        
       /* Inventory inv = new Inventory();
        DBConnection dbc = new DBConnection();
        
        //SQL connection to DB call
        dbc.connect();
        
       Scanner sc = new Scanner(System.in);
        while(true){
            inv.displayMenu();
           // Scanner sc = new Scanner(System.in);
            
        
        
                switch(sc.nextInt()){

                    case 1: 

                        // dbc.connect();
                        inv.viewInventory();
                        break;
                    case 2: 
                        //search method call
                        inv.searchInventory();
                        break;
                    case 3: 
                        //add method call

                        inv.addProduct();
                        break;
                        
                    case 4:
                        inv.deleteRow();
                        break;
                    case 5: 
                        System.out.println("Thanks for using my program!");
                        System.exit(0);
                      
                        
                    default:
                        System.out.println("Please enter a valid choice 1-5!");

                }
        
       
            }
        
    */
    }
    
//}
