/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAO {
    
    public String addService(
           String serviceName,
           String serviceCategory,
           String serviceUnit,
           double servicePrice,
           String serviceStatus) {
        
        String sql = "INSERT INTO tbl_service "
                   + "(service_name,service_Category,service_unit,service_price,service_status) "
                   + "VALUES (?,?,?,?,?)";
        
        try(Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, serviceName);
            pstmt.setString(2, serviceCategory);
            pstmt.setString(3, serviceUnit);
            pstmt.setDouble(4, servicePrice);
            pstmt.setString(5, serviceStatus);
            
            int rowsInserted = pstmt.executeUpdate();
            
            if(rowsInserted > 0) {
                
                try(ResultSet rs = pstmt.getGeneratedKeys()) {
                    
                    if(rs.next()) {
                        
                        int id = rs.getInt(1);
                        
                        return String.format("SVC-%03d",id);
                    }
                }
            }
        } catch (SQLException error) {
            System.out.println("Error inserting service: " + error.getMessage());
        }
        return null;
    }
    
    public List<Object[]> loadServices() {
        
        List<Object[]> services = new ArrayList<>();
        
        String sql = "SELECT service_id, service_name, service_category, "
                   + "service_unit, service_price, service_status "
                   + "FROM tbl_service";
        
        try(Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            
            while(rs.next()) {
                
                int id = rs.getInt("service_id");
                String serviceID = String.format("SVC-%03d",id);
                
                Object[] row = {
                    serviceID,
                    rs.getString("service_name"),
                    rs.getString("service_category"),
                    rs.getString("service_unit"),
                    rs.getString("service_price"),
                    rs.getString("service_status")
                };
                
                services.add(row);
            }
        } catch (SQLException error) {
            System.out.println("Error loading service: " + error.getMessage());
        }
        return services;
    }
    
    public List<Object[]> searchService(String search) {
        
        List<Object[]> services = new ArrayList<>();
        
        String sql = "SELECT service_id, service_name, service_category, "
                   + "service_unit, service_price, service_status "
                   + "FROM tbl_service "
                   + "WHERE (service_name LIKE ? "
                   + "OR service_category LIKE ? "
                   + "OR CONCAT('SVC-', LPAD(service_id, 3, '0')) LIKE ?)";
        
        try(Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            String key = "%" + search + "%";
            
            pstmt.setString(1, key);
            pstmt.setString(2, key);
            pstmt.setString(3, key);
            
            try(ResultSet rs = pstmt.executeQuery()) {
                
                while(rs.next()) {
                    
                    int id = rs.getInt("service_id");
                    String serviceID = String.format("SVC-%03d",id);
                    
                    Object[] row = {
                        serviceID,
                        rs.getString("service_name"),
                        rs.getString("service_category"),
                        rs.getString("service_unit"),
                        rs.getString("service_price"),
                        rs.getString("service_status")
                    };
                    
                    services.add(row);
                }
            }
        } catch (SQLException error) {
            System.out.println("Error searching service: " + error.getMessage());
        }
        return services;
    }
}
