/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventariojava;

import static inventariojava.Configuracionserver.sucursalglobal;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

/**
 *
 * @author AGONZALEZ
 */
public class Procesar extends javax.swing.JFrame {

    Statement st;
    PreparedStatement ps = null;
    ResultSet rs ;
     
    GregorianCalendar gg = new GregorianCalendar();

    public Procesar() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/recursos/logo.png")).getImage());
        SimpleDateFormat dd = new SimpleDateFormat("yyyy/MM/dd");
        String fechadisplay = dd.format(gg.getTime());
        JTFECHA.setDate(gg.getTime());

    }
    public void procesar() {
    //**************PROGRESSS BAR
        String cval;
        int nval;

        final JDialog dialog = new JDialog(Frame.getFrames()[0], "Sistema de Inventario", true);
        Thread runnable_progress = new Thread() {
            public void run() {
                JTextArea msgLabel;
                JProgressBar progressBar;
                final int MAXIMUM = 100;
                JPanel panel;
                progressBar = new JProgressBar(0, MAXIMUM);
                progressBar.setIndeterminate(true);
                msgLabel = new JTextArea("Procesando inventario. Por favor espere...");
                msgLabel.setEditable(false);
                panel = new JPanel(new BorderLayout(5, 5));
                panel.add(msgLabel, BorderLayout.PAGE_START);
                panel.add(progressBar, BorderLayout.CENTER);
                panel.setBorder(BorderFactory.createEmptyBorder(11, 11, 11, 11));
                dialog.getContentPane().add(panel);
                dialog.setResizable(false);
                dialog.pack();
                dialog.setSize(500, dialog.getHeight());
                dialog.setLocationRelativeTo(null);
                dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                dialog.setAlwaysOnTop(false);
                msgLabel.setBackground(panel.getBackground());
                ///////////////////////        
             
                int nren; 
        
        
        ///////////PROGRESSSBARRRR
        
        
        
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String F = sdf.format(JTFECHA.getDate());
                try {
                    Class.forName("net.sourceforge.jtds.jdbc.Driver");
                    java.sql.Connection conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://" + sucursalglobal + "", "usounds", "madljda");
                    st = conexion.createStatement();

                    rs = st.executeQuery("delete from invent; insert into invent (codigo, cantidad) "
                            + "select codigo, sum(cantidad) as cantidad from InventoryAudit where fecha >= '" + F + "' group by codigo; "
                            + "exec spp_cargaperiodosdosmeses; "
                            + "declare @tcv as float; "
                            + "set @tcv = (select TipoCambioVenta from infor); "
                            + "exec spp_ADiferenciasDeInventario @tcv");

                    int c = 0;
                    while (!(rs.isAfterLast())) {
                        System.out.println("##" + rs.next() + " >" + c);
                        c = c + 1;
                    }

                } catch (HeadlessException | NumberFormatException | SQLException e) {
                    String respuesta = "The executeQuery method must return a result set.";
            if (respuesta.equals(e.getMessage())) {
                progressBar.setIndeterminate(false);///PROGRESSBAR
                dialog.dispose();//PROGRESSBAR
                JOptionPane.showMessageDialog(null, "Se ha procesado de manera exitosa", "Informacion", JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(rootPane, "ERROR!!!!!!!" + e.getMessage());
            }
              
        } catch (ClassNotFoundException ex) {     
            Logger.getLogger(Procesar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
           nren=0;
           
                ///////////////////////
              
            }
        };
        ///PROGRESSBAR>
        runnable_progress.start();    
        dialog.setVisible(true);                  
//<PROGRESSBAR
     
    
 } 
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCalendar1 = new com.toedter.calendar.JCalendar();
        JTFECHA = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        btnprocesar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de inventario - Procesar inventario");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JTFECHA.setDateFormatString("yyyy/MM/dd");
        JTFECHA.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JTFECHA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTFECHAKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTFECHAKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTFECHAKeyTyped(evt);
            }
        });
        getContentPane().add(JTFECHA, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 62, 410, 58));

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("FECHA DE DIA A PROCESAR:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 410, 36));

        btnprocesar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnprocesar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/procesar_1.png"))); // NOI18N
        btnprocesar.setText("PROCESAR DIFERENCIAS");
        btnprocesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnprocesarActionPerformed(evt);
            }
        });
        getContentPane().add(btnprocesar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 157, 410, 58));

        btncancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btncancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/volver.png"))); // NOI18N
        btncancelar.setText("VOLVER");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btncancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 226, 410, 58));

        jLabel2.setText("  ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 280, 40, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnprocesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprocesarActionPerformed
         if (JOptionPane.showConfirmDialog(null, " Estas seguro de procesar con la fecha seleccionada ", " ATENCION!!! ",
        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
              // yes option
            System.out.println("si perro");
         procesar();
      
            } else {
                     System.out.println("nel perro");
            }

    }//GEN-LAST:event_btnprocesarActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
          this.dispose();

    }//GEN-LAST:event_btncancelarActionPerformed

    private void JTFECHAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTFECHAKeyReleased
          String jc = JTFECHA.getDateFormatString().toString();
          System.out.println("mames"+jc);
        btnprocesar.setEnabled(
                jc.length() != 0
        );
    }//GEN-LAST:event_JTFECHAKeyReleased

    private void JTFECHAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTFECHAKeyTyped
    
    }//GEN-LAST:event_JTFECHAKeyTyped

    private void JTFECHAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTFECHAKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (JOptionPane.showConfirmDialog(null, " Estas seguro de procesar con la fecha seleccionada ", " ATENCION!!! ",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                procesar();

            } else {

            }

        }
    }//GEN-LAST:event_JTFECHAKeyPressed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Procesar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Procesar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Procesar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Procesar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Procesar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser JTFECHA;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnprocesar;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
