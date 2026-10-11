/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserConnector {
     public ResultSet login(String firstName, String lastName, String password) {

        Connection conn = DBConnection.connect();

        try {
            String sql = "SELECT * FROM tbl_users WHERE first_name = ? AND last_name = ? AND password = ?";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, firstName);
            pst.setString(2, lastName);
            pst.setString(3, password);

            return pst.executeQuery();

        } catch (Exception e) {
            System.out.println("Login error: " + e.getMessage());
            return null;
        }
    }
     
     public boolean verify(int userId, String pin) {
         
         Connection conn = DBConnection.connect();
         
         try {
             String sql = "SELECT * FROM tbl_users "
                        + "WHERE user_id = ? "
                        + "AND role = 'Administrator' "
                        + "AND user_pin = ?";
             
             PreparedStatement pst = conn.prepareStatement(sql);
             
             pst.setInt(1,userId);
             pst.setString(2, pin);
             
             ResultSet rs = pst.executeQuery();
             
             return rs.next();
         } catch (Exception error) {
             System.out.println("Login error: " + error.getMessage());
             return false;
         }
     }
     
     public String addUser(
            String firstName,
            String lastName,
            String phoneNumber,
            String password,
            String role,
            String accountStatus) {
         
         String sql = "INSERT INTO tbl_users "
                    + "(first_name,last_name,password,contactNumber,role,account_status) "
                    + "VALUES (?,?,?,?,?,?)";
         
         try(Connection conn = DBConnection.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
             
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, phoneNumber);
            pstmt.setString(4, password);
            pstmt.setString(5, role);
            pstmt.setString(6, accountStatus);
            
            int rowsInserted = pstmt.executeUpdate();
            
            if(rowsInserted > 0) {
                
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    
                    if(rs.next()) {
                        
                        int id = rs.getInt(1);
                        
                        return String.format("USER-%05d",id);
                    }
                }
            }
         } catch (SQLException error) {
             System.out.println("Error adding user: " + error.getMessage());
         }
         return null;
     }
     
     public List<Object[]> loadUsers() {
         
         List<Object[]> users = new ArrayList<>();
         
         String sql = "SELECT user_id, first_name, last_name, "
                    + "password, contactNumber, account_status "
                    + "FROM tbl_users "
                    + "WHERE user_archived = 0";
         
         try (Connection conn = DBConnection.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {
                     
                     while(rs.next()) {
                         
                         int id = rs.getInt("user_id");
                         String userID = String.format("USER-%05d",id);
                         
                         Object[] row = {
                             userID,
                             rs.getString("first_name"),
                             rs.getString("last_name"),
                             rs.getString("password"),
                             rs.getString("contactNumber"),
                             rs.getString("account_status")
                         };
                         
                         users.add(row);
                     }
            } catch (SQLException error) {
                System.out.println("Error searching user: " + error.getMessage());
            }
         return users;
     }
     
    public List<Object[]> searchCustomer(String search) {
        
        List<Object[]> customers = new ArrayList<>();
        
        String sql = "SELECT user_id, first_name, last_name, password, "
           + "contactNumber, account_status "
           + "FROM tbl_users "
           + "WHERE user_archived = 0 "
           + "AND (first_name LIKE ? "
           + "OR last_name LIKE ? "
           + "OR CONCAT('USER-', LPAD(user_id, 5, '0')) LIKE ?)";
        
        try(Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            String keyword = "%" + search + "%";
            
            pstmt.setString(1, keyword);
            pstmt.setString(2, keyword);
            pstmt.setString(3, keyword);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                
                while(rs.next()) {
                    
                    int id = rs.getInt("user_id");
                    String customerID = String.format("USER-%05d",id);
                    
                    Object[] row = {
                        customerID,
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("password"),
                        rs.getString("contactNumber"),
                        rs.getString("account_status")
                    };
                    
                    customers.add(row);
                }
            }
            
        } catch (SQLException error) {
            System.out.println("Error searching customers: " + error.getMessage());
        }
        return customers;
    }
}
