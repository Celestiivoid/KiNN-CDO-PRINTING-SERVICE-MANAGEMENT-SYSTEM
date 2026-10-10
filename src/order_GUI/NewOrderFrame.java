/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package order_GUI;

import customer_GUI.CustomerSearch;
import Database.CustomerConnector;
import Database.OrderConnector;
import Database.ServiceConnector;
import Session.userSession;
import Utility.Customer;
import Utility.DateAndTimeHandler;
import Utility.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class NewOrderFrame extends javax.swing.JInternalFrame {
    private int serviceID;
    private int customerID;
    private String welcomeName;
    private String userRole;
    private javax.swing.Timer clockTimer;

    
    public NewOrderFrame(String welcomeName, String userRole) {
        this.welcomeName = welcomeName;
        this.userRole = userRole;
        
        initComponents();
        displayDateTime();
        dashboardComponents();
        loadService();
        quantityListener();
    }
    
    public void quantityListener() {
        quantityField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
            orderCalculation();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            orderCalculation();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            orderCalculation();
            }
        });
    }
     
    public void dashboardComponents() {
        simulatedDate.setEditable(false);
        localTime.setEditable(false);
        priceUnitLabel.setText("0.00");
        discountTypeLabel.setText("");
        discountPercentageLabel.setText("0" + "%");
        discountAmountLabel.setText("0.00");
        subtotalLabel.setText("0.00");
        totalAfterDiscountLabel.setText("0.00");
        
        clockTimer = new javax.swing.Timer(1000, e -> displayDateTime());
        clockTimer.start();
        
    }
    
    
     private void displayDateTime() {
        
        simulatedDate.setText(DateAndTimeHandler.getDate().format(
            DateTimeFormatter.ofPattern("MMMM dd, yyyy")
            )
        );
        LocalTime currentTime = LocalTime.now();

        DateTimeFormatter timeFormatter =
        DateTimeFormatter.ofPattern("hh:mm:ss a");

    localTime.setText(currentTime.format(timeFormatter));
}
     private void loadService() {
         
        ServiceConnector svcDAO = new ServiceConnector();
         
        List<Service> services = svcDAO.loadServiceName();
        
        serviceBox.removeAll();
        
        for(Service svc : services) {
            serviceBox.addItem(svc.getServiceName() + " - " + svc.getServiceSize());
        }
        
        serviceBox.addActionListener( e -> {
            String selectedItem = (String) serviceBox.getSelectedItem();
            int selectedIndex = serviceBox.getSelectedIndex();
            
            if(selectedIndex <= 0) {
                priceUnitLabel.setText("0.00");
                serviceID = 0;
                return;
            }
            
            Service selectedService = services.get(selectedIndex - 1);
            
            serviceID = selectedService.getServiceID();
            
            for(Service svc : services) {
                String displayedService = svc.getServiceName() + " - " + svc.getServiceSize();
                
                if(displayedService.equals(selectedItem)) {
                    priceUnitLabel.setText(String.format("%.2f", svc.getServicePrice()));
                    orderCalculation();
                }
            }
        });
     }
     
     private void orderCalculation() {
         String serviceUnit = priceUnitLabel.getText();
         String serviceQuantity = quantityField.getText();
         String discountPercentage = discountPercentageLabel.getText().replace("%","");
         
         try {
             double convertedUnit = Double.parseDouble(serviceUnit);
             int convertedQuantity = Integer.parseInt(serviceQuantity);
             double convertedPercentage = Double.parseDouble(discountPercentage);
             
             double subtotal = convertedUnit * convertedQuantity;
             double discountAmount = subtotal * (convertedPercentage / 100);
             double finalTotal = subtotal - discountAmount;
               
             subtotalLabel.setText(String.format("%.2f", subtotal));
             discountAmountLabel.setText(String.format("%.2f", discountAmount));
             totalAfterDiscountLabel.setText(String.format("%.2f",finalTotal));
         } catch (NumberFormatException error) {
             System.out.println("Error calculating: " + error.getMessage());
             subtotalLabel.setText("0.00");
             return;
         }
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        simulatedDate = new javax.swing.JTextField();
        localTime = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        searchField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        firstNameLabel = new javax.swing.JLabel();
        lastNameLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        ctNumberLabel = new javax.swing.JLabel();
        emAddressLabel = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        serviceBox = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        quantityField = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        priceUnitLabel = new javax.swing.JLabel();
        discountTypeLabel = new javax.swing.JLabel();
        discountPercentageLabel = new javax.swing.JLabel();
        discountAmountLabel = new javax.swing.JLabel();
        subtotalLabel = new javax.swing.JLabel();
        totalAfterDiscountLabel = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        orderStatusBox = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        customerListButton = new javax.swing.JButton();
        placeOrderButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        simulatedDate.setBackground(new java.awt.Color(255, 255, 255));
        simulatedDate.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        simulatedDate.setForeground(new java.awt.Color(0, 153, 153));
        simulatedDate.setBorder(null);
        jPanel1.add(simulatedDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1025, 20, 225, 50));

        localTime.setBackground(new java.awt.Color(255, 255, 255));
        localTime.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        localTime.setForeground(new java.awt.Color(255, 153, 0));
        localTime.setBorder(null);
        jPanel1.add(localTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(1025, 80, 225, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Downloads\\rgb-colors-10810_128.png")); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 10, 130, 130));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Place Order");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 40, 270, 70));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Customer Information");

        searchField.setBackground(new java.awt.Color(255, 255, 255));
        searchField.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        searchField.setForeground(new java.awt.Color(0, 0, 0));
        searchField.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        searchButton.setBackground(new java.awt.Color(0, 153, 153));
        searchButton.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        searchButton.setForeground(new java.awt.Color(255, 255, 255));
        searchButton.setText("Search");
        searchButton.addActionListener(this::searchButtonActionPerformed);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("First Name");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Last Name");

        firstNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        firstNameLabel.setForeground(new java.awt.Color(0, 0, 0));

        lastNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lastNameLabel.setForeground(new java.awt.Color(0, 0, 0));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Contact Number");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Email Address");

        ctNumberLabel.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        ctNumberLabel.setForeground(new java.awt.Color(0, 0, 0));

        emAddressLabel.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        emAddressLabel.setForeground(new java.awt.Color(0, 0, 0));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Select Service");

        serviceBox.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        serviceBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select service..." }));
        serviceBox.addActionListener(this::serviceBoxActionPerformed);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Quantity");

        quantityField.setBackground(new java.awt.Color(255, 255, 255));
        quantityField.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        quantityField.setForeground(new java.awt.Color(0, 0, 0));
        quantityField.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Price/Unit:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Discount amount:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Discount type:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Discount percentage:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Subtotal:");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Total after discount:");

        priceUnitLabel.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        priceUnitLabel.setForeground(new java.awt.Color(0, 0, 0));

        discountTypeLabel.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        discountTypeLabel.setForeground(new java.awt.Color(0, 0, 0));

        discountPercentageLabel.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        discountPercentageLabel.setForeground(new java.awt.Color(51, 255, 51));

        discountAmountLabel.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        discountAmountLabel.setForeground(new java.awt.Color(251, 55, 55));

        subtotalLabel.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        subtotalLabel.setForeground(new java.awt.Color(0, 0, 0));

        totalAfterDiscountLabel.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        totalAfterDiscountLabel.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(priceUnitLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(discountTypeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(subtotalLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(totalAfterDiscountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(12, 12, 12)
                        .addComponent(discountAmountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(discountPercentageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(priceUnitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(discountTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(discountPercentageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(discountAmountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(subtotalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(totalAfterDiscountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Order Status");

        orderStatusBox.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        orderStatusBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select order status...", "PENDING", "PROCESSING" }));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Search Customer ID");

        customerListButton.setBackground(new java.awt.Color(0, 153, 153));
        customerListButton.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        customerListButton.setForeground(new java.awt.Color(255, 255, 255));
        customerListButton.setText("Customer List");
        customerListButton.addActionListener(this::customerListButtonActionPerformed);

        placeOrderButton.setBackground(new java.awt.Color(255, 153, 0));
        placeOrderButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        placeOrderButton.setForeground(new java.awt.Color(255, 255, 255));
        placeOrderButton.setText("Place Order");
        placeOrderButton.addActionListener(this::placeOrderButtonActionPerformed);

        clearButton.setBackground(new java.awt.Color(251, 55, 55));
        clearButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        clearButton.setForeground(new java.awt.Color(255, 255, 255));
        clearButton.setText("Clear");
        clearButton.addActionListener(this::clearButtonActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(ctNumberLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(firstNameLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(serviceBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(193, 193, 193))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(orderStatusBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(204, 204, 204)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lastNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                            .addComponent(emAddressLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(quantityField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(customerListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(placeOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(customerListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(searchField, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lastNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(firstNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ctNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(emAddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(quantityField)
                            .addComponent(serviceBox, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
                        .addGap(12, 12, 12)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(orderStatusBox, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(placeOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 146, 1250, 550));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1270, 710));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        String customerIDText = searchField.getText().trim();
        
        try {
            this.customerID = Integer.parseInt(customerIDText.replace("CUST-",""));
            
            CustomerConnector ctmDAO = new CustomerConnector();
            
            Customer ctm = ctmDAO.getCustomer(customerID);
            
            if(ctm != null) {
                firstNameLabel.setText(ctm.getFirstName());
                lastNameLabel.setText(ctm.getLastName());
                ctNumberLabel.setText(ctm.getPhoneNumber());
                emAddressLabel.setText(ctm.getEmailAddress());
                discountTypeLabel.setText(ctm.getCustomerType());
                
                if(discountTypeLabel.getText().equals("Student")) {
                    discountPercentageLabel.setText("10%");
                }
                else {
                    discountPercentageLabel.setText("0");
                    discountAmountLabel.setText("0.00");
                }
                orderCalculation();
            }
            else {
                JOptionPane.showMessageDialog(this,"Customer not found!","Not found!",JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException error) {
            JOptionPane.showMessageDialog(this,"Invalid customer ID! (CUST-XXXXX)","Invalid",JOptionPane.WARNING_MESSAGE);
            return;
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void serviceBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serviceBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_serviceBoxActionPerformed

    private void customerListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerListButtonActionPerformed
        CustomerSearch ctmSearch = new CustomerSearch(welcomeName,userRole);
        ctmSearch.setVisible(true);
    }//GEN-LAST:event_customerListButtonActionPerformed

    private void placeOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_placeOrderButtonActionPerformed
        int convertedQuantity;
        double convertedPrice;
        double convertedSubtotal;
        double convertedPercentage;
        double convertedDiscountAmount;
        double convertedTotalAmount;
        
        int userID = userSession.getUserId();
        String quantity = quantityField.getText();
        String unitPrice = priceUnitLabel.getText();
        String subtotal = subtotalLabel.getText();
        String discountPercentage = discountPercentageLabel.getText().replace("%", "");
        String discountAmount = discountAmountLabel.getText();
        String totalAmount = totalAfterDiscountLabel.getText();
        String service = (String) serviceBox.getSelectedItem();
        String orderStatus = (String) orderStatusBox.getSelectedItem();
        LocalDate orderDate = LocalDate.now();
        LocalTime orderTime = LocalTime.now();
        LocalDateTime orderDateTime = LocalDateTime.of(orderDate, orderTime);
        
        if(service.equals("Select service...")) {
            JOptionPane.showMessageDialog(this,"Please select a service.","Select service",JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if(orderStatus.equals("Select order status...")) {
            JOptionPane.showMessageDialog(this,"Please select order status.","Select order",JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if(quantity.isEmpty()) {
            JOptionPane.showMessageDialog(this,"Quantity field is required to be filled out.","Empty field",JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            convertedPrice = Double.parseDouble(unitPrice);
            convertedSubtotal = Double.parseDouble(subtotal);
            convertedPercentage = Double.parseDouble(discountPercentage);
            convertedDiscountAmount = Double.parseDouble(discountAmount);
            convertedTotalAmount = Double.parseDouble(totalAmount);
        } catch (NumberFormatException error) {
            JOptionPane.showMessageDialog(this,"Unexpected error!","Idk what may cause this lol",JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            convertedQuantity = Integer.parseInt(quantity);
        } catch (NumberFormatException error) {
            JOptionPane.showMessageDialog(this,"Invalid quantity input!","Invalid",JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        OrderConnector odrConn = new OrderConnector();
        
        boolean added = odrConn.addOrder(
                userID,
                customerID,
                serviceID,
                convertedQuantity,
                convertedPrice,
                convertedSubtotal,
                convertedPercentage,
                convertedDiscountAmount,
                convertedTotalAmount,
                orderStatus,
                "UNPAID",
                orderDateTime);
        
        if(added) {
            JOptionPane.showMessageDialog(this,"Successfully placed order!","Success!",JOptionPane.INFORMATION_MESSAGE);
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            ctNumberLabel.setText("");
            emAddressLabel.setText("");
            serviceBox.setSelectedIndex(0);
            orderStatusBox.setSelectedIndex(0);
            quantityField.setText("");
            priceUnitLabel.setText("");
            discountTypeLabel.setText("");
            discountPercentageLabel.setText("");
            discountAmountLabel.setText("");
            subtotalLabel.setText("");
            totalAfterDiscountLabel.setText("");
        } else {
            JOptionPane.showMessageDialog(this,"Error placing order.","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_placeOrderButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        searchField.setText("");
        firstNameLabel.setText("");
        lastNameLabel.setText("");
        ctNumberLabel.setText("");
        emAddressLabel.setText("");
        serviceBox.setSelectedIndex(0);
        orderStatusBox.setSelectedIndex(0);
        quantityField.setText("");
        priceUnitLabel.setText("");
        discountTypeLabel.setText("");
        discountPercentageLabel.setText("");
        discountAmountLabel.setText("");
        subtotalLabel.setText("");
        totalAfterDiscountLabel.setText("");
    }//GEN-LAST:event_clearButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearButton;
    private javax.swing.JLabel ctNumberLabel;
    private javax.swing.JButton customerListButton;
    private javax.swing.JLabel discountAmountLabel;
    private javax.swing.JLabel discountPercentageLabel;
    private javax.swing.JLabel discountTypeLabel;
    private javax.swing.JLabel emAddressLabel;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JTextField localTime;
    private javax.swing.JComboBox<String> orderStatusBox;
    private javax.swing.JButton placeOrderButton;
    private javax.swing.JLabel priceUnitLabel;
    private javax.swing.JTextField quantityField;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JComboBox<String> serviceBox;
    private javax.swing.JTextField simulatedDate;
    private javax.swing.JLabel subtotalLabel;
    private javax.swing.JLabel totalAfterDiscountLabel;
    // End of variables declaration//GEN-END:variables
}
