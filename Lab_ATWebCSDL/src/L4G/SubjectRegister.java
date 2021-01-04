/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package L4G;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hpmdu
 */
public class SubjectRegister extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    
    private List<HocPhan> l1 = new ArrayList<>(); // all subject
    private List<HocPhan> l2 = new ArrayList<>(); // choosen subject
    private String maSV = "SV03";
    
    public SubjectRegister() {
        initComponents();
        getChoosenList();
        getSubjectList();
        for(HocPhan h2: l2){
            for(int i = 0; i < l1.size(); i++){
                if (l1.get(i).getMaHP().equalsIgnoreCase(h2.getMaHP())){
                    sJList.setValueAt("Đã chọn", i, 3);
                }
            }
        }
    }
    
    public void getSubjectList(){
        Connection conn = new MssqlConnection().getConnection();
        try{
            String sql = "SELECT * FROM HOCPHAN";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                l1.add(new HocPhan(rs.getString("MAHP"), rs.getString("TENHP"), rs.getString("SOTC")));
            }
            DefaultTableModel dtm = this.addInTable(sJList, l1);
            sJList.setModel(dtm);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void getChoosenList(){
        Connection conn = new MssqlConnection().getConnection();
        try{
            String sql = "SELECT HP.* FROM BANGDIEM BD JOIN HOCPHAN HP ON BD.MAHP = HP.MAHP WHERE BD.MASV = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, this.maSV);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                l2.add(new HocPhan(rs.getString("MAHP"), rs.getString("TENHP"), rs.getString("SOTC")));
            }
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void delRegistedSubject(HocPhan h){
        for(HocPhan e: l2){
            if (e.getMaHP().equalsIgnoreCase(h.getMaHP())) l2.remove(e);
        }
    }
    
    public DefaultTableModel addInTable(javax.swing.JTable ltable, List<HocPhan> listHp){
        DefaultTableModel dtm = (DefaultTableModel) ltable.getModel();
        for(HocPhan e: listHp){
            Vector vt = new Vector();
            vt.add(e.getMaHP());
            vt.add(e.getTenHP());
            vt.add(e.getStc());
            dtm.addRow(vt);
        }
        return dtm;
    }
    
    public boolean existInList(HocPhan h){
        for(HocPhan hp: l2){
            if (hp.getMaHP().equalsIgnoreCase(h.getMaHP())){
                return true;
            }
        }
        return false;
    }
    
    public void existAndDelete(HocPhan h){
        for(int i = 0; i < l2.size(); i++){
            if (l2.get(i).getMaHP().equalsIgnoreCase(h.getMaHP())) {
                System.out.println("Đã xóa");
                l2.remove(i);
                this.removeFromDb(h);
                sJList.setValueAt("", i, 3);
                break;
            }
        }
    }
    
    public void addToDb(HocPhan h){
        Connection conn = new MssqlConnection().getConnection();
        RSA rsa = new RSA();
        try{
            String sql = "EXEC SP_REGISTER_SUBJECT ?, ?, ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, this.maSV);
            ps.setString(2, h.getMaHP());
            ps.setString(3, rsa.encrypt("0", rsa.getPublicKey(rsa.getFileInBytes(new File("publicKey.txt")))));
            ps.execute();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void removeFromDb(HocPhan h){
        Connection conn = new MssqlConnection().getConnection();
        try{
            String sql = "DELETE FROM BANGDIEM WHERE MASV = ? AND MAHP = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, this.maSV);
            ps.setString(2, h.getMaHP());
            ps.execute();
        }
        catch(Exception e){
            e.printStackTrace();
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        sJList = new javax.swing.JTable();
        chooseBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Đăng ký môn học");

        sJList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã môn học", "Tên môn học", "Số tín chỉ", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        sJList.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(sJList);
        if (sJList.getColumnModel().getColumnCount() > 0) {
            sJList.getColumnModel().getColumn(3).setResizable(false);
        }

        chooseBtn.setText("Đăng ký");
        chooseBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chooseBtnMouseClicked(evt);
            }
        });

        cancelBtn.setText("Hủy");
        cancelBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelBtnMouseClicked(evt);
            }
        });

        backBtn.setText("Quay lại");
        backBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cancelBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chooseBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(backBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(chooseBtn)
                        .addGap(18, 18, 18)
                        .addComponent(cancelBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backBtn))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chooseBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chooseBtnMouseClicked
        // TODO add your handling code here:
        if (sJList.getSelectedRow() >= 0){
            
            int i = sJList.getSelectedRow();
            HocPhan h = new HocPhan(sJList.getValueAt(i, 0).toString(), sJList.getValueAt(i, 1).toString(), 
                    sJList.getValueAt(i, 2).toString());
            if (!existInList(h)) {
                l2.add(h);
                this.addToDb(h);
                System.out.println("Đã thêm");
                sJList.setValueAt("Đã chọn", i, 3);
            }
        }
    }//GEN-LAST:event_chooseBtnMouseClicked

    private void cancelBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelBtnMouseClicked
        // TODO add your handling code here:
        if (sJList.getSelectedRow() >= 0){
            int i = sJList.getSelectedRow();
            HocPhan h = new HocPhan(sJList.getValueAt(i, 0).toString(), sJList.getValueAt(i, 1).toString(), 
                    sJList.getValueAt(i, 2).toString());
            existAndDelete(h);
            
        }
    }//GEN-LAST:event_cancelBtnMouseClicked

    private void backBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backBtnMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_backBtnMouseClicked

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
            java.util.logging.Logger.getLogger(SubjectRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SubjectRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SubjectRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SubjectRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SubjectRegister().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton chooseBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable sJList;
    // End of variables declaration//GEN-END:variables
}
