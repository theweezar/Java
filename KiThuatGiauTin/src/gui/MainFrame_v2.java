/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import wulee.WuLeeLastedVersion_v1;
import wulee.WuLeeLastedVersion_v2;


/**
 *
 * @author hpmdu
 */
public class MainFrame_v2 extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame_v2
     */
    
    private WuLeeLastedVersion_v2 wulee;
//    private int totalCharHidden;
    private boolean isKeySet = false;
    
    public MainFrame_v2() {
        initComponents();
        setup();
        wulee = new WuLeeLastedVersion_v2();
    }
    
    public void setup(){
//        this.setResizable(false);
        nofiField.setEditable(false);
        nofiField.setLineWrap(true);
        messageField.setLineWrap(true);
        keyField.setEditable(false);
        setKeyBtn.setEnabled(false);
        hideBtn.setEnabled(false);
        retrieveBtn.setEnabled(false);
        saveBtn.setEnabled(false);
        pathString.setEditable(false);
        previewBtn.setEnabled(false);
    }
    
    public void displayProcessLine(String line){
        Date d = new Date();
        
        this.nofiField.append(String.format("[%d:%s:%s] %s\n", d.getHours(), 
                d.getMinutes() < 10 ? "0"+d.getMinutes():d.getMinutes(), 
                d.getSeconds() < 10 ? "0"+d.getSeconds():d.getSeconds(), 
                line));
        // Kéo scroll bar của nofiField xuống dưới cùng
        this.nofiScroll.getVerticalScrollBar().setValue(this.nofiScroll.getVerticalScrollBar().getMaximum());
    }
    
    public void resetKeyFieldAndSetKeyBtn(){
        keyField.setText("");
        keyField.setEditable(true);
        setKeyBtn.setText("Set key");
        isKeySet = false;
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
        browseBtn = new javax.swing.JButton();
        hideBtn = new javax.swing.JButton();
        retrieveBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        messageField = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        previewBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        keyField = new javax.swing.JPasswordField();
        nofiScroll = new javax.swing.JScrollPane();
        nofiField = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        setKeyBtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        pathString = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Wu - Lee Steganography");

        browseBtn.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        browseBtn.setText("Browse");
        browseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseBtnActionPerformed(evt);
            }
        });

        hideBtn.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        hideBtn.setText("Hide");
        hideBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hideBtnActionPerformed(evt);
            }
        });

        retrieveBtn.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        retrieveBtn.setText("Retrieve");
        retrieveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retrieveBtnActionPerformed(evt);
            }
        });

        saveBtn.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        saveBtn.setText("Save");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        messageField.setColumns(20);
        messageField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        messageField.setRows(5);
        jScrollPane1.setViewportView(messageField);

        jLabel2.setText("Messages");

        previewBtn.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        previewBtn.setText("Preview");
        previewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previewBtnActionPerformed(evt);
            }
        });

        jLabel4.setText("Password");

        keyField.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N

        nofiField.setColumns(20);
        nofiField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nofiField.setRows(5);
        nofiScroll.setViewportView(nofiField);

        jLabel3.setText("Notifications");

        setKeyBtn.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        setKeyBtn.setText("Set key");
        setKeyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setKeyBtnActionPerformed(evt);
            }
        });

        jLabel5.setText("Image Path");

        pathString.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        pathString.setText("Image is null");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pathString)
                    .addComponent(keyField)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(setKeyBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addComponent(nofiScroll)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(browseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(hideBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(retrieveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(previewBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(browseBtn)
                    .addComponent(hideBtn)
                    .addComponent(retrieveBtn)
                    .addComponent(saveBtn)
                    .addComponent(previewBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pathString, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(keyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(setKeyBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nofiScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void browseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseBtnActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG & GIF Images", "jpg", "png", "jpeg", "gif");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().getAbsolutePath();
            wulee.setCoverImage(path);
            if (wulee.isCoverImageNull()){
                displayProcessLine("Something wrong with Opencv module. Please check again.");
            }
            else {
                pathString.setText(path);
                displayProcessLine(String.format("Import image at %s successfully!\nThe longer key string get, the less characters hidden", 
                    path));
                displayProcessLine(wulee.calculate());
                keyField.setEditable(true);
                setKeyBtn.setEnabled(true);
                messageField.setText("");
                hideBtn.setEnabled(true);
                retrieveBtn.setEnabled(true);
                saveBtn.setEnabled(true);
                previewBtn.setEnabled(true);
            }
        }
    }//GEN-LAST:event_browseBtnActionPerformed

    private void previewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previewBtnActionPerformed
        // TODO add your handling code here:
        PreviewImage preview = new PreviewImage();
        preview.imageShow(pathString.getText().trim());
        preview.setVisible(true);
    }//GEN-LAST:event_previewBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        // TODO add your handling code here:
        if (wulee.isCoverImageNull()){
            JOptionPane.showMessageDialog(this, "Image is null", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        else{
            JFileChooser saver = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "png", "jpeg", "gif");
            saver.setFileFilter(filter);
            int returnVal = saver.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION){
                String fileName = saver.getSelectedFile().getAbsolutePath();
                // Nếu fileName ko có đuôi là .png thì sẽ được thêm vào tự động
                if (!fileName.matches("(.*).png")) fileName += ".png";
                wulee.saveStegoImage(fileName);
                displayProcessLine(String.format("Save image successfully to %s", fileName));
            }
        }
    }//GEN-LAST:event_saveBtnActionPerformed

    private void hideBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hideBtnActionPerformed
        // TODO add your handling code here:
        String keyString = keyField.getText().trim();
        String messages = messageField.getText().trim();
        if (wulee.isCoverImageNull()){
            JOptionPane.showMessageDialog(this, "Image is null", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        else if (keyString.length() == 0 || !isKeySet){
            JOptionPane.showMessageDialog(this, "Key is null", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        else if (keyString.length() > wulee.getCoverImageHeight()){
            JOptionPane.showMessageDialog(this, "Key is too long", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        else if (messages.length() == 0){
            JOptionPane.showMessageDialog(this, "Message is null", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        else{
//            int totalCharHidden = (int)Math.floor((wulee.getTotalPixels() / (keyString.length() * 8))) * 3;
            
            // Không thể tính được độ dài tin nhắn có thể giấu được
            displayProcessLine("Begin to hide....");
                
            wulee.setKey(keyString);
            wulee.setMessage(messages);
            wulee.hide();
            wulee.resetWhenHide();
            wulee.setKeyToNull();

            resetKeyFieldAndSetKeyBtn();

            messageField.setText("");
            displayProcessLine("Done.");
        }
    }//GEN-LAST:event_hideBtnActionPerformed

    private void retrieveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retrieveBtnActionPerformed
        // TODO add your handling code here:
        String keyString = keyField.getText().trim();
        if (wulee.isCoverImageNull()){
            JOptionPane.showMessageDialog(this, "Image is null", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        else if (keyString.length() == 0 || !isKeySet){
            JOptionPane.showMessageDialog(this, "Key is null", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        else {
//            String lengthString = JOptionPane.showInputDialog(this, 
//                String.format("Message's length (maximum: %d)", totalCharHidden), 
//                "Message's Length", JOptionPane.DEFAULT_OPTION).trim();
            displayProcessLine("Begin to retrieve.");
                    
            wulee.setKey(keyString);
//            wulee.setRetrieveMax(length);
            wulee.retrieve();
            wulee.setKeyToNull();
            messageField.setText(wulee.getRetrieveMessage());
            wulee.resetWhenRetrieve();

            displayProcessLine("Retrieve successfully.");
            resetKeyFieldAndSetKeyBtn();
        }
    }//GEN-LAST:event_retrieveBtnActionPerformed

    private void setKeyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setKeyBtnActionPerformed
        // TODO add your handling code here:
        if (!isKeySet){
            String keyString = keyField.getText().trim();
            if (keyString.length() == 0){
                JOptionPane.showMessageDialog(this, "Key is null", "Warning", JOptionPane.WARNING_MESSAGE);
            }
            else if (keyString.length() < 5){
                // Nếu như key quá ngắn thì sẽ có sai sót trong chương trình này
                JOptionPane.showMessageDialog(this, "Key's length must be longer than 4", "Warning", JOptionPane.WARNING_MESSAGE);
            }
            else if (keyString.length() > wulee.getCoverImageHeight()){
                JOptionPane.showMessageDialog(this, "Key is too long", "Warning", JOptionPane.WARNING_MESSAGE);
            }
            else{
                keyField.setEditable(false);
                setKeyBtn.setText("Change key");
//                totalCharHidden = (int)Math.floor((wulee.getTotalPixels() / (keyString.length() * 8))) * 3;
//                displayProcessLine(String.format("\nKey's length: %d"
//                        + "\nCharacters can be hidden in 3 channel: %d", keyString.length(), totalCharHidden));
                displayProcessLine(String.format("\nKey's length: %d", keyString.length()));
                isKeySet = true;
            }
        }
        else{
            isKeySet = false;
            keyField.setEditable(true);
            setKeyBtn.setText("Set key");
        }
    }//GEN-LAST:event_setKeyBtnActionPerformed

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
            java.util.logging.Logger.getLogger(MainFrame_v2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame_v2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame_v2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame_v2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame_v2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseBtn;
    private javax.swing.JButton hideBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPasswordField keyField;
    private javax.swing.JTextArea messageField;
    private javax.swing.JTextArea nofiField;
    private javax.swing.JScrollPane nofiScroll;
    private javax.swing.JTextField pathString;
    private javax.swing.JButton previewBtn;
    private javax.swing.JButton retrieveBtn;
    private javax.swing.JButton saveBtn;
    private javax.swing.JButton setKeyBtn;
    // End of variables declaration//GEN-END:variables
}
