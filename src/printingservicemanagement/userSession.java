/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package printingservicemanagement;

/**
 *
 * @author User
 */
public class userSession {
    private static int userId;
    private static String username;
    private static String userRole;
    
    public static void setUser(int id, String user, String role) {
        userId = id;
        username = user;
        userRole = role;
    }
    
    public static int getUserId() {
        return userId;
    }
    public static String getUsername() {
        return username;
    }
    public static String getUserRole() {
        return userRole;
    }
    public static void clearSection() {
        userId = 0;
        username = null;
    }
}
