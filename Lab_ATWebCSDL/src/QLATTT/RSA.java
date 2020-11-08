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
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
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
            double start = (double)System.nanoTime();
            // Phần tạo khóa
            int bitKey = 2048;
            KeyPair key = rsa.generateKeyPair(bitKey);
//            String pathPublicKey = "C:\\Users\\hpmdu\\OneDrive\\Documents\\Nam_4\\QuanLyATTT\\LabCuoiKi\\plaintext\\publickey.txt";
//            String pathPrivateKey = "C:\\Users\\hpmdu\\OneDrive\\Documents\\Nam_4\\QuanLyATTT\\LabCuoiKi\\plaintext\\privatekey.txt";
//            rsa.saveKeyPair(pathPublicKey, pathPrivateKey, key);
            
            // Phần mã hóa
//            String plain = "Hoang Phan Minh Duc – N17DCAT018 – D17CQAT01, hien tai dang lam bai bao cao mon quan ly an toan thong tin";
            String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCUyWq/OHeLzbTsuE6L66/vxKS8Z8m+CcFYJx6EgclJv9v/IWCCKf1pFPOVw3/tMgwxWSeB149cpPZeSjY5h8lQmjMlS0XdkiqM6RB+T43YD7ZHHlvFiViqafdjHgSGfnLKliexu0UQjX9f49loAT8pE9JkfmiJ+PAMxOS7BL7bRwIDAQAB".trim();
            String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJTJar84d4vNtOy4Tovrr+/EpLxnyb4JwVgnHoSByUm/2/8hYIIp/WkU85XDf+0yDDFZJ4HXj1yk9l5KNjmHyVCaMyVLRd2SKozpEH5PjdgPtkceW8WJWKpp92MeBIZ+csqWJ7G7RRCNf1/j2WgBPykT0mR+aIn48AzE5LsEvttHAgMBAAECgYAp00EdxgrdExOUI+94p+WKWlYQ3IA62tUuKbkLeMyT3cpDOye9D368JnafGBkDHbmNuclAV89mNL1JHkWGAKTXhPJ3A7NmBdhT7WD7o/0vtJos2pGyO/t9E/EMzwz/Em3bql0eXyp9dPT35cXWv4Lm/jvqtrYP+ttx5xakuQG1iQJBAMba4AnNPdt7dNvnF+VzJm7VGXZ6nEyhTVgwLZpUG9EcHK2hTG4zgd1noDm62keTp20L6q8O/KrsMXdvRKiUzn0CQQC/iy9P6r0u1cE/zH5Joeq6i+wLQPXpOJQePaU02Qg02aUNvZfO0H06ixlEdlbYrRHUweCILeVj+mzBTKrSMCgTAkAoXU9yzeWLgtDivlL8cVZQ0xLbGOJXL7radtUa6Y3H8ZPkrsQr7mqi/aDpdwNN2iv3F58or7scGtujqcNfEO2lAkB839raeSkZLZYtZ741dvA26h3bQGCRyacXCA16pLuq6PGoQaCE3nul/SVd8uCvpjVnxXYCkhlo0sywQLFlEqwtAkBBns4ZJqNbWcFvcG4P1G0vKcidasaF4zlwlJUMpCpoDsgKrks9CnM5gtnFWhibYEOpBGMYrETL3mQgEjOGjlBG".trim();
            File pFile = new File("C:\\Users\\hpmdu\\OneDrive\\Documents\\Nam_4\\QuanLyATTT\\LabCuoiKi\\plaintext\\plain4.txt");
            byte[] plainByte = rsa.getFileInBytes(pFile);
            String plain = new String(plainByte,"UTF-8");
            // bitKey / 8 - 11 là độ dài tối đa để mã hóa, nếu dài hơn sẽ crack
            // Vì vậy ta sẽ chia plaintext ra thành từng mảng, rồi mã hóa từng mảng đó
            int maxlen = (bitKey / 8 - 11); // 2048 ở đây là 245 byte là max
            List<String> plainArr = new ArrayList<>();
            int tongByte = plain.length();
            int i = 0;
            while(tongByte != 0){
                if (tongByte > maxlen) {
                    plainArr.add(plain.substring(i * maxlen, maxlen * (i+1)));
                    tongByte -= maxlen;
                }
                else {
                    plainArr.add(plain.substring(i * maxlen, plain.length()));
                    tongByte = 0;
                }
                i++;
            }
            for(String plainE: plainArr){
                tongByte += plainE.length();
            }
            System.out.printf("Tổng byte sau khi phân tách: %d\n", tongByte);
            PublicKey pubKey = rsa.getPublicKey(publicKey.getBytes());
            PrivateKey priKey = rsa.getPrivateKey(privateKey.getBytes());
            List<String> encryptArr = new ArrayList<>();
            List<String> decryptArr = new ArrayList<>();
            for(String plainE: plainArr){
                String encrypted = rsa.encrypt(plainE, key.getPublic());
                encryptArr.add(encrypted);
            }
            for(String encrypted: encryptArr){
                String decrypted = rsa.decrypt(encrypted, key.getPrivate());
                decryptArr.add(decrypted);
//                System.out.println(decrypted);
            }
//            String encrypted = rsa.encrypt(plain, key.getPublic());
//            encrypted = "He7GXlfVvmE1mR5nX3RFQwvwzD4CWXeEjsWkmTyHWKdXd+y3SkKWVKf+S4UhgDOSsGq1N8si3Zbavgr0HIJASV1dA0a0ZPiVDsGRkEqEX9QsjBc6gQ4jesoozK4VPIBjpyqOk1fk+0OugWLtWajv8vs5pAABAe6cBsQu5jXEm4Q=";
//            System.out.printf("Mã hóa: \n%s\n", encrypted);
//            String decrypted = rsa.decrypt(encrypted, key.getPrivate());
//            System.out.printf("Giải mã: \n%s\n", decrypted);
            double endSec = ((double)System.nanoTime() - start) / 1000000000;
            System.out.println("File name: " + pFile.getName());
            System.out.println("Thời gian hoàn thành mã hóa và giải mã: " + endSec);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
