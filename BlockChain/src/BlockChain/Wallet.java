/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockChain;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 *
 * @author hpmdu
 */
public class Wallet {
    
    // privateKey dùng để tạo ra signature
    public PrivateKey privateKey;
    
    // publicKey dùng để kiểm chứng signature đó
    public PublicKey publicKey;
}