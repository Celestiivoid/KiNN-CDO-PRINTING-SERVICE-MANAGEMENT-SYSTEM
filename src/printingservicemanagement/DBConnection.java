/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package printingservicemanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
    
    public static Connection connect() {
        try {
            String url = "jdbc:MySQL://localhost:3306/printing_service_system";
            String user = "root";
            String pass = ""; //security
                                                      
            Connection conn = DriverManager.getConnection(url, user, pass);
            return conn;
        
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
            return null;
        }
    }
}
