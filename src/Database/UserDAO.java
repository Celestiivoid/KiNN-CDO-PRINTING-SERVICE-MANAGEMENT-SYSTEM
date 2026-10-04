/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;
import java.sql.*;

public class UserDAO {
     public ResultSet login(String username, String password) {

        Connection conn = DBConnection.connect();

        try {
            String sql = "SELECT * FROM tbl_users WHERE username = ? AND password = ?";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, username);
            pst.setString(2, password);

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
}
