/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inventariojava;

import static inventariojava.Configuracionserver.sucursalglobal;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author AGONZALEZ
 */
public class ImprimirReporte extends javax.swing.JFrame {

    Statement st;
    PreparedStatement ps = null;
    ResultSet rs;

    GregorianCalendar gg = new GregorianCalendar();

    /**
     * Creates new form ImprimirReporte
     */
    public ImprimirReporte() {
        initComponents();
           setIconImage(new ImageIcon(getClass().getResource("/recursos/logo.png")).getImage());
     //   JBGON.setSelected(true);

        SimpleDateFormat dd = new SimpleDateFormat("yyyy/MM/dd");
        String fechadisplay = dd.format(gg.getTime());
        txtfecha.setDate(gg.getTime());

    }
/////
    
    
    
  /////  
  

    public void Imprimirdia(){
    
         //  System.out.println(System.getProperty("user.name")); 
              String user = System.getProperty("user.name");
              // File archivo = new File("C:\\Users\\" + user + "\\Desktop\\LEEME.txt");
      try { 
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
          String F = sdf.format(txtfecha.getDate());
          String G = txtgondola.getText();
          pruebaconexion con = new pruebaconexion();
          Connection conn = con.Conectar();
          System.out.println("GONDOLA>" + G + "FECHA>>>" + F);
          JasperReport reporte = null;
          Map parametro = new HashMap(); // MAPEO DE MAPA TIPO HASH
          parametro.put("jtxt_fecha", "'" +F+ "'");
          parametro.put("jtxt_gondola",G);          
          String path = "C:\\Program Files\\TomaInventario\\src\\reportes/Dia.jasper";
       //   String path = "C:\\Users\\"+user+"\\Documents\\NetBeansProjects\\Inventario\\src\\reportes\\Dia.jasper";
          reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
          JasperPrint jprint = JasperFillManager.fillReport(reporte, parametro, conn);
          JasperViewer view = new JasperViewer(jprint, false);
          view.setTitle("REPORTE GONDOLA");
          view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          view.setVisible(true); 
          this.dispose();
        } catch (JRException ex) {
            Logger.getLogger(ImprimirReporte.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null, ">>"+ex);
            
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        btngenerar = new javax.swing.JToggleButton();
        txtfecha = new com.toedter.calendar.JDateChooser();
        txtgondola = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnregresar = new javax.swing.JToggleButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Sistema de inventario - Imprimir Gondola");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Castellar", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("IMPRIMIR GONDOLA");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 310, 36));

        btngenerar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btngenerar.setForeground(new java.awt.Color(0, 102, 51));
        btngenerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/impresora.png"))); // NOI18N
        btngenerar.setText("GENERAR");
        btngenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngenerarActionPerformed(evt);
            }
        });
        getContentPane().add(btngenerar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, 210, 70));

        txtfecha.setDateFormatString("yyyy/MM/dd");
        txtfecha.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtfecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtfechaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtfechaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfechaKeyTyped(evt);
            }
        });
        getContentPane().add(txtfecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 290, 50));

        txtgondola.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtgondola.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtgondolaActionPerformed(evt);
            }
        });
        txtgondola.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtgondolaKeyPressed(evt);
            }
        });
        getContentPane().add(txtgondola, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 290, 48));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("GONDOLA:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 100, 51));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("FECHA:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 98, 58));

        jLabel2.setText(" ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 330, -1, -1));

        btnregresar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnregresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/volver.png"))); // NOI18N
        btnregresar.setText("REGRESAR");
        btnregresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregresarActionPerformed(evt);
            }
        });
        getContentPane().add(btnregresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 190, 70));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 330, 20, 60));

        jLabel8.setText(" ");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 150, 30, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btngenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngenerarActionPerformed
 Imprimirdia();  
    }//GEN-LAST:event_btngenerarActionPerformed

    private void txtfechaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfechaKeyReleased
      /*  // TODO add your handling code here:
        String jc = txtfecha.getDateFormatString().toString();
        System.out.println("mames"+jc);
        btngenerar.setEnabled(
            jc.length() != 0
        );*/
    }//GEN-LAST:event_txtfechaKeyReleased

    private void txtfechaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfechaKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_txtfechaKeyTyped

    private void btnregresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregresarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnregresarActionPerformed

    private void txtgondolaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtgondolaKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Imprimirdia();
        }
    }//GEN-LAST:event_txtgondolaKeyPressed

    private void txtfechaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfechaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtfecha.requestFocus();
        }
    }//GEN-LAST:event_txtfechaKeyPressed

    private void txtgondolaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtgondolaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtgondolaActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ImprimirReporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ImprimirReporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ImprimirReporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ImprimirReporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ImprimirReporte().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btngenerar;
    private javax.swing.JToggleButton btnregresar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private com.toedter.calendar.JDateChooser txtfecha;
    private javax.swing.JTextField txtgondola;
    // End of variables declaration//GEN-END:variables
}
