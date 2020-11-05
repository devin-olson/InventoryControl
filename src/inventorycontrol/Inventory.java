/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorycontrol;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.*;

import java.util.logging.Level;

/**
 *
 * @author Devin
 */
public class Inventory {
    
    protected int productID;
    protected String productName;
    protected int stock;
    protected float price;
    
    
   // final static Logger.log(Inventory.class.getName());
    
    public Inventory(){};
    //contructor
    public Inventory(int productID, String productName, int stock, float price)
    {
        this.productID = productID;
        this.productName = productName;
        this.stock = stock;
        this.price = price;
    }
    
     public void displayMenu()throws SQLException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        
        
        System.out.println("\nWelcome to my simple invetory control system\n");
        System.out.println("Choose from the following.\n");
        
        System.out.println("1.View Inventory\n");
        System.out.println("2.Search Products\n");
        System.out.println("3.Add items\n");
        System.out.println("4.Exit\n");
        
       
    }
    
    //getters and setters
    
    public void setProductID(int productID){
        this.productID = productID;
    }
    
    public int getProductID(){
        return productID;
    }
    
    public void setProductName(String productName){
        this.productName = productName;
    }
    
    public String getProductName(){
        return productName;
    }
    
    public void setStock(int stock){
        this.stock = stock;
    }
    
    public int getStock(){
        return stock;
    }
    
    public void setPrice(float price){
        this.price = price;
    }
    
    public float  getPrice(){
        return price;
    }
    
    //View all method logic
    public void viewInventory()throws SecurityException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
       
        
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        Connection conn = null;
       
        try{
            String connectionURL = "jdbc:mysql://127.0.0.1:3306/Products" + "?verifyServerCertificate=false"
                                        + "&useSSL=false" + "&requireSSL=true";
                conn = DriverManager.getConnection(connectionURL, "root","Gortoonforleeway232");

                if(conn!=null){
                    System.out.println("Successful connection to the database.");
                  Statement stmt = conn.createStatement();
                  String SQL = "SELECT * FROM inventory_products";
                  ResultSet rs = stmt.executeQuery(SQL);

                    while(rs.next()){
                        productID = rs.getInt("productID");
                        productName = rs.getString("productName");
                        stock = rs.getInt("stock");
                        price = rs.getFloat("price");

                        //print statement\
                        System.out.println(productID + " " + productName + " " 
                                            + stock + " " + price + " ");
                    }
                }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }       
            
    }
    
    //Search method logic
    public String searchInventory(String productName)throws SecurityException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        
        //DB Connection logic for searching an item
        Connection conn = null;
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        
        try{
            String URL = "jdbc:mysql://127.0.0.1:3306/Products" + "?verifyServerCertificate=false"
                                        + "&useSSL=false" + "&requireSSL=true";
            conn = DriverManager.getConnection(URL, "root", "Gortoonforleeway232");
            if(conn!=null){
                System.out.println("Successful connection to the database!");
                PreparedStatement preStatement = conn.prepareStatement("SELECT * FROM inventory_products WHERE productName =? ");
                String SQL = "SELECT * FROM invntory_products WHERE productName = ?";
                 ResultSet rs = preStatement.executeQuery(SQL);
                 
                while(rs.next()){
                   // productName = rs.getString("productName");
                    
                    System.out.println(productID + rs.getString("productName") + " " + stock + " " + price);
                }
            }
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return productName;
    }
    
    //Add method logic
    public void addProduct(){
        
        //user input  for adding items  
        Scanner sc = new Scanner(System.in);
        
        while(true)
        {
            System.out.println("\nPress q to stop adding products.");
            if(sc.next().equalsIgnoreCase("q")){
                break;   
            }else{
            sc.nextLine();
            System.out.println("Enter the product ID: ");
            productID = sc.nextInt();
            getProductID();
            
            System.out.println("\nEnter the product name: ");
            productName = sc.next();
            sc.nextLine();
            getProductName();
            
            System.out.println("\nEnter the number of available units: ");
            stock = sc.nextInt();
             sc.nextLine();
            getStock();
            
            System.out.println("\nEnter the price: ");
            price = sc.nextFloat();
             sc.nextLine();
            getPrice();
            
            System.out.println("\nItem Succesfully added!");
            }
            
        }
        
    
    
    
        
        
    }
    
}