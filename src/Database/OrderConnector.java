/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;
import Utility.Order;
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
    
    public Order getOrder(int orderID) {

        String sql =
            "SELECT "
          + "o.order_id, "
          + "o.user_id, "
          + "o.customer_id, "
          + "o.service_id, "
          + "o.quantity, "
          + "o.unit_price, "
          + "o.subtotal, "
          + "o.discount_percentage, "
          + "o.discount_amount, "
          + "o.total_amount, "
          + "o.order_status, "
          + "o.payment_status, "
          + "o.order_date, "

          + "c.ctm_ftName, "
          + "c.ctm_ltName, "
          + "c.ctm_phNumber, "
          + "c.ctm_emAddress, "
          + "c.ctm_Type, "

          + "s.service_name, "
          + "s.service_size "

          + "FROM tbl_orders o "

          + "JOIN tbl_customers c "
          + "ON o.customer_id = c.ctm_id "

          + "JOIN tbl_service s "
          + "ON o.service_id = s.service_id "

          + "WHERE o.order_id = ?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderID);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {

                    Order order = new Order();

                    order.setOrderID(rs.getInt("order_id"));

                    order.setUserID(rs.getInt("user_id"));

                    order.setCustomerID(rs.getInt("customer_id"));

                    order.setServiceID(rs.getInt("service_id"));

                    order.setQuantity(rs.getInt("quantity"));

                    order.setUnitPrice(rs.getDouble("unit_price"));

                    order.setSubtotal(rs.getDouble("subtotal"));

                    order.setDiscountPercentage(rs.getDouble("discount_percentage"));

                    order.setDiscountAmount(rs.getDouble("discount_amount"));

                    order.setTotalAmount(rs.getDouble("total_amount"));

                    order.setOrderStatus(rs.getString("order_status"));

                    order.setPaymentStatus(rs.getString("payment_status"));
                    
                    order.setFirstName(rs.getString("ctm_ftName"));

                    order.setLastName(rs.getString("ctm_ltName"));

                    order.setPhoneNumber(rs.getString("ctm_phNumber"));

                    order.setEmailAddress(rs.getString("ctm_emAddress"));

                    order.setCustomerType(rs.getString("ctm_Type"));
                   
                    order.setServiceName(rs.getString("service_name"));

                    order.setServiceSize(rs.getString("service_size"));

                    Timestamp timestamp = (rs.getTimestamp("order_date"));

                    if (timestamp != null) {
                        order.setOrderDate(
                            timestamp.toLocalDateTime()
                        );
                    }

                    return order;
                }
            }

        } catch (SQLException error) {

            System.out.println(
                "Error loading order: "
                + error.getMessage()
            );
        }

        return null;
    }
}
