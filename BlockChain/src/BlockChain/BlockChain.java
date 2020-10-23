/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockChain;

import java.util.ArrayList;
import java.util.HashMap;


/**
 *
 * @author hpmdu
 */
public class BlockChain {
    
    private ArrayList<Block> blockchain = new ArrayList<Block>();
    private int difficulty = 3;
    
    public void add(String data){
        // Thêm block mới vào chain
        if (blockchain.isEmpty()) blockchain.add(new Block(data,"0"));
        else blockchain.add(new Block(data, blockchain.get(blockchain.size() - 1).getHash()));
        
        // Áp dùng proof of work để xác thực block mới
        blockchain.get(blockchain.size() - 1).mineBlock(this.difficulty);
        
        // Thêm block mới vào database
//        insertDB(blockchain.get(blockchain.size() - 1));
    }
    
    public void insertDB(Block b){
        
    }
    
    
    public boolean isChainValid() {
	Block currentBlock; 
	Block previousBlock;
	
	// Vòng lặp của chain, bắt đầu từ 1. Vì nếu chain chỉ có 1 block thì ko cần duyệt
	for(int i=1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            // So sánh hash hiện tại của block i vs block i - 1
            if(!currentBlock.getHash().equals(currentBlock.calculateHash()) ){
                    System.out.println("Current Hashes not equal");			
                    return false;
            }
            // So sánh hash trước của block i và block i - 1
            if(!previousBlock.getHash().equals(currentBlock.getPreHash()) ) {
                    System.out.println("Previous Hashes not equal");
                    return false;
            }
	}
        System.out.println("Hashes is equal");
	return true;
    }
    
    public void showHash(){
        for(Block b: blockchain){
            System.out.println("Current Hash : " + b.getHash());
            System.out.println("Previous Hash: " + b.getPreHash());
        }
    }
    
    /*
    //Returns true if new transaction could be created.	
    public boolean processTransaction(Transaction ts) {
        RSA rsa = new RSA();
        try{
            if(rsa.verify(ts.pData, ts.signature, ts.sender) == false) {
                System.out.println("#Transaction Signature failed to verify");
                return false;
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }

        //gather transaction inputs (Make sure they are unspent):
        for(TransactionInput i : ts.inputs) {
                i.UTXO = this.UTXOs.get(i.transactionOutputId);
        }

//        //check if transaction is valid:
//        if(getInputsValue() < NoobChain.minimumTransaction) {
//                System.out.println("#Transaction Inputs to small: " + getInputsValue());
//                return false;
//        }

        //generate transaction outputs:
        double leftOver = getInputsValue() - ts.value; //get value of inputs then the left over change:
        ts.transactionId = ts.calulateHash();
        ts.outputs.add(new TransactionOutput(ts.reciepient, ts.value, ts.transactionId)); //send value to recipient
        ts.outputs.add(new TransactionOutput(ts.sender, leftOver, ts.transactionId)); //send the left over 'change' back to sender		

        //add outputs to Unspent list
        for(TransactionOutput o : ts.outputs) {
            this.UTXOs.put(o.id , o);
        }

        //remove transaction inputs from UTXO lists as spent:
        for(TransactionInput i : ts.inputs) {
            if(i.UTXO == null) continue; //if Transaction can't be found skip it 
            this.UTXOs.remove(i.UTXO.id);
        }

        return true;
    }

//returns sum of inputs(UTXOs) values
    public float getInputsValue() {
        float total = 0;
        for(TransactionInput i : inputs) {
                if(i.UTXO == null) continue; //if Transaction can't be found skip it 
                total += i.UTXO.value;
        }
        return total;
    }

//returns sum of outputs:
    public float getOutputsValue() {
        float total = 0;
        for(TransactionOutput o : outputs) {
                total += o.value;
        }
        return total;
    }
    */
    
    public static void main(String[] args) {
        BlockChain bChain = new BlockChain();
        bChain.add("Hoang Phan Minh Duc");
        bChain.add("Duong Truc Dong");
        bChain.add("Phan Dai");
        bChain.showHash();
        bChain.isChainValid();
    }
}
