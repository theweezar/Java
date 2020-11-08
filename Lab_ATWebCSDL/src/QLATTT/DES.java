/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLATTT;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 *
 * @author hpmdu
 */
public class DES {
    private Cipher encryptCipher = null;
    private Cipher decryptCipher = null;

    /**
     * Construct a new object which can be utilized to encrypt
     * and decrypt strings using the specified key
     * with a DES encryption algorithm.
     *
     * @param key The secret key used in the crypto operations.
     * @throws Exception If an error occurs.
     *
     */
    public DES(SecretKey key) throws Exception {
        encryptCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        decryptCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        encryptCipher.init(Cipher.ENCRYPT_MODE, key);
        decryptCipher.init(Cipher.DECRYPT_MODE, key);
    }    

    /**
     * Encrypt a string using DES encryption, and return the encrypted
     * string as a base64 encoded string.
     * @param unencryptedString The string to encrypt.
     * @return String The DES encrypted and base 64 encoded string.
     * @throws Exception If an error occurs.
     */
    public byte[] encryptBase64 (byte[] unencryptedByte) throws Exception {
        // Encrypt
        byte[] encryptedBytes = encryptCipher.doFinal(unencryptedByte);

        return encryptedBytes;
    }

    /**
     * Decrypt a base64 encoded, DES encrypted string and return
     * the unencrypted string.
     * @param encryptedString The base64 encoded string to decrypt.
     * @return String The decrypted string.
     * @throws Exception If an error occurs.
     */
    public byte[] decryptBase64 (byte[] encryptedByte) throws Exception {
        // Decrypt
        byte[] unencryptedByteArray = decryptCipher.doFinal(encryptedByte);

        // Decode using utf-8
        return unencryptedByteArray;
    }
    
    public byte[] getFileInBytes(File f) throws IOException {
        byte[] fbytes;
        try (FileInputStream fis = new FileInputStream(f)) {
            fbytes = new byte[(int) f.length()];
            fis.read(fbytes);
        }
        return fbytes;
    }

    /**
     * Main unit test method.
     * @param args Command line arguments.
     *
     */
    public static void main(String args[]) {
        try {
            double start = (double)System.nanoTime();
            //Generate the secret key
            String password = "n17at018";
            DESKeySpec key = new DESKeySpec(password.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");

            //Instantiate the encrypter/decrypter
            DES des = new DES(keyFactory.generateSecret(key));
//            String plain = "Hoang Phan Minh Duc - N17DCAT018";
            File pFile = new File("C:\\Users\\hpmdu\\OneDrive\\Documents\\Nam_4\\QuanLyATTT\\LabCuoiKi\\plaintext\\plain4.txt");
            byte[] plainFromFile = des.getFileInBytes(pFile);
            byte[] crypted = des.encryptBase64(plainFromFile);
//            System.out.println("Mã hóa:\n" + Base64.getEncoder().encodeToString(crypted));
            
            byte[] decrypted = des.decryptBase64(crypted);
//            System.out.println("Giải mã: \n" + new String(decrypted, "UTF-8"));
            double endSec = ((double)System.nanoTime() - start) / 1000000000;
            System.out.println("File name: " + pFile.getName());
            System.out.println("Thời gian hoàn thành mã hóa và giải mã: " + endSec);
            
        } catch (Exception e) {
            System.err.println("Error:"+e.toString());
            e.printStackTrace();
        }
    }
}
