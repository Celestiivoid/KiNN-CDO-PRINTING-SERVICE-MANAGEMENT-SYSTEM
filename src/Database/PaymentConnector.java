/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class PaymentConnector {
    
public int getNextFIFOOrderID() {

    String sql =
            "SELECT order_id "
          + "FROM tbl_orders "
          + "WHERE payment_status = 'Unpaid' "
          + "AND order_status <> 'Cancelled' "
          + "ORDER BY order_date ASC, order_id ASC "
          + "LIMIT 1";

    try (Connection conn = DBConnection.connect();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            return rs.getInt("order_id");
        }

        } catch (SQLException error) {
            System.out.println("Error retrieving FIFO order: "+ error.getMessage());
        }
        return -1;
    }

    public boolean isNextFIFOOrder(int orderID) {
        return getNextFIFOOrderID() == orderID;
    }
    
    public boolean addPayment(
        int orderID,
        double amountPaid,
        double change,
        LocalDateTime paymentDate) {

        String checkSQL =
            "SELECT payment_status, order_status "
          + "FROM tbl_orders "
          + "WHERE order_id = ? "
          + "FOR UPDATE";

        String insertSQL =
            "INSERT INTO tbl_payments "
          + "(order_id, amount_paid, payment_change, payment_date) "
          + "VALUES (?, ?, ?, ?)";

        String updateSQL =
            "UPDATE tbl_orders "
          + "SET payment_status = 'PAID' "
          + "WHERE order_id = ? "
          + "AND payment_status = 'Unpaid' "
          + "AND order_status <> 'Cancelled'";

        try (Connection conn = DBConnection.connect()) {

            conn.setAutoCommit(false);

            try {
                String paymentStatus;
                String orderStatus;

                try (PreparedStatement ps =
                    conn.prepareStatement(checkSQL)) {

                    ps.setInt(1, orderID);

                    try (ResultSet rs = ps.executeQuery()) {
                        if (!rs.next()) {
                            conn.rollback();
                            return false;
                        }
                        paymentStatus = rs.getString("payment_status");
                        orderStatus = rs.getString("order_status");
                    }
                }

                    if (!"Unpaid".equalsIgnoreCase(paymentStatus) || "Cancelled".equalsIgnoreCase(orderStatus)) {

                    conn.rollback();
                    return false;
                    }

                try (PreparedStatement ps =conn.prepareStatement(insertSQL)) {

                    ps.setInt(1, orderID);
                    ps.setDouble(2, amountPaid);
                    ps.setDouble(3, change);
                    ps.setTimestamp(4, Timestamp.valueOf(paymentDate));

                    int inserted = ps.executeUpdate();

                    if (inserted != 1) {
                    conn.rollback();
                    return false;
                    }
                }

                try (PreparedStatement ps = conn.prepareStatement(updateSQL)) {

                    ps.setInt(1, orderID);

                    int updated = ps.executeUpdate();

                    if (updated != 1) {
                    conn.rollback();
                    return false;
                    }
                }

                conn.commit();
                return true;

            } catch (SQLException error) {
                conn.rollback();
                throw error;
            }

        } catch (SQLException error) {
            System.out.println("Error adding payment: " + error.getMessage()
            );
            return false;
        }
    }
    
    public List<Object[]> getTransactionHistory() {

        List<Object[]> transactions = new ArrayList<>();

        String sql ="SELECT "
            + "p.payment_id, "
            + "o.order_id, "
            + "c.ctm_ftName, "
            + "c.ctm_ltName, "
            + "s.service_name, "
            + "s.service_size, "
            + "o.quantity, "
            + "o.total_amount, "
            + "o.order_date, "
            + "p.payment_date "
            + "FROM tbl_orders o "
            + "JOIN tbl_customers c ON o.customer_id = c.ctm_id "
            + "JOIN tbl_service s ON o.service_id = s.service_id "
            + "JOIN tbl_payments p ON o.order_id = p.order_id "
            + "WHERE o.order_status = 'Completed' "
            + "AND o.payment_status = 'Paid' "
            + "ORDER BY p.payment_date DESC, p.payment_id DESC";

        try (Connection conn = DBConnection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {

                String transactionID = String.format("TRX-%05d", rs.getInt("payment_id"));

                String orderID = String.format("ORD-%05d", rs.getInt("order_id"));

                Object[] row = {
                    transactionID,
                    orderID,
                    rs.getString("ctm_ftName"),
                    rs.getString("ctm_ltName"),
                    rs.getString("service_name"),
                    rs.getString("service_size"),
                    rs.getInt("quantity"),
                    rs.getBigDecimal("total_amount"),
                    rs.getTimestamp("order_date"),
                    rs.getTimestamp("payment_date")
                };

            transactions.add(row);
            }

        } catch (SQLException error) {
            System.out.println("Error loading transaction history: " + error.getMessage());
        }
        return transactions;
    }
    
    public List<Object[]> searchTransaction(String keyword) {

        List<Object[]> transactions = new ArrayList<>();

        String sql =
            "SELECT "
            + "p.payment_id, "
            + "o.order_id, "
            + "c.ctm_ftName, "
            + "c.ctm_ltName, "
            + "s.service_name, "
            + "s.service_size, "
            + "o.quantity, "
            + "o.total_amount, "
            + "o.order_date, "
            + "p.payment_date "
            + "FROM tbl_orders o "
            + "JOIN tbl_customers c ON o.customer_id = c.ctm_id "
            + "JOIN tbl_service s ON o.service_id = s.service_id "
            + "JOIN tbl_payments p ON o.order_id = p.order_id "
            + "WHERE o.order_status = 'Completed' "
            + "AND o.payment_status = 'Paid' "
            + "AND ( "
            + "CONCAT('TRX-', LPAD(p.payment_id, 5, '0')) LIKE ? "
            + "OR CONCAT('ORD-', LPAD(o.order_id, 5, '0')) LIKE ? "
            + "OR c.ctm_ftName LIKE ? "
            + "OR c.ctm_ltName LIKE ? "
            + ") "
            + "ORDER BY p.payment_date DESC, p.payment_id DESC";

        try (Connection conn = DBConnection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String searchValue = "%" + keyword.trim() + "%";

            pstmt.setString(1, searchValue);
            pstmt.setString(2, searchValue);
            pstmt.setString(3, searchValue);
            pstmt.setString(4, searchValue);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    Object[] row = {
                        String.format("TRX-%05d",
                        rs.getInt("payment_id")),
                        String.format("ORD-%05d",
                        rs.getInt("order_id")),
                        rs.getString("ctm_ftName"),
                        rs.getString("ctm_ltName"),
                        rs.getString("service_name"),
                        rs.getString("service_size"),
                        rs.getInt("quantity"),
                        rs.getBigDecimal("total_amount"),
                        rs.getTimestamp("order_date"),
                        rs.getTimestamp("payment_date")
                        };

                    transactions.add(row);
                }
            }

        } catch (SQLException error) {
            System.out.println("Error searching transactions: " + error.getMessage());
        }
        return transactions;
    }
}

