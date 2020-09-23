/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package L4Per;

import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hpmdu
 */
public class QLNV extends javax.swing.JFrame {

    /**
     * Creates new form QLNV
     */
    
    private Connection conn = new MssqlConnection().getConnection();
    private List<NhanVien> listNV = new ArrayList<NhanVien>();
    public QLNV() {
        initComponents();
        okBtn.setVisible(false);
        this.load();
    }
    
    public void load(){
        DefaultTableModel dtm = (DefaultTableModel) nvList.getModel();
        try{
            String sql = "EXEC SP_SEL_NHANVIEN";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Vector vt = new Vector();
                vt.add(rs.getString("MANV"));
                vt.add(rs.getString("HOTEN"));
                vt.add(rs.getString("EMAIL"));
                vt.add(rs.getString("LUONG"));
                dtm.addRow(vt);
            }
            nvList.setModel(dtm);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void refresh(){
        DefaultTableModel dtm = (DefaultTableModel) nvList.getModel();
        Vector vt = new Vector();
        if (listNV.size() != 0){
            NhanVien nv = listNV.get(listNV.size() - 1);
            vt.add(nv.getMaNV());
            vt.add(nv.getHoTen());
            vt.add(nv.geteMail());
            vt.add(nv.getLuong());
            dtm.addRow(vt);
            nvList.setModel(dtm);
        }
    }
    
    public void setVisibleOkBtn(boolean visible){
        okBtn.setVisible(visible);
        insertBtn.setVisible(!visible);
        delBtn.setVisible(!visible);
        editBtn.setVisible(!visible);
        saveBtn.setVisible(!visible);
        khongBtn.setVisible(!visible);
        escBtn.setVisible(!visible);
        maNV.setEditable(!visible);
    }
    
    public void eraseTextField(){
        maNV.setText("");
        hoTen.setText("");
        eMail.setText("");
        luong.setText("");
        tenDN.setText("");
        matKhau.setText("");
    }
    
    public boolean maNVExist(String maNV){
        try{
            String sql = "SELECT MANV FROM NHANVIEN WHERE MANV = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, maNV);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return true;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean isNum(String num){
        try{
            Integer.parseInt(num);
            return true;
        }
        catch(NumberFormatException e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        maNV = new javax.swing.JTextField();
        eMail = new javax.swing.JTextField();
        tenDN = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        hoTen = new javax.swing.JTextField();
        luong = new javax.swing.JTextField();
        matKhau = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();
        nvList = new javax.swing.JTable();
        insertBtn = new javax.swing.JButton();
        delBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        khongBtn = new javax.swing.JButton();
        escBtn = new javax.swing.JButton();
        okBtn = new javax.swing.JButton();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Danh mục nhân viên");

        jLabel2.setText("Thông tin nhân viên");

        jLabel3.setText("Mã NV");

        jLabel4.setText("Email");

        jLabel5.setText("Tên đăng nhập");

        jLabel6.setText("Họ tên");

        jLabel7.setText("Lương");

        jLabel8.setText("Mật khẩu");

        nvList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ NHÂN VIÊN", "HỌ TÊN", "EMAIL", "LUONG"
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
        jScrollPane1.setViewportView(nvList);

        insertBtn.setText("Thêm");
        insertBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                insertBtnMouseClicked(evt);
            }
        });

        delBtn.setText("Xóa");
        delBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                delBtnMouseClicked(evt);
            }
        });

        editBtn.setText("Sửa");
        editBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editBtnMouseClicked(evt);
            }
        });

        saveBtn.setText("Lưu");
        saveBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveBtnMouseClicked(evt);
            }
        });

        khongBtn.setText("Không");

        escBtn.setText("Thoát");
        escBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                escBtnMouseClicked(evt);
            }
        });

        okBtn.setText("OK");
        okBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(insertBtn)
                                .addGap(18, 18, 18)
                                .addComponent(delBtn)
                                .addGap(18, 18, 18)
                                .addComponent(editBtn)
                                .addGap(18, 18, 18)
                                .addComponent(saveBtn)
                                .addGap(18, 18, 18)
                                .addComponent(khongBtn)
                                .addGap(18, 18, 18)
                                .addComponent(escBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 282, Short.MAX_VALUE)
                                .addComponent(okBtn))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(tenDN, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addGap(90, 90, 90)
                                            .addComponent(eMail, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel7))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(luong, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                                        .addComponent(hoTen, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(matKhau, javax.swing.GroupLayout.Alignment.LEADING)))
                                .addComponent(jLabel2)
                                .addComponent(jLabel1)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(83, 83, 83)
                                    .addComponent(maNV, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel6))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(maNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(hoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(eMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(luong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tenDN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(matKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(insertBtn)
                    .addComponent(delBtn)
                    .addComponent(editBtn)
                    .addComponent(saveBtn)
                    .addComponent(khongBtn)
                    .addComponent(escBtn)
                    .addComponent(okBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void insertBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insertBtnMouseClicked
        // TODO add your handling code here:
        if (maNV.getText().trim().equals("") && hoTen.getText().trim().equals("") && eMail.getText().trim().equals("")
                && luong.getText().trim().equals("") && tenDN.getText().trim().equals("")
                && matKhau.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Vui long khong de trong", "Canh bao", JOptionPane.WARNING_MESSAGE);
        }
        else if (this.maNVExist(maNV.getText().trim())){
            JOptionPane.showMessageDialog(this, "Ma nhan vien da ton tai", "Canh bao", JOptionPane.WARNING_MESSAGE);
        }
        else if (!this.isNum(luong.getText().trim())){
            JOptionPane.showMessageDialog(this, "Nhap luong nhan vien sai", "Canh bao", JOptionPane.WARNING_MESSAGE);
        }
        else{
            NhanVien nv = new NhanVien(maNV.getText(), hoTen.getText(), eMail.getText(),
                luong.getText(),tenDN.getText(),matKhau.getText());
            this.listNV.add(nv);
            JOptionPane.showMessageDialog(this, "Them nhan vien thanh cong");
            this.refresh();
            this.eraseTextField();
        }
    }//GEN-LAST:event_insertBtnMouseClicked

    private void saveBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveBtnMouseClicked
        // TODO add your handling code here:
        try{
            for(int i = 0; i < this.listNV.size(); i++){
                String sql = "EXEC SP_INS_ENCRYPT_NHANVIEN ?, ?, ?, ?, ?, ?";
                PreparedStatement ps = this.conn.prepareStatement(sql);
                ps.setString(1, this.listNV.get(i).getMaNV());
                ps.setString(2, this.listNV.get(i).getHoTen());
                ps.setString(3, this.listNV.get(i).geteMail());
                ps.setInt(4, Integer.parseInt(this.listNV.get(i).getLuong()));
                ps.setString(5, this.listNV.get(i).getTenDN());
                ps.setString(6, this.listNV.get(i).getMatKhau());
                ps.execute();
            }
            JOptionPane.showMessageDialog(this, "Luu du lieu thanh cong");
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Co loi khi luu du lieu", "Canh bao", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_saveBtnMouseClicked

    private void delBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delBtnMouseClicked
        // TODO add your handling code here:
        DefaultTableModel dtm = (DefaultTableModel) nvList.getModel();
        try{
            String sql = "DELETE FROM NHANVIEN WHERE MANV = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, nvList.getValueAt(nvList.getSelectedRow(), 0).toString());
            int selectedOption = JOptionPane.showConfirmDialog(null, 
                                  "Dữ liệu sẽ xóa trực tiếp vào cơ sở dữ liệu. Bạn có muốn tiếp tục", 
                                  "Choose", 
                                  JOptionPane.YES_NO_OPTION); 
            if (selectedOption == JOptionPane.YES_OPTION) {
                ps.execute();
                dtm.removeRow(nvList.getSelectedRow());
                nvList.setModel(dtm);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_delBtnMouseClicked

    private void editBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editBtnMouseClicked
        // TODO add your handling code here:
        try{
            String sql = "EXEC SP_SEL_DECRYPT_NHANVIEN '" + nvList.getValueAt(nvList.getSelectedRow(), 0).toString() + "'";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                hoTen.setText(rs.getString("HOTEN"));
                eMail.setText(rs.getString("EMAIL"));
                luong.setText(rs.getString("LUONG"));
                tenDN.setText(rs.getString("TENDN"));
                this.setVisibleOkBtn(true);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_editBtnMouseClicked

    private void okBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okBtnMouseClicked
        // TODO add your handling code here:
        try{
            String sql = "EXEC SP_UPD_NHANVIEN ?, ?, ?, ?, ?, ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_okBtnMouseClicked

    private void escBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_escBtnMouseClicked
        // TODO add your handling code here:
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_escBtnMouseClicked

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
            java.util.logging.Logger.getLogger(QLNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLNV().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton delBtn;
    private javax.swing.JTextField eMail;
    private javax.swing.JButton editBtn;
    private javax.swing.JButton escBtn;
    private javax.swing.JTextField hoTen;
    private javax.swing.JButton insertBtn;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton khongBtn;
    private javax.swing.JTextField luong;
    private javax.swing.JTextField maNV;
    private javax.swing.JPasswordField matKhau;
    private javax.swing.JTable nvList;
    private javax.swing.JButton okBtn;
    private javax.swing.JButton saveBtn;
    private javax.swing.JTextField tenDN;
    // End of variables declaration//GEN-END:variables
}
