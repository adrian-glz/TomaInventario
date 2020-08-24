/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventariojava;

import static inventariojava.Configuracionserver.sucursalglobal;
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

/**
 *
 * @author AGONZALEZ
 */
public class HistorialCaptura extends javax.swing.JFrame {

    /**
     * Creates new form HistorialCaptura
     */
    Statement st;
    ResultSet rs;
    DefaultTableModel md;
    PreparedStatement ps = null;
    GregorianCalendar gg = new GregorianCalendar();

    public HistorialCaptura() {

        initComponents();//INICIAN COMPONENTES 
        llenarcombobox();
        tblhistorial.getTableHeader().setReorderingAllowed(false);
        setIconImage(new ImageIcon(getClass().getResource("/recursos/logo.png")).getImage());
        String data[][] = {};
        String cabeza[] = {"CODIGO", "DESCRIPCION", "CANTIDAD", "GONDOLA", "FECHA"};

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

        tblhistorial.getColumnModel().getColumn(0).setPreferredWidth(100); //1
        tblhistorial.getColumnModel().getColumn(0).setMaxWidth(250);
        tblhistorial.getColumnModel().getColumn(0).setMinWidth(100);

        tblhistorial.getColumnModel().getColumn(1).setPreferredWidth(300); //2
        tblhistorial.getColumnModel().getColumn(1).setMaxWidth(750);
        tblhistorial.getColumnModel().getColumn(1).setMinWidth(300);

        tblhistorial.getColumnModel().getColumn(2).setPreferredWidth(80); //3
        tblhistorial.getColumnModel().getColumn(2).setMaxWidth(250);
        tblhistorial.getColumnModel().getColumn(2).setMinWidth(80);

        tblhistorial.getColumnModel().getColumn(3).setPreferredWidth(80); //4
        tblhistorial.getColumnModel().getColumn(3).setMaxWidth(250);
        tblhistorial.getColumnModel().getColumn(3).setMinWidth(80);

        tblhistorial.getColumnModel().getColumn(4).setPreferredWidth(250); //5
        tblhistorial.getColumnModel().getColumn(4).setMaxWidth(500);
        tblhistorial.getColumnModel().getColumn(4).setMinWidth(250);
        llenartablahistorial();
    }

    public void filtrartablahistorial() {

        GregorianCalendar gg = new GregorianCalendar();
        SimpleDateFormat dd = new SimpleDateFormat("yyyy/MM/dd");
        String fechadisplay = dd.format(gg.getTime());
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://" + sucursalglobal + "", "usounds", "madljda");
            st = conexion.createStatement();
            
            //  JOptionPane.showMessageDialog(rootPane, "PRUEBA FECHA"+pruebafecha);
            //Seleccionar datos      rs = st.executeQuery("select caja,folio,codigo,precioventaneto ,(select descripcion from codigos where codigo=ventas.codigo)as descrip,(select nombrec from empleados where empleado=ventas.cajero)as cajero from ventas where fecha='"+pruebafecha+"' and sucursal='"+SUCURSAL+"' and folio='" + folio + "' and caja='" + caja + "'; ");

            rs = st.executeQuery("select codigo,(select descripcion from codigos where codigo=inventoryaudit.codigo)as descripcioncodigo,cantidad,gondola,fecha "
                    + "from InventoryAudit  where  fecha>=' " + fechadisplay + " ' and gondola='" + cbgondolas.getSelectedItem().toString().trim() + "'  order by fecha ASC  ");

       //     System.out.println("select codigo,(select descripcion from codigos where codigo=inventoryaudit.codigo)as descripcioncodigo,cantidad,gondola,fecha "
            //          + "from InventoryAudit  where  fecha>=' "+ fechadisplay + " ' and gondola='"+cbgondolas.getSelectedItem().toString().trim()+"'  order by fecha ASC ");
            md = (DefaultTableModel) tblhistorial.getModel();
            md.setRowCount(0);
            try {

                while (rs.next()) {

                    Object[] fila = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)};
                    md.addRow(fila);
                   // tblhistorial.setRowColour(2, Color.YELLOW);
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
            Logger.getLogger(HistorialCaptura.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void llenarcombobox() {
        SimpleDateFormat dd = new SimpleDateFormat("yyyy/MM/dd");
        String fecha = dd.format(gg.getTime());
        Statement st;
        PreparedStatement ps = null;
        ResultSet rs;
        cbgondolas.removeAllItems();
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://" + sucursalglobal, "usounds", "madljda");
            st = conexion.createStatement();
          
            rs = st.executeQuery("select distinct  gondola from inventoryaudit where fecha>='"+fecha+"'");

            while (rs.next()) {
                String elemento = rs.getString(1);
                //System.out.println("elemento: " + elemento);
                cbgondolas.addItem(elemento);
            }
            st.close();
        } catch (Exception e) {
            System.out.println("ERROR: failed to load HSQLDB JDBC driver.");

            e.printStackTrace();
            return;
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
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        BTNSALIR = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        cbgondolas = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Sistema de inventario - Historial de Captura");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblhistorial.setAutoCreateRowSorter(true);
        tblhistorial.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblhistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblhistorial.setRowHeight(40);
        jScrollPane1.setViewportView(tblhistorial);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 800, 350));

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("HISTORIAL");
        jLabel3.setToolTipText("");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 210, 38));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 140, 110, 40));

        BTNSALIR.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BTNSALIR.setText("CERRAR");
        BTNSALIR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNSALIRActionPerformed(evt);
            }
        });
        getContentPane().add(BTNSALIR, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 430, 280, 70));

        jLabel2.setText(" ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 520, 300, 30));

        jButton1.setText("IMPRIMIR GONDOLA");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 300, 70));

        getContentPane().add(cbgondolas, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 190, 40));

        jButton2.setText("FILTRAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 140, 40));

        jButton3.setText("MOSTRAR TODO");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 20, 200, 40));

        jLabel4.setText(" ");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 210, 10, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BTNSALIRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNSALIRActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_BTNSALIRActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ImprimirReporte ir = new ImprimirReporte();
        ir.setVisible(true);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         llenartablahistorial();
         llenarcombobox();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       filtrartablahistorial();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
     public void llenartablahistorial() {
         
        GregorianCalendar gg = new GregorianCalendar();
        SimpleDateFormat dd = new SimpleDateFormat("yyyy/MM/dd");
        String fechadisplay = dd.format(gg.getTime());
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://" + sucursalglobal + "", "usounds", "madljda");
            st = conexion.createStatement();
           
            //  JOptionPane.showMessageDialog(rootPane, "PRUEBA FECHA"+pruebafecha);
            //Seleccionar datos      rs = st.executeQuery("select caja,folio,codigo,precioventaneto ,(select descripcion from codigos where codigo=ventas.codigo)as descrip,(select nombrec from empleados where empleado=ventas.cajero)as cajero from ventas where fecha='"+pruebafecha+"' and sucursal='"+SUCURSAL+"' and folio='" + folio + "' and caja='" + caja + "'; ");

            rs = st.executeQuery("select codigo,(select descripcion from codigos where codigo=inventoryaudit.codigo)as descripcioncodigo,cantidad,gondola,fecha "
                    + "from InventoryAudit  where  fecha>='" + fechadisplay + "'  order by fecha ASC ; ");
            System.out.println(fechadisplay);
            md = (DefaultTableModel) tblhistorial.getModel();
            md.setRowCount(0);
            try {

                while (rs.next()) {

                    Object[] fila = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)};
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
            Logger.getLogger(HistorialCaptura.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
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
            java.util.logging.Logger.getLogger(HistorialCaptura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HistorialCaptura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HistorialCaptura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HistorialCaptura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HistorialCaptura().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNSALIR;
    private javax.swing.JComboBox cbgondolas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tblhistorial;
    // End of variables declaration//GEN-END:variables
}
