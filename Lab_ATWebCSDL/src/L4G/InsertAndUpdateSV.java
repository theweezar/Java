/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package L4G;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author hpmdu
 */
public class InsertAndUpdateSV extends javax.swing.JFrame {

    /**
     * Creates new form UpdateSV
     */
    
    private String maSV;
    private String maLop;
    private Boolean edit = null;
    private Connection conn = new MssqlConnection().getConnection();
    private SinhVien newSv = null;
    
    public InsertAndUpdateSV() {
        initComponents();
        this.setMonthYearItem();
    }
    
    public void fixTitle(){
        if (edit) {
            title.setText("Chỉnh sửa thông tin sinh viên");
            warning.setText("*Mật khẩu để trống thì không thay đổi");
        }
        else {
            title.setText("Nhập thông tin sinh viên");
            warning.setText("*Không được bỏ trống");
            hoTen.setText("");
            diaChi.setText("");
            tenDN.setText("");
        }
    }
    
    public void setMonthYearItem(){
        for(int i = 1; i <= 12; i++){
            month.addItem("Tháng " + i);
        }
        for(int i = 0; i < 150; i++){
            int y = 1980 + i;
            year.addItem("" + y);
        }
    }
    
    public void calculateDay(){
        int[] m31 = {1,3,5,7,8,10,12};
        boolean is30 = true;
        day.removeAllItems();
        if (month.getSelectedIndex() + 1 == 2){
            System.out.println("Tháng 2");
            int endDay = Integer.parseInt(year.getSelectedItem().toString()) % 4 != 0 ? 28:29;
            for(int j = 1; j <= endDay; j++){
                day.addItem("" + j);
            }
            is30 = false;
        }
        else {
            for(int i = 0; i < m31.length; i++){
                if (month.getSelectedIndex() + 1 == m31[i]){
                    System.out.println("Tháng có 31 ngày");
                    for(int j = 1; j <= 31; j++){
                        day.addItem("" + j);
                    }
                    is30 = false;
                    break;
                }
            }
        }
        if (is30){
            System.out.println("Tháng có 30 ngày");
            for(int j = 1; j <= 30; j++){
                day.addItem("" + j);
            }
        }
    }
    
    public void setMaSV(String maSV){
        this.maSV = maSV;
        System.out.println(this.maSV);
    }
    
    public void setEditSV(boolean e){
        this.edit = e;
        this.fixTitle();
    }
    
    public void setMalop(String maLop){
        this.maLop = maLop;
    }
    
    public SinhVien getSv(){
        return newSv;
    }
    
    public void getInforSV(){
        // TODO add your handling code here:
        try{
            String sql = "EXEC SP_SEL_ENCRYPT_SINHVIEN ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, this.maSV);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                Date ngaysinh = rs.getDate("NGAYSINH");
                hoTen.setText(rs.getString("HOTEN"));
                month.setSelectedIndex(ngaysinh.getMonth());
                year.setSelectedIndex(ngaysinh.getYear() + 1900 - 1980);
                this.calculateDay();
                day.setSelectedIndex(ngaysinh.getDate() - 1);
                diaChi.setText(rs.getString("DIACHI"));
                tenDN.setText(rs.getString("TENDN"));
            }
            else{
                JOptionPane.showMessageDialog(this, "Có lỗi xảy ra", "Canh bao", JOptionPane.WARNING_MESSAGE);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void insertSV(){
        try{
            Hash hash = new Hash();
            boolean ok = true;
            if (hoTen.getText().trim().isEmpty() || diaChi.getText().trim().isEmpty() || tenDN.getText().trim().isEmpty() || 
                    matKhau.getText().trim().isEmpty()){
                ok = false;
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin", "Canh bao", JOptionPane.WARNING_MESSAGE);
            }
            if (ok){
                SinhVien sv = new SinhVien(this.maSV, hoTen.getText().trim(), 
                    new Date(Integer.parseInt(year.getSelectedItem().toString()) - 1900, 
                    month.getSelectedIndex(), Integer.parseInt(day.getSelectedItem().toString())), 
                diaChi.getText().trim(), this.maLop, tenDN.getText().trim(), hash.getMd5(matKhau.getText().trim()));
                
                String sql = "SP_INS_ENCRYPT_SINHVIEN ?, ?, ?, ?, ?, ?, ?";
                PreparedStatement ps = this.conn.prepareStatement(sql);
                ps.setString(1, this.maSV);
                ps.setString(2, sv.getHoTen());
                ps.setDate(3, sv.getNgaysinh());
                ps.setString(4, sv.getDiaChi());
                ps.setString(5, sv.getMaLop());
                ps.setString(6, sv.getTenDN());
                ps.setString(7, sv.getMatKhau());
                ps.execute();
                // Gán vào để return ra ngoài sau đó đẩy lên table
                newSv = sv;
                JOptionPane.showMessageDialog(this, "Thêm sinh viên thành công");
                this.dispose();
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra", "Canh bao", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
    }
    
    public void updateSV(){
        try{
            boolean changePw = false;
            String sql = "";
            if (!matKhau.getText().trim().equals("")) {
                changePw = true;
                sql = "EXEC SP_UPD_SINHVIEN_WITH_MATKHAU ?, ?, ?, ?, ?, ?";
            }
            else{
                sql = "EXEC SP_UPD_SINHVIEN_WITHOUT_MATKHAU ?, ?, ?, ?, ?";
            }
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, this.maSV);
            ps.setString(2, hoTen.getText().trim());
            Date newDate = new Date(Integer.parseInt(year.getSelectedItem().toString()) - 1900, 
                    month.getSelectedIndex(), Integer.parseInt(day.getSelectedItem().toString()));
            ps.setDate(3, newDate);
            ps.setString(4, diaChi.getText().trim());
            ps.setString(5, tenDN.getText().trim());
            SinhVien sv = new SinhVien(this.maSV, hoTen.getText().trim(), newDate, diaChi.getText().trim(), 
                    this.maLop, tenDN.getText().trim(), null);
            if (changePw){
                Hash hash = new Hash();
                String mK = hash.getMd5(matKhau.getText().trim());
                ps.setString(6, mK);
                sv.setMatKhau(mK);
            }
            ps.executeUpdate();
            System.out.println(newDate.toString());
            this.newSv = sv;
            JOptionPane.showMessageDialog(this, "Thay đổi thành công");
            this.dispose();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra", "Canh bao", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
        this.setVisible(false);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        saveBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();
        hoTen = new javax.swing.JTextField();
        diaChi = new javax.swing.JTextField();
        tenDN = new javax.swing.JTextField();
        day = new javax.swing.JComboBox<>();
        month = new javax.swing.JComboBox<>();
        year = new javax.swing.JComboBox<>();
        matKhau = new javax.swing.JPasswordField();
        title = new javax.swing.JLabel();
        warning = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Họ tên");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Ngày sinh");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Địa chỉ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Tên đăng nhập");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Mật khẩu mới");

        saveBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        saveBtn.setText("Lưu");
        saveBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveBtnMouseClicked(evt);
            }
        });

        backBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        backBtn.setText("Quay lại");
        backBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backBtnMouseClicked(evt);
            }
        });

        hoTen.setText("jTextField1");

        diaChi.setText("jTextField3");

        tenDN.setText("jTextField4");

        month.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthActionPerformed(evt);
            }
        });

        year.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearActionPerformed(evt);
            }
        });

        title.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        title.setText("Tiêu đề theo chức năng thêm hoặc sửa");

        warning.setFont(new java.awt.Font("Tahoma", 2, 16)); // NOI18N
        warning.setText("*warning theo chức năng");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(backBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveBtn))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(title)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tenDN)
                            .addComponent(diaChi)
                            .addComponent(matKhau)
                            .addComponent(hoTen)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(warning)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(day, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(hoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(diaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(tenDN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(matKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(warning)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBtn)
                    .addComponent(backBtn))
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

    private void monthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthActionPerformed
        // TODO add your handling code here:
        this.calculateDay();
    }//GEN-LAST:event_monthActionPerformed

    private void yearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearActionPerformed
        // TODO add your handling code here:
        this.calculateDay();
    }//GEN-LAST:event_yearActionPerformed

    private void saveBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveBtnMouseClicked
        // TODO add your handling code here:
        // TODO add your handling code here:
        if (this.edit) {
            this.updateSV();
        }
        else {
            this.insertSV();
        }
    }//GEN-LAST:event_saveBtnMouseClicked

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
            java.util.logging.Logger.getLogger(InsertAndUpdateSV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InsertAndUpdateSV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InsertAndUpdateSV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InsertAndUpdateSV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InsertAndUpdateSV().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JComboBox<String> day;
    private javax.swing.JTextField diaChi;
    private javax.swing.JTextField hoTen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField matKhau;
    private javax.swing.JComboBox<String> month;
    private javax.swing.JButton saveBtn;
    private javax.swing.JTextField tenDN;
    private javax.swing.JLabel title;
    private javax.swing.JLabel warning;
    private javax.swing.JComboBox<String> year;
    // End of variables declaration//GEN-END:variables
}
