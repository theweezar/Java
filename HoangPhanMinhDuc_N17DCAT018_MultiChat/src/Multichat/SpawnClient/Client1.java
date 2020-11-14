/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Multichat.SpawnClient;

import Multichat.client.*;
import java.io.IOException;
import java.net.InetAddress;
/**
 *
 * @author hpmdu
 */
public class Client1 {
    public static void main(String[] args) throws IOException {
        Client c1 = new Client(InetAddress.getLocalHost(), 1234);
        c1.run();
    }
}
