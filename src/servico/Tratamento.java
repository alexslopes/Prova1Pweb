/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
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
public class Tratamento implements Runnable {

    Socket socket;
    Usuario usr;

    public Tratamento(Socket socket) {
        this.socket = socket;
        this.usr = new Usuario();
    }

    @Override
    public void run() {
        try {
            Scanner ler = new Scanner(socket.getInputStream());
            PrintWriter escrever = new PrintWriter(socket.getOutputStream(), true);

            boolean email;
            boolean cpf;
            boolean cnpj;
            do {
                usr.setEmail(ler.nextLine());
                usr.setCpf(ler.nextLine());
                usr.setCnpj(ler.nextLine());
                
                String msgEmail = "";
                String msgCNPJ = "";
                String msgCPF = "";
                
                email = usr.isValidEmail();
                cpf = usr.isValidCpf();
                cnpj = usr.isValidCnpj();

                if (!email) 
                    msgEmail = "Email,";   
                if (!cpf) 
                    msgCPF = "CPF,";
                if (!cnpj) 
                    msgCNPJ = "CNPJ,";
                if(!email || !cpf || !cnpj){
                escrever.println("Esta incorreto: " + msgEmail + msgCPF  + msgCNPJ + " por favor preencher novamente.");
                } else {
                    escrever.println("Arquivo pronto");
                    processRequest();
                }
            } while (!(cpf && email && cnpj));

        } catch (IOException ex) {
            Logger.getLogger(Tratamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void processRequest() throws IOException {
        File file = new File("arquivo.txt");
        /*try {

            BufferedReader lerArq = new BufferedReader(new FileReader(file));

            String linha = lerArq.readLine();
            do{
                System.out.printf(linha);
                linha = lerArq.readLine(); // lê da segunda até a última linha
                if(linha == null)
                    escreverArquivo(file);
            }while (linha != null); 
            escreverArquivo(file);
        } catch (Exception e) {
            escreverArquivo(file);
        }*/
        escreverArquivo(file);
    }

    private void escreverArquivo(File file) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        writer.write(usr.getEmail() + ", " + usr.getCpf() + ", " + usr.getCnpj());
        writer.newLine();
        writer.close();
    }
}
