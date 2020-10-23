/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockChain;

/**
 *
 * @author hpmdu
 */
import java.security.*;
import java.util.ArrayList;

public class Transaction {
	
    // Mã giao dịch
    public String transactionId;
    
    // puclicKey của người gửi
    public PublicKey sender; 
    
    // publicKey của người nhận
    public PublicKey reciepient; 
    
    // Giá tiền tệ 
    public double value;
    
    // Chữ ký - ngăn chặn người khác sử dụng tiền của người gửi
    public byte[] signature;

    public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
    public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();

    // Số lần giao dịch dc tạo ra
    public static int sequence = 0;
    
    // Plain data là kết hợp của 2 public của sender và reciepient + value
    public String pData;

    // Constructor: 
    public Transaction(PrivateKey sFrom, PublicKey from, PublicKey to, double value,  ArrayList<TransactionInput> inputs) {
        this.sender = from;
        this.reciepient = to;
        this.value = value;
        this.inputs = inputs;
        
        RSA rsa = new RSA();
        pData = rsa.publicKeyToString(sender) + rsa.publicKeyToString(reciepient) + Double.toString(value);
        try{
            this.signature = rsa.createSignature(pData, sFrom);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    // This Calculates the transaction hash (which will be used as its Id)
    public String calulateHash() {
        sequence++; //increase the sequence to avoid 2 identical transactions having the same hash
        RSA rsa = new RSA();
        return new SHA256().getSHA256(
            rsa.publicKeyToString(sender) +
            rsa.publicKeyToString(reciepient) +
            Double.toString(value) + sequence
        );
    }
    
}
