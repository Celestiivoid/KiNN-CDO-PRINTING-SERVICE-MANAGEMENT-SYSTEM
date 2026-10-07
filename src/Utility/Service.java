/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;

/**
 *
 * @author User
 */
public class Service {
    private int serviceID;
    private String serviceName;
    private String serviceCategory;
    private String serviceUnit;
    private double servicePrice;
    private String serviceStatus;
    
    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }
    
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    
    public void setServiceCategory(String serviceCategory) {
        this.serviceCategory = serviceCategory;
    }
    
    public void setServiceUnit(String serviceUnit) {
        this.serviceUnit = serviceUnit;
    }
    
    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }
    
    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }
    
    public String getServiceName() {
        return serviceName;
    }
    
    public String getServiceCategory() {
        return serviceCategory;
    }
    
    public String getServiceUnit() {
        return serviceUnit;
    }
    
    public double getServicePrice() {
        return servicePrice;
    }
    
    public String getServiceStatus() {
        return serviceStatus;
    }
}
