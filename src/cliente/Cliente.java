/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author alex
 */
public class Cliente {
   private String ip;
   private int porta;
   
    public void conectar() throws IOException{
        Socket socket = new Socket(this.ip,this.porta);
    }
 
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPorta() {
        return porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }
}
