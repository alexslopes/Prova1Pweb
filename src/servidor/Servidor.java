/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import negocio.Usuario;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author alex
 */
public class Servidor {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(1234);
        
        while(true){
            Socket cliente = socket.accept();
            Thread t = new Thread(new Tratamento(cliente));
            t.start();
        }
    }
}