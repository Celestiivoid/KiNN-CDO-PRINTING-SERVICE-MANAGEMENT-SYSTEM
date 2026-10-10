/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Session;

/**
 *
 * @author User
 */
public class userSession {
    private static int userId;
    private static String firstName;
    private static String lastName;
    private static String userRole;
    
    public static void setUser(int id, String fName,String lName, String role) {
        userId = id;
        firstName = fName;
        lastName = lName;
        userRole = role;
    }
    
    public static int getUserId() {
        return userId;
    }
    
    public static String getFirstName() {
        return firstName;
    }
    
    public static String getLastName() {
        return lastName;
    }
    
    public static String getUserRole() {
        return userRole;
    }
    
    public static void clearSection() {
        userId = 0;
        firstName = null;
        lastName = null;
    }
}
