/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLATTT;

import L4G.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;

/**
 *
 * @author hpmdu
 */
public class RSA {
    
    // Nguồn tham khảo
    // https://mkyong.com/java/java-asymmetric-cryptography-example/
    // https://niels.nu/blog/2016/java-rsa.html
    // https://www.devglan.com/java8/rsa-encryption-decryption-java
    
    // Create a private key and a public key. We will store the public key in database
    public KeyPair generateKeyPair(int size) throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(size, new SecureRandom());
        KeyPair pair = generator.generateKeyPair();
        return pair;
    }
    
    // We encrypt the data with the public key 
    public String encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] cipherText = encryptCipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(cipherText);
    }
    
    // We decrypt the data with the private key
    public String decrypt(String cipherText, PrivateKey privateKey) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(cipherText);

        Cipher decriptCipher = Cipher.getInstance("RSA");
        decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);

        return new String(decriptCipher.doFinal(bytes), StandardCharsets.UTF_8);
    }
    
    // We get the signature 
    public static String sign(String plainText, PrivateKey privateKey) throws Exception {
        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(privateKey);
        privateSignature.update(plainText.getBytes(StandardCharsets.UTF_8));

        byte[] signature = privateSignature.sign();

        return Base64.getEncoder().encodeToString(signature);
    }
    
    // We verify that signature with the public key stored in database
    public static boolean verify(String plainText, String signature, PublicKey publicKey) throws Exception {
        Signature publicSignature = Signature.getInstance("SHA256withRSA");
        publicSignature.initVerify(publicKey);
        publicSignature.update(plainText.getBytes(StandardCharsets.UTF_8));

        byte[] signatureBytes = Base64.getDecoder().decode(signature);

        return publicSignature.verify(signatureBytes);
    }
    
    // We can use write Byte[] method in here instead of write String
    public void writeFile(String path, String key){
        File f = new File(path);
        try{
            f.createNewFile();
            FileWriter w = new FileWriter(path);
            w.write(key);
            w.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    // Save key pair to a file 
    public void saveKeyPair(String pathPublicKey, String pathPrivateKey, KeyPair pair){
        String base64publicKey = Base64.getEncoder().encodeToString(pair.getPublic().getEncoded());
        String base64privateKey = Base64.getEncoder().encodeToString(pair.getPrivate().getEncoded());
        System.out.printf("Khóa công khai: %s\n", base64publicKey);
        System.out.printf("Khóa bí mật   : %s\n", base64privateKey);
        this.writeFile(pathPublicKey, base64publicKey);
        this.writeFile(pathPrivateKey, base64privateKey);
    }
    
    public byte[] getFileInBytes(File f) throws IOException {
        byte[] fbytes;
        try (FileInputStream fis = new FileInputStream(f)) {
            fbytes = new byte[(int) f.length()];
            fis.read(fbytes);
        }
        return fbytes;
    }

    public PublicKey getPublicKey(byte[] base64PublicKey){
        PublicKey publicKey = null;
        try{
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }
    
    public PrivateKey getPrivateKey(byte[] base64PrivateKey){
        PrivateKey privateKey = null;
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey));
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            privateKey = keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return privateKey;
    }
    
    public static void main(String[] args) {
        RSA rsa = new RSA();
        try{
            KeyPair key = rsa.generateKeyPair(512);
            String pathPublicKey = "C:\\Users\\hpmdu\\OneDrive\\Documents\\Nam_4\\QuanLyATTT\\LabCuoiKi\\plaintext\\publickey.txt";
            String pathPrivateKey = "C:\\Users\\hpmdu\\OneDrive\\Documents\\Nam_4\\QuanLyATTT\\LabCuoiKi\\plaintext\\privatekey.txt";
            rsa.saveKeyPair(pathPublicKey, pathPrivateKey, key);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
