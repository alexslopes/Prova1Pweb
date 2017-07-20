/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.List;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author alex
 */
public class Usuario {
    private String email;
    private String cpf;

    
    public boolean validarEmail(){
        if(this.email.contains("@"))
            return true;
        return false;
    }
    
    
    public boolean isValidCpf() {
        cpf = this.formatarCPF(this.cpf);
        if(validarCPF(cpf)){
            this.cpf = this.devolverCPF(cpf);
            return true;
        }
        return false;
    }

    public String getCpf() {
        return cpf;
    }
    
    private String formatarCPF(String cpf){
        return cpf.replace(".", "").replace("-", "");
    }
    
    private boolean validarCPF(String cpf){
        int[] nums = new int[11];
        
        
        
        for(int i = 0; i < cpf.length(); i++){
            nums[i] = Character.getNumericValue(cpf.charAt(i));;
        }
        int soma = 0;
        int soma2 = 0;
        if(nums.length != 11)
            return false;
      
        for(int i = 0; i < 9 ; i++){
            
            soma = soma + (nums[i]*(i+1));
            soma2 = soma2 + (nums[i]*(9-i));
            
        }
        System.out.println(soma2);
        if(soma%11 == nums[9] && soma2%11 == nums[10])
            return true;

        return false;
    }    
    
    private String devolverCPF(String cpf){
        String n1 = cpf.substring(0, 3);
        String n2 = cpf.substring(3, 6);
        String n3 = cpf.substring(6, 9);
        String n4 = cpf.substring(9,11);
        
        return n1 +"."+n2+"."+n3+"-"+n4;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    
    
}
