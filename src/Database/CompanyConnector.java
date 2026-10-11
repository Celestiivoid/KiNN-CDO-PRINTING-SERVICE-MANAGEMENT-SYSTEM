/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import Utility.Company;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyConnector {
    
    public String addCompany(
           String companyName,
           String contactNumber,
           String emailAddress) {
        
        String sql = "INSERT INTO tbl_companies "
                   + "(company_name,company_contact,company_email) "
                   + "VALUES (?,?,?)";
        
        try(Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, companyName);
            pstmt.setString(2, contactNumber);
            pstmt.setString(3, emailAddress);
            
            int rowsInserted = pstmt.executeUpdate();
            
            if(rowsInserted > 0) {
                
                try(ResultSet rs = pstmt.getGeneratedKeys()) {
                    
                    if(rs.next()) {
                        
                        int id = rs.getInt(1);
                        
                        return String.format("CMP-%05d",id);
                    }
                }
            }
        } catch (SQLException error) {
            System.out.println("Error adding company: " + error.getMessage());
        }
        return null;
    }
    
    
    public List<Object[]> loadCompanies() {

        List<Object[]> companies = new ArrayList<>();

        String sql = "SELECT company_id, company_name, company_contact,company_email "
               + "FROM tbl_companies "
               + "WHERE company_archived = 0";

        try (Connection conn = DBConnection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {

                int id = rs.getInt("company_id");
                String companyID = String.format("COMP-%05d", id);

                Object[] row = {
                    companyID,
                    rs.getString("company_name"),
                    rs.getString("company_contact"),
                    rs.getString("company_email")
                };

                companies.add(row);
            }

        } catch (SQLException e) {
            System.out.println("Error loading companies: " + e.getMessage());
        }
    return companies;
}

    
    public List<Object[]> searchCompany(String search) {

        List<Object[]> companies = new ArrayList<>();

        String sql = "SELECT company_id, company_name, company_contact, company_email "
               + "FROM tbl_companies "
               + "WHERE company_archived = 0 "
               + "AND (company_name LIKE ? "
               + "OR company_contact LIKE ? "
               + "OR CONCAT('COMP-', LPAD(company_id, 5, '0')) LIKE ?)";

        try (Connection conn = DBConnection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String keyword = "%" + search + "%";

            pstmt.setString(1, keyword);
            pstmt.setString(2, keyword);
            pstmt.setString(3, keyword);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    int id = rs.getInt("company_id");
                    String companyID = String.format("COMP-%05d", id);

                    Object[] row = {
                        companyID,
                        rs.getString("company_name"),
                        rs.getString("company_contact")
                    };

                    companies.add(row);
                }
            }

            } catch (SQLException error) {
            System.out.println("Error searching companies: " + error.getMessage());
            }
        return companies;
    }
    
    
    public List<Object[]> loadArchivedCompanies() {

        List<Object[]> companies = new ArrayList<>();

        String sql = "SELECT company_id, company_name, company_contact, company_email "
               + "FROM tbl_companies "
               + "WHERE company_archived = 1";

        try (Connection conn = DBConnection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {

                int id = rs.getInt("company_id");
                String companyID = String.format("COMP-%05d", id);

                Object[] row = {
                    companyID,
                    rs.getString("company_name"),
                    rs.getString("company_contact"),
                    rs.getString("company_email")
                };
                companies.add(row);
            }

        } catch (SQLException error) {
            System.out.println("Error loading archived companies: " + error.getMessage());
        }
        return companies;
    }
    
    
    public List<Object[]> searchArchivedCompany(String search) {

        List<Object[]> companies = new ArrayList<>();

        String sql = "SELECT company_id, company_name, company_contact, company_email "
               + "FROM tbl_companies "
               + "WHERE company_archived = 1 "
               + "AND (company_name LIKE ? "
               + "OR company_contact LIKE ? "
               + "OR CONCAT('COMP-', LPAD(company_id, 5, '0')) LIKE ?)";

        try (Connection conn = DBConnection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String keyword = "%" + search + "%";

            pstmt.setString(1, keyword);
            pstmt.setString(2, keyword);
            pstmt.setString(3, keyword);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    int id = rs.getInt("company_id");
                    String companyID = String.format("COMP-%05d", id);

                    Object[] row = {
                        companyID,
                        rs.getString("company_name"),
                        rs.getString("company_contact"),
                        rs.getString("company_email")
                    };
                companies.add(row);
                }
            }
        } catch (SQLException error) {
            System.out.println("Error searching archived companies: " + error.getMessage()
            );
        }
    return companies;
    }
    
    public boolean archiveCompany(int companyID) {
        
        String sql = "UPDATE tbl_companies "
                   + "SET company_archived = 1 "
                   + "WHERE company_id = ?";
        
        try (Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, companyID);
            
            int updated = pstmt.executeUpdate();
            
            return updated > 0;
        } catch (SQLException error) {
            System.out.println("Error archiving: " + error.getMessage());
            return false;
        }
    }
    
    public boolean retrieveCompany(int companyID) {
        
        String sql = "UPDATE tbl_companies "
                   + "SET company_archived = 0 "
                   + "WHERE company_id = ?";
        
        try(Connection conn = DBConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, companyID);
            
            int updated = pstmt.executeUpdate();
            
            return updated > 0;
        } catch (SQLException error) {
            System.out.println("Error retrieving: " + error.getMessage());
            return false;
        }
    }
    
    public Company getCompany(int companyID) {

        String sql = "SELECT company_id, company_name, company_contact, company_email "
               + "FROM tbl_companies "
               + "WHERE company_id = ? AND company_archived = 0";

        try (Connection conn = DBConnection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, companyID);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {

                    Company compSet = new Company();

                    compSet.setCompanyID(rs.getInt("company_id"));
                    compSet.setCompanyName(rs.getString("company_name"));
                    compSet.setContactNumber(rs.getString("company_contact"));
                    compSet.setEmailAddress(rs.getString("company_email"));

                return compSet;
                }
            }

        } catch (SQLException error) {
            System.out.println("Error searching company: " + error.getMessage());
        }
    return null;
    }
}
