/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventariojava;
import static inventariojava.BuscarDescripcion.coddescripciontempregreso;
import static inventariojava.Configuracionserver.sucursalglobal;
 
import java.awt.Color;
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
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AGONZALEZ
 */
public class CapturaInventario extends javax.swing.JFrame {

    Statement st;
    PreparedStatement ps = null;
    ResultSet rs, rs2;

    public static String coddescripciontemp = "";
    public static String gondolatemp = "";
  
       public void limpiarventanas() {
           
        txtcodigo.setText("");
        lblresultado.setText("");
        txtcantidad.setText("0");
        txtcodigo.requestFocusInWindow();

    }

    public void eventograbar() {
        int cantidad = Integer.parseInt(txtcantidad.getText());
        int gondola = txtgondola.getText().length();
        int codigot = (txtcodigo.getText().length());
        //  System.out.println(cantidad+gondola+codigo);
        if (cantidad != 0 & gondola > 1 & codigot > 1) {
            grabarcodigo();
        } else {
            JOptionPane.showMessageDialog(null, "REVISA LOS DATOS CANTINDAD, GONDOLA, CODIGO");
         }

    }

    public void grabarcodigo() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SS");
        LocalDateTime date = LocalDateTime.now();
        System.out.println(dtf.format(date));
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://" + sucursalglobal + "", "usounds", "madljda");
            Statement st = conexion.createStatement();
            

      //      System.out.println(">>>>>xxxx" + txtgondola.getText().toUpperCase() + txtcantidad.getText().toUpperCase() + date);
            ps = conexion.prepareStatement("insert into inventoryaudit (codigo, gondola, cantidad, fecha) VALUES('" + codigo + "',"
                    + "'" + txtgondola.getText().toUpperCase() + "','" + txtcantidad.getText().toUpperCase() + "','" + date + "');");
            int n = ps.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "¡Los datos han sido guardados exitósamente!");
                limpiarventanas();
            }
        } catch (HeadlessException | SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error en la base de datos");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CapturaInventario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void validarcodigo() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://" + sucursalglobal + "", "usounds", "madljda");
            st = conexion.createStatement();
         
            rs = st.executeQuery("select codigo, descripcion, grupo, precioventa from codigos where codigo='" + txtcodigo.getText() + "'  or codigo2='" + txtcodigo.getText() + "'     ");
            try {
                while (rs.next()) {

                    Object[] fila = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)};
                    lblresultado.setText("CODIGO:" + rs.getString(1) + "\n" + "DESCRIPCION:" + rs.getString(2) + "\n" + "GRUPO:" + rs.getString(3) + "\n" + "PRECIOVENTA:" + rs.getString(4));
                    codigo = rs.getString(1);
                    descripcion = rs.getString(2);
                    grupo = rs.getString(3);
                    precioventa = rs.getString(4);
                }
                txtcantidad.requestFocus();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CapturaInventario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void buscarcodigo() {////evento para buscar codigo
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://" + sucursalglobal + "", "usounds", "madljda");
            st = conexion.createStatement();
            
            rs = st.executeQuery("select codigo, descripcion, grupo, precioventa from codigos where codigo='" + txtcodigo.getText() + "' or codigo2='" + txtcodigo.getText() + "'");
      //  System.out.println(">>>"+sucursalglobal);

            boolean friv = rs.next();
            String s1 = Boolean.toString(friv);
            try {
                if (s1.equals("true")) {
                    validarcodigo();
                    while (rs.next()) {
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "UPPS!!  el codigo ----> " + txtcodigo.getText() + "  no existe o esta incorrecto ", "Alerta", JOptionPane.WARNING_MESSAGE);
                    txtcodigo.setText("");
                    lblresultado.setText("");
                    txtcodigo.requestFocus();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CapturaInventario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public CapturaInventario() {
        initComponents();
        System.out.println(">>"+coddescripciontempregreso+"<<");
        if (coddescripciontempregreso.equals("")) {///cuando esta vacia la variable

            lblresultado.setEditable(false);
          //  btngrabar.setEnabled(false);
            GregorianCalendar gg = new GregorianCalendar();
            SimpleDateFormat dd = new SimpleDateFormat("yyyy/MM/dd");
            String fechadisplay = dd.format(gg.getTime());
            txtfecha.setText(fechadisplay);
            txtcantidad.setHorizontalAlignment(txtcantidad.RIGHT);
             txtgondola.setText(gondolatemp);
             
        } else {                                    //cuando esta llena
            txtcodigo.setText(coddescripciontempregreso);
            lblresultado.setEditable(false);
          //  btngrabar.setEnabled(false);
            GregorianCalendar gg = new GregorianCalendar();
            SimpleDateFormat dd = new SimpleDateFormat("yyyy/MM/dd");
            String fechadisplay = dd.format(gg.getTime());
            txtfecha.setText(fechadisplay);
            txtcantidad.setHorizontalAlignment(txtcantidad.RIGHT);
            txtgondola.setText(gondolatemp);
            txtcodigo.requestFocusInWindow();
            buscarcodigo();
        }
    }
    String codigo = "", descripcion = "", grupo = "", precioventa = "";

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtcodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnbuscar = new javax.swing.JButton();
        btnborrarcodigo = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnvolver = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtgondola = new javax.swing.JTextField();
        btnborrarcantidad = new javax.swing.JButton();
        btngrabar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lblresultado = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        txtfecha = new javax.swing.JLabel();
        txtcantidad = new javax.swing.JTextField();
        vercapturados = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sistema de inventario - Captura Inventario");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtcodigo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtcodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcodigoKeyPressed(evt);
            }
        });
        getContentPane().add(txtcodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 650, 40));

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel2.setText("CODIGO:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 100, 30));

        btnbuscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnbuscar.setText("BUSCAR");
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btnbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 190, 57));

        btnborrarcodigo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnborrarcodigo.setText("LIMPIAR");
        btnborrarcodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnborrarcodigoActionPerformed(evt);
            }
        });
        getContentPane().add(btnborrarcodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, 210, 57));

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel3.setText("CANTIDAD:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 110, 42));

        btnvolver.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnvolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/volver.png"))); // NOI18N
        btnvolver.setText("VOLVER");
        btnvolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvolverActionPerformed(evt);
            }
        });
        getContentPane().add(btnvolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 200, 60));

        jLabel4.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel4.setText("GONDOLA");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 120, 42));

        txtgondola.setFont(new java.awt.Font("Castellar", 1, 18)); // NOI18N
        txtgondola.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtgondolaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtgondolaKeyReleased(evt);
            }
        });
        getContentPane().add(txtgondola, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 140, 40));

        btnborrarcantidad.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnborrarcantidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/limpiar.png"))); // NOI18N
        btnborrarcantidad.setText("LIMPIAR");
        btnborrarcantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnborrarcantidadActionPerformed(evt);
            }
        });
        getContentPane().add(btnborrarcantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 410, 260, 58));

        btngrabar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btngrabar.setForeground(new java.awt.Color(0, 150, 52));
        btngrabar.setText("GRABAR");
        btngrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngrabarActionPerformed(evt);
            }
        });
        getContentPane().add(btngrabar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 410, 180, 58));

        lblresultado.setColumns(20);
        lblresultado.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        lblresultado.setRows(5);
        lblresultado.setFocusable(false);
        jScrollPane1.setViewportView(lblresultado);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 650, 140));

        jLabel6.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel6.setText("FECHA:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 82, 45));

        txtfecha.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        getContentPane().add(txtfecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 154, 45));

        txtcantidad.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        txtcantidad.setText("0");
        txtcantidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtcantidadFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtcantidadFocusLost(evt);
            }
        });
        txtcantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcantidadActionPerformed(evt);
            }
        });
        txtcantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcantidadKeyPressed(evt);
            }
        });
        getContentPane().add(txtcantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 360, 118, 40));

        vercapturados.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        vercapturados.setText("VER CAPTURADOS");
        vercapturados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vercapturadosActionPerformed(evt);
            }
        });
        getContentPane().add(vercapturados, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, 200, 57));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 470, 160, 30));

        jLabel5.setText(" ");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 200, 10, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnborrarcantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnborrarcantidadActionPerformed
       txtcantidad.setText("0");
        lblresultado.setText("");
    }//GEN-LAST:event_btnborrarcantidadActionPerformed

    private void btnvolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvolverActionPerformed
   this.dispose();
    }//GEN-LAST:event_btnvolverActionPerformed

    private void btnborrarcodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnborrarcodigoActionPerformed
         txtcodigo.setText("");
    }//GEN-LAST:event_btnborrarcodigoActionPerformed

    private void txtgondolaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtgondolaKeyReleased
       
    }//GEN-LAST:event_txtgondolaKeyReleased

    private void btngrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngrabarActionPerformed
        eventograbar();
    }//GEN-LAST:event_btngrabarActionPerformed

    private void txtcodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodigoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            buscarcodigo();
        }
    }//GEN-LAST:event_txtcodigoKeyPressed

    private void txtgondolaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtgondolaKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {           
            txtcodigo.requestFocus();
        }
    }//GEN-LAST:event_txtgondolaKeyPressed

    private void txtcantidadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcantidadFocusGained
        if (txtcantidad.getText().trim().toLowerCase().equals("0")) {
            txtcantidad.setText("");
            txtcantidad.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_txtcantidadFocusGained

    private void txtcantidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcantidadFocusLost
         if (txtcantidad.getText().trim().equals("") || txtcantidad.getText().trim().toLowerCase().equals("0")) {
            txtcantidad.setText("0"); 
        }
    }//GEN-LAST:event_txtcantidadFocusLost

    private void txtcantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcantidadKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            eventograbar();

        }
    }//GEN-LAST:event_txtcantidadKeyPressed

    private void vercapturadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vercapturadosActionPerformed
        HistorialCaptura his = new HistorialCaptura();
        his.setVisible(true);
    }//GEN-LAST:event_vercapturadosActionPerformed

    private void txtcantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcantidadActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        int l = txtcodigo.getText().length();
        if (l < 2) {
            JOptionPane.showMessageDialog(null, "LA CAJA DE CODIGO ESTA VACIA, TECLEA UN CODIGO : ");
        } else {
            coddescripciontemp = txtcodigo.getText().trim();
            gondolatemp = txtgondola.getText().trim();
            BuscarDescripcion m = new BuscarDescripcion();
            m.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnbuscarActionPerformed

    public static void main(String args[]) {
     
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {               
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CapturaInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CapturaInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CapturaInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CapturaInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CapturaInventario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnborrarcantidad;
    private javax.swing.JButton btnborrarcodigo;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btngrabar;
    private javax.swing.JButton btnvolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea lblresultado;
    private javax.swing.JTextField txtcantidad;
    private javax.swing.JTextField txtcodigo;
    private javax.swing.JLabel txtfecha;
    private javax.swing.JTextField txtgondola;
    private javax.swing.JButton vercapturados;
    // End of variables declaration//GEN-END:variables
}
