/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockChain;

import java.util.Date;

/**
 *
 * @author hpmdu
 */
public class Block {
    
    private String hash = null;
    private String preHash = null;
    private String data = null;
    private long timeStamp;
    private int nonce;
    
    public Block(String data, String preHash){
        this.data = data;
        this.preHash = preHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
        this.nonce = 0;
    }
    
    public String calculateHash(){
        String calHash = new SHA256().getSHA256(preHash + Long.toString(timeStamp) + Integer.toString(nonce) + data);
        return calHash;
    }
    
    public String getHash(){
        return this.hash;
    }
    
    public String getPreHash(){
        return this.preHash;
    }
    
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); //Tạo mã băm với theo độ khó n"0"
        System.out.println("Mining block......");
        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
//            System.out.println(hash);
        }
        System.out.println("Block Mined!!! : " + hash);
    }
}
