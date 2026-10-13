/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package order_GUI;

import Database.OrderConnector;
import Database.PaymentConnector;
import Utility.Order;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.openpdf.text.Document;
import org.openpdf.text.Element;
import org.openpdf.text.Font;
import org.openpdf.text.FontFactory;
import org.openpdf.text.Image;
import org.openpdf.text.Paragraph;
import org.openpdf.text.Rectangle;
import org.openpdf.text.pdf.PdfWriter;

public class Payment extends javax.swing.JInternalFrame {
    private javax.swing.Timer clockTimer;
    private int currentOrderID = -1;
    private String welcomeName;
    private String userRole;

    
    public Payment(String welcomeName, String userRole) {
        this.welcomeName = welcomeName;
        this.userRole = userRole;
        
        initComponents();
        payButton.setEnabled(false);
        displayDateTime();
        
        clockTimer = new javax.swing.Timer(1000, e -> displayDateTime());
        clockTimer.start();
    }
    
    private void searchOrder() {

        String text = searchField.getText()
            .trim()
            .toUpperCase();

        if (text.startsWith("ORD-")) {
            text = text.substring(4);
        }

        int searchedID;

        try {
            searchedID = Integer.parseInt(text);

            if (searchedID <= 0) {
                throw new NumberFormatException();
            }

        } catch (NumberFormatException error) {
            JOptionPane.showMessageDialog(this,"Enter a valid Order ID, such as ORD-00001.");
            return;
        }

        PaymentConnector paymentConn = new PaymentConnector();

        int nextOrderID = paymentConn.getNextFIFOOrderID();

        if (nextOrderID == -1) {
            JOptionPane.showMessageDialog(this,"There are no unpaid orders in the queue.");
            return;
        }

        if (searchedID != nextOrderID) {
            JOptionPane.showMessageDialog(this,"FIFO rule: Please handle order "+ String.format("ORD-%05d", nextOrderID)+ " first.","Order Queue",JOptionPane.WARNING_MESSAGE);
            return;
        }

        OrderConnector orderConn = new OrderConnector();
        Order order = orderConn.getOrder(searchedID);

        if (order == null) {
            JOptionPane.showMessageDialog(this,"Order not found.");
            return;
        }

        if (!"Completed".equalsIgnoreCase(order.getOrderStatus())) {
            JOptionPane.showMessageDialog(this,"This is the next order in the FIFO queue, "+ "but it must be completed before payment.");
            return;
        }

        currentOrderID = order.getOrderID();

        orderNumberLabel.setText(String.format("ORD-%05d", order.getOrderID()));

        if (order.getCompanyID() != null) {
            
            customerNameLabel.setText(order.getCompanyName());
            
        } else {
            
            customerNameLabel.setText(order.getFirstName() + " " + order.getLastName());
            
        }

        serviceNameLabel.setText(order.getServiceName() + " - "+ order.getServiceSize());

        quantityLabel.setText(String.valueOf(order.getQuantity()) + " " + order.getUnitType());

        totalAmountLabel.setText(String.format("₱%.2f", order.getTotalAmount()));

        payButton.setEnabled(true);
    }
    
    public void displayDateTime() {
        
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        
        LocalDate currentDate = LocalDate.now();
        
        dateLabel.setText(currentDate.format(dateFormatter));
        
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        
        LocalTime currentTime = LocalTime.now();
        
        timeLabel.setText(currentTime.format(timeFormatter));
    }
    
    private void calculateChange() {

        String totalText = totalAmountLabel.getText();
        String paidText = amountField.getText().trim();

        if (paidText.isEmpty()) {
            changeLabel.setText("₱0.00");
            return;
        }
        
        totalText = totalText
            .replace("₱", "")
            .replace(",", "")
            .trim();

        try {
            double total = Double.parseDouble(totalText);
            double paid = Double.parseDouble(paidText);
            
            if (paid < total) {
                changeLabel.setText("₱0.00");
            } else {
                double change = paid - total;
                changeLabel.setText(String.format("₱%.2f", change));
            }

        } catch (NumberFormatException error) {
            changeLabel.setText("₱0.00");
        }
    }
    
    private Paragraph centeredText(String text, Font font) {
    Paragraph paragraph = new Paragraph(text, font);
    paragraph.setAlignment(Element.ALIGN_CENTER);
    return paragraph;
}
    
    private void generateTally() {
    LocalDate localDate = LocalDate.now();
    LocalTime localTime = LocalTime.now();
    LocalDateTime paymentDateTime = LocalDateTime.of(localDate, localTime);    
    OrderConnector conn = new OrderConnector();
    Order order = conn.getOrder(currentOrderID);
    
    
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Save Receipt");

    String orderID = orderNumberLabel.getText();

    fileChooser.setSelectedFile(new java.io.File("Receipt_" + orderID + ".pdf"));

    int result = fileChooser.showSaveDialog(this);

    if (result != JFileChooser.APPROVE_OPTION) {
        return;
    }

    java.io.File file = fileChooser.getSelectedFile();

    if (!file.getName().toLowerCase().endsWith(".pdf")) {
        file = new java.io.File(file.getAbsolutePath() + ".pdf");
    }

    float width = 210 * 2.83465f;
    float height = 297 * 2.83465f;

    Rectangle tallySize = new Rectangle(width, height);

    Document document = new Document(tallySize,10,10,10,10);

    try {

        PdfWriter.getInstance(
            document,
            new FileOutputStream(file)
        );

        document.open();
        
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD,16);

        Font normalFont = FontFactory.getFont(FontFactory.HELVETICA,9);

        Font smallFont = FontFactory.getFont(FontFactory.HELVETICA,8);

        Font totalFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD,12);

        Image logo = Image.getInstance("src/images/client_logo_noBG (2).png");

        logo.scaleToFit(70, 70);
        logo.setAlignment(Element.ALIGN_CENTER);

        document.add(logo);

        document.add(centeredText("KiNN CDO PRINTING SERVICE", titleFont));

        document.add(centeredText("Official Receipt", normalFont));

        document.add(centeredText("================================", smallFont));

        document.add(centeredText("Order ID: " + orderNumberLabel.getText(), normalFont));

        document.add(centeredText("Customer: " + customerNameLabel.getText(), normalFont));

        document.add(centeredText("Date: " + paymentDateTime.toLocalDate(), normalFont));

        document.add(centeredText("Service: " + order.getServiceName(), normalFont));

        document.add(centeredText("Quantity: " + order.getQuantity(), normalFont));


        document.add(centeredText("--------------------------------", smallFont));

        document.add(centeredText("TOTAL", totalFont));

        document.add(centeredText("₱" + order.getTotalAmount(), normalFont));

        document.add(centeredText("================================", smallFont));

        document.add(centeredText("THANK YOU FOR YOUR ORDER!", normalFont));

        document.add(centeredText("Please come again.", smallFont));
        document.close();

        JOptionPane.showMessageDialog(this,"Tally saved successfully!","Tally Generated",JOptionPane.INFORMATION_MESSAGE);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this,"Error generating tally:\n"+ e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
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
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        orderNumberLabel = new javax.swing.JLabel();
        customerNameLabel = new javax.swing.JLabel();
        serviceNameLabel = new javax.swing.JLabel();
        quantityLabel = new javax.swing.JLabel();
        totalAmountLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        receiptTextArea = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        amountField = new javax.swing.JTextField();
        payButton = new javax.swing.JButton();
        generateTallyButton = new javax.swing.JButton();
        changeLabel = new javax.swing.JLabel();
        searchField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        dateLabel = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Payment");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 220, 60));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Order Summary");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Order No.");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user-6767_32.png"))); // NOI18N
        jLabel4.setText("Customer");
        jLabel4.setIconTextGap(10);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/repairing-service-black-outline-22670_32.png"))); // NOI18N
        jLabel5.setText("Service");
        jLabel5.setIconTextGap(10);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/packages-3358_32.png"))); // NOI18N
        jLabel6.setText("Quantity");
        jLabel6.setIconTextGap(10);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/budget-calculator-and-dollar-sign-black-outline-18854_32.png"))); // NOI18N
        jLabel7.setText("Total Amount");
        jLabel7.setIconTextGap(10);

        orderNumberLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        orderNumberLabel.setForeground(new java.awt.Color(0, 0, 0));

        customerNameLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        customerNameLabel.setForeground(new java.awt.Color(0, 0, 0));

        serviceNameLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        serviceNameLabel.setForeground(new java.awt.Color(0, 0, 0));

        quantityLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        quantityLabel.setForeground(new java.awt.Color(0, 0, 0));

        totalAmountLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        totalAmountLabel.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totalAmountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(serviceNameLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                                .addComponent(quantityLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(customerNameLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(orderNumberLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 23, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(orderNumberLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(customerNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(serviceNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(quantityLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(totalAmountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 460, 510));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/transaction-14855_32.png"))); // NOI18N
        jLabel13.setText("Change");
        jLabel13.setIconTextGap(10);
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, 50));

        receiptTextArea.setBackground(new java.awt.Color(255, 255, 255));
        receiptTextArea.setColumns(20);
        receiptTextArea.setForeground(new java.awt.Color(0, 0, 0));
        receiptTextArea.setRows(5);
        jScrollPane1.setViewportView(receiptTextArea);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 260, 300));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/coin-dollar-2534_32.png"))); // NOI18N
        jLabel14.setText("Enter Amount");
        jLabel14.setIconTextGap(10);
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, 60));

        amountField.setBackground(new java.awt.Color(255, 255, 255));
        amountField.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        amountField.setForeground(new java.awt.Color(0, 0, 0));
        amountField.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        amountField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                amountFieldKeyReleased(evt);
            }
        });
        jPanel3.add(amountField, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 150, 50));

        payButton.setBackground(new java.awt.Color(255, 153, 0));
        payButton.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        payButton.setForeground(new java.awt.Color(255, 255, 255));
        payButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/money-dollar-6365_32.png"))); // NOI18N
        payButton.setText("Pay");
        payButton.setIconTextGap(20);
        payButton.addActionListener(this::payButtonActionPerformed);
        jPanel3.add(payButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, 240, 80));

        generateTallyButton.setBackground(new java.awt.Color(0, 153, 153));
        generateTallyButton.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        generateTallyButton.setForeground(new java.awt.Color(255, 255, 255));
        generateTallyButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer-1434_32.png"))); // NOI18N
        generateTallyButton.setText("Print Tally");
        generateTallyButton.setIconTextGap(20);
        generateTallyButton.addActionListener(this::generateTallyButtonActionPerformed);
        jPanel3.add(generateTallyButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 360, 260, 80));

        changeLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        changeLabel.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(changeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 150, 50));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 130, 660, 500));

        searchField.setBackground(new java.awt.Color(255, 255, 255));
        searchField.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        searchField.setForeground(new java.awt.Color(0, 0, 0));
        searchField.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(searchField, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 50, 280, 50));

        searchButton.setBackground(new java.awt.Color(0, 153, 153));
        searchButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        searchButton.setForeground(new java.awt.Color(255, 255, 255));
        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search-2904_32 (1).png"))); // NOI18N
        searchButton.setText("Search");
        searchButton.setIconTextGap(10);
        searchButton.addActionListener(this::searchButtonActionPerformed);
        jPanel1.add(searchButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 50, 160, 50));

        dateLabel.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        dateLabel.setForeground(new java.awt.Color(255, 153, 0));
        jPanel1.add(dateLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 20, 200, 30));

        timeLabel.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        timeLabel.setForeground(new java.awt.Color(0, 153, 153));
        jPanel1.add(timeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 60, 200, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1270, 710));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        searchOrder();
    }//GEN-LAST:event_searchButtonActionPerformed

    private void payButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payButtonActionPerformed
        String totalAmount = totalAmountLabel.getText();
        String amountPaid = amountField.getText().trim();
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime paymentDateTime = LocalDateTime.of(localDate, localTime);

        if (currentOrderID <= 0) {
            JOptionPane.showMessageDialog(this, "Please select an order first.","Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (totalAmount == null || totalAmount.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Total amount is empty. Please load an order first.","Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        totalAmount = totalAmount
        .replace("₱", "")
        .replace(",", "")
        .trim();

        try {
            double convertedTotal = Double.parseDouble(totalAmount);
            double convertedPaid = Double.parseDouble(amountPaid);

            if (convertedTotal <= 0) {
                JOptionPane.showMessageDialog(this, "The total amount must be greater than zero.");
                return;
            }

            if (convertedPaid <= 0) {
                JOptionPane.showMessageDialog(this, "Amount paid must be greater than zero.");
                return;
            }

            if (convertedPaid < convertedTotal) {
                JOptionPane.showMessageDialog(this, "Amount paid is insufficient.");
                return;
            }

            double convertedChange = convertedPaid - convertedTotal;

            changeLabel.setText("₱" + String.format("%.2f", convertedChange));

            PaymentConnector pmt = new PaymentConnector();

            boolean added = pmt.addPayment(currentOrderID,convertedPaid,convertedChange,paymentDateTime);

            if (added) {
                String receipt =
                "====================================\n"
                + "          KiNN CDO PRINTING          \n"
                + "              OFFICIAL RECEIPT       \n"
                + "====================================\n\n"
                + "Order ID    : "
                + String.format("ORD-%05d", currentOrderID) + "\n"
                + "Date        : "
                + paymentDateTime.toLocalDate() + "\n"
                + "Time        : "
                + paymentDateTime.toLocalTime().format(java.time.format.DateTimeFormatter.ofPattern("hh:mm:ss a")) + "\n"
                + "------------------------------------\n"
                + "Total Amount: ₱"
                + String.format("%.2f", convertedTotal) + "\n"
                + "Amount Paid : ₱"
                + String.format("%.2f", convertedPaid) + "\n"
                + "Change      : ₱"
                + String.format("%.2f", convertedChange) + "\n"
                + "------------------------------------\n"
                + "Payment Status: PAID\n\n"
                + "       Thank you for ordering!      \n"
                + "====================================\n";

                receiptTextArea.setText(receipt);
                receiptTextArea.setCaretPosition(0);

                JOptionPane.showMessageDialog(this, "Successfully paid!","Success!", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "This order has already been paid.","Payment Rejected", JOptionPane.ERROR_MESSAGE);
                return;
                }
        } catch (NumberFormatException error) {
            JOptionPane.showMessageDialog(this,"Please enter a valid numeric amount.","Invalid Amount", JOptionPane.WARNING_MESSAGE);
            return;
        }
    }//GEN-LAST:event_payButtonActionPerformed

    private void amountFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amountFieldKeyReleased
        calculateChange();
    }//GEN-LAST:event_amountFieldKeyReleased

    private void generateTallyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateTallyButtonActionPerformed
       generateTally();
    }//GEN-LAST:event_generateTallyButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField amountField;
    private javax.swing.JLabel changeLabel;
    private javax.swing.JLabel customerNameLabel;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JButton generateTallyButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel orderNumberLabel;
    private javax.swing.JButton payButton;
    private javax.swing.JLabel quantityLabel;
    private javax.swing.JTextArea receiptTextArea;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JLabel serviceNameLabel;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel totalAmountLabel;
    // End of variables declaration//GEN-END:variables
}
