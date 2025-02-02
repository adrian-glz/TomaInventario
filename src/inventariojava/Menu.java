/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventariojava;

import OpcionesAvanzada.AccesoAvanzado;
import static inventariojava.BuscarDescripcion.coddescripciontempregreso;
import static inventariojava.CapturaInventario.gondolatemp;
import static inventariojava.Configuracionserver.sucursalglobal;
import static inventariojava.Configuracionserver.sucursalnombre;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author AGONZALEZ
 */
public class Menu extends javax.swing.JFrame {
 public static String coddescripciontemp="";

    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
           setIconImage(new ImageIcon(getClass().getResource("/recursos/logo.png")).getImage());
         Leer_fichero.leerconexion();
         
         lblsucursal.setText("SUCURSAL: "+sucursalnombre);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        btncapturar = new javax.swing.JButton();
        btnconsultar = new javax.swing.JButton();
        btnimprimir = new javax.swing.JButton();
        btnprocesardiferencia = new javax.swing.JButton();
        btncerrar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblsucursal = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Sistema de inventario - Menu de Seleccion");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 0, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("SELECCION DE MENU ");
        jLabel3.setToolTipText("");
        jLabel3.setFocusable(false);
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 250, 38));

        btncapturar.setFont(new java.awt.Font("Cambria", 1, 22)); // NOI18N
        btncapturar.setForeground(new java.awt.Color(255, 255, 255));
        btncapturar.setText("CAPTURAR");
        btncapturar.setContentAreaFilled(false);
        btncapturar.setFocusPainted(false);
        btncapturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncapturarActionPerformed(evt);
            }
        });
        getContentPane().add(btncapturar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 310, 50));

        btnconsultar.setFont(new java.awt.Font("Cambria", 1, 22)); // NOI18N
        btnconsultar.setForeground(new java.awt.Color(255, 255, 255));
        btnconsultar.setText("CONSULTAR");
        btnconsultar.setContentAreaFilled(false);
        btnconsultar.setFocusPainted(false);
        btnconsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconsultarActionPerformed(evt);
            }
        });
        getContentPane().add(btnconsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 230, 320, 50));

        btnimprimir.setFont(new java.awt.Font("Cambria", 1, 22)); // NOI18N
        btnimprimir.setForeground(new java.awt.Color(255, 255, 255));
        btnimprimir.setText("IMPRIMIR");
        btnimprimir.setContentAreaFilled(false);
        btnimprimir.setFocusPainted(false);
        btnimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimirActionPerformed(evt);
            }
        });
        getContentPane().add(btnimprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 310, 320, 50));

        btnprocesardiferencia.setFont(new java.awt.Font("Cambria", 1, 22)); // NOI18N
        btnprocesardiferencia.setForeground(new java.awt.Color(255, 255, 255));
        btnprocesardiferencia.setText("PROCESAR DIFERENCIAS");
        btnprocesardiferencia.setContentAreaFilled(false);
        btnprocesardiferencia.setFocusPainted(false);
        btnprocesardiferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnprocesardiferenciaActionPerformed(evt);
            }
        });
        getContentPane().add(btnprocesardiferencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 140, 300, 60));

        btncerrar.setFont(new java.awt.Font("Cambria", 1, 22)); // NOI18N
        btncerrar.setForeground(new java.awt.Color(255, 255, 255));
        btncerrar.setText("CERRAR");
        btncerrar.setContentAreaFilled(false);
        btncerrar.setFocusPainted(false);
        btncerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncerrarActionPerformed(evt);
            }
        });
        getContentPane().add(btncerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(296, 390, 320, 60));

        jLabel2.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jLabel2.setText("Ver 2.0.1");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 474, 100, 30));

        lblsucursal.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        lblsucursal.setForeground(new java.awt.Color(0, 0, 102));
        lblsucursal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblsucursal.setToolTipText("");
        getContentPane().add(lblsucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 540, 38));

        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 210, 210));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ta_1.png"))); // NOI18N
        jLabel4.setText(" ");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 660, 520));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btncapturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncapturarActionPerformed
        // TODO add your handling code here:
        if (sucursalglobal == "") {
            System.out.println(sucursalglobal);
            JOptionPane.showMessageDialog(null, "Conectate a una tienda para capturar: ", "ALERTA", JOptionPane.WARNING_MESSAGE);
        } else {
           // CapturarInventario2 ci = new CapturarInventario2();
           coddescripciontempregreso="";
            gondolatemp="";
            CapturaInventario ci = new CapturaInventario ();
            
            ci.setVisible(true);
           
        }

    }//GEN-LAST:event_btncapturarActionPerformed

    private void btnconsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconsultarActionPerformed
        // TODO add your handling code here:      
        
          if (sucursalglobal == "") {
            System.out.println(sucursalglobal);
            JOptionPane.showMessageDialog(null, "Conectate a una tienda para consultar: ", "ALERTA", JOptionPane.WARNING_MESSAGE);
        } else {
        ConsultaInventario ci = new ConsultaInventario();
        ci.setVisible(true);
          }
    }//GEN-LAST:event_btnconsultarActionPerformed

    private void btnprocesardiferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprocesardiferenciaActionPerformed
        // TODO add your handling code here:
      
        
           if (sucursalglobal == "") {
            System.out.println(sucursalglobal);
            JOptionPane.showMessageDialog(null, "Conectate a una tienda para procesa: ", "ALERTA", JOptionPane.WARNING_MESSAGE);
        } else {
        Acceso acc = new Acceso();
        acc.setVisible(true);
           }
        
    }//GEN-LAST:event_btnprocesardiferenciaActionPerformed

    private void btncerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncerrarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btncerrarActionPerformed

    private void btnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimirActionPerformed
        // TODO add your handling code here:
        ImprimirReporte ir = new ImprimirReporte();
        ir.setVisible(true);
        
        
    }//GEN-LAST:event_btnimprimirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       AccesoAvanzado a= new AccesoAvanzado();
       a.setVisible(true);
        
        
        
     /*   Avanzadas av = new Avanzadas();
        av.setVisible(true);*/
         
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncapturar;
    private javax.swing.JButton btncerrar;
    private javax.swing.JButton btnconsultar;
    private javax.swing.JButton btnimprimir;
    private javax.swing.JButton btnprocesardiferencia;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblsucursal;
    // End of variables declaration//GEN-END:variables
}
