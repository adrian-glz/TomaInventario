/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventariojava;

import static inventariojava.CapturaInventario.coddescripciontemp;
import static inventariojava.Configuracionserver.sucursalglobal;
import java.awt.HeadlessException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

 
public class BuscarDescripcion extends javax.swing.JFrame {

    Statement st;
    ResultSet rs;
    DefaultTableModel md;
    public static String coddescripciontempregreso = "";

 
    public BuscarDescripcion() {


        initComponents();//INICIAN COMPONENTES 
        codes.setText("'"+coddescripciontemp.trim()+"'");
        tblhistorial.getTableHeader().setReorderingAllowed(false);
        setIconImage(new ImageIcon(getClass().getResource("/recursos/logo.png")).getImage());
        String data[][] = {};
        String cabeza[] = { "DESCRIPCION","CODIGO", "CODIGO PROV", "CODIGO BARRAS"};

        md = new DefaultTableModel(data, cabeza) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column != 5) {
                    return false;
                } else {
                    return true;
                }
            }
        };
        tblhistorial.setModel(md);
        JTableHeader th;
        th = tblhistorial.getTableHeader();
        th.setFont(new java.awt.Font("Italic", 0, 14));

        //Centrar el encabezado de la tabla
        TableCellRenderer rendererFromHeader = tblhistorial.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);

        tblhistorial.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        tblhistorial.getColumnModel().getColumn(0).setPreferredWidth(450); //1
        tblhistorial.getColumnModel().getColumn(0).setMaxWidth(500);
        tblhistorial.getColumnModel().getColumn(0).setMinWidth(450);

        tblhistorial.getColumnModel().getColumn(1).setPreferredWidth(200); //2
        tblhistorial.getColumnModel().getColumn(1).setMaxWidth(200);
        tblhistorial.getColumnModel().getColumn(1).setMinWidth(200);

        tblhistorial.getColumnModel().getColumn(2).setPreferredWidth(200); //3
        tblhistorial.getColumnModel().getColumn(2).setMaxWidth(200);
        tblhistorial.getColumnModel().getColumn(2).setMinWidth(200);

        tblhistorial.getColumnModel().getColumn(3).setPreferredWidth(200); //4
        tblhistorial.getColumnModel().getColumn(3).setMaxWidth(200);
        tblhistorial.getColumnModel().getColumn(3).setMinWidth(200);

 
        llenartablahistorial();
    }
     
       public void llenartablahistorial() {
         
        GregorianCalendar gg = new GregorianCalendar();
        SimpleDateFormat dd = new SimpleDateFormat("yyyy/MM/dd");
        String fechadisplay = dd.format(gg.getTime());
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://" + sucursalglobal + "", "usounds", "madljda");
            st = conexion.createStatement();
        
       
         //   rs = st.executeQuery("select descripcion,codigo,codigoprov,codigo2  from codigos where descripcion LIKE '%"+coddescripciontemp+"%'");
            rs = st.executeQuery("select descripcion,codigo,codigoprov,codigo2  from codigos where descripcion LIKE '%"+coddescripciontemp+"%'  OR codigo LIKE '%"+coddescripciontemp+"%' OR codigo2 LIKE '%"+coddescripciontemp+"%' ");
            System.out.println(fechadisplay);
            md = (DefaultTableModel) tblhistorial.getModel();
            md.setRowCount(0);
            try {

                while (rs.next()) {

                    Object[] fila = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4) };
                    md.addRow(fila);
                }
                int CONT = md.getRowCount();
                if (CONT == 0) {
                    JOptionPane.showMessageDialog(null, "No se encontraron elementos");
                    this.dispose();
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BuscarDescripcion.class.getName()).log(Level.SEVERE, null, ex);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblhistorial = new javax.swing.JTable();
        codes = new javax.swing.JLabel();
        BTNSALIR = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        BTNSALIR1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblhistorial.setAutoCreateRowSorter(true);
        tblhistorial.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblhistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ERROR", "ERROR", "ERROR", "ERROR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblhistorial.setRowHeight(40);
        tblhistorial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblhistorialMouseClicked(evt);
            }
        });
        tblhistorial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblhistorialKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblhistorial);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 1040, 350));

        codes.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        codes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        codes.setText(" ");
        codes.setToolTipText("");
        codes.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        getContentPane().add(codes, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 320, 38));

        BTNSALIR.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BTNSALIR.setText("Elegir");
        BTNSALIR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNSALIRActionPerformed(evt);
            }
        });
        getContentPane().add(BTNSALIR, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 440, 280, 70));

        jLabel4.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Resultados por que buscaste  ");
        jLabel4.setToolTipText("");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 340, 38));

        BTNSALIR1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BTNSALIR1.setText("CERRAR");
        BTNSALIR1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNSALIR1ActionPerformed(evt);
            }
        });
        getContentPane().add(BTNSALIR1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 280, 70));

        jLabel1.setText(" ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 510, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BTNSALIRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNSALIRActionPerformed
        int filaseleccionada = tblhistorial.getSelectedRow();
        if (filaseleccionada >= 0) {
            String itemcodigo = (tblhistorial.getValueAt(filaseleccionada, 1).toString().trim());
        //    JOptionPane.showMessageDialog(null, "SELECCIONASTE: " + itemcodigo);
            coddescripciontempregreso = itemcodigo;
            CapturaInventario m = new CapturaInventario();
            m.setVisible(true);
            this.dispose();
            
        } else {
            JOptionPane.showMessageDialog(null, "NO SELECCIONASTE NINGUN CODIGO ");
        }
    }//GEN-LAST:event_BTNSALIRActionPerformed

    private void BTNSALIR1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNSALIR1ActionPerformed
        // TODO add your handling code here:
        CapturaInventario m= new CapturaInventario();
        m.setVisible(true);
            coddescripciontempregreso = "";
        this.dispose();
    }//GEN-LAST:event_BTNSALIR1ActionPerformed

    private void tblhistorialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblhistorialKeyPressed
         // TODO add your handling code here:
    }//GEN-LAST:event_tblhistorialKeyPressed

    private void tblhistorialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhistorialMouseClicked
        // TODO add your handling code here:
         if (evt.getClickCount() == 2) {
         int filaseleccionada = tblhistorial.getSelectedRow();
        if (filaseleccionada >= 0) {
            String itemcodigo = (tblhistorial.getValueAt(filaseleccionada, 1).toString().trim());
        //    JOptionPane.showMessageDialog(null, "SELECCIONASTE: " + itemcodigo);
            coddescripciontempregreso = itemcodigo;
            CapturaInventario m = new CapturaInventario();
            m.setVisible(true);
            this.dispose();
            
        } else {
            JOptionPane.showMessageDialog(null, "NO SELECCIONASTE NINGUN CODIGO ");
        }
        } 
    }//GEN-LAST:event_tblhistorialMouseClicked

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
            java.util.logging.Logger.getLogger(BuscarDescripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuscarDescripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuscarDescripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuscarDescripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BuscarDescripcion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNSALIR;
    private javax.swing.JButton BTNSALIR1;
    private javax.swing.JLabel codes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tblhistorial;
    // End of variables declaration//GEN-END:variables
}
