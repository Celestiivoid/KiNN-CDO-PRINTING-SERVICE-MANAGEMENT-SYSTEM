/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;
import java.sql.*;
import java.time.LocalDateTime;

public class OrderConnector {
    
    public boolean addOrder(
        int userID,
        int customerID,
        int serviceID,
        int quantity,
        double unitPrice,
        double subtotal,
        double discountPercentage,
        double discountAmount,
        double totalAmount,
        String orderStatus,
        String paymentStatus,
        LocalDateTime orderDate) {
        
        String sql = "INSERT into tbl_orders"
                   + "(user_id, customer_id, service_id, quantity, "
                   + "unit_price, subtotal, discount_percentage, "
                   + "discount_amount, total_amount, order_status, "
                   + "payment_status, order_date) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try(Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userID);
            pstmt.setInt(2, customerID);
            pstmt.setInt(3, serviceID);
            pstmt.setInt(4, quantity);
            pstmt.setDouble(5, unitPrice);
            
            pstmt.setDouble(6, subtotal);
            pstmt.setDouble(7, discountPercentage);
            pstmt.setDouble(8, discountAmount);
            pstmt.setDouble(9, totalAmount);
            pstmt.setString(10, orderStatus);
            
            pstmt.setString(11, paymentStatus);
            pstmt.setTimestamp(12, Timestamp.valueOf(orderDate));
            
            int rowsInserted = pstmt.executeUpdate();
            
            return rowsInserted > 0;
            
        } catch (SQLException error) {
            System.out.println("Error adding order: " + error.getMessage());
            return false;
        }
    }
}
