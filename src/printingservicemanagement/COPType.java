/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package printingservicemanagement;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.openpdf.text.Document;
import org.openpdf.text.Paragraph;
import org.openpdf.text.Font;
import org.openpdf.text.FontFactory;
import org.openpdf.text.pdf.PdfWriter;
import org.openpdf.text.Element;
import org.openpdf.text.Rectangle;
import org.openpdf.text.Image;
/**
 *
 * @author User
 */
public class COPType extends javax.swing.JFrame {
    private String typeName;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(COPType.class.getName());

    /**
     * Creates new form COPType
     */
    public COPType() {
        initComponents();
        
        JDate.getDateEditor().addPropertyChangeListener(evt -> {
        if ("date".equals(evt.getPropertyName())) {
            updateDailySales();
        }
    });
    }
    
    private void updateDailySales() {

    Date selectedDate = JDate.getDate();

    if (selectedDate == null) {
        salesLabel.setText("₱0.00");
        return;
    }

    LocalDate date = selectedDate.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();

    double sales;

    if (date.equals(LocalDate.of(2026, 10, 2))) {
        sales = 2450.00;
    } else if (date.equals(LocalDate.of(2026, 10, 1))) {
        sales = 1800.00;
    } else if (date.equals(LocalDate.of(2026, 9, 30))) {
        sales = 3200.00;
    } else {
        sales = 0.00;
    }

    salesLabel.setText(String.format("₱%.2f", sales));
}
    private Paragraph centeredText(String text, Font font) {
    Paragraph paragraph = new Paragraph(text, font);
    paragraph.setAlignment(Element.ALIGN_CENTER);
    return paragraph;
}
    private void generateReceiptPDF() {

    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Save Receipt");

    String orderID = orderIDField.getText();

    fileChooser.setSelectedFile(
        new java.io.File("Receipt_" + orderID + ".pdf")
    );

    int result = fileChooser.showSaveDialog(this);

    if (result != JFileChooser.APPROVE_OPTION) {
        return;
    }

    java.io.File file = fileChooser.getSelectedFile();

    // Make sure the file has .pdf extension
    if (!file.getName().toLowerCase().endsWith(".pdf")) {
        file = new java.io.File(file.getAbsolutePath() + ".pdf");
    }

    /*
     * RECEIPT SIZE
     *
     * 80 mm wide
     * 200 mm tall
     *
     * 1 mm = 2.83465 PDF points
     */
    float width = 80 * 2.83465f;
    float height = 200 * 2.83465f;

    Rectangle receiptSize = new Rectangle(width, height);

    /*
     * Margins:
     * left   = 10
     * right  = 10
     * top    = 10
     * bottom = 10
     */
    Document document = new Document(
        receiptSize,
        10,
        10,
        10,
        10
    );

    try {

        PdfWriter.getInstance(
            document,
            new FileOutputStream(file)
        );

        document.open();

        // =========================
        // FONTS
        // =========================

        Font titleFont = FontFactory.getFont(
            FontFactory.HELVETICA_BOLD,
            16
        );

        Font normalFont = FontFactory.getFont(
            FontFactory.HELVETICA,
            9
        );

        Font smallFont = FontFactory.getFont(
            FontFactory.HELVETICA,
            8
        );

        Font totalFont = FontFactory.getFont(
            FontFactory.HELVETICA_BOLD,
            12
        );

        // =========================
        // LOGO
        // =========================

        Image logo = Image.getInstance(
            "src/images/client_logo.jpg"
        );

        logo.scaleToFit(70, 70);
        logo.setAlignment(Element.ALIGN_CENTER);

        document.add(logo);

        // =========================
        // TITLE
        // =========================

        document.add(
            centeredText("KiNN CDO PRINTING SERVICE", titleFont)
        );

        document.add(
            centeredText("Official Receipt", smallFont)
        );

        document.add(
            centeredText(
                "================================",
                smallFont
            )
        );

        // =========================
        // ORDER INFORMATION
        // =========================

        document.add(
            centeredText(
                "Order ID: " + orderIDField.getText(),
                normalFont
            )
        );

        document.add(
            centeredText(
                "Customer: " + customerNameField.getText(),
                normalFont
            )
        );

        document.add(
            centeredText(
                "Date: " + dateField.getText(),
                normalFont
            )
        );

        document.add(
            centeredText(
                "Service: " + serviceField.getText(),
                normalFont
            )
        );

        document.add(
            centeredText(
                "Quantity: " + quantityField.getText(),
                normalFont
            )
        );

        document.add(
            centeredText(
                "Payment: " + paymentMethodField.getText(),
                normalFont
            )
        );

        document.add(
            centeredText(
                "--------------------------------",
                smallFont
            )
        );

        // =========================
        // TOTAL
        // =========================

        document.add(
            centeredText(
                "TOTAL",
                normalFont
            )
        );

        document.add(
            centeredText(
                "₱" + totalField.getText(),
                totalFont
            )
        );

        document.add(
            centeredText(
                "================================",
                smallFont
            )
        );

        // =========================
        // THANK YOU
        // =========================

        document.add(
            centeredText(
                "THANK YOU FOR YOUR ORDER!",
                titleFont
            )
        );

        document.add(
            centeredText(
                "Please come again.",
                smallFont
            )
        );

        // =========================
        // CLOSE PDF
        // =========================

        document.close();

        JOptionPane.showMessageDialog(
            this,
            "Receipt saved successfully!\n\n"
            + file.getAbsolutePath(),
            "Receipt Generated",
            JOptionPane.INFORMATION_MESSAGE
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
            this,
            "Error generating receipt:\n"
            + e.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
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

        jPanel2 = new javax.swing.JPanel();
        salesLabel = new javax.swing.JTextField();
        JDate = new com.toedter.calendar.JDateChooser();
        JDateFrom = new com.toedter.calendar.JDateChooser();
        JDateTo = new com.toedter.calendar.JDateChooser();
        salesLabel2 = new javax.swing.JTextField();
        generate = new javax.swing.JButton();
        printRP = new javax.swing.JButton();
        orderIDField = new javax.swing.JTextField();
        customerNameField = new javax.swing.JTextField();
        dateField = new javax.swing.JTextField();
        serviceField = new javax.swing.JTextField();
        quantityField = new javax.swing.JTextField();
        totalField = new javax.swing.JTextField();
        paymentMethodField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        salesLabel.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jPanel2.add(salesLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 143, 270, 70));
        jPanel2.add(JDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 57, -1, -1));
        jPanel2.add(JDateFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 311, 143, -1));
        jPanel2.add(JDateTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 345, 143, -1));

        salesLabel2.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jPanel2.add(salesLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 385, 270, 70));

        generate.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        generate.setText("Generate");
        generate.addActionListener(this::generateActionPerformed);
        jPanel2.add(generate, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 467, 158, 38));

        printRP.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        printRP.setText("PRINT RECEIPT");
        printRP.addActionListener(this::printRPActionPerformed);
        jPanel2.add(printRP, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 390, 170, 50));

        orderIDField.addActionListener(this::orderIDFieldActionPerformed);
        jPanel2.add(orderIDField, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, 110, -1));
        jPanel2.add(customerNameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 130, 110, -1));

        dateField.addActionListener(this::dateFieldActionPerformed);
        jPanel2.add(dateField, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, 110, -1));
        jPanel2.add(serviceField, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 190, 110, -1));
        jPanel2.add(quantityField, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 220, 110, -1));
        jPanel2.add(totalField, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 250, 110, -1));
        jPanel2.add(paymentMethodField, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, 110, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void generateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateActionPerformed
        Date fromDate = JDateFrom.getDate();
Date toDate = JDateTo.getDate();

if (fromDate == null || toDate == null) {
    JOptionPane.showMessageDialog(this,
            "Please select both dates.");
    return;
}

LocalDate from = fromDate.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate();

LocalDate to = toDate.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate();

if (from.isAfter(to)) {
    JOptionPane.showMessageDialog(this,
            "From date cannot be after To date.");
    return;
}
double sales = 0;

LocalDate current = from;

while (!current.isAfter(to)) {

    if (current.equals(LocalDate.of(2026, 9, 1))) {
        sales += 1000;
    }

    if (current.equals(LocalDate.of(2026, 9, 15))) {
        sales += 1500;
    }

    if (current.equals(LocalDate.of(2026, 9, 30))) {
        sales += 3200;
    }

    if (current.equals(LocalDate.of(2026, 10, 1))) {
        sales += 800;
    }

    current = current.plusDays(1);
}

salesLabel2.setText(String.format("₱%.2f", sales));
    }//GEN-LAST:event_generateActionPerformed

    private void printRPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printRPActionPerformed
        generateReceiptPDF();
    }//GEN-LAST:event_printRPActionPerformed

    private void orderIDFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderIDFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_orderIDFieldActionPerformed

    private void dateFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateFieldActionPerformed

    /**
     * @param args the command line arguments
     */
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new COPType().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser JDate;
    private com.toedter.calendar.JDateChooser JDateFrom;
    private com.toedter.calendar.JDateChooser JDateTo;
    private javax.swing.JTextField customerNameField;
    private javax.swing.JTextField dateField;
    private javax.swing.JButton generate;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField orderIDField;
    private javax.swing.JTextField paymentMethodField;
    private javax.swing.JButton printRP;
    private javax.swing.JTextField quantityField;
    private javax.swing.JTextField salesLabel;
    private javax.swing.JTextField salesLabel2;
    private javax.swing.JTextField serviceField;
    private javax.swing.JTextField totalField;
    // End of variables declaration//GEN-END:variables
}
