/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;

/**
 *
 * @author User
 */
public class Company {
    private int companyID;
    private String companyName;
    private String contactNumber;
    private String emailAddress;
    
    
    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    public int getCompanyID() {
        return companyID;
    }
    
    public String getCompanyName() {
        return companyName;
    }
    
    public String getContactNumber() {
        return contactNumber;
    }
    
    public String getEmailAddress() {
        return emailAddress;
    }
}
