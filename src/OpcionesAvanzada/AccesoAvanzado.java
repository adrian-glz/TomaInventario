/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OpcionesAvanzada;
import inventariojava.*;
import static inventariojava.Configuracionserver.sucursalglobal;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author AGONZALEZ
 */
public class AccesoAvanzado extends javax.swing.JFrame {
public static String husuario="";
    /**
     * Creates new form Acceso
     */
    public AccesoAvanzado() {
        initComponents();
           setIconImage(new ImageIcon(getClass().getResource("/recursos/logo.png")).getImage());
    }
    
    public void login(){
       
        String user = txtusuario.getText().trim();   //Tomar el contenido de textField
        String pass = new String(txtcontraseña.getPassword()).trim();  //Tomar el contenido de password
        if ((user.isEmpty()) || (pass.isEmpty())) {   //Checar que no estén vacíos
            JOptionPane.showMessageDialog(null, "Ingrese su nombre de usuario y contraseña");
        } else {
            try {
                //Conexión a la BD   
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                java.sql.Connection conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://" + sucursalglobal + "", "usounds", "madljda");
               // System.out.println(">>>>>>>"+sucursalglobal);
                Statement st = conexion.createStatement();
                st.executeUpdate("USE Accesos;");
                //Seleccionar datos
                ResultSet rs = st.executeQuery("SELECT * FROM usuarios  WHERE usuario ='" + txtusuario.getText() + "';");
                if (rs.next()) {
                    //Si existe el usuario
                    if (pass.equals(rs.getString("Password").trim())) {   //comparando campo password
                        
                        this.dispose();
                        Avanzadas av = new Avanzadas();
                        husuario = txtusuario.getText().trim();
                        av.setVisible(true);
                        

                    } else {
                        JOptionPane.showMessageDialog(this, "Contraseña incorrecta");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "El usuario no existe");
                }
            } catch (HeadlessException | SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, "Error en la base de datos");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AccesoAvanzado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }}
    
    
    
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        txtusuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btncancelar = new javax.swing.JButton();
        btnentrar = new javax.swing.JButton();
        txtcontraseña = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();

        jMenuItem1.setText("jMenuItem1");

        setTitle("Sistema de inventario - Validar Acceso");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("USUARIO");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 145, 119, -1));

        txtusuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtusuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtusuarioKeyPressed(evt);
            }
        });
        getContentPane().add(txtusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 185, 400, 48));

        jLabel2.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("CONTRASEÑA");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 251, 179, -1));

        btncancelar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btncancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/volver.png"))); // NOI18N
        btncancelar.setText("VOLVER");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btncancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 345, 199, 55));

        btnentrar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnentrar.setForeground(new java.awt.Color(0, 204, 0));
        btnentrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Entrar2.png"))); // NOI18N
        btnentrar.setText("ACCEDER");
        btnentrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnentrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnentrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 345, 183, 55));

        txtcontraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcontraseñaKeyPressed(evt);
            }
        });
        getContentPane().add(txtcontraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 291, 400, 48));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/user.png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 6, 155, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnentrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnentrarActionPerformed
        // TODO add your handling code here:
        login();
    }//GEN-LAST:event_btnentrarActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        // TODO add your handling code here:
     //   Menu M = new Menu();
       // M.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_btncancelarActionPerformed

    private void txtusuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtusuarioKeyPressed
               if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           txtcontraseña.requestFocus();
            
        }
    }//GEN-LAST:event_txtusuarioKeyPressed

    private void txtcontraseñaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcontraseñaKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           
            login();
        }
              
    }//GEN-LAST:event_txtcontraseñaKeyPressed

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
            java.util.logging.Logger.getLogger(AccesoAvanzado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AccesoAvanzado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AccesoAvanzado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AccesoAvanzado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AccesoAvanzado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnentrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPasswordField txtcontraseña;
    private javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables
}
