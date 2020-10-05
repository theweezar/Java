/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockChain;

import java.util.ArrayList;


/**
 *
 * @author hpmdu
 */
public class BlockChain {
    
    private ArrayList<Block> blockchain = new ArrayList<Block>();
    
    public void add(String data){
        if (blockchain.isEmpty()) blockchain.add(new Block(data,"0"));
        else blockchain.add(new Block(data, blockchain.get(blockchain.size() - 1).getHash()));
        
        blockchain.get(blockchain.size() - 1).mineBlock(1);
    }
    
    public Boolean isChainValid() {
	Block currentBlock; 
	Block previousBlock;
	
	//loop through blockchain to check hashes:
	for(int i=1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            //compare registered hash and calculated hash:
            if(!currentBlock.getHash().equals(currentBlock.calculateHash()) ){
                    System.out.println("Current Hashes not equal");			
                    return false;
            }
            //compare previous hash and registered previous hash
            if(!previousBlock.getHash().equals(currentBlock.getPreHash()) ) {
                    System.out.println("Previous Hashes not equal");
                    return false;
            }
	}
	return true;
    }
    
    
    
    public static void main(String[] args) {
        BlockChain bChain = new BlockChain();
        bChain.add("Hoang Phan Minh Duc");
        bChain.add("Duong Truc Dong");
        bChain.add("Phan Dai");
        
        
    }
}
