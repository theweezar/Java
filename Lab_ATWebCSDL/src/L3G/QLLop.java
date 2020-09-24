/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package L3G;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hpmdu
 */
public class QLLop extends javax.swing.JFrame {

    /**
     * Creates new form QLLop
     */
    
    // Default var for maLop
    private String maLop = "CNTT-D17";
    
    public QLLop() {
        initComponents();
    }
    
    public void setMaLop(String maLop){
        this.maLop = maLop;
        System.out.println("Ma lop: " + this.getMaLop());
    }
    
    public String getMaLop(){
        return this.maLop;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        classTable = new javax.swing.JTable();
        editBtn = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("Quản lý lớp học");

        classTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MASV", "Họ tên", "Ngày sinh", "Địa chỉ", "Mã lớp", "Tên DN", "Mật khẩu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(classTable);
        if (classTable.getColumnModel().getColumnCount() > 0) {
            classTable.getColumnModel().getColumn(0).setPreferredWidth(25);
        }

        editBtn.setText("Chỉnh sửa thông tin");
        editBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editBtnMouseClicked(evt);
            }
        });

        jButton2.setText("Quay lại");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 759, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(editBtn))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(editBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editBtnMouseClicked
        // TODO add your handling code here:
        String maSV = classTable.getValueAt(classTable.getSelectedRow(), 0).toString();
        System.out.println(maSV);
        UpdateSV updateSV = new UpdateSV();
        updateSV.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosed(WindowEvent e) {
                // call terminate
                DefaultTableModel dtm = (DefaultTableModel) classTable.getModel();
                MssqlConnection mssql = new MssqlConnection();
                Connection conn = mssql.getConnection();
                try{
                    String sql = "select * from SINHVIEN where MASV = ?";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, maSV);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()){
                        classTable.setValueAt(rs.getString("MASV"), classTable.getSelectedRow(), 0);
                        classTable.setValueAt(rs.getString("HOTEN"), classTable.getSelectedRow(), 1);
                        classTable.setValueAt(rs.getString("NGAYSINH"), classTable.getSelectedRow(), 2);
                        classTable.setValueAt(rs.getString("DIACHI"), classTable.getSelectedRow(), 3);
                        classTable.setValueAt(rs.getString("MALOP"), classTable.getSelectedRow(), 4);
                        classTable.setValueAt(rs.getString("TENDN"), classTable.getSelectedRow(), 5);
                        classTable.setValueAt(rs.getString("MATKHAU"), classTable.getSelectedRow(), 6);
                    }
                }
                catch(SQLException excp){
                    excp.printStackTrace();
                }
            }
        });
        updateSV.setMaSV(maSV);
        updateSV.getCurrentInfo_SV();
    }//GEN-LAST:event_editBtnMouseClicked

    /**
     * @param args the command line arguments
     */
    
    public void showList(){
        DefaultTableModel dtm = (DefaultTableModel) classTable.getModel();
        MssqlConnection mssql = new MssqlConnection();
        Connection conn = mssql.getConnection();
        try{
            String sql = "select * from SINHVIEN where MALOP = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            System.out.println("showList maLop: " + this.getMaLop());
            ps.setString(1, this.maLop);
            ResultSet rs = ps.executeQuery();
            Vector vt;
            while(rs.next()){
                vt = new Vector();
                vt.add(rs.getString("MASV"));
                vt.add(rs.getString("HOTEN"));
                vt.add(rs.getString("NGAYSINH"));
                vt.add(rs.getString("DIACHI"));
                vt.add(rs.getString("MALOP"));
                vt.add(rs.getString("TENDN"));
                vt.add(rs.getString("MATKHAU"));
                dtm.addRow(vt);
            }
            classTable.setModel(dtm);
            ps.close();
            rs.close();
            conn.close();
        }
        catch(SQLException e){
            e.printStackTrace();
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
            java.util.logging.Logger.getLogger(QLLop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLLop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLLop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLLop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLLop().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable classTable;
    private javax.swing.JButton editBtn;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
