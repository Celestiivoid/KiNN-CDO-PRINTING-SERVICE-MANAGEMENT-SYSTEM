/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;
import customer_GUI.UpdateCustomerFrame;
import Utility.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerConnector {
    
    public String addCustomer(
           String firstName,
           String lastName,
           String phoneNumber,
           String emailAddress,
           String customerType) {
        
        String sql = "INSERT INTO tbl_customers "
                + "(ctm_ftName,ctm_ltName,ctm_phNumber,ctm_emAddress,ctm_Type) "
                + "VALUES (?,?,?,?,?)";
        
        try(Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, phoneNumber);
            pstmt.setString(4, emailAddress);
            pstmt.setString(5, customerType);
            
            int rowsInserted = pstmt.executeUpdate();
            
            if(rowsInserted > 0) {
                
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    
                    if(rs.next()) {
                        
                        int id = rs.getInt(1);
                        
                        return String.format("CUST-%05d",id);
                    }
                }
            }
        } catch (SQLException error) {
            System.out.println("Error inserting customer: " + error.getMessage());
        }
        return null;
    }
    
    public boolean updateCustomer(
           int customerID,
           String firstName,
           String lastName,
           String phoneNumber,
           String emailAddress,
           String customerType) {
        
        String sql = "UPDATE tbl_customers "
                   + "SET ctm_ftName = ?, "
                   + "ctm_ltName = ?, "
                   + "ctm_phNumber = ?, "
                   + "ctm_emAddress = ?, "
                   + "ctm_Type = ? "
                   + "WHERE ctm_id = ?";
        
        try(Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, phoneNumber);
            pstmt.setString(4, emailAddress);
            pstmt.setString(5, customerType);
            pstmt.setInt(6, customerID);
            
            int update = pstmt.executeUpdate();
            
            return update > 0;
        } catch (SQLException error) {
            System.out.println("Error updating customer: " + error.getMessage());
            return false;
        }
    }
    
    public List<Object[]> loadCustomers() {

        List<Object[]> customers = new ArrayList<>();

        String sql = "SELECT ctm_id, ctm_ftName, ctm_ltName, "
               + "ctm_phNumber, ctm_emAddress, ctm_Type "
               + "FROM tbl_customers "
               + "WHERE ctm_archived = 0";

        try (Connection conn = DBConnection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {

                int id = rs.getInt("ctm_id");
                String customerID = String.format("CUST-%05d", id);

                Object[] row = {
                    customerID,
                    rs.getString("ctm_ftName"),
                    rs.getString("ctm_ltName"),
                    rs.getString("ctm_phNumber"),
                    rs.getString("ctm_emAddress"),
                    rs.getString("ctm_Type")
                };

                customers.add(row);
            }

        } catch (SQLException e) {
            System.out.println("Error loading customers: " + e.getMessage());
        }
        return customers;
    }
    
    public List<Object[]> loadarchivedCustomer() {
        
        List<Object[]> customers = new ArrayList<>();
        
        String sql = "SELECT ctm_id, ctm_ftName, ctm_ltName, "
                   + "ctm_phNumber, ctm_emAddress, ctm_Type "
                   + "FROM tbl_customers "
                   + "WHERE ctm_archived = 1";
        
        try(Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            
            while(rs.next()) {
                
                int id = rs.getInt("ctm_id");
                String customerID = String.format("CUST-%05d",id);
                
                Object[] row = {
                    customerID,
                    rs.getString("ctm_ftName"),
                    rs.getString("ctm_ltName"),
                    rs.getString("ctm_phNumber"),
                    rs.getString("ctm_emAddress"),
                    rs.getString("ctm_Type")
                };
                
                customers.add(row);
            }
        } catch(SQLException error) {
            System.out.println("Error loading archived customer: " + error.getMessage());
        }
        return customers;
    }
    
    public List<Object[]> searchCustomer(String search) {
        
        List<Object[]> customers = new ArrayList<>();
        
        String sql = "SELECT ctm_id, ctm_ftName, ctm_ltName, "
           + "ctm_phNumber, ctm_emAddress, ctm_Type "
           + "FROM tbl_customers "
           + "WHERE ctm_archived = 0 "
           + "AND (ctm_ftName LIKE ? "
           + "OR ctm_ltName LIKE ? "
           + "OR CONCAT('CUST-', LPAD(ctm_id, 5, '0')) LIKE ?)";
        
        try(Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            String keyword = "%" + search + "%";
            
            pstmt.setString(1, keyword);
            pstmt.setString(2, keyword);
            pstmt.setString(3, keyword);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                
                while(rs.next()) {
                    
                    int id = rs.getInt("ctm_id");
                    String customerID = String.format("CUST-%05d",id);
                    
                    Object[] row = {
                        customerID,
                        rs.getString("ctm_ftName"),
                        rs.getString("ctm_ltName"),
                        rs.getString("ctm_phNumber"),
                        rs.getString("ctm_emAddress"),
                        rs.getString("ctm_Type")
                    };
                    
                    customers.add(row);
                }
            }
            
        } catch (SQLException error) {
            System.out.println("Error searching customers: " + error.getMessage());
        }
        return customers;
    }
    
    public List<Object[]> searchArchivedCustomer(String search) {
        List<Object[]> customers = new ArrayList<>();
        
        String sql = "SELECT ctm_id, ctm_ftName, ctm_ltName, "
                   + "ctm_phNumber, ctm_emAddress, ctm_Type "
                   + "FROM tbl_customers "
                   + "WHERE ctm_archived = 1 "
                   + "AND (ctm_ftName LIKE ? "
                   + "OR ctm_ltName LIKE ? "
                   + "OR CONCAT('CUST-', LPAD(ctm_id, 5, '0')) LIKE ?)";
        
        try(Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            String keyword = "%" + search + "%";
            
            pstmt.setString(1, keyword);
            pstmt.setString(2, keyword);
            pstmt.setString(3, keyword);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                
                while(rs.next()) {
                    
                    int id = rs.getInt("ctm_id");
                    String customerID = String.format("CUST-%05d",id);
                    
                    Object[] row = {
                        customerID,
                        rs.getString("ctm_ftName"),
                        rs.getString("ctm_ltName"),
                        rs.getString("ctm_phNumber"),
                        rs.getString("ctm_emAddress"),
                        rs.getString("ctm_Type")
                    };
                    
                    customers.add(row);
                }
            }
        } catch (SQLException error) {
            System.out.println("Error searching archived customer: " + error.getMessage());
        }
        return customers;
    }
    
    public boolean archiveCustomer(int customerID) {
        
        String sql = "UPDATE tbl_customers "
                   + "SET ctm_archived = 1 "
                   + "WHERE ctm_id = ?";
        
        try (Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, customerID);
            
            int updated = pstmt.executeUpdate();
            
            return updated > 0;
        } catch (SQLException error) {
            System.out.println("Error archiving: " + error.getMessage());
            return false;
        }
    }
    
    public boolean retrieveCustomer(int customerID) {
        
        String sql = "UPDATE tbl_customers "
                   + "SET ctm_archived = 0 "
                   + "WHERE ctm_id = ?";
        
        try(Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, customerID);
            
            int updated = pstmt.executeUpdate();
            
            return updated > 0;
        } catch (SQLException error) {
            System.out.println("Error retrieving: " + error.getMessage());
            return false;
        }
    }
    
    public Customer getCustomer(int customerID) {
        
        String sql = "SELECT ctm_id, ctm_ftName, ctm_ltName, "
                   + "ctm_phNumber, ctm_emAddress, ctm_Type "
                   + "FROM tbl_customers "
                   + "WHERE ctm_id = ? AND ctm_archived = 0";
        
        try(Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
            pstmt.setInt(1, customerID);
            
            try(ResultSet rs = pstmt.executeQuery()) {
                
                if(rs.next()) {
                    
                    Customer ctmSet = new Customer();
                    
                    ctmSet.setcustomerID(rs.getInt("ctm_id"));
                    ctmSet.setFirstName(rs.getString("ctm_ftName"));
                    ctmSet.setLastName(rs.getString("ctm_ltName"));
                    ctmSet.setPhoneNumber(rs.getString("ctm_phNumber"));
                    ctmSet.setEmailAddress(rs.getString("ctm_emAddress"));
                    ctmSet.setCustomerType(rs.getString("ctm_Type"));
                    
                    return ctmSet;
                }
            }
        } catch (SQLException error) {
            System.out.println("Error searching: " + error.getMessage());
        }
        return null;
    }
}
