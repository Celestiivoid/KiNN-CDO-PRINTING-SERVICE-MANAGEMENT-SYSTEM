/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import Database.CustomerConnector;
import Database.OrderConnector;
import Database.ServiceConnector;
import Session.userSession;
import Utility.Customer;
import Utility.DateAndTimeHandler;
import Utility.Order;
import Utility.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class UpdateOrder extends javax.swing.JInternalFrame {
    private int orderID;
    private int serviceID;
    private int customerID;
    private String welcomeName;
    private String userRole;
    private javax.swing.Timer clockTimer;

    
    public UpdateOrder(String welcomeName, String userRole) {
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
        jLabel15 = new javax.swing.JLabel();
        orderStatusBox = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        updateOrder = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        serviceLabel = new javax.swing.JLabel();
        orderStatusLabel = new javax.swing.JLabel();
        quantityLabel = new javax.swing.JLabel();
        paymentStatusLabel = new javax.swing.JLabel();

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
        jLabel2.setText("Update Order");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 40, 270, 70));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Customer Information");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 22, 299, -1));

        searchField.setBackground(new java.awt.Color(255, 255, 255));
        searchField.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        searchField.setForeground(new java.awt.Color(0, 0, 0));
        searchField.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.add(searchField, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 110, 491, 40));

        searchButton.setBackground(new java.awt.Color(0, 153, 153));
        searchButton.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        searchButton.setForeground(new java.awt.Color(255, 255, 255));
        searchButton.setText("Search");
        searchButton.addActionListener(this::searchButtonActionPerformed);
        jPanel3.add(searchButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(522, 110, 150, 40));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("First Name");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 168, 310, 39));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Last Name");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(533, 168, 310, 39));

        firstNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        firstNameLabel.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(firstNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 213, 310, 35));

        lastNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lastNameLabel.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(lastNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(533, 213, 310, 35));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Contact Number");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 254, 310, 36));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Email Address");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(533, 254, 310, 36));

        ctNumberLabel.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        ctNumberLabel.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(ctNumberLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 302, 310, 28));

        emAddressLabel.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        emAddressLabel.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(emAddressLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(533, 302, 310, 28));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Select Service");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 342, 310, 41));

        serviceBox.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        serviceBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- Select service ---" }));
        serviceBox.addActionListener(this::serviceBoxActionPerformed);
        jPanel3.add(serviceBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 390, 180, 47));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Quantity");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(533, 342, 310, 41));

        quantityField.setBackground(new java.awt.Color(255, 255, 255));
        quantityField.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        quantityField.setForeground(new java.awt.Color(0, 0, 0));
        quantityField.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.add(quantityField, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 390, 190, 47));

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

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(897, 110, -1, 313));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Order Status");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 448, 310, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Payment Status");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(533, 448, 337, -1));

        orderStatusBox.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        orderStatusBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- Select Status ---", "PENDING", "PROCESSING", "COMPLETED", "CANCELLED" }));
        jPanel3.add(orderStatusBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 480, 180, 45));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Search Order ID");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 68, 215, 34));

        updateOrder.setBackground(new java.awt.Color(255, 153, 0));
        updateOrder.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        updateOrder.setForeground(new java.awt.Color(255, 255, 255));
        updateOrder.setText("Update Order");
        updateOrder.addActionListener(this::updateOrderActionPerformed);
        jPanel3.add(updateOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(1064, 454, 155, 60));

        clearButton.setBackground(new java.awt.Color(251, 55, 55));
        clearButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        clearButton.setForeground(new java.awt.Color(255, 255, 255));
        clearButton.setText("Clear");
        jPanel3.add(clearButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(897, 454, 155, 60));

        serviceLabel.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        serviceLabel.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(serviceLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 150, 47));

        orderStatusLabel.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        orderStatusLabel.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(orderStatusLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 152, 45));

        quantityLabel.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        quantityLabel.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(quantityLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 390, 80, 47));

        paymentStatusLabel.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        paymentStatusLabel.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(paymentStatusLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 480, 290, 45));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 146, 1250, 550));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1270, 710));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        String orderIDText = searchField.getText().trim();

        if (orderIDText.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Please enter an Order ID.",
                "Missing Order ID",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {

            int orderID = Integer.parseInt(
                orderIDText.replace("ORD-", "")
            );

            OrderConnector ordConn = new OrderConnector();
            Order order = ordConn.getOrder(orderID);

            if (order == null) {
                JOptionPane.showMessageDialog(
                    this,
                    "Order not found.",
                    "Search Result",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            this.orderID = order.getOrderID();
            this.customerID = order.getCustomerID();
            this.serviceID = order.getServiceID();

            firstNameLabel.setText(order.getFirstName());
            lastNameLabel.setText(order.getLastName());
            ctNumberLabel.setText(order.getPhoneNumber());
            emAddressLabel.setText(order.getEmailAddress());
            quantityLabel.setText(String.valueOf(order.getQuantity()));
            serviceLabel.setText(order.getServiceName() + " - " + order.getServiceSize());
            orderStatusLabel.setText(order.getOrderStatus());
            paymentStatusLabel.setText(order.getPaymentStatus());
            priceUnitLabel.setText(String.format("%.2f", order.getUnitPrice()));
            discountTypeLabel.setText(order.getCustomerType());
            subtotalLabel.setText(String.format("%.2f", order.getSubtotal()));
            discountPercentageLabel.setText(String.format("%.0f%%", order.getDiscountPercentage()));
            discountAmountLabel.setText(String.format("%.2f", order.getDiscountAmount()));
            totalAfterDiscountLabel.setText(String.format("%.2f", order.getTotalAmount()));

        } catch (NumberFormatException error) {

            JOptionPane.showMessageDialog(this,"Invalid Order ID (ORD-XXXXX)","Invalid Order ID",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void serviceBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serviceBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_serviceBoxActionPerformed

    private void updateOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateOrderActionPerformed
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
        String paymentStatus = (String) paymentStatusLabel.getText();
        LocalDate orderDate = DateAndTimeHandler.getDate();
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
        
        if(paymentStatus.equals("Select payment status...")) {
            JOptionPane.showMessageDialog(this,"Please select payment status.","Select payment",JOptionPane.WARNING_MESSAGE);
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
        
        OrderConnector updConn = new OrderConnector();
        
        boolean updated = updConn.updateOrder(
                orderID, 
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
                paymentStatus);
        
        if(updated) {
            JOptionPane.showMessageDialog(this,"Successfully updated order!","Success!",JOptionPane.INFORMATION_MESSAGE);
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
            serviceLabel.setText("");
            quantityLabel.setText("");
            orderStatusLabel.setText("");
            paymentStatusLabel.setText("");
        } else {
            JOptionPane.showMessageDialog(this,"Error updating order.","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_updateOrderActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearButton;
    private javax.swing.JLabel ctNumberLabel;
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
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JLabel orderStatusLabel;
    private javax.swing.JLabel paymentStatusLabel;
    private javax.swing.JLabel priceUnitLabel;
    private javax.swing.JTextField quantityField;
    private javax.swing.JLabel quantityLabel;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JComboBox<String> serviceBox;
    private javax.swing.JLabel serviceLabel;
    private javax.swing.JTextField simulatedDate;
    private javax.swing.JLabel subtotalLabel;
    private javax.swing.JLabel totalAfterDiscountLabel;
    private javax.swing.JButton updateOrder;
    // End of variables declaration//GEN-END:variables
}
