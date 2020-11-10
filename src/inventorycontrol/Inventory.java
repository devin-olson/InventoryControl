/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorycontrol;


import java.io.IOException;

import static java.lang.System.in;
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
    protected String productCategory;
    
    
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
    
    public void setCategory(String productCategory){
        this.productCategory = productCategory;
    }
    
    public String getCategory(){
        return productCategory;
    }
    
    //View all method logic
    public void viewInventory()throws SecurityException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
       
        
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        Connection conn = null;
       
        try{
            String connectionURL = "jdbc:mysql://127.0.0.1:3306/products" + "?verifyServerCertificate=false"
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
                        productCategory = rs.getString("productCategory");

                        //print statement\
                        System.out.println("ID: "+ productID + "  Product Name: " + productName + "  Stock: " 
                                            + stock + "  Price: $ " + price + "  Category: " + productCategory + " \n");
                    }
                }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }       
            
    }
    
    //Search method logic
    public void searchInventory()throws SecurityException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        
        //DB Connection logic for searching an item
        Connection conn = null;
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        Scanner sc = new Scanner(System.in);
        
        try{
            String URL = "jdbc:mysql://127.0.0.1:3306/products" + "?verifyServerCertificate=false"
                                        + "&useSSL=false" + "&requireSSL=true";
            conn = DriverManager.getConnection(URL, "root", "Gortoonforleeway232");
            if(conn!=null){
                
                System.out.println("Successful connection to the database!");
               
                System.out.println("Please enter a product category.\n");
                         
                productCategory = sc.nextLine();
                String SQL = "SELECT * FROM inventory_products WHERE productCategory = '[^a-z]-[^A-Z]'  " ;
                PreparedStatement preStatement = conn.prepareStatement("SELECT * FROM inventory_products WHERE productCategory = '[^a-z]-[^A-Z]' " );
                
                
                ResultSet rs = preStatement.executeQuery(SQL);
                
                while(rs.next()){
                   productID = rs.getInt("productID");
                   productName = rs.getString("productName");
                   stock = rs.getInt("stock");
                   price = rs.getFloat("price");
                   productCategory = rs.getString("productCategory");
                   
                   // System.out.println(productID + ", "+ productName + ", " + stock + ", " + price + ", " + productCategory);
                }
                
                
                    //System.out.println(rs.getInt("productID") + " "+ rs.getString("productName") + " " + rs.getInt("stock") + " " + rs.getFloat("price") + " " + rs.getString(productCategory));
                   // System.out.println(productID + ", "+ productName + ", " + stock + ", " + price + ", " + productCategory);
                    rs.toString();
                    System.out.println(productID + ", "+ productName + ", " + stock + ", " + price + ", " + productCategory);
            }
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        //return productCategory;
    }
    
    //Add method logic
    public void addProduct(){
        
        //user input  for adding items  
        Scanner sc = new Scanner(System.in).useDelimiter("\\n");
        
        
       try {
           String URL = "jdbc:mysql://localhost:3306/products";
            
            System.out.println("\nPlease enter the requested information.\n");
            System.out.println("Enter the product ID: ");
            productID = sc.nextInt();
            
            System.out.println("\nEnter the product name: ");
            
            productName = sc.next();
            
            sc.nextLine();
            
            
            
            System.out.println("\nEnter the number of available units: ");
            stock = sc.nextInt();
             
            
            
            System.out.println("\nEnter the price: ");
            price = sc.nextFloat();
            
            System.out.println("Enter the products category!");
            productCategory = sc.next();
             
            
            //connection to DB       
            Connection conn = DriverManager.getConnection(URL, "root", "Gortoonforleeway232");
          
            
            //code to input new user input into database
            PreparedStatement pst = conn.prepareStatement("INSERT into inventory_products(productID, productName, stock, price, productCategory) values(?,?,?,?,?)");
             
            pst.setInt(1, productID);
            pst.setString(2, productName);
            pst.setInt(3, stock);
            pst.setFloat(4, price);
            pst.setString(5, productCategory);
            
            int i = pst.executeUpdate();
            
            if(i != 0){
                System.out.println("\nItem Succesfully added!");
            }else{
                System.out.println("Failed to add item!");
            }
            
            
            }catch(Exception e){
                System.out.println(e);
            
        }
        
    
       System.out.println("\n\n\n\n\n");
    
        
        
    }
    
}
