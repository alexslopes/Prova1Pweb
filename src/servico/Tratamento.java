/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.Usuario;

/**
 *
 * @author alex
 */
public class Tratamento implements Runnable{
    Socket socket;
    Usuario usr;
    
    public Tratamento(Socket socket){
        this.socket = socket;
        this.usr = new Usuario();
    }
    
    @Override
    public void run(){
        try {
            Scanner ler = new Scanner(socket.getInputStream());
            PrintWriter escrever = new PrintWriter(socket.getOutputStream(), true);
            
            boolean email;
            boolean cpf;
            do{  
            usr.setEmail(ler.nextLine());
            usr.setCpf(ler.nextLine());
            
            email = usr.isValidEmail();
            cpf = usr.isValidCpf();
            
          
              
              
            if(!email){
                if(!cpf)
                    escrever.println("Email e cpf inválidos, porfavor preencher corretamente.");
                escrever.println("Email inválido, porfavor preencher corretamente.");
            }else if(!cpf)
                escrever.println("CPF inválido, porfavor preencher corretamente.");
            else{
                escrever.println("Arquivo pronto");
                processRequest();
            }
          }while(!(cpf && email));
            
        } catch (IOException ex) {
            Logger.getLogger(Tratamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void processRequest() throws IOException{
        File file = new File("arquivo.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        
        writer.write(usr.getEmail());
        writer.newLine();
        writer.write(usr.getCpf());
        writer.newLine();
        writer.close();
    }
}
