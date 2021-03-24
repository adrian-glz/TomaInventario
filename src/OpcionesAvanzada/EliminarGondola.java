/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OpcionesAvanzada;

import static OpcionesAvanzada.AccesoAvanzado.husuario;
import static inventariojava.Configuracionserver.sucursalglobal;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author AGONZALEZ
 */
public class EliminarGondola extends javax.swing.JFrame {

    Statement st;
    PreparedStatement ps = null;
    ResultSet rs;
    GregorianCalendar gg = new GregorianCalendar();
      
    
    /**
     * Creates new form EliminarGondola
     */
    public EliminarGondola() {
        initComponents();
        txt_gondola.requestFocusInWindow();
      
    }
    public void historialeliminado(){
     
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SS");
        LocalDateTime date = LocalDateTime.now();
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://" + sucursalglobal + "", "usounds", "madljda");
            Statement st = conexion.createStatement();
       ///     System.out.println(">>>>>xxxx" + txtgondola.getText().toUpperCase() + txtcantidad.getText().toUpperCase() + date);
            ps = conexion.prepareStatement("insert into HistorialInventoryAudit (responsable, transaccion, fechaeliminado) VALUES('" + husuario + "','ELIMINO GONDOLA: "+txt_gondola.getText().trim()+"'"+",getdate())");
            System.out.println("insert into HistorialInventoryAudit (responsable, transaccion, fechaeliminado) VALUES('" + husuario + "','ELIMINO GONDOLA: "+txt_gondola.getText().trim()+"'"+",getdate())");
            int n = ps.executeUpdate();
            if (n > 0) {
            ///  JOptionPane.showMessageDialog(null, "¡Los datos han grabados en hostirial de auditoria!");             
            }
        } catch (HeadlessException | SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error en la base de datos historial eliminado");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EliminarGondola.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
    
     public void eliminarcodigo(){
     SimpleDateFormat dd = new SimpleDateFormat("yyyy/MM/dd");
        String fechadisplay = dd.format(gg.getTime());    
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://" +sucursalglobal+"", "usounds", "madljda");
            Statement st = conexion.createStatement();
          
            String query = "delete from inventoryaudit where gondola= '" + txt_gondola.getText() + "' and fecha>='"+fechadisplay+"' ";          
            System.out.println("delete from inventoryaudit where gondola= '" + txt_gondola.getText() + "' and fecha>='"+fechadisplay+"' ");
            
            ps = conexion.prepareStatement(query);
            int n = ps.executeUpdate();

            if (n > 0) {
                JOptionPane.showMessageDialog(null, "¡Se elimino la gondola : " + txt_gondola.getText());
                st.close();
                historialeliminado();
            }
        } catch (HeadlessException | SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error en la base de datos");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EliminarGondola.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  public void existecodigo() {
     SimpleDateFormat dd = new SimpleDateFormat("yyyy/MM/dd");
        String fechadisplay = dd.format(gg.getTime());
        try {
        //    System.out.println("**>>>>"+fechadisplay);
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://"+sucursalglobal+"", "usounds", "madljda");
            st = conexion.createStatement();
          
            rs = st.executeQuery("select  gondola from inventoryaudit "
                    + "where gondola='"+txt_gondola.getText() +"' and fecha>= '"+fechadisplay+"'    ");
             boolean variable = rs.next();
      String s1 = Boolean.toString(variable);
            try {
                if (s1.equals("false")) {
                    while (rs.next()) {///
                    }
                    JOptionPane.showMessageDialog(null, "La gondola: " + txt_gondola.getText() + " No existe o esta mal escrito", "Alerta", JOptionPane.WARNING_MESSAGE);
                } else {
                    int result = JOptionPane.showConfirmDialog(null, "ELIMINAR LA GONDOLA  '" + txt_gondola.getText() + "' ?  \n Una vez eliminado no se puede recuperar el registro  de ninguna manera  NI POR SISTEMAS ", "ATENCION",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                           eliminarcodigo();
                    } else if (result == JOptionPane.NO_OPTION) {
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EliminarGondola.class.getName()).log(Level.SEVERE, null, ex);
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

        lblgondola = new javax.swing.JLabel();
        txt_gondola = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        lblgondola1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnregresar2 = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblgondola.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lblgondola.setText("Inserte el Nombre de la gondola:");
        getContentPane().add(lblgondola, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 69, 225, 35));

        txt_gondola.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_gondolaKeyPressed(evt);
            }
        });
        getContentPane().add(txt_gondola, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 70, 223, 35));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/HELP2.png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(538, 0, 73, 63));

        lblgondola1.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        lblgondola1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblgondola1.setText("Eliminar Gondola");
        getContentPane().add(lblgondola1, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 11, 225, 35));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 270, 20));

        btnregresar2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnregresar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/volver.png"))); // NOI18N
        btnregresar2.setText("Regresar");
        btnregresar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregresar2ActionPerformed(evt);
            }
        });
        getContentPane().add(btnregresar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 240, 60));

        btneliminar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btneliminar.setForeground(new java.awt.Color(255, 0, 0));
        btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Eliminar.png"))); // NOI18N
        btneliminar.setText("Eliminar");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btneliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, 250, 60));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
  JOptionPane.showMessageDialog(null, "ATENCION!!!!!!! \n" 
  +        "Asegurese de ingresar correctamente el nombre de la gondola a eliminar \n"
          + "Una vez eliminada no se puede recuperar de ninguna manera"
  );  
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnregresar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregresar2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
         
    }//GEN-LAST:event_btnregresar2ActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        // TODO add your handling code here:

        existecodigo();

    }//GEN-LAST:event_btneliminarActionPerformed

    private void txt_gondolaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_gondolaKeyPressed
  if (evt.getKeyCode() == KeyEvent.VK_ENTER) {     
      existecodigo();
  }
    }//GEN-LAST:event_txt_gondolaKeyPressed

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
                if ("windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EliminarGondola.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EliminarGondola.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EliminarGondola.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EliminarGondola.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EliminarGondola().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnregresar2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblgondola;
    private javax.swing.JLabel lblgondola1;
    private javax.swing.JTextField txt_gondola;
    // End of variables declaration//GEN-END:variables
}
