/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main_GUI;

import order_GUI.UpdateOrder;
import order_GUI.OrderList;
import order_GUI.NewOrderFrame;
import service_GUI.UpdateServiceFrame;
import service_GUI.ServiceFrame;
import customer_GUI.UpdateCustomerFrame;
import customer_GUI.CustomerFrame;
import Login_GUI.AdminAuthenticator;
import Utility.FrameResizerRestriction;
import Login_GUI.Login;
import Login_GUI.Login;
import order_GUI.Payment;
import AdminAccess_GUI.ReportsFrame;
import AdminAccess_GUI.TransactionHistory;
import AdminAccess_GUI.UserManagement;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

public class MainMenuFrame extends javax.swing.JFrame {
    private String welcomeName;
    private String userRole;
    private javax.swing.Timer clockTimer;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainMenuFrame.class.getName());

    public MainMenuFrame(String welcomeName,String userRole) {
        initComponents();
        
        this.welcomeName = welcomeName;
        this.userRole = userRole;
        
        dashboardComponents();
        openDashboard();
        dashboardMessageSetter();
        
        FrameResizerRestriction.setupDesktopPane(desktopPane);
    }
    
    public void showFrame(JInternalFrame frame) {
        desktopPane.removeAll();
        FrameResizerRestriction.setupInternalFrame(frame);
        desktopPane.add(frame);
        frame.setBounds(0,0, desktopPane.getWidth(), desktopPane.getHeight());
        frame.setBorder(BorderFactory.createEmptyBorder());
        frame.setVisible(true);
        desktopPane.revalidate();
        desktopPane.repaint();
    }
    
    public void openDashboard() {
        Dashboard openDash = new Dashboard(userRole,this);
        showFrame(openDash);
    }
    
    public void openCustomerFrame() {
        CustomerFrame ctmFrame = new CustomerFrame(welcomeName,userRole,this);
        showFrame(ctmFrame);
    }
    
    public void openUpdateFrame() {
        UpdateCustomerFrame updCtm = new UpdateCustomerFrame(welcomeName,userRole,this);
        showFrame(updCtm);
    }
    
    public void openServiceFrame() {
        ServiceFrame svf = new ServiceFrame(welcomeName,userRole);
        showFrame(svf);
    }
    
    public void openUpdateServiceFrame() {
        UpdateServiceFrame svfupd = new UpdateServiceFrame(welcomeName,userRole);
        showFrame(svfupd);
    }
    
    public void openNewOrderFrame() {
        NewOrderFrame newOrder = new NewOrderFrame(welcomeName,userRole,this);
        showFrame(newOrder);
    }
    
    public void openUpdateOrderFrame() {
        UpdateOrder updateOrder = new UpdateOrder(welcomeName,userRole);
        showFrame(updateOrder);
    }
    
    public void openOrderListFrame() {
        OrderList odrList = new OrderList(welcomeName,userRole);
        showFrame(odrList);
    }
    
    public void openPaymentFrame() {
        Payment pmt = new Payment(welcomeName,userRole);
        showFrame(pmt);
    }
    
    public void openReportFrame() {
        ReportsFrame rpt = new ReportsFrame(welcomeName,userRole);
        showFrame(rpt);
    }
    
    public void openTransactionFrame() {
        TransactionHistory trn = new TransactionHistory(welcomeName,userRole);
        showFrame(trn);
    }
    
    public void openUserManagementFrame() {
        UserManagement user = new UserManagement(welcomeName,userRole,this);
        showFrame(user);
    }
    
    public void dashboardComponents() {
        setLocationRelativeTo(null);
        welcomeNameLabel.setText(welcomeName);
        userRoleLabel.setText(userRole);
        
        welcomeText.setText("Welcome, " + welcomeName + "!");
        
        if(!userRole.equals("Administrator")) {
            serviceButton.setVisible(false);
            updateServiceButton.setVisible(false);
            reportsButton.setVisible(false);
            transactionButton.setVisible(false);
            userManagementButton.setVisible(false);
        }
    }
    
    public void dashboardMessageSetter() {
        Random random = new Random();
        
        int randomizer = random.nextInt(1,5);
        
        if(randomizer == 1) {
            welcomeMessage.setText("Here’s what’s happening with your orders today.");
        }
        else if(randomizer == 2) {
            welcomeMessage.setText("Keep track of your printing services and orders.");
        }
        else if(randomizer == 3) {
            welcomeMessage.setText("Manage today’s orders and service activities.");
        }
        else if(randomizer == 4) {
            welcomeMessage.setText("Here’s your overview for today.");
        }
        else if(randomizer == 5) {
            welcomeMessage.setText("Keep track of what’s happening.");
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

        jLabel1 = new javax.swing.JLabel();
        desktopPane = new javax.swing.JDesktopPane();
        jPanel3 = new javax.swing.JPanel();
        userRoleLabel = new javax.swing.JLabel();
        Logout = new javax.swing.JButton();
        welcomeNameLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        welcomeText = new javax.swing.JTextField();
        welcomeMessage = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        addCustomerButton = new javax.swing.JButton();
        updateCustomerButton = new javax.swing.JButton();
        newOrderButton = new javax.swing.JButton();
        updateOrderButton = new javax.swing.JButton();
        ordersButton = new javax.swing.JButton();
        paymentButton = new javax.swing.JButton();
        reportsButton = new javax.swing.JButton();
        transactionButton = new javax.swing.JButton();
        userManagementButton = new javax.swing.JButton();
        serviceButton = new javax.swing.JButton();
        updateServiceButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Management Dashboard");
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1610, -1));

        desktopPane.setBackground(new java.awt.Color(255, 255, 255));
        desktopPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout desktopPaneLayout = new javax.swing.GroupLayout(desktopPane);
        desktopPane.setLayout(desktopPaneLayout);
        desktopPaneLayout.setHorizontalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1266, Short.MAX_VALUE)
        );
        desktopPaneLayout.setVerticalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 716, Short.MAX_VALUE)
        );

        getContentPane().add(desktopPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 170, 1270, 720));

        jPanel3.setBackground(new java.awt.Color(255, 153, 0));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userRoleLabel.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        userRoleLabel.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(userRoleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 90, 177, 30));

        Logout.setBackground(new java.awt.Color(255, 51, 51));
        Logout.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Logout.setForeground(new java.awt.Color(255, 255, 255));
        Logout.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Downloads\\exit-2860_32.png")); // NOI18N
        Logout.setText("Logout");
        Logout.setBorder(null);
        Logout.setIconTextGap(15);
        Logout.addActionListener(this::LogoutActionPerformed);
        jPanel3.add(Logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1370, 50, 190, 60));

        welcomeNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        welcomeNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(welcomeNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 50, 177, 27));

        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Downloads\\user-3295_64.png")); // NOI18N
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 40, -1, 80));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Client_logo_noBG (2).png"))); // NOI18N
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 6, -1, -1));

        welcomeText.setBackground(new java.awt.Color(255, 153, 0));
        welcomeText.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        welcomeText.setForeground(new java.awt.Color(255, 255, 255));
        welcomeText.setBorder(null);
        jPanel3.add(welcomeText, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 614, -1));

        welcomeMessage.setBackground(new java.awt.Color(255, 153, 0));
        welcomeMessage.setFont(new java.awt.Font("Segoe UI", 0, 25)); // NOI18N
        welcomeMessage.setForeground(new java.awt.Color(255, 255, 255));
        welcomeMessage.setBorder(null);
        jPanel3.add(welcomeMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, 614, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1300, 40, 10, 90));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1600, 170));

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jButton1.setBackground(new java.awt.Color(0, 153, 153));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Dashboard");
        jButton1.setBorder(null);
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(this::jButton1ActionPerformed);

        addCustomerButton.setBackground(new java.awt.Color(0, 153, 153));
        addCustomerButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        addCustomerButton.setForeground(new java.awt.Color(255, 255, 255));
        addCustomerButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Downloads\\users-267_32.png")); // NOI18N
        addCustomerButton.setText("Add Customer");
        addCustomerButton.setBorder(null);
        addCustomerButton.setFocusPainted(false);
        addCustomerButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        addCustomerButton.setIconTextGap(15);
        addCustomerButton.setName(""); // NOI18N
        addCustomerButton.addActionListener(this::addCustomerButtonActionPerformed);

        updateCustomerButton.setBackground(new java.awt.Color(0, 153, 153));
        updateCustomerButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        updateCustomerButton.setForeground(new java.awt.Color(255, 255, 255));
        updateCustomerButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Downloads\\users-245_32.png")); // NOI18N
        updateCustomerButton.setText("Update Customer");
        updateCustomerButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        updateCustomerButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        updateCustomerButton.setIconTextGap(15);
        updateCustomerButton.setName(""); // NOI18N
        updateCustomerButton.addActionListener(this::updateCustomerButtonActionPerformed);

        newOrderButton.setBackground(new java.awt.Color(0, 153, 153));
        newOrderButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        newOrderButton.setForeground(new java.awt.Color(255, 255, 255));
        newOrderButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Downloads\\add-to-cart-3046_32.png")); // NOI18N
        newOrderButton.setText("New Order");
        newOrderButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        newOrderButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        newOrderButton.setIconTextGap(15);
        newOrderButton.setName(""); // NOI18N
        newOrderButton.addActionListener(this::newOrderButtonActionPerformed);

        updateOrderButton.setBackground(new java.awt.Color(0, 153, 153));
        updateOrderButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        updateOrderButton.setForeground(new java.awt.Color(255, 255, 255));
        updateOrderButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Downloads\\add-to-cart-3046_32.png")); // NOI18N
        updateOrderButton.setText("Update Order");
        updateOrderButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        updateOrderButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        updateOrderButton.setIconTextGap(15);
        updateOrderButton.setName(""); // NOI18N
        updateOrderButton.addActionListener(this::updateOrderButtonActionPerformed);

        ordersButton.setBackground(new java.awt.Color(0, 153, 153));
        ordersButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ordersButton.setForeground(new java.awt.Color(255, 255, 255));
        ordersButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Downloads\\list-6236_32.png")); // NOI18N
        ordersButton.setText("View Orders");
        ordersButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        ordersButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ordersButton.setIconTextGap(15);
        ordersButton.addActionListener(this::ordersButtonActionPerformed);

        paymentButton.setBackground(new java.awt.Color(0, 153, 153));
        paymentButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        paymentButton.setForeground(new java.awt.Color(255, 255, 255));
        paymentButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Downloads\\bill-8853_32.png")); // NOI18N
        paymentButton.setText("Payments");
        paymentButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        paymentButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        paymentButton.setIconTextGap(15);
        paymentButton.addActionListener(this::paymentButtonActionPerformed);

        reportsButton.setBackground(new java.awt.Color(0, 153, 153));
        reportsButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        reportsButton.setForeground(new java.awt.Color(255, 255, 255));
        reportsButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Downloads\\document-663_32.png")); // NOI18N
        reportsButton.setText("Reports");
        reportsButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        reportsButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        reportsButton.setIconTextGap(15);
        reportsButton.addActionListener(this::reportsButtonActionPerformed);

        transactionButton.setBackground(new java.awt.Color(0, 153, 153));
        transactionButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        transactionButton.setForeground(new java.awt.Color(255, 255, 255));
        transactionButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Downloads\\history-7611_32.png")); // NOI18N
        transactionButton.setText("Transaction History");
        transactionButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        transactionButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        transactionButton.setIconTextGap(15);
        transactionButton.addActionListener(this::transactionButtonActionPerformed);

        userManagementButton.setBackground(new java.awt.Color(0, 153, 153));
        userManagementButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        userManagementButton.setForeground(new java.awt.Color(255, 255, 255));
        userManagementButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Downloads\\admin-9575_32.png")); // NOI18N
        userManagementButton.setText("User Management");
        userManagementButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        userManagementButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        userManagementButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        userManagementButton.setIconTextGap(15);
        userManagementButton.addActionListener(this::userManagementButtonActionPerformed);

        serviceButton.setBackground(new java.awt.Color(0, 153, 153));
        serviceButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        serviceButton.setForeground(new java.awt.Color(255, 255, 255));
        serviceButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Downloads\\technical-support-black-gear-and-information-18738_32.png")); // NOI18N
        serviceButton.setText("Services");
        serviceButton.setBorder(null);
        serviceButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        serviceButton.setIconTextGap(15);
        serviceButton.setName(""); // NOI18N
        serviceButton.addActionListener(this::serviceButtonActionPerformed);

        updateServiceButton.setBackground(new java.awt.Color(0, 153, 153));
        updateServiceButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        updateServiceButton.setForeground(new java.awt.Color(255, 255, 255));
        updateServiceButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Downloads\\technical-support-black-gear-and-information-18738_32.png")); // NOI18N
        updateServiceButton.setText("Update Service");
        updateServiceButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        updateServiceButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        updateServiceButton.setIconTextGap(15);
        updateServiceButton.setName(""); // NOI18N
        updateServiceButton.addActionListener(this::updateServiceButtonActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addCustomerButton, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(updateCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(newOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(updateOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ordersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(paymentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(reportsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(transactionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(userManagementButton, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(serviceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(updateServiceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(updateCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(newOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(updateOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(ordersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(paymentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(reportsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(transactionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(userManagementButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(serviceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(updateServiceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 330, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void addCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCustomerButtonActionPerformed
        openCustomerFrame();
    }//GEN-LAST:event_addCustomerButtonActionPerformed

    private void LogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutActionPerformed
        int logoutOption = JOptionPane.showConfirmDialog(this,"Are you sure you want to logout?","Logout",JOptionPane.YES_NO_OPTION);
        
        if(logoutOption == JOptionPane.YES_OPTION) {
            Login backLogin = new Login();
            backLogin.setVisible(true);
            this.dispose();
        }
        else if(logoutOption == JOptionPane.NO_OPTION) {
            return;
        }
    }//GEN-LAST:event_LogoutActionPerformed

    private void transactionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transactionButtonActionPerformed
        openTransactionFrame();
    }//GEN-LAST:event_transactionButtonActionPerformed

    private void paymentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentButtonActionPerformed
        openPaymentFrame();
    }//GEN-LAST:event_paymentButtonActionPerformed

    private void userManagementButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userManagementButtonActionPerformed
        openUserManagementFrame();
    }//GEN-LAST:event_userManagementButtonActionPerformed

    private void reportsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportsButtonActionPerformed
        openReportFrame();
    }//GEN-LAST:event_reportsButtonActionPerformed

    private void ordersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ordersButtonActionPerformed
        openOrderListFrame();
    }//GEN-LAST:event_ordersButtonActionPerformed

    private void newOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newOrderButtonActionPerformed
        openNewOrderFrame();
    }//GEN-LAST:event_newOrderButtonActionPerformed

    private void serviceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serviceButtonActionPerformed
        openServiceFrame();
    }//GEN-LAST:event_serviceButtonActionPerformed

    private void updateCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateCustomerButtonActionPerformed
        openUpdateFrame();
    }//GEN-LAST:event_updateCustomerButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        openDashboard();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void updateServiceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateServiceButtonActionPerformed
        openUpdateServiceFrame();
    }//GEN-LAST:event_updateServiceButtonActionPerformed

    private void updateOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateOrderButtonActionPerformed
        openUpdateOrderFrame();
    }//GEN-LAST:event_updateOrderButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Logout;
    private javax.swing.JButton addCustomerButton;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton newOrderButton;
    private javax.swing.JButton ordersButton;
    private javax.swing.JButton paymentButton;
    private javax.swing.JButton reportsButton;
    private javax.swing.JButton serviceButton;
    private javax.swing.JButton transactionButton;
    private javax.swing.JButton updateCustomerButton;
    private javax.swing.JButton updateOrderButton;
    private javax.swing.JButton updateServiceButton;
    private javax.swing.JButton userManagementButton;
    private javax.swing.JLabel userRoleLabel;
    private javax.swing.JTextField welcomeMessage;
    private javax.swing.JLabel welcomeNameLabel;
    private javax.swing.JTextField welcomeText;
    // End of variables declaration//GEN-END:variables
}
