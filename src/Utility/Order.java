/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;

import java.time.LocalDateTime;

public class Order {
    private int orderID;
    private int userID;
    private int customerID;
    private int serviceID;

    private int quantity;

    private double unitPrice;
    private double subtotal;
    private double discountPercentage;
    private double discountAmount;
    private double totalAmount;

    private String orderStatus;
    private String paymentStatus;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private String customerType;

    private String serviceName;
    private String serviceSize;

    private LocalDateTime orderDate;
    
    
    public int getOrderID() {
        return orderID;
    }
    
    public int getUserID() {
        return userID;
    }
    
    public int getCustomerID() {
        return customerID;
    }
    
    public int getServiceID() {
        return serviceID;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public double getUnitPrice() {
        return unitPrice;
    }
    
    public double getSubtotal() {
        return subtotal;
    }
    
    public double getDiscountPercentage() {
        return discountPercentage;
    }
    
    public double getDiscountAmount() {
        return discountAmount;
    }
    
    public double getTotalAmount() {
        return totalAmount;
    }
    
    public String getOrderStatus() {
        return orderStatus;
    }
    
    public String getPaymentStatus() {
        return paymentStatus;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public String getEmailAddress() {
        return emailAddress;
    }
    
    public String getCustomerType() {
        return customerType;
    }
    
    public String getServiceName() {
        return serviceName;
    }
    
    public String getServiceSize() {
        return serviceSize;
    }
    
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    
    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    
    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
    
    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }
    
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }
    
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    
    public void setServiceSize(String serviceSize) {
        this.serviceSize = serviceSize;
    }
    
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
