/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package De_CN03;

import java.net.InetAddress;

/**
 *
 * @author hpmdu
 */
public class PacketProp {
    
    private String data = null;
    private InetAddress address = null;
    private int port;

    public PacketProp(String data, InetAddress address, int port) {
        this.data = data;
        this.address = address;
        this.port = port;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
