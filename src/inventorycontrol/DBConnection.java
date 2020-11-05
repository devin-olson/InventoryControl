/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorycontrol;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Devin
 */
public class DBConnection {
    
    
    public void connect() throws SecurityException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        
   
    Inventory inv = new Inventory();
     Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        Connection conn = null;
        
        try{
          
            String connectionURL = "jdbc:mysql://127.0.0.1:3306/Products" + "?verifyServerCertificate=false"
                                    + "&useSSL=false" + "&requireSSL=true";
            conn = DriverManager.getConnection(connectionURL, "root","Gortoonforleeway232");
            
            
            if(conn != null)
            {
                System.out.println("Successful connection to the database");
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String SQL = "SELECT * FROM inventory_products";
            ResultSet rs = stmt.executeQuery(SQL);
            while(rs.next()){
                int productID = rs.getInt("productID");
                String productName = rs.getString("productName");
                int stock = rs.getInt("stock");
                float price = rs.getFloat("price");
                
                //print statement
                System.out.println("\nProduct_ID:  " + productID + "\nProduct_Name:  " + productName + "\nstock:  " + stock + "\nPrice:  " + price);
            }
               // log.info("I successfully connected the database.");
            }
        }catch(SQLException e)
        {
            System.out.println(e.getMessage());
            
        }
    }
}
