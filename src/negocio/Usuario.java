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
    private String cnpj;

    public boolean isValidEmail() {
        if (this.email.contains("@")) {
            return true;
        }
        return false;
    }

    public boolean isValidCpf() {
        if (validarCPF(this.cpf)) {
            this.cpf = this.getCpf();
            return true;
        }
        return false;
    }

    public boolean isValidCnpj() {
        if (validarCNPJ(cnpj)) {
            this.cnpj = this.getCnpj();
            return true;
        }
        return false;
    }

    public String getCnpj() {
        this.cnpj = this.formatarString(this.cnpj);
        String n1 = cnpj.substring(0, 2);
        String n2 = cnpj.substring(2, 5);
        String n3 = cnpj.substring(5, 8);
        String n4 = cnpj.substring(8, 12);
        String n5 = cnpj.substring(12);

        return n1 + "." + n2 + "." + n3 + "." + n4 + "-" + n5;
    }

    public String getCpf() {
        this.cpf = this.formatarString(this.cpf);
        String n1 = cpf.substring(0, 3);
        String n2 = cpf.substring(3, 6);
        String n3 = cpf.substring(6, 9);
        String n4 = cpf.substring(9, 11);

        return n1 + "." + n2 + "." + n3 + "-" + n4;
    }

    private String formatarString(String string) {
        return string.replace(".", "").replace("-", "");
    }

    private boolean validarCPF(String cpf) {
        int[] nums = new int[11];
        
        //permite digitar cpf sem traços e pontos, porém se inserir tem que estar devidamente colocados
        if (cpf.contains(".") || cpf.contains("-")) {
            System.out.println("ok");
            if ((cpf.charAt(3) != '.') || (cpf.charAt(7) != '.') || (cpf.charAt(11) != '-')) {
                return false;
            }
        }

        cpf = this.formatarString(cpf);
        
        if (cpf.equals("00000000000") || cpf.equals("11111111111") ||
        cpf.equals("22222222222") || cpf.equals("33333333333") ||
        cpf.equals("44444444444") || cpf.equals("55555555555") ||
        cpf.equals("66666666666") || cpf.equals("77777777777") ||
        cpf.equals("88888888888") || cpf.equals("99999999999") ||
       (cpf.length() != 11))
       return(false);

        for (int i = 0; i < cpf.length(); i++) {
            nums[i] = Character.getNumericValue(cpf.charAt(i));

        }
        int soma = 0;
        int soma2 = 0;
        if (nums.length != 11) {
            return false;
        }

        for (int i = 0; i < 9; i++) {

            soma = soma + (nums[i] * (i + 1));
            soma2 = soma2 + (nums[i] * (9 - i));

        }

        if (soma % 11 == nums[9] && soma2 % 11 == nums[10]) {
            return true;
        }

        return false;
    }

    private boolean validarCNPJ(String cnpj) {
        int[] numsAntecessor = new int[12];
        int[] verificador = new int[2];
        
        //permite digitar cnpj sem traços e pontos, porém se inserir tem que estar devidamente colocados
        if (cnpj.contains(".") || cnpj.contains("-")) {
            if ((cnpj.charAt(2) != '.') || (cnpj.charAt(6) != '.') || (cnpj.charAt(10) != '.') || (cnpj.charAt(15) != '-')) {
                return false;
            }
        }

        cnpj = this.formatarString(cnpj);
        if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111")
                || cnpj.equals("22222222222222") || cnpj.equals("33333333333333")
                || cnpj.equals("44444444444444") || cnpj.equals("55555555555555")
                || cnpj.equals("66666666666666") || cnpj.equals("77777777777777")
                || cnpj.equals("88888888888888") || cnpj.equals("99999999999999")
                || (cnpj.length() != 14)) {
            return (false);
        }

        String nums1 = cnpj.substring(0, 12);
        String nums2 = cnpj.substring(12);

        //verifica primeiro dígito
        for (int i = 0; i < nums1.length(); i++) {
            numsAntecessor[i] = Character.getNumericValue(cnpj.charAt(i));

        }

        verificador[0] = Character.getNumericValue(cnpj.charAt(0));
        verificador[1] = Character.getNumericValue(cnpj.charAt(1));

        int soma = 0, peso = 2, mod = 0;
        for (int i = numsAntecessor.length - 1; i >= 0; i--) {
            if (peso == 10) {
                peso = 2;
            }
            soma = soma + (numsAntecessor[i] * peso);
            peso++;
        }

        mod = soma % 11;

        if ((verificador[0] != (11 - mod)) && (mod == 1 && verificador[0] != 0)) {
            return false;
        }

        //verifica segundo digito
        soma = 0;
        peso = 2;
        mod = 0;
        for (int i = numsAntecessor.length - 1; i >= 0; i--) {
            if (peso == 10) {
                peso = 2;
            }
            soma = soma + (numsAntecessor[i] * peso);
            peso++;
        }

        soma = soma + verificador[0];

        mod = soma % 11;
        if ((verificador[0] != (11 - mod)) && (mod == 1 && verificador[0] != 0)) {
            return false;
        }

        return true;
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

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

}
